package com.xjgc.wind.app.vo;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;

import java.util.List;
import java.util.Map;




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
public class ResponseResults_Map {
	
	//response：所请求的url
	String response;
	//结果状态
	boolean status;
	

	//结果信息
	String msgText;

	Map resultsMap;

	
	//以结果为入参重写构造函数
	public ResponseResults_Map(String response,Map resultsMap) {
		super();
		this.response=response;
		this.resultsMap=resultsMap;
		this.msgText="success!";
		this.status=true;
	}

	public ResponseResults_Map(String jsonStr) {
		super();
		JsonObject jsonObject=null;
		try {

			Gson gson=new Gson();
			jsonObject=gson.fromJson(jsonStr,JsonObject.class);
			this.response=jsonObject.get("response").toString();
			
			JsonObject statusObj=jsonObject.getAsJsonObject("status");
			this.msgText=statusObj.get("msgText").getAsString();
			this.status=statusObj.get("status").getAsBoolean();

			this.resultsMap=gson.fromJson(jsonObject.getAsJsonObject("results").toString(),Map.class);
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
		resultStr+=gson.toJson(this.resultsMap);    	
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

	public Map getResultsMap() {
		return resultsMap;
	}

	public void setResultsMap(Map resultsMap) {
		this.resultsMap = resultsMap;
	}
	
	

}
