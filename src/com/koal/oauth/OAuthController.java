package com.koal.oauth;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.message.BasicNameValuePair;

import com.google.gson.Gson;

/**
 * ��װ��Koal��OAuth����˵��÷���
 * @author microdsy
 *
 */
public class OAuthController {

	private String TOKEN;
	private String GETUSRINFO;
	private Config config;
	
	/**
	 * �����ļ��ķ�ʽ��ʼ��Config
	 */
	public OAuthController(){
		config = OauthConfig.getOauthConfig();
		TOKEN = config.getToken();
		GETUSRINFO = config.getGetusrinfo();
	}
	
	/**
	 * �Զ��崫��Config�ķ�ʽ
	 * @param config
	 */
	public OAuthController(Config config){
		this.config = config;
		TOKEN = this.config.getToken();
		GETUSRINFO = this.config.getGetusrinfo();
	}
	
	/**
	 * ����config��url
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
	 * ��ȡtoken
	 * 
	 * @param grant_type �������ͣ��̶�ֵ��authorization_code
	 * @param client_id Ӧ�õ�APP ID
	 * @param scope ������Ȩ��Ȩ�� �� user app
	 * @param redirect_uri ��Ȩ�ص���ַ
	 * @param state ��õ������
	 * @param client_secret Ӧ�õ�APP KEY 
	 * @param code ��õ���Ȩ��
	 * @return token��Ϣ����һ��JSON�ַ�����ret= &msg= &access_token= &token_type= &refresh_token= &expire_in= &scope= 
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
	 * ��ȡ�û���Ϣ
	 * 
	 * @param access_token �û���Ȩ��token
	 * @param client_id Ӧ�õ�APP ID
	 * @return һ��JSON�ַ�����ret= &msg= &certvalue= &login_account= &nick_name=
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
