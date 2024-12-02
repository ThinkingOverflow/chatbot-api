package cn.zhizhiport.chatbot.api.domain.ai;

import java.io.IOException;

/**
 * 类描述
 *
 * @author ThinkingOverflow
 * @date 2024/12/2
 */
public interface IOpenAI {

    String doChatGLM(String question) throws IOException;
}
