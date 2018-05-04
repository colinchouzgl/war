package entity;

import lombok.Data;

import java.util.List;

/**
 * @author Zhou Guanliang
 * @since 2018/4/25
 */
@Data
public class City extends Entity {
    private Integer id;
    private String name;
    private List<Integer> neighborCityIds;
    private Integer damageLevel;
    private Integer happiness;

    private Integer emperorId;
    private Integer governorId;
    private List<Integer> officerIds;
    private List<Integer> localGeneralIds;

    private Integer building1;
    private Integer building2;

    private Integer moneyGrowSpeed;
    private Integer supplyGrowSpeed;
    private Integer armySizeGrowSpeed;

    public City() {
    }

    public City(Integer id, String name, List<Integer> neighborCityIds, Integer damageLevel, Integer happiness, Integer emperorId,
                Integer governorId, List<Integer> officerIds, List<Integer> localGeneralIds, Integer building1, Integer building2,
                Integer moneyGrowSpeed, Integer supplyGrowSpeed, Integer armySizeGrowSpeed) {
        this.id = id;
        this.name = name;
        this.neighborCityIds = neighborCityIds;
        this.damageLevel = damageLevel;
        this.happiness = happiness;
        this.emperorId = emperorId;
        this.governorId = governorId;
        this.officerIds = officerIds;
        this.localGeneralIds = localGeneralIds;
        this.building1 = building1;
        this.building2 = building2;
        this.moneyGrowSpeed = moneyGrowSpeed;
        this.supplyGrowSpeed = supplyGrowSpeed;
        this.armySizeGrowSpeed = armySizeGrowSpeed;
    }
}
