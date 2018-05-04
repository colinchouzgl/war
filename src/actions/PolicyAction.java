package actions;

import common.Constants;
import common.Response;
import common.dao.PlayerDAO;
import entity.Force;
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
}
