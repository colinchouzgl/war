package entity;

import lombok.Data;

/**
 * @author Zhou Guanliang
 * @since 2018/4/25
 */
@Data
public class General extends Entity {
    private Integer id;
    private String name;
    private Integer gender;
    private Integer price;
    private Integer liking;
    private boolean isPlayer;

    public General() {
    }

    public General(Integer id, String name, Integer gender, Integer price, Integer liking, boolean isPlayer) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.price = price;
        this.liking = liking;
        this.isPlayer = isPlayer;
    }
}