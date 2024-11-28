package cn.zhizhiport.chatbot.api.domain.zxsq.model.req;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 回答问题接口的请求信息封装
 *
 * @author ThinkingOverflow
 * @date 2024/11/25
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AnswerReq {

    // 用于封装请求体数据结构
    private ReqData req_data;

    // 用于封装请求头信息
    private String groupId;
    private String cookie;
    private String topicId;

    // 内部类，对应请求体中的具体数据结构
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class ReqData {
        private String text;
        private String[] image_ids;
    }
}
