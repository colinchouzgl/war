package entity;

import lombok.Data;

import java.util.List;

/**
 * @author Zhou Guanliang
 * @since 2018/4/25
 */
@Data
public class Country extends Force {
    private List<Integer> cityIds;
    private Integer capitalCityId;
}
