package com.medi.preclinic.config.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.filter.OncePerRequestFilter;

import com.medi.preclinic.config.JwtTokenService;
import com.medi.preclinic.util.ServiceUtil;


public class MedilabUserAuthzFilter extends OncePerRequestFilter {
	
	@Autowired
	private JwtTokenService jwtTokenService;
	
	public MedilabUserAuthzFilter() {
		super();
	}
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		super.destroy();
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String tokenWithBearer = request.getHeader(ServiceUtil.JWT_TOKEN_HEADER_NAME);
		if(tokenWithBearer != null) {
			if(tokenWithBearer != null && tokenWithBearer.contains(ServiceUtil.JWT_TOKEN_TYPE)) {
				boolean isTokenValid = jwtTokenService.validateToken(tokenWithBearer);
				if(isTokenValid) {
					filterChain.doFilter(request, response);
				}else {
					JSONObject jsonBuilder = new JSONObject();
					jsonBuilder.put("error_message:", "token is invalid, please get the new token");
					throw new BadCredentialsException(jsonBuilder.toString());
				}
			}else {
				filterChain.doFilter(request, response);
			}
		}
		
	}

}
