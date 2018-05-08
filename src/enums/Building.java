package enums;

import lombok.Getter;

/**
 * @author Zhou Guanliang
 * @since 2018/4/25
 */
public enum Building {
    MARKET(0, "Market"),
    MINE(1, "Mine"),
    CAMP(2, "Camp"),
    EQUIPMENT_SHOP(3, "Equipment Shop"),

    ERROR(-1, "ERROR");

    @Getter
    private final int value;
    @Getter
    private final String desc;

    Building(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public static Building fromValue(Integer value) {
        for (Building e : Building.values()) {
            if (e.value == value) {
                return e;
            }
        }
        return ERROR;
    }
}