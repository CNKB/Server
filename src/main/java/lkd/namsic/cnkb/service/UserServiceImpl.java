package lkd.namsic.cnkb.service;

import lkd.namsic.cnkb.Config;
import lkd.namsic.cnkb.bearer.JwtTokenProvider;
import lkd.namsic.cnkb.domain.*;
import lkd.namsic.cnkb.domain.game.player.Player;
import lkd.namsic.cnkb.dto.UserInput;
import lkd.namsic.cnkb.dto.response.Response;
import lkd.namsic.cnkb.exception.CommonException;
import lkd.namsic.cnkb.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserRoleRepository userRoleRepository;

    @Autowired
    SignInRepository signInRepository;

    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Override
    public Response signIn(HttpServletRequest request, UserInput.SignInInput input) {
        return Config.getInstance().safeCall("signIn", () -> {
            String email = input.getEmail();
            String provider = input.getProvider();

            if(email == null) {
                throw new CommonException(412, "Requires email");
            } else if(provider == null) {
                throw new CommonException(412, "Requires provider");
            }

            String ipString = Config.getInstance().getIp(request);
            long ip = Config.getInstance().ipToLong(ipString);

            User user = userRepository.findByEmail(email).orElseGet(() -> {
                User savedUser = User.builder()
                        .email(email)
                        .lastIp(ip)
                        .build();

                Role role = roleRepository.findByName("user").orElseThrow(
                        () -> new RuntimeException("Database has not been initialized")
                );

                savedUser = userRepository.save(savedUser);

                userRoleRepository.save(UserRole.builder()
                        .pk(UserRolePk.builder()
                                .user(savedUser)
                                .role(role)
                                .build())
                        .build());

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
        return Config.getInstance().safeCall("getPlayers", () -> {
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

                    innerData.put("lv", player.getLv());
                    innerData.put("name", player.getName());
                    innerData.put("title", player.getTitle());
                    innerData.put("lastPlayed", Config.getInstance().dateFormatter.format(player.getUpdated()));
                } catch (IndexOutOfBoundsException ignore) {}

                data.add(innerData);
            }

            return Response.builder().data(data).build();
        });
    }

    @Override
    public Response getToken(String tokenInput) {
        return Config.getInstance().safeCall("getPlayers", () -> {
            long userId = jwtTokenProvider.validateToken(tokenInput);
            if(userId == 0) {
                throw new CommonException(401, "Unauthorized");
            }

            User user = userRepository.findById(userId).orElseThrow(
                    () -> new CommonException(409, "Unknown user")
            );

            return Response.builder()
                    .data(jwtTokenProvider.getTokenData(user))
                    .build();
        });
    }

}
