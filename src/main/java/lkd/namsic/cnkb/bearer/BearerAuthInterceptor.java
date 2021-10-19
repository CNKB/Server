package lkd.namsic.cnkb.bearer;

import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@SuppressWarnings("ClassCanBeRecord")
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

        if (!(token != null && token.trim().length() > 0 && jwtTokenProvider.validateToken(token))) {
            response.sendError(401, "Unauthorized");
            return false;
        }

        Map<String, Object> claims = jwtTokenProvider.getSubject(token);
        request.setAttribute("id", claims.get("id"));
        request.setAttribute("roles", claims.get("roles"));

        return true;
    }

}
