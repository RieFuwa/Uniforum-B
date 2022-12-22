package com.uniforum.securityUser;
import io.jsonwebtoken.*;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import java.util.Date;

@Component
@Data
public class JwtTokenProvider {
    @Value("${universitem.app.secret}")
    private String APP_SECRET; //TOKEN OLUSTURMA
    @Value("${universitem.expires.in}")
    private Long EXPIRES_IN; // KAÇ SANIYE TOKEN DEGERİNİ YİTİRİR.

    public String generateJwtToken(Authentication auth){
        JwtUserDetails userDetails = (JwtUserDetails) auth.getPrincipal();//Auth edecegimiz User
        Date expireDate = new Date(new Date().getTime() + EXPIRES_IN);
        return Jwts.builder().setSubject(userDetails.getId()) //USER ID SI
                .setIssuedAt(new Date()).setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, APP_SECRET).compact(); // TOKEN OLUŞTURMA
    }

    public String generateJwtTokenByUserId(String userId) {
        Date expireDate = new Date(new Date().getTime() + EXPIRES_IN);
        return Jwts.builder().setSubject(userId)
                .setIssuedAt(new Date()).setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, APP_SECRET).compact();
    }

    boolean validateToken(String token) { //FRONTEND DEN GELEN TOKEN DOGRU MU KONTROL İŞLEMİ YAPIYORUZ.
        try {
            Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(token); //PARS EDEBİLİYORSAK BİZİM TOKENIMIZDIR.
            return !isTokenExpired(token);
        } catch (SignatureException e) {
            return false;
        } catch (MalformedJwtException e) {
            return false;
        } catch (ExpiredJwtException e) {
            return false;
        } catch (UnsupportedJwtException e) {
            return false;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
    private boolean isTokenExpired(String token) {
        Date expiration = Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(token).getBody().getExpiration();
        return expiration.before(new Date());
    }

    Long getUserIdFromJwt(String token) {
        Claims claims = Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(token).getBody(); //CLAIMS JWT ALTINDA HAZIR GELEN BİR METHOD
        return Long.parseLong(claims.getSubject()); //KEY'DEN USER A GİDİYORUZ. ŞİFREYİ GERİ ÇÖZÜYORUZ.
    }

}
//  JWTS = SPRING TARAFINDAN HAZIR GELİR.
// HS512 ALGORİTMASINA GORE TOKEN OLUSTURUR.
