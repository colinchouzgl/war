package common;

import com.fasterxml.jackson.databind.ObjectMapper;
import entity.City;
import entity.Entity;
import entity.Force;
import entity.General;
import enums.LikingLevel;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;
import org.apache.commons.collections.CollectionUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Zhou Guanliang
 * @since 2018/4/25
 */
public class Utils {
    public static void init(String configPath) {
        String content = read(configPath);
        initAll(JSONObject.fromObject(content));
    }

    public static void initAll(JSONObject json) {
        initField(json, Constants.TABLE_ID_FORCE, Integer.class);
        initField(json, Constants.TABLE_ID_CITY, Integer.class);
        initField(json, Constants.TABLE_ID_GENERAL, Integer.class);
        initListField(json, Constants.KEY_FORCES, Force.class);
        initListField(json, Constants.KEY_CITIES, City.class);
        initListField(json, Constants.KEY_GENERALS, General.class);
        initField(json, Constants.KEY_PLAYER_ID, Integer.class);
        initField(json, Constants.KEY_PLAYER_MONEY, Integer.class);
        initField(json, Constants.KEY_PLAYER_SUPPLY, Integer.class);
        initField(json, Constants.KEY_PLAYER_ARMY_SIZE, Integer.class);
    }

    public static <T> void initField(JSONObject json, String key, Class<T> clazz) {
        String value = JSONUtils.valueToString(json.get(key));
        Cache.put(key, fromJson(value, clazz));
    }

    public static <T> void initListField(JSONObject json, String key, Class<T> clazz) {
        JSONArray array = json.optJSONArray(key);
        if (!CollectionUtils.isEmpty(array)) {
            List<T> list = new ArrayList<>();
            array.forEach(e -> list.add(fromJson(e.toString(), clazz)));
            Cache.put(key, list);
        }
    }

    public static <T extends Entity> List<T> initId(List<T> entities) {
        if (entities != null) {
            entities.forEach(e -> e.setId(generateId(getTableKey(e.getClass()))));
        }
        return entities;
    }

    public static Integer generateId(Class clazz) {
        return generateId(getTableKey(clazz));
    }

    public static Integer generateId(String tableKey) {
        Integer id = Cache.get(tableKey);
        id = id == null ? 0 : id;
        Cache.put(tableKey, ++id);
        return id;
    }

    public static String getTableKey(Class clazz) {
        return "id_" + clazz.getSimpleName().toLowerCase();
    }

    public static String toJson(Object o) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(o);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String toPrettyJson(Object o) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(o);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> T fromJson(String json, Class<T> clazz) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(json, clazz);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String read(String filePath) {
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader in = new BufferedReader(new FileReader(new File(filePath).getAbsoluteFile()));
            try {
                String s;
                while ((s = in.readLine()) != null) {
                    sb.append(s);
                    sb.append("\r\n");
                }
            } finally {
                in.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return sb.toString();
    }

    public static void write(String filePath, String text) {
        try {
            PrintWriter out = new PrintWriter(new File(filePath).getAbsoluteFile());
            try {
                out.print(text);
            } finally {
                out.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean randomSucceedByLiking(LikingLevel likingLevel) {
        int range;
        switch (likingLevel) {
            case E:
                range = 10;
                break;
            case D:
                range = 30;
                break;
            case C:
                range = 50;
                break;
            case B:
                range = 65;
                break;
            case A:
                range = 80;
                break;
            case S:
                range = 95;
                break;
            default:
                range = 1;
        }
        int result = getRandomNumber(1, 100);
        return result <= range;
    }

    public static LikingLevel getLikingLevel(Integer liking) {
        if (liking >= 0 && liking <= Constants.LIKING_LEVEL_SPAN) {
            return LikingLevel.E;
        }
        if (liking > Constants.LIKING_LEVEL_SPAN && liking <= Constants.LIKING_LEVEL_SPAN * 2) {
            return LikingLevel.D;
        }
        if (liking > Constants.LIKING_LEVEL_SPAN * 2 && liking <= Constants.LIKING_LEVEL_SPAN * 3) {
            return LikingLevel.C;
        }
        if (liking > Constants.LIKING_LEVEL_SPAN * 3 && liking <= Constants.LIKING_LEVEL_SPAN * 4) {
            return LikingLevel.B;
        }
        if (liking > Constants.LIKING_LEVEL_SPAN * 4 && liking <= Constants.LIKING_LEVEL_SPAN * 5) {
            return LikingLevel.A;
        }
        if (liking > Constants.LIKING_LEVEL_SPAN * 5 && liking <= Constants.LIKING_LEVEL_SPAN * 6) {
            return LikingLevel.S;
        }
        return LikingLevel.ERROR;
    }

    public static int getRandomNumber(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }
}
