package com.amano;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import lombok.Data;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @className: RpcServer
 * @package com.amano
 * @description: TODO 类描述
 * @author: weifeng
 * @date: 2023/6/12
 **/
public class RpcServer {
    private Map<String, Object> serviceMap = new ConcurrentHashMap<>();

    public void register(Object service, int port) {
        String serviceName = service.getClass().getInterfaces()[0].getName();
        serviceMap.put(serviceName, service);
    }

    public void start() throws Exception {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel channel) throws Exception {
                            channel.pipeline()
                                    .addLast(new ObjectDecoder(Integer.MAX_VALUE, ClassResolvers.cacheDisabled(null)))
                                    .addLast(new ObjectEncoder())
                                    .addLast(new RpcServerHandler(serviceMap));
                        }
                    })
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);

            ChannelFuture future = bootstrap.bind(8080).sync();
            System.out.println("Server started on port " + 8080);
            future.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }
}

class RpcServerHandler extends ChannelInboundHandlerAdapter {
    private final Map<String, Object> serviceMap;

    public RpcServerHandler(Map<String, Object> serviceMap) {
        this.serviceMap = serviceMap;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        RpcRequest request = (RpcRequest) msg;
        Object result = invoke(request);
        ctx.writeAndFlush(result);
        ctx.close();
    }

    private Object invoke(RpcRequest request) throws Exception {
        String className = request.getClassName();
        Object service = serviceMap.get(className);

        Method method = service.getClass().getMethod(request.getMethodName(), request.getParameterTypes());
        return method.invoke(service, request.getParameters());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}

@Data
class RpcRequest implements Serializable {
    private String className;
    private String methodName;
    private Class<?>[] parameterTypes;
    private Object[] parameters;
}