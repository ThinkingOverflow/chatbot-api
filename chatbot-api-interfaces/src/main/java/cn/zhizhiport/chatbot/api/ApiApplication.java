package cn.zhizhiport.chatbot.api;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 项目启动类
 *
 * @author ThinkingOverflow
 * @date 2024/11/21
 */
@SpringBootApplication
@EnableScheduling
@Configurable
public class ApiApplication {
     public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);
    }
}
