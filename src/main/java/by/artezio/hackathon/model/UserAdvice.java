package by.artezio.hackathon.model;

import javax.persistence.*;

/**
 * @author ntishkevich
 * @version 19.03.2016
 */
@Entity
@Table(name = "user_advice", schema = "hackathon")
public class UserAdvice {

    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "user_advice_id_seq", sequenceName = "user_advice_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_advice_id_seq")
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "advice_id")
    private Advice advice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "weight")
    private Integer weight;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Advice getAdvice() {
        return advice;
    }

    public void setAdvice(Advice advice) {
        this.advice = advice;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
