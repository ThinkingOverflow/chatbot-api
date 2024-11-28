package cn.zhizhiport.chatbot.api.domain.zxsq.model.vo;

public class ImagesItem{
	private Thumbnail thumbnail;
	private Original original;
	private Large large;
	private long imageId;
	private String type;

	public Thumbnail getThumbnail(){
		return thumbnail;
	}

	public Original getOriginal(){
		return original;
	}

	public Large getLarge(){
		return large;
	}

	public long getImageId(){
		return imageId;
	}

	public String getType(){
		return type;
	}
}
