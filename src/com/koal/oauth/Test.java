package com.koal.oauth;

/**
 * ������
 * @author microdsy
 *
 */
public class Test {
	
	public static void main(String[] args){
//		Config config = OauthConfigFile.getOauthConfigFile();
//		Config config = OauthConfig.getOauthConfig(); //��ȡconfig����
//		config.setUrl("192.168.42.170:8080");
//		config.setUrl("www.zyjyzx.com"); //���ö�Ӧ��url������Ŀ��XXͳһ��֤����URL
//		AuthUtil oAuthController = new AuthUtil(config); //ͨ����configʵ����AuthUtil����
//		
//		String result_token = AuthUtil.getUserInfo("authorization_code", "mail", "userapp", "http://127.0.0.1", "126", "987", "ST-3-a94ubZirsEqNxTNHZH1O-mail.test.com"); //��ȡtoken
//		System.out.println(result_token);
//		{"ret":"1","msg":"custom_msg","access_token":"TGT-2-CRxjyEf1hkH5LvqybBg5cP0OuFAW11HvDlbMZoGfboHhIA4Zq0-mail.test.com","token_type":"custom_token_type","refresh_token":"TGT-2-CRxjyEf1hkH5LvqybBg5cP0OuFAW11HvDlbMZoGfboHhIA4Zq0-mail.test.com","expire_in":"7135","scope":"custom_scope"}

//		AuthUtil a = new AuthUtil(config);
//		String result_usrinfo = a.getUserInfo("TGT-2-CRxjyEf1hkH5LvqybBg5cP0OuFAW11HvDlbMZoGfboHhIA4Zq0-mail.test.com", "mail"); //��ȡusrinfo
//		System.out.println(result_usrinfo);
//		
//		Config of = OauthConfigFile.getOauthConfigFile();
//		System.out.println(of.getUrl());
//		of.setUrl("test");
//		System.out.println(of.getUrl());
		
		
		AuthUtil oAuthController = new AuthUtil(); //ͨ����configʵ����AuthUtil����
		oAuthController.setUrl("192.168.42.170:8080");
//		System.out.println(oAuthController.getUrl());
		String result_token = oAuthController.getToken("authorization_code", "mail", "userapp", "http://127.0.0.1", "126", "987", "ST-19-CrIyQWbsmNZUfYz0Fokm-mail.test.com"); //��ȡtoken
		System.out.println(result_token);
		
	} 

}
