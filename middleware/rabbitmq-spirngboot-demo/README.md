# RabbitMQ Demo
## Docker部署RabbitMQ
```shell
# RABBITMQ_DEFAULT_USER:默认登陆用户
# RABBITMQ_DEFAULT_PASS:默认登陆密码
# 3.8.3-management:带控制台插件版本
docker run -d --name rabbitmq -p 5672:5672 -p 15672:15672 -e RABBITMQ_DEFAULT_PASS=admin -e RABBITMQ_DEFAULT_USER=admin rabbitmq:3.8.3-management
```
## 配置RabbitMQ队列