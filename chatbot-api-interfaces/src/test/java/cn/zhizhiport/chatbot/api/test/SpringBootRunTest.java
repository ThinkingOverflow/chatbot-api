package cn.zhizhiport.chatbot.api.test;

import cn.zhizhiport.chatbot.api.domain.zxsq.IZsxqApi;
import cn.zhizhiport.chatbot.api.domain.zxsq.model.aggregate.Response;
import cn.zhizhiport.chatbot.api.domain.zxsq.model.req.AnswerReq;
import cn.zhizhiport.chatbot.api.domain.zxsq.model.req.SearchReq;
import cn.zhizhiport.chatbot.api.domain.zxsq.model.resp.RespData;
import cn.zhizhiport.chatbot.api.domain.zxsq.model.vo.TopicsItem;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

/**
 * 功能测试
 *
 * @author ThinkingOverflow
 * @date 2024/11/25
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class SpringBootRunTest {
    @Value("${chatbot-api.groupId}")
    private String groupId;
    @Value("${chatbot-api.cookie}")
    private String cookie;

    @Resource
    private IZsxqApi zsxqApi;

    @Test
    public void test_zsxqApi() throws IOException {
        Response response = zsxqApi.searchQuestions(SearchReq.builder().groupId(groupId).cookie(cookie).build());
        if(Objects.nonNull(response)){
            RespData respData = response.getRespData();
            if(Objects.nonNull(respData)){
                List<TopicsItem> topics = respData.getTopics();
                for (TopicsItem topic : topics) {
                    long topicId = topic.getTopicId();
                    String text = topic.getQuestion().getText();
                    log.info("提问帖子ID:{}，问题内容:{}",topicId,text);

                    AnswerReq.ReqData reqData = AnswerReq.ReqData.builder().text("1111回答").build();


                    boolean answerQuestion = zsxqApi.answerQuestion(AnswerReq.builder().groupId(groupId).cookie(cookie)
                            .topicId(String.valueOf(topicId)).req_data(reqData).build());
                    log.info("回答结果:{}",answerQuestion);
                    break;
                }
            }
        }
    }

}
