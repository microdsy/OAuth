package com.koal.oauth;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;

/**
 * 负责加载配置文件
 * @author microdsy
 *
 */
public class PropertiesLoader {
	
	/**
	 * 根据传入的文件名加载配置文件
	 * @param name 文件名
	 * @return 加载的文件
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
