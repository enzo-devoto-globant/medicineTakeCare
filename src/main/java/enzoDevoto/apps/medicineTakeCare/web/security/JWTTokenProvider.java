package enzoDevoto.apps.medicineTakeCare.web.security;


import enzoDevoto.apps.medicineTakeCare.web.exception.MedicineTakeCareAPIException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Component
public class JWTTokenProvider {

    @Value("app.jwt-secret")
    private String jwtSecret;
    @Value("app.jwt-expiration-milliseconds")
    private int jwtExpirationDateInMs;

    public String generateToken(Authentication authentication) {
        String username = authentication.getName();
        Date currentDate = new Date();
        Date expireDate = new Date(currentDate.getTime() + jwtExpirationDateInMs);
        //Generate token
        String token = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();

        log.info("token generated.");
        return token;
    }
        public String getUsernameforJWT(String token){
            Claims claims = Jwts.parser()
                    .setSigningKey(jwtSecret)
                    .parseClaimsJws(token)
                    .getBody();
            return claims.getSubject();
        }

    public boolean valdiateToken(String token){
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        }catch (SignatureException exception){
            throw new MedicineTakeCareAPIException(HttpStatus.BAD_REQUEST, "Invalid JWT signature");
        }catch (MalformedJwtException exception){
            throw new MedicineTakeCareAPIException(HttpStatus.BAD_REQUEST, "Invalid JWT token.");
        }catch (ExpiredJwtException exception) {
            throw new MedicineTakeCareAPIException(HttpStatus.BAD_REQUEST, "Expired JWT token");
        }catch (UnsupportedJwtException exception){
            throw new MedicineTakeCareAPIException(HttpStatus.BAD_REQUEST, "Unsopported JWT token");
        }catch (IllegalArgumentException exception){
            throw new MedicineTakeCareAPIException(HttpStatus.BAD_REQUEST, "JWT claims string is empty");
        }
    }

}
