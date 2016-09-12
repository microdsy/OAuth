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
	
	public OAuthController(Config config){
		TOKEN = config.getToken();
		GETUSRINFO = config.getGetusrinfo();
	}

	private String TOKEN;
	private String GETUSRINFO;
	
	
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
//		AccessToken accessToken = new AccessToken();
		String json = "";
		try{
			json = Utils.post(token_url, pairs);
//			Gson gson = new Gson();
//			accessToken = gson.fromJson(json, AccessToken.class);
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
//		UserInfo info = null;
		String json = "";
		try{
			json = Utils.post(getUserInfo_url, pairs);
//			json = Utils.get(GETUSRINFO + "?access_token=" + access_token + "&client_id=" + client_id );
//          Gson gson = new Gson();
//          info = gson.fromJson(json, UserInfo.class);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return json;
	}

}
