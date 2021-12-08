package lkd.namsic.cnkb.bearer;

import io.jsonwebtoken.Claims;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class BearerAuthInterceptor implements HandlerInterceptor {
    
    private final AuthorizationExtractor authExtractor;
    private final JwtTokenProvider jwtTokenProvider;
    
    public BearerAuthInterceptor(AuthorizationExtractor authExtractor, JwtTokenProvider jwtTokenProvider) {
        this.authExtractor = authExtractor;
        this.jwtTokenProvider = jwtTokenProvider;
    }
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        if(request.getMethod().equals(HttpMethod.OPTIONS.name())) {
            return true;
        }
        
        String token = authExtractor.extract(request, "Bearer");
        
        if(!(token != null && token.trim().length() > 0 && jwtTokenProvider.validateToken(token) != 0)) {
            response.sendError(401, "Unauthorized");
            return false;
        }
        
        Claims claims = jwtTokenProvider.getSubject(token);
        request.setAttribute("id", claims.get("id"));
        request.setAttribute("roles", claims.get("roles"));
        
        String accessToken;
        if((accessToken = claims.get("accessToken", String.class)) != null) {
            response.setHeader("accessToken", accessToken);
            response.setHeader("refreshToken", claims.get("refreshToken", String.class));
        }
        
        return true;
    }
    
}