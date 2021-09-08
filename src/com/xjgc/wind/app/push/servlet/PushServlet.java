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


//��չ HttpServlet ��
public class PushServlet extends HttpServlet {

	static int count = 0;
	private PushAppService pushAppService;
	private DateFormat dfsample = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private String apiKey = "fBAIqbOt1BGSw3t2r49GFmx8cH3WuB64";
	private String secretKey = "lsOHy1mGMXZKIZe1YYYpHXP9RQzo3BGU";
	private BaiduPushClient pushClient;
	private long lastCheckTime;
	
	
	public void init() throws ServletException {


		System.out.println("** servlet�����ͣ���ʼ������ʼ��");
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

		// ����ִ��ʱ��
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int day = calendar.get(Calendar.DAY_OF_MONTH);// ÿ��
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int minute = calendar.get(Calendar.MINUTE);

		// ����ִ��ʱ��
		calendar.set(year, month, day, hour, minute + 1, 00);
		Date date = calendar.getTime();
		Timer timer = new Timer();
		int period = 60 * 1000;//60��       һ����
		
		// dateʱ��ִ��task��ÿ��10���ظ�ִ��
		timer.schedule(task, date, period);

	}

	
	private List<Map> getMessage(){
		List<Map> result;
		if(count!=1){
			//����һ�β�ѯ��Ĳ�ѯ��ʼʱ��Ϊ��һ�εĲ�ѯʱ�䣬ʱ����Ϊ��ʱ����ʱ�������������ݿ��쳣�Ļ�Ϊ��һ�β�ѯ�����β�ѯ��ʱ������
			String startTime=dfsample.format(new Date(lastCheckTime));
			String endTime=dfsample.format(new Date());
			lastCheckTime=System.currentTimeMillis();
			System.out.println("ʱ��=" + endTime + " ִ����" +count + "��"+startTime+" "+endTime); // ��
			result = pushAppService.getBreakdownByTime(startTime,endTime);
			result.addAll(pushAppService.getLastStatusBreakdownByTime(startTime,endTime));
			
		}else{
			//��һ�β�ѯ��ʼʱ��Ϊ��ǰʱ�̵������֮ǰ
			String startTime=dfsample.format(new Date(System.currentTimeMillis()-300000));
			//String startTime=dfsample.format(new Date(System.currentTimeMillis()-30000000));
			String endTime=dfsample.format(new Date());
			lastCheckTime=System.currentTimeMillis();
			System.out.println("ʱ��=" + endTime + " ִ����" + count + "��"+startTime+" "+endTime); // ��
			result = pushAppService.getBreakdownByTime(startTime,endTime);
			result.addAll(pushAppService.getLastStatusBreakdownByTime(startTime,endTime));
			
		}
		
		return result;
	}
	
	private void putMessage(Map map) throws PushClientException, PushServerException{
		
		String title;
		String description;
		title=(String)map.get("dzname")+":"+(String)map.get("fzname")+"�������ϣ�";
		description=(String)map.get("gzname")+" "+(String)map.get("datatime");
		
		//����Android֪ͨ
		JSONObject notification = new JSONObject(); // new JSONObject();
		notification.put("title", title);
		notification.put("description", description);
		//�Զ������ݣ�json�ṹ��
		JSONObject jsonCustormCont = new JSONObject();
		jsonCustormCont.put("key", "value"); //�Զ������ݣ�key-value
		notification.put("custom_content", jsonCustormCont);
		
		try {
			// 4. specify request arguments
			PushMsgToAllRequest request = new PushMsgToAllRequest()
					.addMsgExpires(new Integer(3600)).addMessageType(1)
					.addMessage(notification.toString()) // ���͸����Ϣ
					.addSendTime(System.currentTimeMillis() / 1000 + 62) // ���ö�ʱ����ʱ�䣬���賬����ǰʱ��һ���ӣ���λ��.ʵ��2���Ӻ�����
					.addDeviceType(3);
			// 5. http request
			PushMsgToAllResponse response = pushClient.pushMsgToAll(request);

			// Http������������ӡ
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

		// ����Android֪ͨ
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
		jsonCustormCont.put("key", "value"); // �Զ������ݣ�key-value
		notification.put("custom_content", jsonCustormCont);

		try {
			// 4. specify request arguments
			PushMsgToAllRequest request = new PushMsgToAllRequest()
					.addMsgExpires(new Integer(3600)).addMessageType(1)
					.addMessage(notification.toString()) // ���͸����Ϣ
					.addSendTime(System.currentTimeMillis() / 1000 + 62) // ���ö�ʱ����ʱ�䣬���賬����ǰʱ��һ���ӣ���λ��.ʵ��2���Ӻ�����
					.addDeviceType(3);
			// 5. http request
			PushMsgToAllResponse response = pushClient.pushMsgToAll(request);

			// Http������������ӡ
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
		// ������Ӧ��������
		response.setContentType("text/html");

		// ʵ�ʵ��߼���������
		PrintWriter out = response.getWriter();
		out.println("<h1>" + "message" + "</h1>");
	}

	public void destroy() {
		// ʲôҲ����
	}
	
	
}
