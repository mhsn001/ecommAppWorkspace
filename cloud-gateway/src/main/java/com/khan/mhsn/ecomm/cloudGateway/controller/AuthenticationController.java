package com.khan.mhsn.ecomm.cloudGateway.controller;

import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.khan.mhsn.ecomm.cloudGateway.model.Authenticationresponse;
import com.nimbusds.openid.connect.sdk.AuthenticationRequest;
import com.nimbusds.openid.connect.sdk.AuthenticationResponse;

@RestController
@RequestMapping("/authenticate")
public class AuthenticationController {

	@GetMapping("/login")
	public ResponseEntity<Authenticationresponse> login(
			@AuthenticationPrincipal OidcUser oidcUser, 
			Model model, 
			@RegisteredOAuth2AuthorizedClient("okta")
			OAuth2AuthorizedClient client)
	{
		
					Authenticationresponse authenticationresponse 
					= Authenticationresponse.builder()
					.userId(oidcUser.getEmail())
					.accessToken(client.getAccessToken().getTokenValue())
					.refreshToken(client.getRefreshToken().getTokenValue())
					.expiresAt(client.getAccessToken().getExpiresAt().getEpochSecond())
					.authorityList(oidcUser.getAuthorities()
							.stream().map(GrantedAuthority::getAuthority)
							.collect(Collectors.toList())
							)
					.build();
					
					return new ResponseEntity<>(authenticationresponse, org.springframework.http.HttpStatus.OK); 
		
	}
	
}
