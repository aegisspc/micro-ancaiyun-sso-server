package com.ancaiyun.sms;



public class MessageSendDemo {
	public static void main(String[] args) {
		AppConfig config = ConfigLoader.load(ConfigLoader.ConfigType.Message);
		MessageSend submail = new MessageSend(config);
		submail.addTo("176****9");
		submail.addContent("【test2】你好，你的验证码是3373");
		submail.send();
	}

}
