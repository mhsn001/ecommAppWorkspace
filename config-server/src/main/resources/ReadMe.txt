API-GATEWAY will be the kind of proxy for all serviced. See below the configuration for only routing the requests for all microservices 

spring:
 application: 
  name: API-GATEWAY
 config:
  import: configserver:http://localhost:9296  
 cloud:
  gateway:
   routes:
    - id: ORDER-SERVICE
      uri: lb://ORDER-SERVICE
      predicates: 
       - Path=/orders/**
    - id: PAYMENT-SERVICE
      uri: lb://PAYMENT-SERVICE
      predicates: 
       - Path=/payment/** 
    - id: PRODUCT-SERVICE
      uri: lb://PRODUCT-SERVICE
      predicates: 
       - Path=/products/**
