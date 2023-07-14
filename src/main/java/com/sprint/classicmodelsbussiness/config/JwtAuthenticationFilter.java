package com.sprint.classicmodelsbussiness.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;


@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	private Logger logger = LoggerFactory.getLogger(OncePerRequestFilter.class);
	@Autowired
	private JwtService jwtHelper;
	
	@Autowired
	private CustomUserDetailService userDetailService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		System.out.println("1: "+request);
		System.out.println("2:"+response);
		
		String requestHeader = request.getHeader("Authorization");
		
		System.out.println("3:"+requestHeader);
		
		logger.info(" Header : {}", requestHeader);
		String username = null;
		String token = null;

		if (requestHeader != null && requestHeader.startsWith("Bearer ")) {
			
			token = requestHeader.substring(7);

			try {
				username = this.jwtHelper.extractUsername(token);

			} catch (IllegalArgumentException e) {

				logger.info("Illegal Argument while fetching the username !! ");
				e.printStackTrace();

			} catch (ExpiredJwtException e) {

			logger.info("Given jwt token is expired !! ");
//				e.printStackTrace();

			} catch (MalformedJwtException e) {

				logger.info("Some changed has done in tokrn  !! invalid token ");
				e.printStackTrace();

			} catch (Exception e) {

				e.printStackTrace();

			}
		} else {
			logger.info("Invalid Header value !");
		}

		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

			UserDetails userDetails = this.userDetailService.loadUserByUsername(username);
			Boolean validateToken = this.jwtHelper.validateToken(token, userDetails);

			if (validateToken) {

				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
						userDetails,null, userDetails.getAuthorities());
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

				SecurityContextHolder.getContext().setAuthentication(authentication);

			} else {

				logger.info("Validation fails !!");
			}
		}

		filterChain.doFilter(request, response);
	}

}
