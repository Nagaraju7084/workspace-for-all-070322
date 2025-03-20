package com.medi.preclinic;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.http.server.reactive.ServerHttpResponse;

import com.medi.preclinic.config.AuthnServiceCommunicator;

import ch.qos.logback.core.status.OnErrorConsoleStatusListener;
import reactor.core.publisher.Mono;

public class MedilabAutnGateway implements GlobalFilter {
	
	@Autowired
	private AuthnServiceCommunicator authnService;
	
	@Autowired
	private RedisTemplate redisTemplate;

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		System.out.println("*****************************");
		
		/**
		 * intercept every url and verifies the token validity and
		 * pull the user profile from the token and keeps it in a cache
		 */
		HttpHeaders headers = exchange.getRequest().getHeaders();
		if(headers != null && headers.get("Authorization") != null) {
			String headerValueWithBearer = headers.get("Authorization").get(0);
			if(headerValueWithBearer != null & headerValueWithBearer.startsWith("Bearer")) {
				String accessToken = headerValueWithBearer.replace("Bearer ", "");
				System.out.println("access token is:\t"+accessToken);
				if(accessToken != null) {
					String userResp = authnService.getUserInfo(accessToken);
					JSONObject jsonBuilder = new JSONObject(userResp);
					String loggedInUser = jsonBuilder.getString("sub");
					
					HashOperations hashMap = redisTemplate.opsForHash();
					String tokenFromCache = (String) hashMap.get("access_token"+loggedInUser, "Bearer");
					if(tokenFromCache != null && !tokenFromCache.isEmpty()) {
						if(tokenFromCache.equalsIgnoreCase(accessToken)) {
							System.out.println("token verification success at gate way end");
							String authorities = jsonBuilder.getString("groups");
							if(authorities != null) {
								hashMap.put(accessToken, "userRoles", authorities);
							}
						}
					}
				}else {
					return onError(exchange, "forbidden", HttpStatus.FORBIDDEN);
				}
			}else {
				return onError(exchange, "forbidden", HttpStatus.FORBIDDEN);
			}
		}else {
			return onError(exchange, "forbidden", HttpStatus.FORBIDDEN);
		}
		return chain.filter(exchange);
	}
	
	private Mono<Void> onError(ServerWebExchange exchange, String err, HttpStatus httpStatus)  {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(httpStatus);
        return response.setComplete();
    }

}
