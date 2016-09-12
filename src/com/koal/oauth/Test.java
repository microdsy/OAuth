package com.koal.oauth;

/**
 * ������
 * @author microdsy
 *
 */
public class Test {
	
	public static void main(String[] args){
		Config config = OauthConfigFile.getOauthConfigFile();
		
//		Config config = OauthConfig.getOauthConfig(); //��ȡconfig����
		config.setUrl("www.zyjyzx.com"); //���ö�Ӧ��url������Ŀ��XXͳһ��֤����URL
		OAuthController oAuthController = new OAuthController(config); //ͨ����configʵ����OAuthController����
		String result_token = oAuthController.getUserInfo("authorization_code", "mail", "userapp", "http://127.0.0.1", "126", "987", "ST-6-S7DjXhkmVauSMkiU4GOK-mail.test.com"); //��ȡtoken
//		{"ret":"1","msg":"custom_msg","access_token":"TGT-2-CRxjyEf1hkH5LvqybBg5cP0OuFAW11HvDlbMZoGfboHhIA4Zq0-mail.test.com","token_type":"custom_token_type","refresh_token":"TGT-2-CRxjyEf1hkH5LvqybBg5cP0OuFAW11HvDlbMZoGfboHhIA4Zq0-mail.test.com","expire_in":"7135","scope":"custom_scope"}

		OAuthController a = new OAuthController(config);
		String result_usrinfo = a.getUserInfo("TGT-2-CRxjyEf1hkH5LvqybBg5cP0OuFAW11HvDlbMZoGfboHhIA4Zq0-mail.test.com", "mail"); //��ȡusrinfo
		System.out.println(result_usrinfo);
		
		Config of = OauthConfigFile.getOauthConfigFile();
		System.out.println(of.getUrl());
		of.setUrl("test");
		System.out.println(of.getUrl());
		
	} 

}
