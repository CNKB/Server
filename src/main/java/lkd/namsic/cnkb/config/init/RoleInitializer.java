package lkd.namsic.cnkb.config.init;

import lkd.namsic.cnkb.domain.Role;
import lkd.namsic.cnkb.repository.RoleRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoleInitializer extends Initializer {

    @Autowired
    RoleRepository roleRepository;

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
