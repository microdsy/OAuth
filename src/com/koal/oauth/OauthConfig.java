package com.koal.oauth;

/**
 * 连接字符串初始化类
 * @author microdsy
 *
 */
public class OauthConfig implements Config {
	
	private OauthConfig(){}
	
	private static OauthConfig oauthConfig = new OauthConfig();
	
	public static OauthConfig getOauthConfig(){
		return oauthConfig;
	}
	
    private String http = "http://";
    private String url = "192.168.42.170:8080";
    private String connector = "/";
    private String method = "oauth";
    private String token = "token";
    private String getusrinfo = "get_usr_info";
	
    @Override
    public void setUrl(String url){
    	this.url = url;
    }
    
    @Override
    public String getUrl(){
    	String str = http + url + connector;
    	return str;
    }
    
    @Override
    public void setMethod(String method){
    	this.method = method;
    }
    
    @Override
    public String getMethod(){
    	String str = getUrl() + method + connector;
    	return str;
    }
    
    @Override
    public void setToken(String token){
    	this.token = token;
    }

    @Override
	public String getToken() {
		return getMethod() + token;
	}
	
    @Override
    public void setGetusrinfo(String getusrinfo){
    	this.getusrinfo = getusrinfo;
    }

    @Override
	public  String getGetusrinfo() {
		return getMethod() + getusrinfo;
	}

}
