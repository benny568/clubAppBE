package org.clubapps.security;

import static org.clubapps.security.SecurityConstants.HEADER_STRING;
import static org.clubapps.security.SecurityConstants.TOKEN_PREFIX;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.clubapps.model.Worker;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	private AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req,
                                                HttpServletResponse res) throws AuthenticationException {
    		String username = obtainUsername(req);
    		if (username == null) {
    	        username = "";
    	    }
    	    String password = obtainPassword(req);
    	    if (password == null) {
    	        password = "";
    	    }

        return authenticationManager.authenticate(
		        new UsernamePasswordAuthenticationToken(
		                username,
		                password,
		                new ArrayList<>())
		);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req,
                                            HttpServletResponse res,
                                            FilterChain chain,
                                            Authentication auth) throws IOException, ServletException {

    	JwtUtil jwtUtil = new JwtUtil();
    	Worker user = new Worker();
    	List<SimpleGrantedAuthority> authorities = (List<SimpleGrantedAuthority>) auth.getAuthorities();
    	user.setName(auth.getName());
    	user.setRole(authorities.get(0).getAuthority());
    	user.setRoles(authorities);
    	
    	String token = jwtUtil.generateToken(user);
        res.addHeader(HEADER_STRING, TOKEN_PREFIX + token);
        res.getWriter().write(token.toString());

    }
    
}