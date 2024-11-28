package cn.zhizhiport.chatbot.api.test;

import okhttp3.*;
import org.junit.Test;

import java.io.IOException;

/**
 * 单元测试
 *
 * @author ThinkingOverflow
 * @date 2024/11/22
 */
public class Apitest {



    @Test
    public void query_unanswered_questions() throws IOException {
        OkHttpClient httpClient = new OkHttpClient().newBuilder().build();
//        MediaType mediaType = MediaType.parse("text/plain");
//        RequestBody body = RequestBody.create(mediaType, "");

        Request request = new Request.Builder()
                .url("https://api.zsxq.com/v2/groups/88888184224222/topics?scope=unanswered_questions&count=20")
                .method("GET",null)
                .addHeader("priority", "u=1, i")
                .addHeader("x-request-id", "260527c21-621c-3fb4-6184-581adf11c79")
                .addHeader("x-signature", "b1ffbaf09bb67395dc0bc1f103ac08a4d2652e30")
                .addHeader("x-timestamp", "1732545557")
                .addHeader("x-version", "2.65.0")
                .addHeader("Cookie", "sensorsdata2015jssdkcross=%7B%22distinct_id%22%3A%22118252248855282%22%2C%22first_id%22%3A%22191a968bb6e5c7-04b74f0329161f8-26001e51-1433208-191a968bb6f2550%22%2C%22props%22%3A%7B%22%24latest_traffic_source_type%22%3A%22%E7%9B%B4%E6%8E%A5%E6%B5%81%E9%87%8F%22%2C%22%24latest_search_keyword%22%3A%22%E6%9C%AA%E5%8F%96%E5%88%B0%E5%80%BC_%E7%9B%B4%E6%8E%A5%E6%89%93%E5%BC%80%22%2C%22%24latest_referrer%22%3A%22%22%7D%2C%22identities%22%3A%22eyIkaWRlbnRpdHlfY29va2llX2lkIjoiMTkxYTk2OGJiNmU1YzctMDRiNzRmMDMyOTE2MWY4LTI2MDAxZTUxLTE0MzMyMDgtMTkxYTk2OGJiNmYyNTUwIiwiJGlkZW50aXR5X2xvZ2luX2lkIjoiMTE4MjUyMjQ4ODU1MjgyIn0%3D%22%2C%22history_login_id%22%3A%7B%22name%22%3A%22%24identity_login_id%22%2C%22value%22%3A%22118252248855282%22%7D%2C%22%24device_id%22%3A%22191a968bb6e5c7-04b74f0329161f8-26001e51-1433208-191a968bb6f2550%22%7D; zsxq_access_token=FEDFBADA-9855-A8B8-98D9-864E6BB59513_4E34399C570D1EA8; zsxqsessionid=c206fe1cdc13f3ba2ccb46253b39583e; abtest_env=beta")
                .addHeader("User-Agent", "Apifox/1.0.0 (https://apifox.com)")
                .build();
        Response response = httpClient.newCall(request).execute();
        System.out.println(response.body().string());
    }

    @Test
    public void answer() throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\"req_data\":{\"text\":\"test测试\\n\",\"image_ids\":[]}}");

        Request request = new Request.Builder()
                .url("https://api.zsxq.com/v2/topics/1525121215182842/answer")
                .method("POST", body)
                .addHeader("priority", "u=1, i")
                .addHeader("x-request-id", "260527c21-621c-3fb4-6184-581adf11c79")
                .addHeader("x-signature", "b1ffbaf09bb67395dc0bc1f103ac08a4d2652e30")
                .addHeader("x-timestamp", "1732719153")
                .addHeader("x-version", "2.65.0")
                .addHeader("Cookie", "sensorsdata2015jssdkcross=%7B%22distinct_id%22%3A%22118252248855282%22%2C%22first_id%22%3A%22191a968bb6e5c7-04b74f0329161f8-26001e51-1433208-191a968bb6f2550%22%2C%22props%22%3A%7B%22%24latest_traffic_source_type%22%3A%22%E7%9B%B4%E6%8E%A5%E6%B5%81%E9%87%8F%22%2C%22%24latest_search_keyword%22%3A%22%E6%9C%AA%E5%8F%96%E5%88%B0%E5%80%BC_%E7%9B%B4%E6%8E%A5%E6%89%93%E5%BC%80%22%2C%22%24latest_referrer%22%3A%22%22%7D%2C%22identities%22%3A%22eyIkaWRlbnRpdHlfY29va2llX2lkIjoiMTkxYTk2OGJiNmU1YzctMDRiNzRmMDMyOTE2MWY4LTI2MDAxZTUxLTE0MzMyMDgtMTkxYTk2OGJiNmYyNTUwIiwiJGlkZW50aXR5X2xvZ2luX2lkIjoiMTE4MjUyMjQ4ODU1MjgyIn0%3D%22%2C%22history_login_id%22%3A%7B%22name%22%3A%22%24identity_login_id%22%2C%22value%22%3A%22118252248855282%22%7D%2C%22%24device_id%22%3A%22191a968bb6e5c7-04b74f0329161f8-26001e51-1433208-191a968bb6f2550%22%7D; zsxq_access_token=FEDFBADA-9855-A8B8-98D9-864E6BB59513_4E34399C570D1EA8; zsxqsessionid=c206fe1cdc13f3ba2ccb46253b39583e; abtest_env=beta")
                .addHeader("User-Agent", "Apifox/1.0.0 (https://apifox.com)")
                .addHeader("content-type", "application/json")
                .build();

        Response response = client.newCall(request).execute();
        System.out.println(response.body().string());

    }
}
