package cn.zhizhiport.chatbot.api.domain.ai.service;

import cn.zhizhiport.chatbot.api.domain.ai.IOpenAI;
import cn.zhizhiport.chatbot.api.domain.ai.model.aggregate.AIAnswer;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * 类描述
 *
 * @author ThinkingOverflow
 * @date 2024/12/2
 */
@Service
public class OpenAI implements IOpenAI {

    @Value("${chatglm.api-key}")
    private String apiKey;

    @Override
    public String doChatGLM(String question) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS).build();

        MediaType contentType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(contentType, "{\"model\": \"glm-4\"," +
                "\"messages\": [" + "{" + "\"role\": \"user\"," +
                " \"content\": \"" + question + "\"" + " } " + "]}");

        Request request = new Request.Builder()
                .url("https://open.bigmodel.cn/api/paas/v4/chat/completions")
                .method("POST", body)
                .addHeader("Authorization", "Bearer "+apiKey)
                .addHeader("User-Agent", "Apifox/1.0.0 (https://apifox.com)")
                .addHeader("Content-Type", "application/json")
                .build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
        } catch (IOException e) {
            throw new RuntimeException("调用 ChatGLM 接口出错:{}" + e.getMessage());
        }

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);

        try {
            AIAnswer aiAnswer = objectMapper.readValue(response.body().string(), AIAnswer.class);
            StringBuilder results = new StringBuilder();
            aiAnswer.getChoices().forEach(choice -> {
                results.append(choice.getMessage().getContent());
            });
            return results.toString();
        } catch (Exception e) {
            throw new RuntimeException("AI回答解析出错:{}" + e.getMessage());
        }
    }
}
