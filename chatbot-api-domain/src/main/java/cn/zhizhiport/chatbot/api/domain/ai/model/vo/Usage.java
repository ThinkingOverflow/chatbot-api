package cn.zhizhiport.chatbot.api.domain.ai.model.vo;

public class Usage{
	private int completionTokens;
	private int promptTokens;
	private int totalTokens;

	public int getCompletionTokens(){
		return completionTokens;
	}

	public int getPromptTokens(){
		return promptTokens;
	}

	public int getTotalTokens(){
		return totalTokens;
	}
}
