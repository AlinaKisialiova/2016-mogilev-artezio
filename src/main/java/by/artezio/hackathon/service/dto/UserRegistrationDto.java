package by.artezio.hackathon.service.dto;

import by.artezio.hackathon.model.enumeration.GenderEnum;

import java.util.Date;

/**
 * Created by rezerv on 19.03.2016.
 */
public class UserRegistrationDto {
    private String login;
    private String password;
    private String retryPassword;
    private String email;
    private GenderEnum gender;
    private Date birthDate;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRetryPassword() {
        return retryPassword;
    }

    public void setRetryPassword(String retryPassword) {
        this.retryPassword = retryPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public GenderEnum getGender() {
        return gender;
    }

    public void setGender(GenderEnum gender) {
        this.gender = gender;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
}
