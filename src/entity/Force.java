package entity;

import lombok.Data;

import java.util.List;

/**
 * @author Zhou Guanliang
 * @since 2018/4/27
 */
@Data
public class Force extends Entity {
    private Integer id;
    private Integer emperorId;

    private List<Integer> generalIds;
    private List<Integer> allyIds;

    private Integer money;
    private Integer supply;
    private Integer armySize;

    public Force() {
    }

    public Force(Integer id, Integer emperorId, List<Integer> generalIds, List<Integer> allyIds, Integer money,
                 Integer supply, Integer armySize) {
        this.id = id;
        this.emperorId = emperorId;
        this.generalIds = generalIds;
        this.allyIds = allyIds;
        this.money = money;
        this.supply = supply;
        this.armySize = armySize;
    }
}
