package com.xjgc.wind.app.push.filter;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.xjgc.wind.app.breakdown.service.BreakdownAppService;

public class PushFilter implements Filter {

	static int count = 0;
	private String initParam;
	BreakdownAppService breakdownAppService;
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		PushFilter.this.initParam=arg0.getInitParameter("ref");
/*		System.out.println("** 过滤器（推送）初始化，初始化参数="+initParam);*/
		
		
		//springj
		/*WebApplicationContext wac = WebApplicationContextUtils
				.getRequiredWebApplicationContext(arg0.getServletContext());
		breakdownAppService = (BreakdownAppService) wac.getBean("breakdownAppService");	*/	
		
/*		System.out.println("** 过滤器（推送）初始化，初始化参数="+initParam);*/
		//showTimer();
		
	}

	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws ServletException, IOException {
		// TODO Auto-generated method stub
		arg2.doFilter(arg0, arg1);
		
	}
	
	
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
	
	
	public  void showTimer() {
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                ++count;
                System.out.println("时间=" + new Date() + " 执行了" + count + "次"); // 1次
            }
        };

        
        //设置执行时间
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);//每天
        int hour=calendar.get(Calendar.HOUR_OF_DAY);
        int minute=calendar.get(Calendar.MINUTE);
        
        //定制每天的21:09:00执行，
        calendar.set(year, month, day, 21, 9, 00);
        Date date = calendar.getTime();
        Timer timer = new Timer();
        System.out.println(date);
        Date date2=new Date(System.currentTimeMillis()+30000);
        int period = 2 * 1000;
        //每天的date时刻执行task，每隔2秒重复执行
        timer.schedule(task, date2, period);
        //每天的date时刻执行task, 仅执行一次
        //timer.schedule(task, date);
        
        
    }
	
	
/*	*//** 
	 * 
	 * 
	 * ScheduledExecutorService是从Java SE5的java.util.concurrent里，做为并发工具类被引进的，这是最理想的定时任务实现方式。  
	 * 相比于上两个方法，它有以下好处： 
	 * 1>相比于Timer的单线程，它是通过线程池的方式来执行任务的  
	 * 2>可以很灵活的去设定第一次执行任务delay时间 
	 * 3>提供了良好的约定，以便设定执行的时间间隔 
	 * 
	 * 下面是实现代码，我们通过ScheduledExecutorService#scheduleAtFixedRate展示这个例子，通过代码里参数的控制，首次执行加了delay时间。 
	 *  
	 *  
	 * @author GT 
	 *  
	 *//*  
	public class Task3 {  
	    public static void main(String[] args) {  
	        Runnable runnable = new Runnable() {  
	            public void run() {  
	                // task to run goes here  
	                System.out.println("Hello !!");  
	            }  
	        };  
	        ScheduledExecutorService service = Executors  
	                .newSingleThreadScheduledExecutor();  
	        // 第二个参数为首次执行的延时时间，第三个参数为定时执行的间隔时间  
	        service.scheduleAtFixedRate(runnable, 10, 1, TimeUnit.SECONDS);  
	    }  
	} */ 
	
	
	
}
