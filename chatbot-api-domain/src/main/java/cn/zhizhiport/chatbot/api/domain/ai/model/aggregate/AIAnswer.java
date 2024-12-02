package cn.zhizhiport.chatbot.api.domain.ai.model.aggregate;

import cn.zhizhiport.chatbot.api.domain.ai.model.vo.ChoicesItem;
import cn.zhizhiport.chatbot.api.domain.ai.model.vo.Usage;

import java.util.List;

public class AIAnswer {
	private int created;
	private Usage usage;
	private String model;
	private String id;
	private List<ChoicesItem> choices;
	private String requestId;

	public int getCreated(){
		return created;
	}

	public Usage getUsage(){
		return usage;
	}

	public String getModel(){
		return model;
	}

	public String getId(){
		return id;
	}

	public List<ChoicesItem> getChoices(){
		return choices;
	}

	public String getRequestId(){
		return requestId;
	}
}