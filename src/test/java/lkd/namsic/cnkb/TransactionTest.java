package lkd.namsic.cnkb;

import lkd.namsic.cnkb.domain.Role;
import lkd.namsic.cnkb.domain.TestDomain;
import lkd.namsic.cnkb.repository.RoleRepository;
import lkd.namsic.cnkb.repository.TestRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@WebAppConfiguration
@SpringBootTest
@Transactional(propagation = Propagation.NEVER, isolation = Isolation.READ_COMMITTED)
@Rollback(value = false)
public class TransactionTest {

    @Autowired
    TestRepository testRepository;

    @Autowired
    RoleRepository roleRepository;

    @Test
    public void dbTest() {
        System.out.println("YEAH");
    }

    @Test
    public void multiThreadDbTest() {
        for(int i = 0; i < 10000; i++) {
            TestDomain testDomain = testRepository.findById(1L).orElseThrow(RuntimeException::new);
            testDomain.setData(2);
            testRepository.save(testDomain);

            Thread thread1 = new Thread(() -> {
                TestDomain domain = testRepository.findById(1L).orElseThrow(RuntimeException::new);

                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    return;
                }

                domain.setData(1);
                testRepository.save(domain);
            });

            Thread thread2 = new Thread(() -> {
                try {
                    thread1.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    return;
                }

                TestDomain domain = testRepository.findById(1L).orElseThrow(RuntimeException::new);

                if (domain.getData() == 2) {
                    domain.setData(3);
                    domain = testRepository.save(domain);
                    System.out.println("Thread2 after saving: " + domain.getData());
                }
            });

            try {
                thread1.start();
                thread2.start();

                thread2.join();
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }

            TestDomain domain = testRepository.findById(1L).orElseThrow(RuntimeException::new);

            if(domain.getData() != 1) {
                System.out.println("Final value: " + domain.getData());
            }

            if(i % 100 == 0) {
                System.out.println(i);
            }
        }
    }

    @Test
    public void initDb() {
        long roleCount = roleRepository.count();

        if(roleCount == 0) {
            roleRepository.save(Role.builder().name("user").build());
            roleRepository.save(Role.builder().name("admin").build());
        }
    }

}
