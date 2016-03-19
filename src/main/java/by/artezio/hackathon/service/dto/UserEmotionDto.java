package by.artezio.hackathon.service.dto;

import by.artezio.hackathon.service.dto.enumeration.EmotionTypes;
import java.util.Comparator;

public class UserEmotionDto implements Comparable<UserEmotionDto> {

    private EmotionTypes type;
    private Integer value;

    public UserEmotionDto(EmotionTypes type, Integer value) {
        this.type = type;
        this.value = value;
    }

    public EmotionTypes getType() {
        return type;
    }
    public void setType(EmotionTypes type) {
        this.type = type;
    }

    public Integer getValue() {
        return value;
    }
    public void setValue(Integer value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "UserEmotionDto{" +
                "type=" + type +
                ", value=" + value +
                '}';
    }

    @Override
    public int compareTo(UserEmotionDto o) {
        return Comparators.BY_VALUE.compare(this, o);
    }

    public static class Comparators {
        public static Comparator<UserEmotionDto> BY_VALUE = (o1, o2) -> o1.value.compareTo(o2.value);
        public static Comparator<UserEmotionDto> BY_KEY = (o1, o2) -> o1.type.compareTo(o2.type);
    }
}
