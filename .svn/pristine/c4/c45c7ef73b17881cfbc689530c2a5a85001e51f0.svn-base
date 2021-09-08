package com.xjgc.wind.app.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyDate {

	public MyDate() {
		// TODO Auto-generated constructor stub
	}

	static DateFormat dfsample = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss EEEE");
	static DateFormat dfyear = new SimpleDateFormat("yyyy");
	
	public static String getYear(){
		Date _date=new Date();
		String  _result=dfyear.format(_date);
		return _result;
	}
	
	public static int  getLastyearInt(){
		Date _date=new Date();
		return(_date.getYear()+1899);
		
	}
	
	public static String  getLastyearStr(){
		Date _date=new Date();
		return(Integer.toString(_date.getYear()+1899));
		
	}
	
	public static int compare_date(String DATE1, String DATE2) {
        
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        try {
            Date dt1 = df.parse(DATE1);
            Date dt2 = df.parse(DATE2);
            if (dt1.getTime() > dt2.getTime()) {
            	//System.out.println("dt1在dt2后");
                return 1;
            } else if (dt1.getTime() < dt2.getTime()) {
            	//System.out.println("dt1 在dt2前");
                return -1;
            } else {
                return 0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }
	
	
	public static Date todate(String datestr) {
        
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		Date date=new Date();
		try {
			date = df.parse(datestr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return date;
		}
		return date;
    }
	
	
	public static boolean nowTimeInFistMonth(){
		
		String _timeStr=getYear()+"-01-31 23:59";
		Date  _nowdate=new Date();
		Date _targetDate=_nowdate;
		_targetDate=todate(_timeStr);
		if (_nowdate.after(_targetDate)){
			return false;
		}else{
			return true;
		}
		
	}
	
	public static boolean nowTimeInFistWeek(){
		
		String _timeStr=getYear()+"-01-07 23:59";
		Date  _nowdate=new Date();
		Date _targetDate=_nowdate;
		_targetDate=todate(_timeStr);
		if (_nowdate.after(_targetDate)){
			return false;
		}else{
			return true;
		}
	}
	
	public static boolean nowTimeInFistDay(){
		
		String _timeStr=getYear()+"-01-01 23:59";
		Date  _nowdate=new Date();
		Date _targetDate=_nowdate;
		_targetDate=todate(_timeStr);
		if (_nowdate.after(_targetDate)){
			return false;
		}else{
			return true;
		}
	}
	
	
	
}
