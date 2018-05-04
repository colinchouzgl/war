package common.dao;

import common.Cache;
import common.Constants;
import entity.Force;

/**
 * @author Zhou Guanliang
 * @since 2018/4/27
 */
public class PlayerDAO {
    public static Integer getPlayerMoney() {
        Integer money = Cache.get(Constants.KEY_PLAYER_MONEY);
        return money == null ? 0 : money;
    }

    public static void updatePlayerMoney(Integer money) {
        Cache.put(Constants.KEY_PLAYER_MONEY, money);
        Force playerForce = getPlayerForce();
        if (playerForce != null) {
            playerForce.setMoney(money);
            BizDAO.updateForce(playerForce);
        }
    }

    public static Integer getPlayerSupply() {
        Integer supply = Cache.get(Constants.KEY_PLAYER_SUPPLY);
        return supply == null ? 0 : supply;
    }

    public static void updatePlayerSupply(Integer supply) {
        Cache.put(Constants.KEY_PLAYER_SUPPLY, supply);
        Force playerForce = getPlayerForce();
        if (playerForce != null) {
            playerForce.setSupply(supply);
            BizDAO.updateForce(playerForce);
        }
    }

    public static Integer getPlayerArmySize() {
        Integer armySize = Cache.get(Constants.KEY_PLAYER_ARMY_SIZE);
        return armySize == null ? 0 : armySize;
    }

    public static void updatePlayerArmySize(Integer armySize) {
        Cache.put(Constants.KEY_PLAYER_ARMY_SIZE, armySize);
        Force playerForce = getPlayerForce();
        if (playerForce != null) {
            playerForce.setArmySize(armySize);
            BizDAO.updateForce(playerForce);
        }
    }

    public static Integer getPlayerId() {
        return Cache.get(Constants.KEY_PLAYER_ID);
    }

    public static Force getPlayerForce() {
        return BizDAO.getForceByGeneralId(getPlayerId());
    }
}
