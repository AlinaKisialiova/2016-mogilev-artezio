package by.artezio.hackathon.model;

import javax.persistence.*;

/**
 * @author ntishkevich
 * @version 19.03.2016
 */
@Entity
@Table(name = "advice", schema = "hackathon")
public class Advice {

    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "advice_id_seq", sequenceName = "advice_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "advice_id_seq")
    private Long id;

    @Column(name = "emotion")
    private String emotion;

    @Column(name = "description")
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
