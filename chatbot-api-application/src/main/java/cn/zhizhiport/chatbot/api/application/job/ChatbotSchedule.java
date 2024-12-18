//package cn.zhizhiport.chatbot.api.application.job;
//
//import cn.zhizhiport.chatbot.api.domain.ai.IOpenAI;
//import cn.zhizhiport.chatbot.api.domain.zxsq.IZsxqApi;
//import cn.zhizhiport.chatbot.api.domain.zxsq.model.aggregate.Response;
//import cn.zhizhiport.chatbot.api.domain.zxsq.model.req.AnswerReq;
//import cn.zhizhiport.chatbot.api.domain.zxsq.model.req.SearchReq;
//import cn.zhizhiport.chatbot.api.domain.zxsq.model.resp.RespData;
//import cn.zhizhiport.chatbot.api.domain.zxsq.model.vo.TopicsItem;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.scheduling.annotation.EnableScheduling;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//import org.springframework.util.CollectionUtils;
//
//import javax.annotation.Resource;
//import java.io.IOException;
//import java.util.GregorianCalendar;
//import java.util.List;
//import java.util.Objects;
//import java.util.Random;
//
///**
// * 问题任务
// *
// * @author ThinkingOverflow
// * @date 2024/12/2
// */
//@Slf4j
//@Component
//public class ChatbotSchedule {
//
//    @Resource
//    private IZsxqApi zsxqApi;
//
//    @Resource
//    private IOpenAI openAI;
//
//    @Value("${chatbot-api.groupId}")
//    private String groupId;
//
//    @Value("${chatbot-api.cookie}")
//    private String cookie;
//
//
////    @Scheduled(cron = "0 * * * * ?")
//    public void run() throws IOException {
//
//        if(new Random().nextBoolean()){
//            log.info("随机打烊....");
//            return;
//        }
//
//        GregorianCalendar calendar = new GregorianCalendar();
//        int hour = calendar.get(GregorianCalendar.HOUR_OF_DAY);
//        if (hour > 24 || hour < 7) {
//            log.info("打烊时间不工作，AI 下班了！");
//            return;
//        }
//
//        log.info("开始扫描....");
//        //1、扫描需要回答的问题
//        Response questions = zsxqApi.searchQuestions(SearchReq.builder().groupId(groupId).cookie(cookie).build());
//        if(Objects.nonNull(questions)){
//            RespData respData = questions.getRespData();
//            if(Objects.nonNull(respData)){
//                List<TopicsItem> topics = respData.getTopics();
//                if(CollectionUtils.isEmpty(topics)){
//                    log.info("没有需要回答的问题");
//                    return;
//                }
//
//                for (TopicsItem topic : topics) {
//                    long topicId = topic.getTopicId();
//                    String text = topic.getQuestion().getText().trim();
//                    log.info("提问帖子ID:{}，问题内容:{}",topicId,text);
//
//                    //2、调用AI进行答案查询
////                    String answer = openAI.doChatGLM(text);
//                    log.info("AI回答内容成功");
//
//                    AnswerReq.ReqData reqData = AnswerReq.ReqData.builder().text("answer").build();
//
//                    // 使用RetryAnswerQuestion类进行重试
//                    boolean answerResult = retryAnswerQuestion(groupId, cookie, String.valueOf(topicId), reqData);
//                    log.info("回答结果:{}",answerResult);
//                    break;//一次只回答一个，避免被风控
//                }
//            }
//        }
//    }
//
//    public boolean retryAnswerQuestion(String groupId, String cookie, String topicId, AnswerReq.ReqData reqData) throws IOException {
//        int maxRetries = 5;
//        int retryCount = 0;
//        boolean result;
//
//        do {
//            result = zsxqApi.answerQuestion(AnswerReq.builder().groupId(groupId).cookie(cookie)
//                    .topicId(topicId).req_data(reqData).build());
//            if (!result) {
//                log.warn("回答失败，执行重试 (重试次数: {})", retryCount + 1);
//                retryCount++;
//            }
//        } while (!result && retryCount < maxRetries);
//
//        return result;
//    }
//}
