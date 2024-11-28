package cn.zhizhiport.chatbot.api.domain.zxsq.model.vo;

import java.util.List;

public class UserSpecific{
	private boolean subscribed;
	private List<Object> likedEmojis;
	private boolean liked;

	public boolean isSubscribed(){
		return subscribed;
	}

	public List<Object> getLikedEmojis(){
		return likedEmojis;
	}

	public boolean isLiked(){
		return liked;
	}
}