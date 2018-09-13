package org.clubapps.security;

public class SecurityConstants {
	public static final String SECRET = "SecretKeyToGenJWTs";
    public static final long EXPIRATION_TIME = 864_000_000; // 10 days
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/login";
    public static final String GET_TEAM_DETAILS_URL = "/team/{team}";
    public static final String GET_TEAM_MEMBERS = "/teammembers/{teamName}";
    public static final String GET_PHOTOS = "/photos/**";
    public static final String PAYPAL_IPN = "/ipn";
    public static final String PUBLIC_ACCESS = "/public/**";
    public static final String CONFIRM_ACADEMY_REG_POST = "/confirmregistration";
    public static final String ACADEMY_REG_POST = "/academyregistration";
}
