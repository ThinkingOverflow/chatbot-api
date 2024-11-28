package cn.zhizhiport.chatbot.api.domain.zxsq.model.resp;

/**
 * 回答问题的结果封装
 *
 * @author ThinkingOverflow
 * @date 2024/11/25
 */
public class AnswerRes {

    private boolean succeeded;

    private boolean isSucceeded(){
        return succeeded;
    }


    public void setSucceeded(boolean succeeded) {
        this.succeeded = succeeded;
    }
}
