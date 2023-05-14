This microservice handles all common configurations in yaml in a global configuration.

- We save these common configuration in git and add below configuration in config-server's application.yaml

spring:
 application:
  name: CONFIG-SERVER
 cloud: 
  config: 
   server:
    git:
     uri: https://github.com/mhsn001/spring-app-config
     
- In Springboot main class we add @EnableConfigServer 
     
- Similarly we need to configure all other microservices as config client to get the common configuration. See below configuration to be added in other microservices

 config:
  import: configserver:http://localhost:9296 