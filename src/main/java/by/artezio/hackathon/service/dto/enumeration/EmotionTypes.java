package by.artezio.hackathon.service.dto.enumeration;

public enum EmotionTypes {

    ANGER("A", "anger", "Злость"),
    CONTEMPT("C", "contempt", "Презрение"),
    DISGUST("D", "disgust", "Отвращение"),
    FEAR("F", "fear",  "Страх"),
    HAPPINESS("H", "happiness", "Радость"),
    NEUTRAL("N", "neutral", "Нейтральность"),
    SADNESS("SA", "sadness", "Грусть"),
    SURPRISE("SU", "surprise", "Удивление");

    public static EmotionTypes of(String code) {
        for (EmotionTypes type : values()) {
            if (type.shortCode.equals(code)) {
                return type;
            }
        }
        return null;
    }

    private String shortCode;
    private String code;
    private String title;

    EmotionTypes(String shortCode, String code, String title) {
        this.shortCode = shortCode;
        this.code = code;
        this.title = title;
    }

    public String getShortCode() {
        return shortCode;
    }

    public String getCode() {
        return code;
    }

    public String getTitle() {
        return title;
    }
}
