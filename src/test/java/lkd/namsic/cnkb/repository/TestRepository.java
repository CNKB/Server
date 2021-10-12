package lkd.namsic.cnkb.repository;

import lkd.namsic.cnkb.domain.TestDomain;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepository extends JpaRepository<TestDomain, Long> {}
