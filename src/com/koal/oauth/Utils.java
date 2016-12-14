package com.koal.oauth;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/**
 * 连接访问类
 * @author microdsy
 *
 */
public class Utils {	

	private final static String ENCODING = "UTF-8";	
	  /** 
     * 忽视证书HostName 
     */  
    private static HostnameVerifier ignoreHostnameVerifier = new HostnameVerifier() {
		@Override
		public boolean verify(String arg0, SSLSession arg1) {
			return true;
		}
	};
	
	  /** 
     * Ignore Certification 
     */  
    private static TrustManager ignoreCertificationTrustManger = new X509TrustManager() {

		@Override
		public void checkClientTrusted(X509Certificate[] arg0, String arg1)
				throws CertificateException {
			
		}
		@Override
		public void checkServerTrusted(X509Certificate[] arg0, String arg1)
				throws CertificateException {
			
		}
		@Override
		public X509Certificate[] getAcceptedIssuers() {
			return null;
		}  
    };  
  
    public static String sendUrlRequest(String urlStr, String content, String requestMethod) throws Exception{
        HttpsURLConnection.setDefaultHostnameVerifier(ignoreHostnameVerifier); 

    	HttpURLConnection connection = null;
    	String tempStr = null;
    	try{
    		URL url = new URL(urlStr);
    		
    		//创建url连接，提交到数据，获取返回结果
    		//打开连接
    		connection = (HttpURLConnection)url.openConnection();
    		//设置提交方式，默认是GET方式
    		connection.setRequestMethod(requestMethod);
    		//设置是否向connection输出，因为这个是post请求，参数要放在http正文内，因此需要设置为true
    		connection.setDoOutput(true);
    		connection.setDoInput(true);
    		//POST请求不能使用缓存
    		connection.setUseCaches(false);
    		
    		connection.setRequestProperty("User-Agent", "directclient");
    		// 配置本次连接的Content-type，配置为application/x-www-form-urlencoded的意思是
    		// 正文是urlencoded编码过的form参数，下面我们可以看到我们对正文内容使用URLEncoder.encode
            // 进行编码
    		connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
    		
    		
            if(connection instanceof HttpsURLConnection){
            	

       		 // Prepare SSL Context  
               TrustManager[] tm = { ignoreCertificationTrustManger };  
               SSLContext sslContext = SSLContext.getInstance("SSL");  
               sslContext.init(null, tm, new java.security.SecureRandom());  
     
     
               // 从上述SSLContext对象中得到SSLSocketFactory对象  
               SSLSocketFactory ssf = sslContext.getSocketFactory();  
            ((HttpsURLConnection)connection).setSSLSocketFactory(ssf);
            }
            
            
    		DataOutputStream out = new DataOutputStream(connection.getOutputStream());
    		out.writeBytes(content);
    		out.flush();
    		out.close();
    		
    		
    		StringBuffer bankXmlBuffer = new StringBuffer();
    		BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), ENCODING));
    		String inputLine;
    		while((inputLine = in.readLine()) != null){
    			bankXmlBuffer.append(inputLine);
    		}
    		in.close();
    		
    		tempStr = bankXmlBuffer.toString();
    		
    	} catch(Exception e){
    		System.out.println("发送请求出现异常！");
    		e.printStackTrace();
    	} finally{
    		if(connection != null){
    			connection.disconnect();
    		}
    	}
    	return tempStr;
    }

}
