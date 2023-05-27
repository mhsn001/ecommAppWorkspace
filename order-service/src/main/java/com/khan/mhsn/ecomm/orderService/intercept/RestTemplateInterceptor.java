package com.khan.mhsn.ecomm.orderService.intercept;

import java.io.IOException;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class RestTemplateInterceptor implements ClientHttpRequestInterceptor {

	OAuth2AuthorizedClientManager oAuth2AuthorizedClientManager;
	
	public RestTemplateInterceptor(OAuth2AuthorizedClientManager oAuth2AuthorizedClientManager) {
		
		this.oAuth2AuthorizedClientManager = oAuth2AuthorizedClientManager;
	}
			
			
	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
			throws IOException {
		
		request.getHeaders().add("Authorization", "Bearer "+oAuth2AuthorizedClientManager
															.authorize(OAuth2AuthorizeRequest.withClientRegistrationId("internal-client")
															.principal("internal")
															.build()).getAccessToken().getTokenValue());
		
		
		
		return execution.execute(request, body);
	}
	
	

}
