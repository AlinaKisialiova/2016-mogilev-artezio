package by.artezio.hackathon.model;

import javax.persistence.*;
import java.util.Date;

/**
 * @author ntishkevich
 * @version 19.03.2016
 */
@Entity
@Table(name = "user", schema = "hackathon")
public class User {

    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "user_id_seq", sequenceName = "user_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_seq")
    private Long id;

    @Column(name = "login")
    private String login;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "mood")
    private Double mood;

    @Column(name = "last_event_date")
    private Date lastEventDate;

    @Column(name = "gender")
    @Enumerated(value = EnumType.STRING)
    private GenderEnum gender;

    @Column(name = "birth_date")
    private Date birthDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Double getMood() {
        return mood;
    }

    public void setMood(Double mood) {
        this.mood = mood;
    }

    public Date getLastEventDate() {
        return lastEventDate;
    }

    public void setLastEventDate(Date lastEventDate) {
        this.lastEventDate = lastEventDate;
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
