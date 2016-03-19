package by.artezio.hackathon.service.dto;

import java.util.List;

/**
 * @author ntishkevich
 * @version 19.03.2016
 */
public class HistoryTaskDto {

    private Long id;

    private String endDate;

    private List<String> emotions;

    private List<Boolean> itemComplete;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public List<String> getEmotions() {
        return emotions;
    }

    public void setEmotions(List<String> emotions) {
        this.emotions = emotions;
    }

    public List<Boolean> getItemComplete() {
        return itemComplete;
    }

    public void setItemComplete(List<Boolean> itemComplete) {
        this.itemComplete = itemComplete;
    }
}
