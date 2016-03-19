package by.artezio.hackathon.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * @author ntishkevich
 * @version 19.03.2016
 */
@Entity
@Table(name = "advice_list", schema = "hackathon")
public class AdviceList {

    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "list_id_seq", sequenceName = "list_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "list_id_seq")
    private Long id;

    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "current_emotion")
    private String currentEmotion;

    @OneToMany(mappedBy = "adviceList", fetch = FetchType.EAGER)
    private List<AdviceListItem> items;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getCurrentEmotion() {
        return currentEmotion;
    }

    public void setCurrentEmotion(String currentEmotion) {
        this.currentEmotion = currentEmotion;
    }

    public List<AdviceListItem> getItems() {
        return items;
    }

    public void setItems(List<AdviceListItem> items) {
        this.items = items;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
