package lkd.namsic.cnkb.repository;

import lkd.namsic.cnkb.domain.game.living.LivingVariable;
import lkd.namsic.cnkb.domain.game.living.LivingVariableUnique;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LivingVariableRepository extends JpaRepository<LivingVariable, Long> {

    Optional<LivingVariable> findByLiving(LivingVariableUnique living);

}