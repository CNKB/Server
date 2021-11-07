package lkd.namsic.cnkb.config.init;

import lkd.namsic.cnkb.domain.Role;
import lkd.namsic.cnkb.repository.RoleRepository;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
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