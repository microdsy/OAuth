package com.koal.oauth;

/**
 * 连接字符串初始化类
 * @author microdsy
 *
 */
public class OauthConfig {
	
	private OauthConfig(){}
	
	private static OauthConfig oauthConfig = new OauthConfig();
	
	public static OauthConfig getOauthConfig(){
		return oauthConfig;
	}
	
    private String url = "http://192.168.42.170:8080";
    private String connector = "/";
    private String method = "oauth";
    private String token = "token";
    private String getusrinfo = "get_usr_info";
	
    public void setUrl(String url){
    	this.url = url;
    }
    
    public String getUrl(){
    	String str =   url + connector;
    	return str;
    }
    
    public void setMethod(String method){
    	this.method = method;
    }
    
    public String getMethod(){
    	String str = getUrl() + method + connector;
    	return str;
    }
    
    public void setToken(String token){
    	this.token = token;
    }

	public String getToken() {
		return getMethod() + token;
	}
	
    public void setGetusrinfo(String getusrinfo){
    	this.getusrinfo = getusrinfo;
    }

	public  String getGetusrinfo() {
		return getMethod() + getusrinfo;
	}

}
