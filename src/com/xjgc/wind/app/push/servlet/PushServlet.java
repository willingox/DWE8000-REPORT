package com.xjgc.wind.app.push.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.baidu.yun.core.log.YunLogEvent;
import com.baidu.yun.core.log.YunLogHandler;
import com.baidu.yun.push.auth.PushKeyPair;
import com.baidu.yun.push.client.BaiduPushClient;
import com.baidu.yun.push.constants.BaiduPushConstants;
import com.baidu.yun.push.exception.PushClientException;
import com.baidu.yun.push.exception.PushServerException;
import com.baidu.yun.push.model.PushMsgToAllRequest;
import com.baidu.yun.push.model.PushMsgToAllResponse;
import com.xjgc.wind.app.breakdown.service.BreakdownAppService;
import com.xjgc.wind.app.push.service.PushAppService;


//扩展 HttpServlet 类
public class PushServlet extends HttpServlet {

	static int count = 0;
	private PushAppService pushAppService;
	private DateFormat dfsample = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private String apiKey = "fBAIqbOt1BGSw3t2r49GFmx8cH3WuB64";
	private String secretKey = "lsOHy1mGMXZKIZe1YYYpHXP9RQzo3BGU";
	private BaiduPushClient pushClient;
	private long lastCheckTime;
	
	
	public void init() throws ServletException {


		System.out.println("** servlet（推送）初始化，初始化");
		lastCheckTime=System.currentTimeMillis();
		WebApplicationContext wac = WebApplicationContextUtils
				.getRequiredWebApplicationContext(PushServlet.this
						.getServletContext());
		pushAppService = (PushAppService) wac
				.getBean("pushAppService");
		
		PushKeyPair pair = new PushKeyPair(apiKey, secretKey);
		pushClient = new BaiduPushClient(pair,
				BaiduPushConstants.CHANNEL_REST_URL);
		pushClient.setChannelLogHandler(new YunLogHandler() {
			// @Override
			public void onHandle(YunLogEvent event) {
				//System.out.println(event.getMessage());
			}
		});
		
		myTimer();

	}


	
	public void myTimer() {

		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				++count;
				List<Map> list= getMessage();
				for(int i=0; i<list.size();i++){
					try {
						putMessage(list.get(i));
					} catch (PushClientException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (PushServerException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}
		};

		// 设置执行时间
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int day = calendar.get(Calendar.DAY_OF_MONTH);// 每天
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int minute = calendar.get(Calendar.MINUTE);

		// 定制执行时刻
		calendar.set(year, month, day, hour, minute + 1, 00);
		Date date = calendar.getTime();
		Timer timer = new Timer();
		int period = 60 * 1000;//60秒       一分钟
		
		// date时刻执行task，每隔10秒重复执行
		timer.schedule(task, date, period);

	}

	
	private List<Map> getMessage(){
		List<Map> result;
		if(count!=1){
			//除第一次查询外的查询开始时间为上一次的查询时间，时间间隔为定时器的时间间隔（访问数据库异常的话为上一次查询到本次查询的时间间隔）
			String startTime=dfsample.format(new Date(lastCheckTime));
			String endTime=dfsample.format(new Date());
			lastCheckTime=System.currentTimeMillis();
			System.out.println("时间=" + endTime + " 执行了" +count + "次"+startTime+" "+endTime); // 次
			result = pushAppService.getBreakdownByTime(startTime,endTime);
			result.addAll(pushAppService.getLastStatusBreakdownByTime(startTime,endTime));
			
		}else{
			//第一次查询开始时间为当前时刻的五分钟之前
			String startTime=dfsample.format(new Date(System.currentTimeMillis()-300000));
			//String startTime=dfsample.format(new Date(System.currentTimeMillis()-30000000));
			String endTime=dfsample.format(new Date());
			lastCheckTime=System.currentTimeMillis();
			System.out.println("时间=" + endTime + " 执行了" + count + "次"+startTime+" "+endTime); // 次
			result = pushAppService.getBreakdownByTime(startTime,endTime);
			result.addAll(pushAppService.getLastStatusBreakdownByTime(startTime,endTime));
			
		}
		
		return result;
	}
	
	private void putMessage(Map map) throws PushClientException, PushServerException{
		
		String title;
		String description;
		title=(String)map.get("dzname")+":"+(String)map.get("fzname")+"发生故障！";
		description=(String)map.get("gzname")+" "+(String)map.get("datatime");
		
		//创建Android通知
		JSONObject notification = new JSONObject(); // new JSONObject();
		notification.put("title", title);
		notification.put("description", description);
		//自定义内容（json结构）
		JSONObject jsonCustormCont = new JSONObject();
		jsonCustormCont.put("key", "value"); //自定义内容，key-value
		notification.put("custom_content", jsonCustormCont);
		
		try {
			// 4. specify request arguments
			PushMsgToAllRequest request = new PushMsgToAllRequest()
					.addMsgExpires(new Integer(3600)).addMessageType(1)
					.addMessage(notification.toString()) // 添加透传消息
					.addSendTime(System.currentTimeMillis() / 1000 + 62) // 设置定时推送时间，必需超过当前时间一分钟，单位秒.实例2分钟后推送
					.addDeviceType(3);
			// 5. http request
			PushMsgToAllResponse response = pushClient.pushMsgToAll(request);

			// Http请求结果解析打印
			System.out.println("msgId: " + response.getMsgId() + ",sendTime: "
					+ response.getSendTime() + ",timerId: "
					+ response.getTimerId());
		} catch (PushClientException e) {
			if (BaiduPushConstants.ERROROPTTYPE) {
				throw e;
			} else {
				e.printStackTrace();
			}
		} catch (PushServerException e) {
			if (BaiduPushConstants.ERROROPTTYPE) {
				throw e;
			} else {
				System.out.println(String.format(
						"requestId: %d, errorCode: %d, errorMessage: %s",
						e.getRequestId(), e.getErrorCode(), e.getErrorMsg()));
			}
		}	
	}
	
	
	private void putMessage1() throws PushClientException, PushServerException {
		
		/*// 1. get apiKey and secretKey from developer console
		String apiKey = "fBAIqbOt1BGSw3t2r49GFmx8cH3WuB64";
		String secretKey = "lsOHy1mGMXZKIZe1YYYpHXP9RQzo3BGU";
		PushKeyPair pair = new PushKeyPair(apiKey, secretKey);

		// 2. build a BaidupushClient object to access released interfaces
		BaiduPushClient pushClient = new BaiduPushClient(pair,
				BaiduPushConstants.CHANNEL_REST_URL);

		// 3. register a YunLogHandler to get detail interacting information
		// in this request.
		pushClient.setChannelLogHandler(new YunLogHandler() {
			// @Override
			public void onHandle(YunLogEvent event) {
				System.out.println(event.getMessage());
			}
		});*/

		// 创建Android通知
		JSONObject notification = new JSONObject(); // new JSONObject();
		notification.put("title", "TEST1223");
		notification.put("description", "Hello Baidu Push");
		/*
		 * notification.put("notification_builder_id", 0);
		 * notification.put("notification_basic_style", 4);
		 * notification.put("open_type", 1); notification.put("url",
		 * "http://push.baidu.com");
		 */
		JSONObject jsonCustormCont = new JSONObject();
		jsonCustormCont.put("key", "value"); // 自定义内容，key-value
		notification.put("custom_content", jsonCustormCont);

		try {
			// 4. specify request arguments
			PushMsgToAllRequest request = new PushMsgToAllRequest()
					.addMsgExpires(new Integer(3600)).addMessageType(1)
					.addMessage(notification.toString()) // 添加透传消息
					.addSendTime(System.currentTimeMillis() / 1000 + 62) // 设置定时推送时间，必需超过当前时间一分钟，单位秒.实例2分钟后推送
					.addDeviceType(3);
			// 5. http request
			PushMsgToAllResponse response = pushClient.pushMsgToAll(request);

			// Http请求结果解析打印
			System.out.println("msgId: " + response.getMsgId() + ",sendTime: "
					+ response.getSendTime() + ",timerId: "
					+ response.getTimerId());
		} catch (PushClientException e) {
			if (BaiduPushConstants.ERROROPTTYPE) {
				throw e;
			} else {
				e.printStackTrace();
			}
		} catch (PushServerException e) {
			if (BaiduPushConstants.ERROROPTTYPE) {
				throw e;
			} else {
				System.out.println(String.format(
						"requestId: %d, errorCode: %d, errorMessage: %s",
						e.getRequestId(), e.getErrorCode(), e.getErrorMsg()));
			}
		}

	}

	
	
	
	
	
	
	
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 设置响应内容类型
		response.setContentType("text/html");

		// 实际的逻辑是在这里
		PrintWriter out = response.getWriter();
		out.println("<h1>" + "message" + "</h1>");
	}

	public void destroy() {
		// 什么也不做
	}
	
	
}
