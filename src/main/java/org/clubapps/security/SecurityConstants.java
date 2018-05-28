package org.clubapps.security;

public class SecurityConstants {
	public static final String SECRET = "SecretKeyToGenJWTs";
    public static final long EXPIRATION_TIME = 864_000_000; // 10 days
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/login";
    public static final String GET_NEWS_URL = "/stories";
    public static final String GET_TEAMS_URL = "/teams";
    public static final String GET_TEAM_DETAILS_URL = "/team/{team}";
    public static final String GET_TEAM_MEMBERS = "/teammembers/{teamName}";
    public static final String GET_PHOTOS = "/photos/**";
}
