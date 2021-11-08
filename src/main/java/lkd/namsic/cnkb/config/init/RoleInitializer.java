package lkd.namsic.cnkb.config.init;

import lkd.namsic.cnkb.domain.Role;
import lkd.namsic.cnkb.repository.RoleRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RoleInitializer extends Initializer {

    private final RoleRepository roleRepository;

    @Override
    protected void init() {
        createRole("user");
        createRole("admin");
    }

    private void createRole(@NonNull String roleName) {
        if(roleRepository.findByName(roleName).isEmpty()) {
            roleRepository.save(
                Role.builder().name(roleName).build()
            );
        }
    }

}