package enums;

import lombok.Getter;

/**
 * @author Zhou Guanliang
 * @since 2018/4/27
 */
public enum ResponseCode {
    ERROR(-1, "Error"),
    SUCCESS(0, "Success"),

    //1-999 System
    DATA_EXCEPTION(1, "Data exception"),

    /**
     * 1001-2000 Staffing
     */
    //1001-1100 TeamUp
    TEAM_UP_WITH_FORCE_GENERAL(1001, "Can't team up with generals who are in a force"),
    TEAM_UP_IN_A_FORCE(1002, "Can't team up when you are in a force"),
    TEAM_UP_MONEY_NOT_ENOUGH(1003, "Money is not enough to team up"),

    //1101-1200 Employ
    EMPLOY_FORCE_GENERAL(1101, "Can't employ generals who are in a force"),
    EMPLOY_IN_NO_FORCE(1102, "Can't employ generals when you are in no force"),
    EMPLOY_MONEY_NOT_ENOUGH(1103, "Money is not enough to employ"),

    //1201-1300 Communicate
    COMMUNICATE_MONEY_NOT_ENOUGH(1201, "Money is not enough to communicate"),

    /**
     * 2001-3000 Military
     */
    //2001-2100 Conscript

    /**
     * 3001-4000 Policy
     */
    //3001-3100 RaiseFund
    RAISE_FUND_IN_A_FORCE(3001, "Can't raise fund when you are in a force"),

    //3101-3200 RaiseSupply
    RAISE_SUPPLY_IN_A_FORCE(3101, "Can't raise supply when you are in a force");

    @Getter
    private final int value;
    @Getter
    private final String desc;

    ResponseCode(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }
}
