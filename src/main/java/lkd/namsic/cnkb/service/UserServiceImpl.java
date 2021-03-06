package lkd.namsic.cnkb.service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import lkd.namsic.cnkb.bearer.JwtTokenProvider;
import lkd.namsic.cnkb.config.Config;
import lkd.namsic.cnkb.domain.*;
import lkd.namsic.cnkb.domain.game.player.Player;
import lkd.namsic.cnkb.dto.Response;
import lkd.namsic.cnkb.dto.user.SignInInput;
import lkd.namsic.cnkb.exception.StatusException;
import lkd.namsic.cnkb.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    
    private final Config config;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserRoleRepository userRoleRepository;
    private final SignInRepository signInRepository;
    private final PlayerRepository playerRepository;
    private final JwtTokenProvider jwtTokenProvider;
    
    @Autowired
    public UserServiceImpl(Config config,
                           UserRepository userRepository,
                           RoleRepository roleRepository,
                           UserRoleRepository userRoleRepository,
                           SignInRepository signInRepository,
                           PlayerRepository playerRepository,
                           JwtTokenProvider jwtTokenProvider
    ) throws IOException {
        FirebaseOptions options = Objects.requireNonNull(
                FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(
                        new FileInputStream(System.getenv("cnkb_firebase"))
                    ))
            )
            .build();
        FirebaseApp.initializeApp(options);
        
        this.config = config;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userRoleRepository = userRoleRepository;
        this.signInRepository = signInRepository;
        this.playerRepository = playerRepository;
        this.jwtTokenProvider = jwtTokenProvider;
    }
    
    @Override
    public Response signIn(HttpServletRequest request, SignInInput input) {
        return config.safeCall("signIn", () -> {
            String email = input.getEmail();
            String provider = input.getProvider();
            String uid = input.getUid();
            
            if(email == null) {
                throw new StatusException(412, "Requires email");
            } else if(provider == null) {
                throw new StatusException(412, "Requires provider");
            } else if(uid == null || uid.isEmpty()) {
                throw new StatusException(412, "Requires uid");
            }
            
            try {
                FirebaseAuth.getInstance().getUser(uid);
            } catch(FirebaseAuthException e) {
                throw new StatusException(400, "Unknown uid");
            }
            
            String ipString = config.getIp(request);
            long ip = config.ipToLong(ipString);
            
            User user = userRepository.findByEmail(email).orElseGet(() -> {
                Role role = roleRepository.findByName("user").orElseThrow(
                    () -> new RuntimeException("Database has not been initialized")
                );
                
                User savedUser = userRepository.save(
                    User.builder()
                        .email(email)
                        .lastIp(ip)
                        .build()
                );
                
                userRoleRepository.save(
                    UserRole.builder()
                        .pk(UserRolePk.builder()
                            .user(savedUser)
                            .role(role)
                            .build())
                        .build()
                );
                
                return savedUser;
            });
            
            signInRepository.save(SignIn.builder()
                .user(user)
                .ip(ip)
                .provider(provider)
                .build());
            
            return Response.builder()
                .data(jwtTokenProvider.getTokenData(user))
                .build();
        });
    }
    
    @Override
    public Response getPlayers(HttpServletRequest request, User user) {
        return config.safeCall("getPlayers", () -> {
            Long userId;
            
            if(user == null || (userId = user.getId()) == null) {
                userId = Long.parseLong((String) request.getAttribute("id"));
            }
            
            List<Player> playerList = playerRepository.findAllByUserId(userId);
            List<Map<String, Object>> data = new ArrayList<>();
            
            Map<String, Object> innerData;
            for(int i = 0; i < 5; i++) {
                innerData = new HashMap<>();
                
                try {
                    Player player = playerList.get(i);
                    
                    innerData.put("id", player.getId());
                    innerData.put("lv", player.getLv());
                    innerData.put("name", player.getName());
                    innerData.put("title", player.getTitle());
                    innerData.put("lastPlayed", config.dateFormatter.format(player.getUpdated()));
                } catch(IndexOutOfBoundsException ignore) {
                }
                
                data.add(innerData);
            }
            
            return Response.builder().data(data).build();
        });
    }
    
    
    @Override
    public Response getToken(String tokenInput) {
        return config.safeCall("getPlayers", () -> {
            long userId = jwtTokenProvider.validateToken(tokenInput);
            if(userId == 0) {
                throw new StatusException(401, "Unauthorized");
            }
            
            User user = userRepository.findById(userId).orElseThrow(
                () -> new StatusException(409, "Unknown user")
            );
            
            return Response.builder()
                .data(jwtTokenProvider.getTokenData(user))
                .build();
        });
    }
    
}