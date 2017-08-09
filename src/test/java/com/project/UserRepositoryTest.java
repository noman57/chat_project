package com.project;

import com.project.presistance.domain.User;
import com.project.presistance.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;


import static org.assertj.core.api.Assertions.assertThat;
import static sun.nio.cs.Surrogate.is;

import java.util.Arrays;
import java.util.Date;

/**
 * Created by abdullah.alnoman on 09.08.17.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    User adminUser;

    @Before
    public void initialize(){
        adminUser = new User();
        adminUser.setUsername("admin");
        adminUser.setFirstName("Abdullah");
        adminUser.setLastName("alnoman");
        adminUser.setPassword("admin123");
        adminUser.setEmail("noman7119@Yahoo.com");
        adminUser.setPhone("12345678");
        //adminUser.setRoles(Arrays.asList(adminRole));
        adminUser.setEnabled(true);
        adminUser.setCreatedOn(new Date());
        adminUser.setDateOfBirth(new Date());
        adminUser.setLastUpdatedOn(new Date());

        entityManager.persist(adminUser);
        entityManager.flush();

    }

    @Test
    public void whenFindByEmail_thenReturnUser() {
        // when
        User found = userRepository.findByEmail(adminUser.getEmail());

        // then
        assertThat(found.getEmail())
                .isEqualTo(adminUser.getEmail());
    }


    @Test
    public void whenFindByUserName_thenReturnUser() {
        // when
        User found = userRepository.findByUsername(adminUser.getUsername());

        // then
        assertThat(found.getUsername())
                .isEqualTo(adminUser.getUsername());
    }



    @Test
    public void whenExistsByEmail_thenReturnTrue() {
        // when
        boolean found = userRepository.existsByEmail(adminUser.getEmail());

        // then
        assertThat(found)
                .isEqualTo(true);
    }



    @Test
    public void  whenExistsByPhone_thenReturnTrue() {
        // when
        boolean found = userRepository.existsByPhone("12345678");
        // then
        assertThat(found)
                .isEqualTo(true);
    }
}



