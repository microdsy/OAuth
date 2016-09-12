package com.koal.oauth;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * 连接字符串初始化类<p>
 * 通过配置文件
 * @author microdsy
 *
 */
public class OauthConfigFile implements Config {

	private OauthConfigFile() {
    }
	
//	private static OauthConfig oauthConfig = new OauthConfig();	
//	public static OauthConfig getOauthConfig(){
//		return oauthConfig; 
//	}

    private static Properties props = new Properties();
    
    static {
        try {

            File f = PropertiesLoader.loadFromFile("oauth.properties");

            props.load(new FileInputStream(f));
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getValue(String key) {
        return props.getProperty(key);
    }

    public static void updateProperties(String key, String value) {
        props.setProperty(key, value);
    }
    
	private String http = OauthConfigFile.getValue("http");
	private String url = OauthConfigFile.getValue("url");
	private String connector = OauthConfigFile.getValue("connector");
	private String method = OauthConfigFile.getValue("method");
	private String token = OauthConfigFile.getValue("token");
	private String getusrinfo = OauthConfigFile.getValue("getusrinfo");	
	
	private static OauthConfigFile oauthConfigFile = new OauthConfigFile();
	
	public static OauthConfigFile getOauthConfigFile(){
		return oauthConfigFile;
	}

	@Override
	public void setUrl(String url) {
		this.url = url;		
	}

	@Override
	public String getUrl() {
    	String str = http + url + connector;
    	return str;
	}

	@Override
	public void setMethod(String method) {
		this.method = method;
	}

	@Override
	public String getMethod() {
    	String str = getUrl() + method + connector;
    	return str;
	}

	@Override
	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public String getToken() {
		return getMethod() + token;
	}

	@Override
	public void setGetusrinfo(String getusrinfo) {
		this.getusrinfo = getusrinfo;
	}

	@Override
	public String getGetusrinfo() {
		return getMethod() + getusrinfo;
	}

}
