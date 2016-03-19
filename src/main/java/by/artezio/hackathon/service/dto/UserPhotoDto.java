package by.artezio.hackathon.service.dto;

import java.util.List;

/**
 * @author ntishkevich
 * @version 19.03.2016
 */
public class UserPhotoDto {

    private byte[] photo;

    private String photoUrl;

    private List<UserEmotionDto> emotions;

    public UserPhotoDto(byte[] photo, List<UserEmotionDto> emotions) {
        this.photo = photo;
        this.emotions = emotions;
    }

    public UserPhotoDto(String photoUrl, List<UserEmotionDto> emotions) {
        this.photoUrl = photoUrl;
        this.emotions = emotions;
    }

    public List<UserEmotionDto> getEmotions() {
        return emotions;
    }

    public void setEmotions(List<UserEmotionDto> emotions) {
        this.emotions = emotions;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
}
