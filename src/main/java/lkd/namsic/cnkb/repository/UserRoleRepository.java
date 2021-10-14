package lkd.namsic.cnkb.repository;

import lkd.namsic.cnkb.domain.User;
import lkd.namsic.cnkb.domain.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import java.util.List;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

    List<UserRole> findAllByPk_User(@NonNull User user);

}
