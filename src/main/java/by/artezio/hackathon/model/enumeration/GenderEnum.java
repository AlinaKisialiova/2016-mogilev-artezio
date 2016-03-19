package by.artezio.hackathon.model.enumeration;

/**
 * @author ntishkevich
 * @version 19.03.2016
 */
public enum GenderEnum {
    MALE("Мужской"),
    FEMALE("Женский");

    private String name;

    GenderEnum(String gender) {
        this.name = gender;
    }

    public String getName() {
        return name;
    }
}
