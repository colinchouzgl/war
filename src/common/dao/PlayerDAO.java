package common.dao;

import common.Cache;
import common.Constants;

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
        Cache.put(Constants.KEY_PLAYER_MONEY,money);
    }

    public static Integer getPlayerSupply() {
        Integer supply = Cache.get(Constants.KEY_PLAYER_SUPPLY);
        return supply == null ? 0 : supply;
    }

    public static Integer getPlayerArmySize() {
        Integer armySize = Cache.get(Constants.KEY_PLAYER_ARMY_SIZE);
        return armySize == null ? 0 : armySize;
    }

    public static Integer getPlayerId() {
        return Cache.get(Constants.KEY_PLAYER_ID);
    }
}
