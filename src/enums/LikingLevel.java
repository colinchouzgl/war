package enums;

import lombok.Getter;

/**
 * @author Zhou Guanliang
 * @since 2018/4/27
 */
public enum LikingLevel {
    E(0, "E"),
    D(1, "D"),
    C(2, "C"),
    B(3, "B"),
    A(4, "A"),
    S(5, "S"),
    ERROR(-1, "ERROR");

    @Getter
    private final int value;
    @Getter
    private final String desc;

    LikingLevel(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public static LikingLevel fromValue(Integer value) {
        for (LikingLevel e : LikingLevel.values()) {
            if (e.value == value) {
                return e;
            }
        }
        return ERROR;
    }
}
