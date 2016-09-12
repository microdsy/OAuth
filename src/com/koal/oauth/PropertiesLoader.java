package com.koal.oauth;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;

/**
 * ������������ļ�
 * @author microdsy
 *
 */
public class PropertiesLoader {
	
	/**
	 * ���ݴ�����ļ������������ļ�
	 * @param name �ļ���
	 * @return ���ص��ļ�
	 */
    public static File loadFromFile(String name) {

        File f = null;
        
        try {
        	
            Enumeration<URL> urls = Thread.currentThread().getContextClassLoader().getResources(name);

            while (urls.hasMoreElements()) {
            	
                URL u = urls.nextElement();
                File tmpFile = new File(u.getPath());
                if (tmpFile.isFile()) {
                    f = tmpFile;
                    break;
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return f;
    }
}
