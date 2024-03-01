package com.siddhartha.garments.auth;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.siddhartha.garments.entity.SessionDetails;
import com.siddhartha.garments.repository.SessionDetailsRepository;




public class JwtAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private SessionDetailsRepository sessionRep;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		String header = req.getHeader(Constants.HEADER_STRING);
		String username = null;
		String authToken = null;
		SessionDetails table = new SessionDetails();
		HeaderMapRequestWrapper requestWrapper = new HeaderMapRequestWrapper(req);
		if (header != null && header.startsWith(Constants.TOKEN_PREFIX)) {
			authToken = header.replace(Constants.TOKEN_PREFIX, "");
			table = sessionRep.findByTempTokenid(authToken);
			authToken = table.getSessionPk().getTokenId();
	        requestWrapper.addHeader(Constants.HEADER_STRING, authToken);
			try {
				username = jwtTokenUtil.getUsernameFromToken(authToken);
			} catch (IllegalArgumentException e) {
				logger.error("an error occured during getting username from token", e);
			} catch (Exception e) {
				logger.warn("the token is expired and not valid anymore");
				username = table.getSessionPk().getLoginId();
			} 
		} else {
			//req.getSession().invalidate();
			logger.warn("couldn't find bearer string, will ignore the header");
		}
		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails userDetails = userDetailsService.loadUserByUsername(username);
			boolean validtoken = jwtTokenUtil.validateToken(username, userDetails,req);
			if (validtoken) {
				String refreshtoken = jwtTokenUtil.doGenerateToken(username);
				logger.info("new refresh token generated ======> "+refreshtoken);
				int tokenCount =sessionRep.deleteByTempTokenid(table.getTempTokenid());
				logger.info("delete session table and insert new token ======> "+tokenCount);
				table.getSessionPk().setTokenId(refreshtoken); 
				table.getSessionPk().setLoginId(username);
				sessionRep.saveAndFlush(table);
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
						userDetails, null, Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN")));
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(requestWrapper));
				logger.info("authenticated user " + username + ", setting security context");
				SecurityContextHolder.getContext().setAuthentication(authentication);
			} else if (validtoken) {
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
						userDetails, null, Arrays.asList(new SimpleGrantedAuthority("ROLE_USER")));
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(requestWrapper));
				logger.info("authenticated user " + username + ", setting security context");
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}/*else if (!validtoken) {
				String refreshtoken = jwtTokenUtil.doGenerateToken(username);
				logger.info("new refresh token generated ======> "+refreshtoken);
				int deletecount = sessionRep.deleteByTemptokenid(table.getTempTokenid());
				logger.info("delete session table and insert new token ======> "+deletecount);
				table.setTokenId(refreshtoken);  
				sessionRep.save(table);
				req.getSession().invalidate();
				validtoken = jwtTokenUtil.validateToken(username, userDetails,req);
				if (validtoken) {
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
						userDetails, null, Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN")));
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(requestWrapper));
				logger.info("authenticated user " + username + ", setting security context");
				SecurityContextHolder.getContext().setAuthentication(authentication);
				}
			}*/
		}
		chain.doFilter(requestWrapper, res);
	}
	
	
	
}