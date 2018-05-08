package actions;

import common.Constants;
import common.Response;
import common.dao.PlayerDAO;
import entity.Force;
import enums.Building;
import enums.ResponseCode;

/**
 * @author Zhou Guanliang
 * @since 2018/4/28
 */
public class PolicyAction {
    public static Response raiseFund() {
        Force playerForce = PlayerDAO.getPlayerForce();
        //raise fund only supported when player is in no force
        if (playerForce != null) {
            return Response.fail(ResponseCode.RAISE_FUND_IN_A_FORCE);
        }
        PlayerDAO.updatePlayerMoney(Constants.RAISE_FUND_AMOUNT);
        return Response.succeed();
    }

    public static Response raiseSupply() {
        Force playerForce = PlayerDAO.getPlayerForce();
        //raise supply only supported when player is in no force
        if (playerForce != null) {
            return Response.fail(ResponseCode.RAISE_SUPPLY_IN_A_FORCE);
        }
        PlayerDAO.updatePlayerSupply(Constants.RAISE_SUPPLY_AMOUNT);
        return Response.succeed();
    }

    public static Response trade() {
        //TODO check valid
        PlayerDAO.updatePlayerMoney(Constants.TRADE_MONEY_AMOUNT);
        PlayerDAO.updatePlayerSupply(Constants.TRADE_SUPPLY_AMOUNT);
        return Response.succeed();
    }

    public static Response build(Integer cityId, Integer buildingNum, Building building) {
        //TODO check valid
        if (PlayerDAO.getPlayerMoney() < Constants.BUILD_MONEY_COST
                || PlayerDAO.getPlayerSupply() < Constants.BUILD_SUPPLY_COST) {
            return Response.fail(ResponseCode.BUILD_RESOURCE_NOT_ENOUGN);
        }


        //TODO
        return null;
    }
}
