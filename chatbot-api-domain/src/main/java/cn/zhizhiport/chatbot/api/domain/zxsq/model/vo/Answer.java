package cn.zhizhiport.chatbot.api.domain.zxsq.model.vo;

import java.util.List;

public class Answer{
	private Owner owner;
	private List<ImagesItem> images;
	private String text;

	public Owner getOwner(){
		return owner;
	}

	public List<ImagesItem> getImages(){
		return images;
	}

	public String getText(){
		return text;
	}
}