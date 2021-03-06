package com.ancaiyun.sms;


import net.sf.json.JSONObject;

/**
 * message/multixsend 是 SUBMAIL 的短信一对多
 *即1条API请求发送多个号码，并可以灵活控制每个联系人的文本变量）和群发 API 。
 * @author submail
 *
 */
public class MessageMultiXSend extends SenderWapper{
	
	protected AppConfig config = null;
	public static final String TO = "to";
	public static final String PROJECT = "project";
	public static final String  Vars= "vars";
	public static final String  MULTI= "multi";
	
    public MessageMultiXSend(AppConfig config) {
		
		this.config = config;
		
	}
	
	public void addTo(String to) {
		requestData.addWithComma(TO, to);;
	}
	
	
	public void addProject(String project) {
		requestData.addWithComma(PROJECT, project);;
	}
	
	public void addVars(String key,String val){
		requestData.addWithJson(Vars,key,val);
	}
	
	public JSONObject getVars(String key1, String val1, String key2, String val2){
		return requestData.getVarJson(key1, val1, key2, val2);
	}
	
	public void addMulti(String toval){

		requestData.addMulti(Vars, TO, toval,MULTI);
	}

	
	@Override
	public ISender getSender() {
		return new Message(this.config);
	}

	public void multixsend(){
		getSender().multixsend(requestData);
	}
	
	
}
	
	
	
	


