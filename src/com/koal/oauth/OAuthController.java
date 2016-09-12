package com.koal.oauth;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.message.BasicNameValuePair;

import com.google.gson.Gson;

/**
 * 封装了Koal的OAuth服务端调用方法
 * @author microdsy
 *
 */
public class OAuthController {

	private String TOKEN;
	private String GETUSRINFO;
	private Config config;
	
	/**
	 * 以类文件的方式初始化Config
	 */
	public OAuthController(){
		config = OauthConfig.getOauthConfig();
		TOKEN = config.getToken();
		GETUSRINFO = config.getGetusrinfo();
	}
	
	/**
	 * 自定义传入Config的方式
	 * @param config
	 */
	public OAuthController(Config config){
		this.config = config;
		TOKEN = this.config.getToken();
		GETUSRINFO = this.config.getGetusrinfo();
	}
	
	/**
	 * 设置config的url
	 * @param url
	 */
	public void setUrl(String url){
		config.setUrl(url);
		TOKEN = config.getToken();
		GETUSRINFO = config.getGetusrinfo();
	}
	
	public String getUrl(){
		return config.getToken();
	}
	
	
	/**
	 * 获取token
	 * 
	 * @param grant_type 返回类型，固定值：authorization_code
	 * @param client_id 应用的APP ID
	 * @param scope 请求授权的权限 如 user app
	 * @param redirect_uri 授权回调地址
	 * @param state 获得的随机数
	 * @param client_secret 应用的APP KEY 
	 * @param code 获得的授权码
	 * @return token信息。是一段JSON字符串，ret= &msg= &access_token= &token_type= &refresh_token= &expire_in= &scope= 
	 */
	public String getUserInfo(String grant_type, String client_id,String scope,String redirect_uri,String state,String client_secret,String code){
		String token_url = TOKEN;
		List<BasicNameValuePair> pairs = new ArrayList<BasicNameValuePair>();
		pairs.add(new BasicNameValuePair("grant_type", grant_type));
		pairs.add(new BasicNameValuePair("client_id", client_id));
		pairs.add(new BasicNameValuePair("scope", scope));
		pairs.add(new BasicNameValuePair("redirect_uri", redirect_uri));
		pairs.add(new BasicNameValuePair("state", state));
		pairs.add(new BasicNameValuePair("client_secret", client_secret));
		pairs.add(new BasicNameValuePair("code", code));
		String json = "";
		try{
			json = Utils.post(token_url, pairs);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return json;
	}
	
	/**
	 * 获取用户信息
	 * 
	 * @param access_token 用户授权的token
	 * @param client_id 应用的APP ID
	 * @return 一段JSON字符串，ret= &msg= &certvalue= &login_account= &nick_name=
	 */
	public String getUserInfo(String access_token,String client_id){
		String getUserInfo_url = GETUSRINFO;
		List<BasicNameValuePair> pairs = new ArrayList<BasicNameValuePair>();
		pairs.add(new BasicNameValuePair("access_token", access_token));
		pairs.add(new BasicNameValuePair("client_id", client_id));
		String json = "";
		try{
			json = Utils.post(getUserInfo_url, pairs);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return json;
	}

}
