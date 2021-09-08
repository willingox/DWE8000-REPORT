package com.xjgc.wind.app.util;

import java.io.FileInputStream;
import java.net.URLDecoder;
import java.util.Properties;


public class SqlUtil {

	//数据库名
	public static String dbName;
	static{
		Properties p = new Properties();
	    try{
		  String path = SqlUtil.class.getResource("/").getPath();//得到工程名WEB-INF/classes/路径
		  path=path.substring(1, path.indexOf("classes"));//从路径字符串中取出工程路径
		  
		  //将获得的url编码的目录中的%20字符转换成" "
		  String decoderPath=URLDecoder.decode(path);
	      p.load(new FileInputStream(decoderPath+"db.properties")); 
	    }catch(Exception e){
	      e.printStackTrace();
	    }    
	    String bdUrl = p.getProperty("db.url"); 
	    dbName=bdUrl.substring(bdUrl.indexOf("://")+3,bdUrl.indexOf("?"));
	
	    dbName=dbName.substring(dbName.indexOf("/")+1);
	    
	   /* System.out.println("dbName"+dbName);
	    System.out.println("dbName"+dbName);
	    System.out.println("dbName"+dbName);
	    System.out.println("dbName"+dbName);*/
	    
	}
	


	
}
