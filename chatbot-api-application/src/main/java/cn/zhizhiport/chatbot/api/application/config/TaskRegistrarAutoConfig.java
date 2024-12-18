package cn.zhizhiport.chatbot.api.application.config;

import cn.zhizhiport.chatbot.api.application.common.PropertyUtil;
import cn.zhizhiport.chatbot.api.application.job.ChatbotTask;
import cn.zhizhiport.chatbot.api.domain.ai.service.OpenAI;
import cn.zhizhiport.chatbot.api.domain.zxsq.service.ZsxqApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * 任务注册服务，支持多组任务配置
 *
 * @author ThinkingOverflow
 * @date 2024/12/18
 */
@Configuration
@EnableScheduling
@Slf4j
public class TaskRegistrarAutoConfig implements EnvironmentAware, SchedulingConfigurer {

    private Map<String, Map<String, Object>> taskGroupMap = new HashMap<>();


    @Override
    public void setEnvironment(Environment environment) {
        String prefix = "chatbot-api.";
        String launchListStr = environment.getProperty(prefix + "launchList");
        if(Objects.isNull(launchListStr)){
            return;
        }
        for (String groupKey : launchListStr.split(",")) {
            Map<String, Object> taskGroupProps = PropertyUtil.handle(environment, prefix + groupKey, Map.class);
            taskGroupMap.put(groupKey, taskGroupProps);
        }
    }

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegister) {
        Set<String> taskGroups = taskGroupMap.keySet();
        for (String groupKey : taskGroups) {
            Map<String, Object> taskGroup = taskGroupMap.get(groupKey);
            String groupName = taskGroup.get("groupName").toString();
            String groupId = taskGroup.get("groupId").toString();
            String cookie = taskGroup.get("cookie").toString();
            String apiKey = taskGroup.get("apiKey").toString();
            String cronExpression = taskGroup.get("cronExpression").toString();
//            String cronExpression = new String(Base64.getDecoder().decode(cronExpressionBase64), StandardCharsets.UTF_8);
            log.info("创建任务 groupName：{} groupId：{} cronExpression：{}", groupName, groupId, cronExpression);
            taskRegister.addCronTask(new ChatbotTask(groupName, groupId, cookie, apiKey, new ZsxqApi(), new OpenAI()), cronExpression);
        }

    }
}
