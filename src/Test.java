import com.fasterxml.jackson.databind.ObjectMapper;
import common.Cache;
import common.Constants;
import common.Utils;
import entity.City;
import entity.Force;
import entity.General;
import enums.Building;
import net.sf.json.JSONObject;

import java.util.*;

/**
 * Created by Administrator on 2018/4/27.
 */
public class Test {
    public static void main(String[] args) throws Exception {
        testInit();
//        System.out.println(StaffingAction.getTeamUpOptions());
//        StaffingAction.teamUp(8);
        display();
    }

    public static void display() throws Exception {
        Map<String, Object> map = Cache.getData();
        SortedMap<String, Object> sortedMap = new TreeMap<>();
        map.forEach(sortedMap::put);

        ObjectMapper objectMapper = new ObjectMapper();
        String s = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(sortedMap);
        System.out.println(s);
    }

    public static void testInitManual() throws Exception {
        Utils.initAll(manualConfig());
    }

    public static void testInit() throws Exception {
        Utils.init("resources/test_team_up.txt");
    }

    public static JSONObject manualConfig() {
        JSONObject jsonObject = new JSONObject();
        List<General> generals = new ArrayList<>();
        generals.add(new General(1, "LiuBei", 0, 3000, 40, false));
        generals.add(new General(2, "GuanYu", 0, 4000, 40, false));
        generals.add(new General(3, "ZhangFei", 0, 3000, 40, false));
        generals.add(new General(4, "CaoCao", 0, 3000, 40, false));
        generals.add(new General(5, "XiaHouDun", 0, 4000, 40, false));
        generals.add(new General(6, "CaoRen", 0, 2000, 40, false));
        generals.add(new General(7, "LvBu", 0, 4500, 40, false));
        generals.add(new General(8, "DiaoChan", 1, 3000, 80, false));
        generals.add(new General(9, "Colin", 0, 0, 0, true));
        generals.add(new General(10, "ZhangLiao", 0, 4000, 40, true));
        jsonObject.put(Constants.KEY_GENERALS, generals);

        List<City> cities = new ArrayList<>();
        List<Integer> neighborIds = new ArrayList<>();
        neighborIds.add(2);
        List<Integer> officerIds = new ArrayList<>();
        officerIds.add(2);
        List<Integer> localIds = new ArrayList<>();
        localIds.add(1);
        localIds.add(2);
        localIds.add(3);
        localIds.add(8);
        localIds.add(9);
        localIds.add(10);
        cities.add(new City(1, "XiaPi", neighborIds, 0, 100, 1, 1, officerIds, localIds, Building.MARKET.getValue(), Building.CAMP.getValue(), 200, 200, 200));
        neighborIds = new ArrayList<>();
        neighborIds.add(1);
        neighborIds.add(3);
        officerIds = new ArrayList<>();
        officerIds.add(5);
        localIds = new ArrayList<>();
        localIds.add(4);
        localIds.add(5);
        localIds.add(6);
        cities.add(new City(2, "XuChang", neighborIds, 0, 100, 4, 4, officerIds, localIds, Building.MARKET.getValue(), Building.MINE.getValue(), 200, 200, 200));
        neighborIds = new ArrayList<>();
        neighborIds.add(2);
        localIds = new ArrayList<>();
        localIds.add(7);
        cities.add(new City(3, "LuoYang", neighborIds, 0, 100, null, null, new ArrayList<>(), localIds, Building.CAMP.getValue(), Building.MARKET.getValue(), 200, 200, 200));
        jsonObject.put(Constants.KEY_CITIES, cities);

        List<Force> forces = new ArrayList<>();
        List<Integer> generalIds = new ArrayList<>();
        generalIds.add(1);
        generalIds.add(2);
        generalIds.add(3);
        forces.add(new Force(1, 1, generalIds, new ArrayList<>(), 1000, 1000, 1000));
        generalIds = new ArrayList<>();
        generalIds.add(4);
        generalIds.add(5);
        generalIds.add(6);
        forces.add(new Force(2, 4, generalIds, new ArrayList<>(), 1000, 1000, 1000));
        generalIds = new ArrayList<>();
        generalIds.add(9);
        generalIds.add(10);
        forces.add(new Force(3, 9, generalIds, new ArrayList<>(), 5000, 1000, 1000));
        jsonObject.put(Constants.KEY_FORCES, forces);

        jsonObject.put(Constants.TABLE_ID_FORCE, 2);
        jsonObject.put(Constants.TABLE_ID_CITY, 3);
        jsonObject.put(Constants.TABLE_ID_GENERAL, 9);
        jsonObject.put(Constants.KEY_PLAYER_ID, 9);
        jsonObject.put(Constants.KEY_PLAYER_MONEY, 5000);
        jsonObject.put(Constants.KEY_PLAYER_SUPPLY, 1000);
        jsonObject.put(Constants.KEY_PLAYER_ARMY_SIZE, 1000);
        return jsonObject;
    }
}
