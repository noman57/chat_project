package com.project.dto.account;


import com.project.enums.GenderType;
import com.project.validation.PasswordMatches;
import com.project.validation.ValidEmail;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * Created by Noman
 */
@PasswordMatches
public class CreateUserDTO {

    @NotEmpty
    @NotNull
    @Size(min = 3)
    private String username;
    private String socialId;
    @NotEmpty
    @NotNull
    @Size(min = 8, max = 255)
    private String password;
    @NotEmpty
    @NotNull
    @Size(min = 8, max = 255)
    private String matchingPassword;
    @ValidEmail
    @NotEmpty
    @NotNull
    @Size(min = 1, max = 100)
    private String email;
    private String phone;
    @NotEmpty
    @NotNull
    @Size(min = 2, max = 50)
    private String firstName;
    @NotEmpty
    @NotNull
    @Size(min = 2, max = 50)
    private String lastName;
    private GenderType gender;
    private String streetAddress;
    private String city;
    private String profilePicture;
    
    
    private Date dateOfBirth;
    
    
	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username.toLowerCase();
    }

    public String getSocialId() {
        return socialId;
    }

    public void setSocialId(String socialId) {
        this.socialId = socialId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMatchingPassword() {
        return matchingPassword;
    }

    public void setMatchingPassword(String matchingPassword) {
        this.matchingPassword = matchingPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public GenderType getGender() {
        return gender;
    }

    public void setGender(GenderType gender) {
        this.gender = gender;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

   
}
