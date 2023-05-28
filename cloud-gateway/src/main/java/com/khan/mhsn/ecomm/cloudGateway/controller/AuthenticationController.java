package com.khan.mhsn.ecomm.cloudGateway.controller;

import java.util.stream.Collectors;

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

import com.khan.mhsn.ecomm.cloudGateway.model.AuthenticationResponse;

import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/authenticate")
@Log4j2
public class AuthenticationController {

	@GetMapping("/login")
	public ResponseEntity<AuthenticationResponse> login(
			@AuthenticationPrincipal OidcUser oidcUser, 
			Model model, 
			@RegisteredOAuth2AuthorizedClient("okta")
			OAuth2AuthorizedClient client)
	{
		
					AuthenticationResponse authenticationResponse 
					= AuthenticationResponse.builder()
					.userId(oidcUser.getEmail())
					.accessToken(client.getAccessToken().getTokenValue())
					.refreshToken(client.getRefreshToken().getTokenValue())
					.expiresAt(client.getAccessToken().getExpiresAt().getEpochSecond())
					.authorityList(oidcUser.getAuthorities()
							.stream().map(GrantedAuthority::getAuthority)
							.collect(Collectors.toList())
							)
					.build();
					log.info("LOG IN API : "+authenticationResponse.toString());
					return new ResponseEntity<>(authenticationResponse, org.springframework.http.HttpStatus.OK); 
		
	}
	
}
