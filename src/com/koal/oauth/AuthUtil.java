package com.koal.oauth;

/**
 * 封装了Koal的OAuth服务端调用方法
 * @author microdsy
 *
 */
public class AuthUtil {

	private String token;
	private String getUserInfo;
	private OauthConfig oauthConfig;
	
	/**
	 * 以类文件的方式初始化Config
	 */
	public AuthUtil(){
		oauthConfig = OauthConfig.getOauthConfig();
		token = oauthConfig.getToken();
		getUserInfo = oauthConfig.getGetusrinfo();
	}
		
	/**
	 * 设置config的url
	 * @param url
	 */
	public void setUrl(String url){
		oauthConfig.setUrl(url);
		token = oauthConfig.getToken();
		getUserInfo = oauthConfig.getGetusrinfo();
	}
	
	
	/**
	 * 获取token
	 * 
	 * @param grant_type 返回类型，固定值：authorization_code
	 * @param client_id 应用的APP ID
	 * @param redirect_uri 授权回调地址
	 * @param client_secret 应用的APP KEY 
	 * @param code 获得的授权码
	 * @return token信息。是一段JSON字符串 
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
	 * 获取用户信息
	 * 
	 * @param access_token 用户授权的token
	 * @param client_id 应用的APP ID
	 * @return 一段JSON字符串
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
