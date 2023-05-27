package com.khan.mhsn.ecomm.orderService.intercept;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.log4j.Log4j2;

@Configuration
@Log4j2
public class OAuthrequestInterceptor implements RequestInterceptor{

	@Autowired
	OAuth2AuthorizedClientManager oAuth2AuthorizedClientManager;
	
	@Override
	public void apply(RequestTemplate template) {
		
		template.header("Authorization", "Bearer "
					+ oAuth2AuthorizedClientManager.authorize(OAuth2AuthorizeRequest.withClientRegistrationId("internal-client")
																.principal("internal").build())
													.getAccessToken().getTokenValue()
					);
		
		
	
	}

	
	
}
