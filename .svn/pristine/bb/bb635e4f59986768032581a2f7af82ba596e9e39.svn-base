package com.xjgc.wind.app.vo;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;

import java.util.List;




/**
 * resultJson数据格式：
 * 
	{
	    "response": " login",
	    "status": {
	        " status ": "1",
	        " msgText ": "登陆成功！"
	    },
	    "results": [
	    	{
		        "userId": "30505",
		        "userSession": "MD5",
		        "username": "张三"
		    },
		    {},
		    {},...
	}

 * 
 */
public class ResponseResults {
	
	//response：所请求的url
	String response;
	//结果状态
	boolean status;
	

	//结果信息
	String msgText;

	List resultsList;

	
	//以结果为入参重写构造函数
	public ResponseResults(String response,List resultsList) {
		super();
		this.response=response;
		this.resultsList=resultsList;
		this.msgText="success!";
		this.status=true;
	}

	public ResponseResults(String jsonStr) {
		super();
		JsonObject jsonObject=null;
		try {

			Gson gson=new Gson();
			jsonObject=gson.fromJson(jsonStr,JsonObject.class);
			this.response=jsonObject.get("response").toString();
			
			JsonObject statusObj=jsonObject.getAsJsonObject("status");
			this.msgText=statusObj.get("msgText").getAsString();
			this.status=statusObj.get("status").getAsBoolean();

			this.resultsList=gson.fromJson(jsonObject.getAsJsonArray("results").toString(),List.class);
		} catch (JsonIOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public String resultsToStr(){
		Gson gson=new Gson();
		String resultStr="";
		resultStr+="{";
		resultStr+="\"response\": \""+this.response+"\",";
		resultStr+="\"status\": {";
			resultStr+= 	"\"status\":\""+this.status+"\",";
			resultStr+= 	"\"msgText\":\""+this.msgText+"\"";
		resultStr+="},";    
		resultStr+="\"results\":";
		resultStr+=gson.toJson(this.resultsList);    	
		resultStr+="}";
		//System.out.println(resultStr);
		return resultStr;
	}
	
	//getters and setters
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}
	
	

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getMsgText() {
		return msgText;
	}

	public void setMsgText(String msgText) {
		this.msgText = msgText;
	}
	
	public List getResultsList() {
		return resultsList;
	}

	public void setResultsList(List resultsList) {
		this.resultsList = resultsList;
	}

}
