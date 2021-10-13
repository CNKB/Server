package lkd.namsic.cnkb.service;

import lkd.namsic.cnkb.Config;
import lkd.namsic.cnkb.bearer.JwtTokenProvider;
import lkd.namsic.cnkb.domain.SignIn;
import lkd.namsic.cnkb.domain.User;
import lkd.namsic.cnkb.dto.SignInInput;
import lkd.namsic.cnkb.dto.response.Response;
import lkd.namsic.cnkb.exception.CommonException;
import lkd.namsic.cnkb.repository.SignInRepository;
import lkd.namsic.cnkb.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

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

            User user = userRepository.findByEmail(email).orElseGet(() -> {
                User savedUser = User.builder().email(email).build();
                return userRepository.save(savedUser);
            });

            String ipString = Config.getInstance().getIp(request);
            signInRepository.save(SignIn.builder()
                    .user(user)
                    .ip(Config.getInstance().ipToLong(ipString))
                    .provider(provider)
                    .build());

            Map<String, String> data = new HashMap<>();

            String token = jwtTokenProvider.createToken(email);
            data.put("token", token);

            return Response.builder().data(data).build();
        });
    }

}
