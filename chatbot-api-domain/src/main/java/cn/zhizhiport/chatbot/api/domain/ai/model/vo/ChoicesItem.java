package cn.zhizhiport.chatbot.api.domain.ai.model.vo;

public class ChoicesItem{
	private String finishReason;
	private int index;
	private Message message;

	public String getFinishReason(){
		return finishReason;
	}

	public int getIndex(){
		return index;
	}

	public Message getMessage(){
		return message;
	}
}
