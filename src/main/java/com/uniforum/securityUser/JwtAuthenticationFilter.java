package com.uniforum.securityUser;
import com.uniforum.service.Impl.UserDetailsServiceImpl;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Data
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    //REQUEST GELDIGINDE FILTER ASAMASINDAN GEÇER+ EXTRA BİR AŞAMA KENDİMİZ YAZIYORUZ. JWT FILTER AŞAMASI EKLİYORUZ.
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String jwtToken = extractJwtFromRequest(request);
            if(StringUtils.hasText(jwtToken) && jwtTokenProvider.validateToken(jwtToken)) { //JWT VALIDATE TOKENA GÖNDERİYORUZ VE VALIDATE MI BAKIYORUZ
                String id = String.valueOf(jwtTokenProvider.getUserIdFromJwt(jwtToken));
                UserDetails user = userDetailsService.loadUserById(id);
                if(user != null) { // USER VAR ISE DOGRULAMA YAPIYORUZ.
                    UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                    auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(auth);//security için verileri tutar. name pass gibi,
                }
            }
        } catch(Exception e) {
            return;
        }
        filterChain.doFilter(request, response);
    }
    private String extractJwtFromRequest(HttpServletRequest request) { //BİR BEARER + TOKEN
        String bearer = request.getHeader("Authorization");
        if(StringUtils.hasText(bearer) && bearer.startsWith("Bearer "))
            return bearer.substring("Bearer".length() + 1);
        return null;
    }

}
