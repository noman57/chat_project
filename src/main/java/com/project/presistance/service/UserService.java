package com.project.presistance.service;

import com.project.dto.account.CreateUserDTO;
import com.project.dto.account.GetProfileDTO;
import com.project.enums.RoleType;
import com.project.presistance.domain.Role;
import com.project.presistance.domain.User;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by abdullah.alnoman on 05.08.17.
 */
public interface UserService {

    User createUser(CreateUserDTO userData);


    @Transactional(readOnly = true)
    GetProfileDTO getProfile(String userID);

    User getUserByUsername(String username);

    User getUserByEmail(String email);

    /**
     * It takes userID which can be username or email and return an user object if exist
     * @param userID
     * @return
     */
    User getUserByUserNameOrEmail(String userID);


    void saveUser(User user);


    boolean isUserExists(String username);

    boolean isUserEmailExists(String email);

    boolean isUserPhoneExists(String phone);


    @Transactional(readOnly = true)
    Role getRoleByName(RoleType roleType);
}
