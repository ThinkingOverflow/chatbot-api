package cn.zhizhiport.chatbot.api.application.job;

import cn.zhizhiport.chatbot.api.domain.ai.IOpenAI;
import cn.zhizhiport.chatbot.api.domain.zxsq.IZsxqApi;
import cn.zhizhiport.chatbot.api.domain.zxsq.model.aggregate.Response;
import cn.zhizhiport.chatbot.api.domain.zxsq.model.req.AnswerReq;
import cn.zhizhiport.chatbot.api.domain.zxsq.model.req.SearchReq;
import cn.zhizhiport.chatbot.api.domain.zxsq.model.resp.RespData;
import cn.zhizhiport.chatbot.api.domain.zxsq.model.vo.TopicsItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Objects;
import java.util.Random;

/**
 * 定时任务
 *
 * @author ThinkingOverflow
 * @date 2024/12/18
 */
@Slf4j
public class ChatbotTask implements Runnable{

    private IZsxqApi zsxqApi;

    private IOpenAI openAI;

    private String groupId;

    private String cookie;

    private String apiKey;

    private String groupName;

    public ChatbotTask(String groupName, String groupId, String cookie, String apiKey, IZsxqApi zsxqApi, IOpenAI openAI) {
        this.groupName = groupName;
        this.groupId = groupId;
        this.cookie = cookie;
        this.apiKey = apiKey;
        this.zsxqApi = zsxqApi;
        this.openAI = openAI;
    }
    @Override
    public void run() {
        try {
            if(new Random().nextBoolean()){
                log.info("{} 随机打烊中...", groupName);
                return;
            }

            GregorianCalendar calendar = new GregorianCalendar();
            int hour = calendar.get(GregorianCalendar.HOUR_OF_DAY);
            if (hour > 24 || hour < 7) {
                log.info("{} 打烊时间不工作，AI 下班了！", groupName);
                return;
            }

            log.info("开始扫描....");
            //1、扫描需要回答的问题
            Response questions = zsxqApi.searchQuestions(SearchReq.builder().groupId(groupId).cookie(cookie).build());
            if(Objects.nonNull(questions)){
                RespData respData = questions.getRespData();
                if(Objects.nonNull(respData)){
                    List<TopicsItem> topics = respData.getTopics();
                    if(CollectionUtils.isEmpty(topics)){
                        log.info("{} 本次检索未查询到待会答问题", groupName);
                        return;
                    }

                    for (TopicsItem topic : topics) {
                        long topicId = topic.getTopicId();
                        String text = topic.getQuestion().getText().trim();
                        log.info("提问帖子ID:{}，问题内容:{}",topicId,text);

                        //2、调用AI进行答案查询
                        String answer = openAI.doChatGLM(text,apiKey);
                        log.info("AI回答内容成功");

                        AnswerReq.ReqData reqData = AnswerReq.ReqData.builder().text(answer).build();

                        // 使用RetryAnswerQuestion类进行重试
                        boolean answerResult = retryAnswerQuestion(groupId, cookie, String.valueOf(topicId), reqData);
                        log.info("回答结果:{}",answerResult);
                        break;//一次只回答一个，避免被风控
                    }
                }
            }
        } catch (Exception e) {
            log.error("{} 自动回答问题异常", e);
        }
    }


    public boolean retryAnswerQuestion(String groupId, String cookie, String topicId, AnswerReq.ReqData reqData) throws IOException {
        int maxRetries = 5;
        int retryCount = 0;
        boolean result;

        do {
            result = zsxqApi.answerQuestion(AnswerReq.builder().groupId(groupId).cookie(cookie)
                    .topicId(topicId).req_data(reqData).build());
            if (!result) {
                log.warn("回答失败，执行重试 (重试次数: {})", retryCount + 1);
                retryCount++;
            }
        } while (!result && retryCount < maxRetries);

        return result;
    }
}
