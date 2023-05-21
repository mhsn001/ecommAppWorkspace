API-GATEWAY will be the kind of proxy for all services. See below the configuration for only routing the requests for all microservices 

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
       
       
CircuitBreaker in API gateway 
=================================
We need to add filter for circuit breaker in yaml file and then add FallbackController for writing the fallback method 

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
      filters: # Added filter for resilience4J circuit breaker
       - name: CircuitBreaker
         args:
          name: ORDER-SERVICE
          fallbackuri: forward:/orderServiceFallback  # Fallback method call uri
          
          
          