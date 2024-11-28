package cn.zhizhiport.chatbot.api.domain.zxsq;

import cn.zhizhiport.chatbot.api.domain.zxsq.model.aggregate.Response;
import cn.zhizhiport.chatbot.api.domain.zxsq.model.req.AnswerReq;
import cn.zhizhiport.chatbot.api.domain.zxsq.model.req.SearchReq;

import java.io.IOException;

/**
 * 知识星球 API 接口
 *
 * @author ThinkingOverflow
 * @date 2024/11/25
 */
public interface IZsxqApi {

    Response searchQuestions(SearchReq searchReq) throws IOException;

    boolean answerQuestion(AnswerReq request) throws IOException;
}
