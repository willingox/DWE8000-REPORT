package com.xjgc.wind.app.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

public final class HtmlUtil {

	public static void writeStrToHtml(String str,HttpServletResponse response) throws Exception{
			
			response.setHeader("Cache-Control", "no-cache");
			//这句话的意思，是让浏览器用utf8来解析返回的数据  
			response.setHeader("Content-type", "text/html;charset=UTF-8");
			
			//这句话的意思，是告诉servlet用UTF-8转码，而不是用默认的ISO8859  
			response.setCharacterEncoding("UTF-8"); 
			PrintWriter out = response.getWriter();
			
			out.write(str);
			out.flush();
			out.close();
			
		}
	
	public static void downloadFiletoClient(String path,HttpServletResponse response) throws Exception{
		
	}

}
