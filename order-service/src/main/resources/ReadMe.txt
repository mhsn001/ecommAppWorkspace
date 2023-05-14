Order service is the main micro service. which call othe microservices while placing the order. 


FeignClient
-------------
Its a Webservice client which allow you call another microsevice using rest api's 

First of all we add the dependency of FeignClient using OpenFeign.
 - OpenFeign is declarative REST Client. OpenFeign creates a dynamic implementation of an interface decorated with JAX-RS or Spring MVC annotations.

Go to your main class and add the annotation @EnableFeignClient

Now create Productservice class which will be an interface for product service microservice and annotate it with @FeignClient. See below code 

	@FeignClient(name ="PRODUCT-SERVICE/products") ==> we can alsogive the url for product microservice
		public class ProductService {
		
		}

Now whichever rest api from product microservice you want to call you can copy the declaration of that rest api. Please refer class com.khan.mhsn.ecomm.orderService.external.ProductService


Error Decoding and Controller advice (video 54 and 55)
=====================================
While calling another service and if we are getting any exception from that service then we need to decode that error 
If we are not handling this scenario our response on the UI will show internal service error and we will not know what is the issue. 

So to get correct error at the end user we need to propagate exception through out the microservices and then decode them using ErrorDecoder interface.

	- See class CustomErrorDecoder which implements interface ErrorDecoder

Add the bean in confi class for ErrorDecoder so that spring can understand whenever bean ErrorDecoder is initialized object of CustomErrorDecoder should be created. 

	- See class FeignConfig for bean declaration
	
Now we need to return error in response from the controller to end user for that we use @ControllerAdvice

	- check class RestResponseEntityExceptionHandler 



08-May-2023 
--------------
 Till now we have completed: 
 	- eureka client configuration
 	- config client configuration for common configuration for all microservices.
 	- Placing the order
 		- As of now only saving the order details.
 		- Other service calls to be implemented for more details check comments in service impl class.
 
 Need to complete
 	- need to complete the create order business logic. 
 	- exception handling 
 	- logging 
 	
 we have completed point 48 from dailycodebuffer course. 