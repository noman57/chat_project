package com.project;

import com.project.presistance.domain.User;
import com.project.presistance.repository.RoleRepository;
import com.project.presistance.repository.UserRepository;
import com.project.presistance.service.UserService;
import com.project.presistance.service.impl.UserServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by abdullah.alnoman on 09.08.17.
 */
@RunWith(SpringRunner.class)
public class UserServiceImplTest {

    @Autowired
    private UserService userService;


    @TestConfiguration
    static class EmployeeServiceImplTestContextConfiguration {

        @Bean
        public UserService userService() {
            return new UserServiceImpl();
        }
    }



    @MockBean
    private UserRepository userRepository;


    @MockBean
    private RoleRepository roleRepository;


    @MockBean
    private PasswordEncoder passwordEncoder;


    @MockBean
    private ModelMapper modelMapper;


    @Before
    public void setUp() {
        User adminUser = new User();
        adminUser.setUsername("admin");
        adminUser.setFirstName("Abdullah");
        adminUser.setLastName("alnoman");
        adminUser.setPassword("admin123");
        adminUser.setEmail("noman7119@yahoo.com");
        adminUser.setPhone("12345678");
        //adminUser.setRoles(Arrays.asList(adminRole));
        adminUser.setEnabled(true);
        adminUser.setCreatedOn(new Date());
        adminUser.setDateOfBirth(new Date());
        adminUser.setLastUpdatedOn(new Date());

        Mockito.when(userRepository.findByEmail(adminUser.getEmail()))
                .thenReturn(adminUser);

        Mockito.when(userRepository.findOne(adminUser.getUsername()))
                .thenReturn(adminUser);

        Mockito.when(userRepository.existsByEmail(adminUser.getEmail()))
                .thenReturn(true);
        Mockito.when(userRepository.existsByPhone(adminUser.getPhone()))
                .thenReturn(true);
        Mockito.when(userRepository.exists(adminUser.getUsername()))
                .thenReturn(true);
    }


    @Test
    public void whenValidEmail_thenUserShouldBeFound() {
        String name = "noman7119@yahoo.com";
        User found = userService.getUserByEmail(name);

        assertThat(found.getEmail())
                .isEqualTo(name);
    }



    @Test
    public void whenValidUser_thenUserShouldBeFound() {
        String name = "admin";
        User found = userService.getUserByUsername(name);

        assertThat(found.getUsername())
                .isEqualTo(name);
    }


    @Test
    public void whenValidUserName_thenUserShouldBeFound() {
        String name = "admin";
        boolean found = userService.isUserExists(name);

        assertThat(found)
                .isEqualTo(true);
    }


    @Test
    public void whenValidEmail_thenUserShouldExist() {
        String name = "noman7119@yahoo.com";
        boolean found = userService.isUserEmailExists(name);

        assertThat(found)
                .isEqualTo(true);
    }

}
