package enums;

import lombok.Getter;

/**
 * @author Zhou Guanliang
 * @since 2018/4/26
 */
public enum Gender {
    MALE(0, "Male"),
    FEMALE(1, "Female");

    @Getter
    private final int value;
    @Getter
    private final String desc;

    Gender(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }
}
