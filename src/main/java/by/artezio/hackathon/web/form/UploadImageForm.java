package by.artezio.hackathon.web.form;

import org.springframework.web.multipart.MultipartFile;

public class UploadImageForm {

    MultipartFile image;
    String imageUrl;
    String imageBase64;

    public MultipartFile getImage() {
        return image;
    }
    public void setImage(MultipartFile image) {
        this.image = image;
    }

    public String getImageUrl() {
        return imageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageBase64() {
        return imageBase64;
    }
    public void setImageBase64(String imageBase64) {
        this.imageBase64 = imageBase64;
    }

    @Override
    public String toString() {
        return "UploadImageForm{" +
                "image=" + image +
                ", imageUrl='" + imageUrl + '\'' +
                ", imageBase64='" + imageBase64 + '\'' +
                '}';
    }
}
