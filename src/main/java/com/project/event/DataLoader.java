package com.project.event;

import com.project.enums.RoleType;
import com.project.presistance.domain.Role;
import com.project.presistance.domain.User;
import com.project.presistance.repository.RoleRepository;
import com.project.presistance.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;

/**
 * Created by abdullah.alnoman on 06.08.17.
 */
@Component
public class DataLoader {



    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @EventListener(ContextRefreshedEvent.class)
    public void contextRefreshedEvent() {
        System.out.println("#############Hello #########");

        final Role adminRole = roleRepository.findByName(RoleType.ROLE_ADMIN
                .name());
        final Role userRole = roleRepository.findByName(RoleType.ROLE_USER
                .name());

        // admin
        if (!userRepository.exists("admin")) {
            final User adminUser = new User();
            adminUser.setUsername("admin");
            adminUser.setFirstName("Abdullah");
            adminUser.setLastName("alnoman");
            adminUser.setPassword(passwordEncoder.encode("admin123"));
            adminUser.setEmail("noman7119@Yahoo.com");
            adminUser.setRoles(Arrays.asList(adminRole));
            adminUser.setEnabled(true);
            adminUser.setCreatedOn(new Date());
            adminUser.setDateOfBirth(new Date());
            adminUser.setLastUpdatedOn(new Date());
            userRepository.save(adminUser);
        }else{
            User adminUser=userRepository.findByUsername("admin");
            adminUser.setUsername("admin");

            adminUser.setEmail("noman7119@gmail.com");
            userRepository.save(adminUser);

        }

        for (int i = 1; i < 11; i++) {
            String userName = "testuser" + i;
            if (!userRepository.exists(userName)) {
                final User user = new User();
                user.setUsername(userName);
                user.setFirstName(userName);
                user.setLastName(userName);
                user.setPassword(passwordEncoder.encode(userName));
                user.setEmail(userName + "@test.com");
                user.setRoles(Arrays.asList(userRole));
                user.setDateOfBirth(new Date());
                user.setEnabled(true);
                user.setCreatedOn(new Date());
                user.setLastUpdatedOn(new Date());
                userRepository.save(user);
            }
        }

    }




}
