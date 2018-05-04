package actions;

import common.Constants;
import common.Response;
import common.Utils;
import common.dao.BizDAO;
import common.dao.PlayerDAO;
import entity.Force;
import entity.General;
import enums.ResponseCode;

import java.util.*;

/**
 * @author Zhou Guanliang
 * @since 2018/4/26
 */
public class StaffingAction {
    public static Response getTeamUpOptions() {
        Force playerForce = BizDAO.getForceByGeneralId(PlayerDAO.getPlayerId());
        //team up only supported when player is in no force
        if (playerForce != null) {
            return Response.fail(ResponseCode.TEAM_UP_IN_A_FORCE);
        }
        List<General> generalsInTheSameCity = BizDAO.getGeneralsInTheSameCity();
        List<General> targets = new ArrayList<>();
        generalsInTheSameCity.forEach(e -> {
            if (BizDAO.getForceByGeneralId(e.getId()) == null) {
                targets.add(e);
            }
        });
        targets.sort(Comparator.comparing(General::getName));
        Map<String, Object> data = new HashMap<>();
        data.put("options", targets);
        return Response.succeed(data);
    }

    public static Response teamUp(Integer generalId) {
        if (generalId.equals(PlayerDAO.getPlayerId()) || BizDAO.getForceByGeneralId(PlayerDAO.getPlayerId()) != null) {
            return Response.fail(ResponseCode.DATA_EXCEPTION);
        }
        General general = BizDAO.getGeneral(generalId);
        if (general == null) {
            return Response.fail(ResponseCode.DATA_EXCEPTION);
        }
        if (BizDAO.getForceByGeneralId(generalId) != null) {
            return Response.fail(ResponseCode.TEAM_UP_WITH_FORCE_GENERAL);
        }
        if (general.getPrice() > PlayerDAO.getPlayerMoney()) {
            return Response.fail(ResponseCode.TEAM_UP_MONEY_NOT_ENOUGH);
        }
        boolean success = Utils.randomSucceedByLiking(Utils.getLikingLevel(general.getLiking()));
        Map<String, Object> data = new HashMap<>();
        if (success) {
            List<Integer> generalIds = new ArrayList<>();
            generalIds.add(PlayerDAO.getPlayerId());
            generalIds.add(generalId);
            Integer restMoney = PlayerDAO.getPlayerMoney() - general.getPrice();

            Force force = new Force();
            force.setEmperorId(PlayerDAO.getPlayerId());
            force.setGeneralIds(generalIds);
            force.setAllyIds(new ArrayList<>());
            force.setMoney(restMoney);
            force.setSupply(PlayerDAO.getPlayerSupply());
            force.setArmySize(PlayerDAO.getPlayerArmySize());
            Integer newForceId = BizDAO.addForce(force);

            PlayerDAO.updatePlayerMoney(restMoney);
            data.put("newForceId", newForceId);
        }
        data.put("success", success);
        return Response.succeed(data);
    }

    public static Response getEmployOptions() {
        Force playerForce = BizDAO.getForceByGeneralId(PlayerDAO.getPlayerId());
        //employ only supported when player is in a force
        if (playerForce == null) {
            return Response.fail(ResponseCode.EMPLOY_IN_NO_FORCE);
        }
        List<General> generalsInTheSameCity = BizDAO.getGeneralsInTheSameCity();
        List<General> targets = new ArrayList<>();
        generalsInTheSameCity.forEach(e -> {
            if (BizDAO.getForceByGeneralId(e.getId()) == null) {
                targets.add(e);
            }
        });
        targets.sort(Comparator.comparing(General::getName));
        Map<String, Object> data = new HashMap<>();
        data.put("options", targets);
        return Response.succeed(data);
    }

    public static Response employ(Integer generalId) {
        Force playerForce = BizDAO.getForceByGeneralId(PlayerDAO.getPlayerId());
        if (generalId.equals(PlayerDAO.getPlayerId()) || playerForce == null) {
            return Response.fail(ResponseCode.DATA_EXCEPTION);
        }
        General general = BizDAO.getGeneral(generalId);
        if (general == null) {
            return Response.fail(ResponseCode.DATA_EXCEPTION);
        }
        if (BizDAO.getForceByGeneralId(generalId) != null) {
            return Response.fail(ResponseCode.EMPLOY_FORCE_GENERAL);
        }
        if (general.getPrice() > PlayerDAO.getPlayerMoney()) {
            return Response.fail(ResponseCode.EMPLOY_MONEY_NOT_ENOUGH);
        }
        boolean success = Utils.randomSucceedByLiking(Utils.getLikingLevel(general.getLiking()));
        Map<String, Object> data = new HashMap<>();
        if (success) {
            List<Integer> generalIds = playerForce.getGeneralIds();
            if (generalIds == null) {
                generalIds = new ArrayList<>();
            }
            generalIds.add(generalId);
            Integer restMoney = PlayerDAO.getPlayerMoney() - general.getPrice();
            playerForce.setGeneralIds(generalIds);
            playerForce.setMoney(restMoney);

            BizDAO.updateForce(playerForce);
            PlayerDAO.updatePlayerMoney(restMoney);
        }
        data.put("success", success);
        return Response.succeed(data);
    }

    public static Response getCommunicateOptions() {
        List<General> generalsInTheSameCity = BizDAO.getGeneralsInTheSameCity();
        generalsInTheSameCity.sort(Comparator.comparing(General::getName));
        Map<String, Object> data = new HashMap<>();
        data.put("options", generalsInTheSameCity);
        return Response.succeed(data);
    }

    public static Response communicate(Integer generalId) {
        if (generalId.equals(PlayerDAO.getPlayerId())) {
            return Response.fail(ResponseCode.DATA_EXCEPTION);
        }
        int playerMoney = PlayerDAO.getPlayerMoney();
        if (playerMoney < Constants.COMMUNICATE_PRICE) {
            return Response.fail(ResponseCode.COMMUNICATE_MONEY_NOT_ENOUGH);
        }
        General general = BizDAO.getGeneral(generalId);
        if (general == null) {
            return Response.fail(ResponseCode.DATA_EXCEPTION);
        }
        PlayerDAO.updatePlayerMoney(playerMoney - Constants.COMMUNICATE_PRICE);
        general.setLiking(general.getLiking() + Constants.LIKING_LEVEL_SPAN);
        BizDAO.updateGeneral(general);
        return Response.succeed();
    }
}
