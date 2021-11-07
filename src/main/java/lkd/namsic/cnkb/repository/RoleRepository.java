package lkd.namsic.cnkb.repository;

import lkd.namsic.cnkb.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    
    Optional<Role> findByName(@NonNull String name);
    
}