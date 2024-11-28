package cn.zhizhiport.chatbot.api.domain.zxsq.model.aggregate;

import cn.zhizhiport.chatbot.api.domain.zxsq.model.resp.RespData;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Response{
	@JsonProperty("resp_data")
	private RespData respData;
	private boolean succeeded;

	public RespData getRespData(){
		return respData;
	}

	public boolean isSucceeded(){
		return succeeded;
	}
}
