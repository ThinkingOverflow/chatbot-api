package cn.zhizhiport.chatbot.api.domain.zxsq.model.vo;

import cn.zhizhiport.chatbot.api.domain.zxsq.model.vo.Answer;

public class Topic{
	private boolean silenced;
	private int readingCount;
	private Question question;
	private boolean answered;
	private String createTime;
	private UserSpecific userSpecific;
	private int rewardsCount;
	private int touristLikesCount;
	private String type;
	private String title;
	private boolean digested;
	private int likesCount;
	private Answer answer;
	private int commentsCount;
	private boolean sticky;
	private long topicId;
	private int readersCount;
	private Group group;

	public boolean isSilenced(){
		return silenced;
	}

	public int getReadingCount(){
		return readingCount;
	}

	public Question getQuestion(){
		return question;
	}

	public boolean isAnswered(){
		return answered;
	}

	public String getCreateTime(){
		return createTime;
	}

	public UserSpecific getUserSpecific(){
		return userSpecific;
	}

	public int getRewardsCount(){
		return rewardsCount;
	}

	public int getTouristLikesCount(){
		return touristLikesCount;
	}

	public String getType(){
		return type;
	}

	public String getTitle(){
		return title;
	}

	public boolean isDigested(){
		return digested;
	}

	public int getLikesCount(){
		return likesCount;
	}

	public Answer getAnswer(){
		return answer;
	}

	public int getCommentsCount(){
		return commentsCount;
	}

	public boolean isSticky(){
		return sticky;
	}

	public long getTopicId(){
		return topicId;
	}

	public int getReadersCount(){
		return readersCount;
	}

	public Group getGroup(){
		return group;
	}
}
