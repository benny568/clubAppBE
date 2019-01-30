package org.clubapps.security;

import static org.clubapps.security.SecurityConstants.EXPIRATION_TIME;
import static org.clubapps.security.SecurityConstants.SECRET;
import static org.clubapps.security.SecurityConstants.TOKEN_PREFIX;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.clubapps.dao.MySqlDAO;
import org.clubapps.model.Worker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtUtil {

    /**
     * Tries to parse specified String as a JWT token. If successful, returns User object with username, id and role prefilled (extracted from token).
     * If unsuccessful (token is invalid or not containing all required user properties), simply returns null.
     * 
     * @param token the JWT token to parse
     * @return the User object extracted from specified token or null if a token is invalid.
     */
    public Worker parseToken(String token) {
        try {
            Claims body = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                    .getBody();

            Worker u = new Worker();
            u.setName(body.getSubject());
            u.setUserId((int) Long.parseLong((String) body.get("userId")));
            u.setRole((String) body.get("role"));
            u.setRole(u.getRole());
            u.setRoles(extractRolesFromString( (String) body.get("roles") ));

            return u;

        } catch (JwtException | ClassCastException e) {
            return null;
        }
    }

    /**
     * Generates a JWT token containing username as subject, and userId and role as additional claims. These properties are taken from the specified
     * User object. Tokens validity is infinite.
     * 
     * @param u the user for which the token will be generated
     * @return the JWT token
     */
    public String generateToken(Worker u) {
        Claims claims = Jwts.claims().setSubject(u.getName());
        claims.put("userId", u.getUserId() + "");
        claims.put("role", u.getRole());
        claims.put("roles", createRoleString(u.getRoles()) );

        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
    }
    
    private String createRoleString( ArrayList<String> alRoles )
    {
    	String roles = new String();
    	
    	for( int i=0; i<alRoles.size(); i++ )
    	{
    		if( i != 0 )
    			roles += ",";
    		roles += alRoles.get(i);
    	}   	
    	
    	return roles;
    }
    
    private ArrayList<String> extractRolesFromString( String roles )
    {
    	ArrayList<String> aRoles = new ArrayList<String>();
    	
    	String [] elements = roles.split(",");
    	for (String s: elements) 
    	{
    		aRoles.add(s);
    	}
    	
    	return aRoles;
    }
}
