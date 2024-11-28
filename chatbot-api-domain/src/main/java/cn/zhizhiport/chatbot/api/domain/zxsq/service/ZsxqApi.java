package cn.zhizhiport.chatbot.api.domain.zxsq.service;

import cn.zhizhiport.chatbot.api.domain.zxsq.IZsxqApi;
import cn.zhizhiport.chatbot.api.domain.zxsq.model.aggregate.Response;
import cn.zhizhiport.chatbot.api.domain.zxsq.model.req.AnswerReq;
import cn.zhizhiport.chatbot.api.domain.zxsq.model.req.SearchReq;
import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 知识星球 API 接口实现
 *
 * @author ThinkingOverflow
 * @date 2024/11/25
 */
@Slf4j
@Service
public class ZsxqApi implements IZsxqApi {

    @Override
    public Response searchQuestions(SearchReq searchReq) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder().build();

        Request request = new Request.Builder()
                .url("https://api.zsxq.com/v2/groups/" + searchReq.getGroupId() +"/topics?scope=unanswered_questions&count=20")
                .method("GET",null)
                .addHeader("priority", "u=1, i")
                .addHeader("x-request-id", "260527c21-621c-3fb4-6184-581adf11c79")
                .addHeader("x-signature", "b1ffbaf09bb67395dc0bc1f103ac08a4d2652e30")
                .addHeader("x-timestamp", "1732205538")
                .addHeader("x-version", "2.65.0")
                .addHeader("Cookie", searchReq.getCookie())
                .addHeader("User-Agent", "Apifox/1.0.0 (https://apifox.com)")
                .build();

        okhttp3.Response initResponse = client.newCall(request).execute();
        String responseBodyString = initResponse.body().string();
        log.info("查询帖子信息接口响应: {}" ,responseBodyString);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        try {
            Response response = objectMapper.readValue(responseBodyString, Response.class);
            return response;
        } catch (Exception e) {
            log.error("解析查询帖子接口失败", e);
            return null;
        }
    }

    @Override
    public boolean answerQuestion(AnswerReq answerReq) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, toRequiredJsonFormat(answerReq.getReq_data()));

        Request request = new Request.Builder()
                .url("https://api.zsxq.com/v2/topics/" + answerReq.getTopicId() + "/answer")
                .method("POST", body)
                .addHeader("priority", "u=1, i")
                .addHeader("x-request-id", "260527c21-621c-3fb4-6184-581adf11c79")
                .addHeader("x-signature", "b1ffbaf09bb67395dc0bc1f103ac08a4d2652e30")
                .addHeader("x-timestamp", "1732205538")
                .addHeader("x-version", "2.65.0")
                .addHeader("Cookie", answerReq.getCookie())
                .addHeader("User-Agent", "Apifox/1.0.0 (https://apifox.com)")
                .addHeader("content-type", "application/json")
                .build();

        okhttp3.Response response = client.newCall(request).execute();

        String responseBodyString = response.body().string();
        log.info("回答问题接口响应: {}" ,responseBodyString);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            Response result = objectMapper.readValue(responseBodyString, Response.class);
            return result.isSucceeded();
        } catch (Exception e) {
            log.error("解析回答问题接口响应失败", e);
            return false;
        }
    }

    private static String toRequiredJsonFormat(AnswerReq.ReqData reqData) {
        Map<String, Object> reqDataMap = new HashMap<>();
        reqDataMap.put("text", reqData.getText());

        if (reqData.getImage_ids()!= null) {
            reqDataMap.put("image_ids", Arrays.asList(reqData.getImage_ids()));
        } else {
            reqDataMap.put("image_ids", new ArrayList<>());
        }

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("req_data", reqDataMap);

        return JSON.toJSONString(resultMap);
    }
}
