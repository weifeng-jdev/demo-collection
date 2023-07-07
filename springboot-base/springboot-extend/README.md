# springboot拓展点相关
## 解决循环依赖问题
1. 添加 `spring.main.allow-circular-references: true`
2. 添加 `@Lazy` 注解
## 启动时执行一下操作
1. 实现 `ApplicationRunner` 接口, 重写 `run` 方法, 添加 `@Component` 注解, 项目启动时会执行
2. 实现 `CommandLineRunner` 接口, 重写 `run` 方法, 添加 `@Component` 注解, 项目启动时会执行
## Bean的声明周期