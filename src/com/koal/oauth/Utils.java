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
 * ���ӷ�����
 * @author microdsy
 *
 */
public class Utils {	

	private final static String ENCODING = "UTF-8";	
	  /** 
     * ����֤��HostName 
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
    		
    		//����url���ӣ��ύ�����ݣ���ȡ���ؽ��
    		//������
    		connection = (HttpURLConnection)url.openConnection();
    		//�����ύ��ʽ��Ĭ����GET��ʽ
    		connection.setRequestMethod(requestMethod);
    		//�����Ƿ���connection�������Ϊ�����post���󣬲���Ҫ����http�����ڣ������Ҫ����Ϊtrue
    		connection.setDoOutput(true);
    		connection.setDoInput(true);
    		//POST������ʹ�û���
    		connection.setUseCaches(false);
    		
    		connection.setRequestProperty("User-Agent", "directclient");
    		// ���ñ������ӵ�Content-type������Ϊapplication/x-www-form-urlencoded����˼��
    		// ������urlencoded�������form�������������ǿ��Կ������Ƕ���������ʹ��URLEncoder.encode
            // ���б���
    		connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
    		
    		
            if(connection instanceof HttpsURLConnection){
            	

       		 // Prepare SSL Context  
               TrustManager[] tm = { ignoreCertificationTrustManger };  
               SSLContext sslContext = SSLContext.getInstance("SSL");  
               sslContext.init(null, tm, new java.security.SecureRandom());  
     
     
               // ������SSLContext�����еõ�SSLSocketFactory����  
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
    		System.out.println("������������쳣��");
    		e.printStackTrace();
    	} finally{
    		if(connection != null){
    			connection.disconnect();
    		}
    	}
    	return tempStr;
    }

}
