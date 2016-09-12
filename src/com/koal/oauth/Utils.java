package com.koal.oauth;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.AbstractHttpEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.params.HttpParams;

/**
 * ���ӷ�����
 * @author microdsy
 *
 */
@SuppressWarnings("deprecation")
public class Utils {	

	private static ThreadSafeClientConnManager cmm = new ThreadSafeClientConnManager();
	
	static {
	    cmm.setMaxTotal(100);
	    cmm.setDefaultMaxPerRoute(50);
	}
	
	private final static String CONTENT_TYPE = "application/x-www-form-urlencoded";
	private final static String ENCODING = "UTF-8";	
	
    /**
     * ��post��ʽ���ʸ�url
     * @param url url�ĵ�ַ�������ʺ�ǰ��Ķ���
     * @param pairs ��������Ĳ�������list��ʽ����
     * @return ���ض�ȡ�Ľ��
     * @throws Exception
     */
    public static String post(String url, List<BasicNameValuePair> pairs) throws Exception {
        String result = null;
        HttpPost httppost = new HttpPost(url);

        HttpEntity entity;
        UrlEncodedFormEntity initEntity = new UrlEncodedFormEntity(pairs, ENCODING);

        initEntity.setContentType(CONTENT_TYPE);

        httppost.setEntity(initEntity);

        HttpParams params = new BasicHttpParams();
        params.setParameter(CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);
        params.setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 3000); // 3000ms
        HttpClient client = new DefaultHttpClient(cmm, params);

        HttpResponse response = client.execute(httppost);


        entity = response.getEntity();

        if (entity != null) {
            InputStream instream = null;
            try {
                instream = entity.getContent();
                //result = IOUtils.toString(instream);
                byte[] bytearr = IOUtils.toByteArray(instream);
                result = new String(bytearr, ENCODING);
            } catch (IOException ex) {
                throw ex;
            } catch (RuntimeException ex) {
                httppost.abort();
                throw ex;
            } finally {
                instream.close();
            }
        }
        return result;
    }
    
    /**
     * �����Ӧ��url������ȡ���ַ�������
     * @param url
     * @return
     * @throws Exception
     */
    public static String get(String url) throws Exception {
        String result = null;
        HttpGet httpget = new HttpGet(url);

        HttpParams params = new BasicHttpParams();
        params.setParameter(CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);
        params.setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 3000); // 3000ms
        
        HttpClient client = new DefaultHttpClient(cmm, params);
        HttpResponse response = client.execute(httpget);

        HttpEntity entity = response.getEntity();

        if (entity != null) {
            InputStream instream = null;
            try {
                instream = entity.getContent();
                //result = IOUtils.toString(instream);
                byte[] bytearr = IOUtils.toByteArray(instream);
                result = new String(bytearr, ENCODING);
            } catch (IOException ex) {
                throw ex;
            } catch (RuntimeException ex) {
                httpget.abort();
                throw ex;
            } finally {
                instream.close();
            }
        }
        return result;
    }	

}
