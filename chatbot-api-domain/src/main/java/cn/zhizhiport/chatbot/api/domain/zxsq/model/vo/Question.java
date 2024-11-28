package cn.zhizhiport.chatbot.api.domain.zxsq.model.vo;

public class Question{
	private Owner owner;
	private boolean expired;
	private Questionee questionee;
	private boolean anonymous;
	private OwnerDetail ownerDetail;
	private String ownerLocation;
	private String text;

	public Owner getOwner(){
		return owner;
	}

	public boolean isExpired(){
		return expired;
	}

	public Questionee getQuestionee(){
		return questionee;
	}

	public boolean isAnonymous(){
		return anonymous;
	}

	public OwnerDetail getOwnerDetail(){
		return ownerDetail;
	}

	public String getOwnerLocation(){
		return ownerLocation;
	}

	public String getText(){
		return text;
	}
}
