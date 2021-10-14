package lkd.namsic.cnkb.service;

import lkd.namsic.cnkb.Config;
import lkd.namsic.cnkb.bearer.JwtTokenProvider;
import lkd.namsic.cnkb.domain.*;
import lkd.namsic.cnkb.dto.SignInInput;
import lkd.namsic.cnkb.dto.response.Response;
import lkd.namsic.cnkb.exception.CommonException;
import lkd.namsic.cnkb.repository.RoleRepository;
import lkd.namsic.cnkb.repository.UserRoleRepository;
import lkd.namsic.cnkb.repository.SignInRepository;
import lkd.namsic.cnkb.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    JwtTokenProvider jwtTokenProvider;

    @Override
    public Response signIn(HttpServletRequest request, SignInInput input) {
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

            List<String> roleSet = userRoleRepository.findAllByPk_User(user)
                    .stream()
                    .map(userRole -> userRole.getPk().getRole().getName())
                    .collect(Collectors.toList());
            String token = jwtTokenProvider.createToken(email, roleSet);

            user.setToken(token);
            userRepository.save(user);

            Map<String, String> data = new HashMap<>();
            data.put("token", token);

            return Response.builder().data(data).build();
        });
    }

}
