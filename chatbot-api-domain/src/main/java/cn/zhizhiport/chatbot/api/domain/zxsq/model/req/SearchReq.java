package cn.zhizhiport.chatbot.api.domain.zxsq.model.req;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 搜索帖子接口请求信息封装
 *
 * @author ThinkingOverflow
 * @date 2024/11/25
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SearchReq {
    private String groupId;
    private String cookie;
}
