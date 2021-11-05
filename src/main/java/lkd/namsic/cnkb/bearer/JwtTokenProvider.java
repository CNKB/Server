package lkd.namsic.cnkb.bearer;

import io.jsonwebtoken.*;
import lkd.namsic.cnkb.domain.User;
import lkd.namsic.cnkb.repository.UserRepository;
import lkd.namsic.cnkb.repository.UserRoleRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class JwtTokenProvider {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Value("${security.jwt.token.secret-key}")
    String secretKey;

    private final long accessTokenMillis;
    private final long refreshTokenMillis;

    public JwtTokenProvider(@Value(value = "${security.jwt.token.access-expire-millis}") String accessTokenMillis,
                            @Value(value = "${security.jwt.token.refresh-expire-millis}") String refreshTokenMillis) {
        this.accessTokenMillis = Long.parseLong(accessTokenMillis);
        this.refreshTokenMillis = Long.parseLong(refreshTokenMillis);
    }

    public String createToken(Map<String, Object> claims, long millis) {
        Date now = new Date();
        Date validity = new Date(now.getTime() + millis);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public Claims getSubject(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();
    }

    public long validateToken(String token) {
        long userId;

        try {
            Claims subject = getSubject(token);

            Date expiration = subject.getExpiration();
            Date date = new Date();

            if (expiration.before(date)) {
                return 0;
            }

            userId = Long.parseLong((String) subject.get("id"));

            date.setTime(date.getTime() + (accessTokenMillis / 2));
            if(expiration.before(date)) {
                User user = userRepository.findById(userId).orElseThrow(IllegalArgumentException::new);
                Map<String, String> tokenData = getTokenData(user);

                subject.put("accessToken", tokenData.get("accessToken"));
                subject.put("refreshToken", tokenData.get("refreshToken"));
            }
        } catch (JwtException | IllegalArgumentException e) {
            return 0;
        }

        return userId;
    }

    @NonNull
    public List<String> getRoleList(@NonNull User user) {
        return userRoleRepository.findAllByPk_User(user)
                .stream()
                .map(userRole -> userRole.getPk().getRole().getName())
                .collect(Collectors.toList());
    }

    @NonNull
    public Map<String, String> getTokenData(@NonNull User user) {
        List<String> roleList = getRoleList(user);

        Map<String, Object> claims = new LinkedHashMap<>();
        claims.put("id", user.getId().toString());
        claims.put("roles", roleList);
        String accessToken = createToken(claims, accessTokenMillis);

        claims = new LinkedHashMap<>();
        claims.put("id", user.getId().toString());
        String refreshToken = createToken(claims, refreshTokenMillis);

        Map<String, String> data = new HashMap<>();
        data.put("accessToken", accessToken);
        data.put("refreshToken", refreshToken);

        return data;
    }

}