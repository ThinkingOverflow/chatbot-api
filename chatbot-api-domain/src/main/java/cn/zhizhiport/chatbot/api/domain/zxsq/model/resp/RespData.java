package cn.zhizhiport.chatbot.api.domain.zxsq.model.resp;

import cn.zhizhiport.chatbot.api.domain.zxsq.model.vo.Topic;
import cn.zhizhiport.chatbot.api.domain.zxsq.model.vo.TopicsItem;

import java.util.List;

public class RespData{

	private Topic topic;

	public Topic getTopic(){
		return topic;
	}

	private List<TopicsItem> topics;

	public List<TopicsItem> getTopics(){
		return topics;
	}
}