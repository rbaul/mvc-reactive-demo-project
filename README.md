# Spring WEB MVC/Flux Demo mixed microservices
Demo project demonstrate mixed microservices:
> Spring Web MVC with SQL Database (H2) - [Swagger](http://localhost:8080/swagger-ui/)
> Spring Web MVC with NO SQL Database (Mongo) - [Swagger](http://localhost:8083/swagger-ui/)
> Spring Web Flux with SQL Database (R2DBC H2) - [Swagger](http://localhost:8082/swagger-ui/)
> Spring Web Flux with NO SQL Database (Mongo) - [Swagger](http://localhost:8081/swagger-ui/)


### Features implemented
> Disable Security

> Swagger 3.0.0 (http://localhost:8080/swagger-ui/)
> * Support pagable in reactive

> Audit

### Issue with R2DBC
> Not support @OneToMany/@ManyToOne
> Not support auto create schema like in JPA, need SQL