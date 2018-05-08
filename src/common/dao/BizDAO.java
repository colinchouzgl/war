package common.dao;

import common.Cache;
import common.Constants;
import common.Utils;
import entity.City;
import entity.Force;
import entity.General;
import enums.Building;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Zhou Guanliang
 * @since 2018/4/27
 */
public class BizDAO {
    public static List<General> getGeneralsInTheSameCity() {
        City playerCity = getCityByGeneralId(PlayerDAO.getPlayerId());
        List<General> all = Cache.get(Constants.KEY_GENERALS);
        if (playerCity == null || CollectionUtils.isEmpty(all)) {
            return new ArrayList<>();
        }
        List<General> targets = new ArrayList<>();
        all.forEach(e -> {
            City city = getCityByGeneralId(e.getId());
            if (city != null && city.getId().equals(playerCity.getId()) && !e.isPlayer()) {
                targets.add(e);
            }
        });
        return targets;
    }

    public static General getGeneral(Integer generalId) {
        List<General> all = Cache.get(Constants.KEY_GENERALS);
        if (CollectionUtils.isEmpty(all)) {
            return null;
        }
        for (General general : all) {
            if (general.getId().equals(generalId)) {
                return general;
            }
        }
        return null;
    }

    public static boolean updateGeneral(General newData) {
        List<General> all = Cache.get(Constants.KEY_GENERALS);
        if (CollectionUtils.isEmpty(all)) {
            return false;
        }
        for (General general : all) {
            if (general.getId().equals(newData.getId())) {
                all.remove(general);
                all.add(general);
                Cache.put(Constants.KEY_GENERALS, all);
                return true;
            }
        }
        return false;
    }

    public static City getCity(Integer cityId) {
        List<City> all = Cache.get(Constants.KEY_CITIES);
        if (CollectionUtils.isEmpty(all)) {
            return null;
        }
        for (City city : all) {
            if (city.getId().equals(cityId)) {
                return city;
            }
        }
        return null;
    }

    public static City getCityByGeneralId(Integer generalId) {
        List<City> all = Cache.get(Constants.KEY_CITIES);
        if (CollectionUtils.isEmpty(all)) {
            return null;
        }
        for (City city : all) {
            if (city.getLocalGeneralIds().contains(generalId)) {
                return city;
            }
        }
        return null;
    }

    public static Force getForce(Integer forceId) {
        List<Force> all = Cache.get(Constants.KEY_FORCES);
        if (CollectionUtils.isEmpty(all)) {
            return null;
        }
        for (Force force : all) {
            if (force.getId().equals(forceId)) {
                return force;
            }
        }
        return null;
    }

    public static Force getForceByGeneralId(Integer generalId) {
        List<Force> all = Cache.get(Constants.KEY_FORCES);
        if (CollectionUtils.isEmpty(all)) {
            return null;
        }
        for (Force force : all) {
            if (force.getGeneralIds().contains(generalId)) {
                return force;
            }
        }
        return null;
    }

    public static Integer addForce(Force force) {
        Integer newForceId = Utils.generateId(Force.class);
        force.setId(newForceId);
        List<Force> all = Cache.get(Constants.KEY_FORCES);
        if (all == null) {
            all = new ArrayList<>();
        }
        all.add(force);
        Cache.put(Constants.KEY_FORCES, all);
        return newForceId;
    }

    public static boolean updateForce(Force newData) {
        List<Force> all = Cache.get(Constants.KEY_FORCES);
        if (CollectionUtils.isEmpty(all)) {
            return false;
        }
        for (Force force : all) {
            if (force.getId().equals(newData.getId())) {
                all.remove(force);
                all.add(force);
                Cache.put(Constants.KEY_FORCES, all);
                return true;
            }
        }
        return false;
    }

    public static Building getBuilding(Integer cityId, Integer buildingNum) {
        City city = getCity(cityId);
        if (city == null) {
            return Building.ERROR;
        }
        if (buildingNum == 1) {
            return Building.fromValue(city.getBuilding1());
        }
        if (buildingNum == 2) {
            return Building.fromValue(city.getBuilding2());
        }
        return Building.ERROR;
    }
}
