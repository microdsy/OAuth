package com.koal.oauth;

/**
 * ����������Ϣ�Ľӿ�
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
