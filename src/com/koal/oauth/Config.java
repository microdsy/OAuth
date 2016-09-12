package com.koal.oauth;

/**
 * 管理配置信息的接口
 * @author microdsy
 *
 */
public interface Config {
	    
    public void setUrl(String url);
    
    public String getUrl();
    
    public void setMethod(String method);
    
    public String getMethod();
    
    public void setToken(String token);

	public String getToken();
	
    public void setGetusrinfo(String getusrinfo);

	public String getGetusrinfo();

}
