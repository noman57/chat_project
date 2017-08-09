package com.project;

import com.project.dto.account.GetProfileDTO;
import com.project.helper.MessageHelper;
import com.project.helper.ResourceValidationHelper;
import com.project.presistance.domain.User;
import com.project.presistance.service.UserService;
import com.project.resouces.UserResources;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by abdullah.alnoman on 09.08.17.
 */

@RunWith(SpringRunner.class)
@WebMvcTest(UserResources.class)
@ActiveProfiles(value = "test")
public class UserResourcesTest {


    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService userService;

    @MockBean
    private MessageSource messageSource;
    @MockBean
    private ApplicationEventPublisher eventPublisher;
    @MockBean
    private PasswordEncoder passwordEncoder;
    @MockBean
    private ResourceValidationHelper validationHelper;
    @MockBean
    private MessageHelper messageHelper;

    @Test
    public void givenEmployees_whenGetEmployees_thenReturnJsonArray()
            throws Exception {

        User admin = new User("admin");
        GetProfileDTO profile = new GetProfileDTO();
        profile.setUsername("admin");


        given(userService.getUserByUserNameOrEmail("admin")).willReturn(admin);
        given(userService.getProfile("admin")).willReturn(profile);

        mvc.perform(get("/v1/users/admin")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.username", is(admin.getUsername())));
    }


    // Many more test cases can be added ...
}
