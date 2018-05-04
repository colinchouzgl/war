package common;

import enums.ResponseCode;
import lombok.Data;

import java.util.Map;

/**
 * @author Zhou Guanliang
 * @since 2018/4/27
 */
@Data
public class Response {
    private boolean isSuccess;
    private String desc;
    private int code;
    private Map<String, Object> data;

    public static Response succeed() {
        Response response = new Response();
        response.setSuccess(true);
        response.setCode(ResponseCode.SUCCESS.getValue());
        return response;
    }

    public static Response succeed(Map<String, Object> data) {
        Response response = new Response();
        response.setSuccess(true);
        response.setData(data);
        response.setCode(ResponseCode.SUCCESS.getValue());
        return response;
    }

    public static Response fail(String desc) {
        Response response = new Response();
        response.setSuccess(false);
        response.setDesc(desc);
        response.setCode(ResponseCode.ERROR.getValue());
        return response;
    }

    public static Response fail(ResponseCode code) {
        Response response = new Response();
        response.setSuccess(false);
        response.setCode(code.getValue());
        return response;
    }

    public static Response fail(String desc, ResponseCode code) {
        Response response = new Response();
        response.setSuccess(false);
        response.setDesc(desc);
        response.setCode(code.getValue());
        return response;
    }
}
