package com.koal.oauth;

/**
 * ��װ��Koal��OAuth����˵��÷���
 * @author microdsy
 *
 */
public class AuthUtil {

	private String token;
	private String getUserInfo;
	private OauthConfig oauthConfig;
	
	/**
	 * �����ļ��ķ�ʽ��ʼ��Config
	 */
	public AuthUtil(){
		oauthConfig = OauthConfig.getOauthConfig();
		token = oauthConfig.getToken();
		getUserInfo = oauthConfig.getGetusrinfo();
	}
		
	/**
	 * ����config��url
	 * @param url
	 */
	public void setUrl(String url){
		oauthConfig.setUrl(url);
		token = oauthConfig.getToken();
		getUserInfo = oauthConfig.getGetusrinfo();
	}
	
	
	/**
	 * ��ȡtoken
	 * 
	 * @param grant_type �������ͣ��̶�ֵ��authorization_code
	 * @param client_id Ӧ�õ�APP ID
	 * @param redirect_uri ��Ȩ�ص���ַ
	 * @param client_secret Ӧ�õ�APP KEY 
	 * @param code ��õ���Ȩ��
	 * @return token��Ϣ����һ��JSON�ַ��� 
	 */
	public String getToken(String grant_type, String client_id, String redirect_uri, String client_secret, String code){
		String content = "grant_type=" + grant_type 
				+ "&client_id=" + client_id 
				+ "&redirect_uri=" + redirect_uri 
				+ "&client_secret=" + client_secret 
				+ "&code=" + code ;
		String json = null;
		try {
			json = Utils.sendUrlRequest(token, content, "POST");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}
	
	/**
	 * ��ȡ�û���Ϣ
	 * 
	 * @param access_token �û���Ȩ��token
	 * @param client_id Ӧ�õ�APP ID
	 * @return һ��JSON�ַ���
	 */
	public String getUserInfo(String access_token,String client_id){
		String content = "access_token=" + access_token 
				+ "&client_id=" + client_id ;
		String json = null;
		try {
			json = Utils.sendUrlRequest(getUserInfo, content, "POST");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return json;
	}

}
