package isa.project.pharmacyapp.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import isa.project.pharmacyapp.model.Authority;
import isa.project.pharmacyapp.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Component
public class TokenUtils {

    @Value("pharmacyapp")
    private String APP_NAME;

    @Value("bosancero")
    private String SECRET;

    @Value("500000")
    private int EXPIRES_INT;

    @Value("Authorization")
    private String AUTH_HEADER;

    private static final String AUDIENCE_UNKNOWN = "unknown";
    private static final String AUDIENCE_WEB = "web";
    private static final String AUDIENCE_MOBILE = "mobile";
    private static final String AUDIENCE_TABLET = "tablet";

    @Autowired
    public TimeProvider timeProvider;

    private SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS512;

//    public String generateToken(String email){
//
//        return Jwts.builder()
//                .setIssuer(APP_NAME)
//                .setSubject(email)
//                .setAudience(generateAudience())
//                .setIssuedAt(timeProvider.now())
//                .setExpiration(generateExpirationDate())
//                .signWith(SIGNATURE_ALGORITHM,SECRET).compact();
//    }


    public String generateToken(User user, Authority role) {

        return Jwts.builder()
                .setIssuer(APP_NAME)
                .setSubject(user.getUsername())
                .setAudience(generateAudience())
                .setIssuedAt(timeProvider.now())
                .setExpiration(generateExpirationDate())
                .claim("role", role)
//                .claim("role", role.getAuthority())
                .claim("id", user.getId())
                .signWith(SIGNATURE_ALGORITHM, SECRET).compact();


    }

    private Date generateExpirationDate() {
        return  new Date(timeProvider.now().getTime() + EXPIRES_INT);
    }
    private String generateAudience() {

//		String audience = AUDIENCE_UNKNOWN;
//		if (device.isNormal()) {
//			audience = AUDIENCE_WEB;
//		} else if (device.isTablet()) {
//			audience = AUDIENCE_TABLET;
//		} else if (device.isMobile()) {
//			audience = AUDIENCE_MOBILE;
//		}

        return AUDIENCE_WEB;

    }

    //function that refreshes JWT token
    public String refreshToken(String token){
        String refreshedToken;
        try{
            final Claims claims = this.getAllClaimsFromToken(token);
            claims.setIssuedAt(timeProvider.now());
            refreshedToken = Jwts.builder()
                    .setClaims(claims)
                    .setExpiration(generateExpirationDate())
                    .signWith(SIGNATURE_ALGORITHM, SECRET).compact();
        }
        catch (Exception e){
            refreshedToken = null;
        }
        return  refreshedToken;
    }

    public Boolean canTokenBeRefreshed(String token, Date lastPasswordReset){
        final Date created = this.getIssuedAtDateFromToken(token);
        return (!(this.isCreatedBeforeLastPasswordReset(created,lastPasswordReset))
                && (!(this.isTokenExpired(token) || this.ignoreTokenExpired(token)   ))
        );
    }



    private Date getIssuedAtDateFromToken(String token) {
        Date issueAt;
        try{
            final Claims claims = this.getAllClaimsFromToken(token);
            issueAt = claims.getIssuedAt();
        }
        catch (Exception e){
            issueAt = null;
        }
        return issueAt;
    }
    
    private Boolean isCreatedBeforeLastPasswordReset(Date created, Date lastPasswordReset){
        return (lastPasswordReset != null && created.before(lastPasswordReset));
    }

    private boolean isTokenExpired(String token) {
        final Date expiration = this.getExpirationDateFromToken(token);
        return expiration.before(timeProvider.now());
    }

    private boolean ignoreTokenExpired(String token){
        String audience = this.getAudienceFromToken(token);
        return  (audience.equals(AUDIENCE_TABLET) || audience.equals(AUDIENCE_MOBILE));

    }

    private String getAudienceFromToken(String token) {
        String audience;
        try{
            final Claims claims = this.getAllClaimsFromToken(token);
            audience = claims.getAudience();
        }
        catch (Exception e){
            audience = null;
        }

        return audience;
    }

    private Date getExpirationDateFromToken(String token) {
        Date expiration;
        try{
            final Claims claims = this.getAllClaimsFromToken(token);
            expiration = claims.getExpiration();
        }
        catch (Exception e){
            expiration = null;
        }

        return expiration;
    }

    private Claims getAllClaimsFromToken(String token) {
        Claims claims;
        try{
            claims =Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token)
                    .getBody();
        }
        catch (Exception e){
            claims = null;
        }
        return  claims;
    }


    public String getToken(HttpServletRequest request) {
        String authHeader = getAuthHeaderFromHeader(request);

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);
        }

        return null;
    }

    public String getAuthHeaderFromHeader(HttpServletRequest request) {
        return request.getHeader(AUTH_HEADER);
    }


    public String getEmailFromToken(String authToken) {
        String email;
        try{
            final Claims claims = this.getAllClaimsFromToken(authToken);
            email = claims.getSubject();
        }
        catch (Exception e){
            email = null;
        }

        return email;
    }

    //function for validating token
    public boolean validToken(String authToken, UserDetails userDetails) {
        User user = (User) userDetails;
        System.out.println("Validate token UserDetails username: " + userDetails.getUsername());
        final String email = this.getEmailFromToken(authToken);
        final Date created = this.getIssuedAtDateFromToken(authToken);
        return (email != null && email.equals(user.getEmail())
                && !this.isCreatedBeforeLastPasswordReset(created, user.getLastPasswordResetDate())

        );
    }

    public int getExpiredIn() {
        return EXPIRES_INT;
    }

}
