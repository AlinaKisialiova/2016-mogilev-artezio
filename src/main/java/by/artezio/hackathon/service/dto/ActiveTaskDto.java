package by.artezio.hackathon.service.dto;

import java.util.List;

public class ActiveTaskDto {

    private List<UserEmotionDto> emotions;

    private List<Boolean> itemComplete;

    public List<UserEmotionDto> getEmotions() {
        return emotions;
    }
    public void setEmotions(List<UserEmotionDto> emotions) {
        this.emotions = emotions;
    }

    public List<Boolean> getItemComplete() {
        return itemComplete;
    }
    public void setItemComplete(List<Boolean> itemComplete) {
        this.itemComplete = itemComplete;
    }
}