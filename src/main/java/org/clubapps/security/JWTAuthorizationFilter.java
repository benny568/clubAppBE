package org.clubapps.security;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.clubapps.model.Worker;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import io.jsonwebtoken.Jwts;

import static org.clubapps.security.SecurityConstants.HEADER_STRING;
import static org.clubapps.security.SecurityConstants.TOKEN_PREFIX;
import static org.clubapps.security.SecurityConstants.SECRET;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {
	public JWTAuthorizationFilter(AuthenticationManager authManager) {
        super(authManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req,
                                    HttpServletResponse res,
                                    FilterChain chain) throws IOException, ServletException {
    		// (1) Pick out the 'Authorization' header
        String header = req.getHeader(HEADER_STRING);

        // (2) If there is no 'Authorization' header or it doesn't have the 'Bearer' value
        //     then just continue the filter chain as normal
        if (header == null || !header.startsWith(TOKEN_PREFIX)) {
            chain.doFilter(req, res);
            return;
        }

        // (3) Decode the jwt in the request
        UsernamePasswordAuthenticationToken authentication = getAuthentication(req);

        // (4) Store the Principle information, the authenticated user
        SecurityContextHolder.getContext().setAuthentication(authentication);
        
        // (5) Continue with the normal filter chain	
        chain.doFilter(req, res);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(HEADER_STRING);
        JwtUtil jwtUtil = new JwtUtil();
        
        if (token != null) {
            // parse the token.
        	Worker user = jwtUtil.parseToken(token);

            if (user != null) {
                return new UsernamePasswordAuthenticationToken(user.getName(), null, user.getRoles());
            }
            return null;
        }
        return null;
    }
}
