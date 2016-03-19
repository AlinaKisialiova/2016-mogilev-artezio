package by.artezio.hackathon.model;

import javax.persistence.*;
import java.util.Date;

/**
 * @author ntishkevich
 * @version 19.03.2016
 */
@Entity
@Table(name = "list_item", schema = "hackathon")
public class AdviceListItem {

    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "list_item_id_seq", sequenceName = "list_item_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "list_item_id_seq")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "list_id")
    private AdviceList adviceList;

    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "complete")
    private Boolean complete;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_advice_id")
    private UserAdvice userAdvice;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AdviceList getAdviceList() {
        return adviceList;
    }

    public void setAdviceList(AdviceList adviceList) {
        this.adviceList = adviceList;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Boolean getComplete() {
        return complete;
    }

    public void setComplete(Boolean complete) {
        this.complete = complete;
    }

    public UserAdvice getUserAdvice() {
        return userAdvice;
    }

    public void setUserAdvice(UserAdvice userAdvice) {
        this.userAdvice = userAdvice;
    }
}
