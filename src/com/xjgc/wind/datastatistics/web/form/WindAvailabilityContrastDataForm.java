package com.xjgc.wind.datastatistics.web.form;


import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;

import com.xjgc.wind.util.DateFormatUtil;
import com.xjgc.wind.util.YMDHMSUtil;
import com.xjgc.wind.util.YearFormatUtil;


public class WindAvailabilityContrastDataForm extends ActionForm {

	Date startDate;	//
	String startDateDisp;//
	Date endDate;	//
	String endDateDisp;	//
	String str;
    int id;
    String treeFlType;
    Integer alarmType;
	/**
	 * @return the id
	 */
	

	public WindAvailabilityContrastDataForm() {
	}

	
	public Date getStartDate() {
		startDate = null;
		if (StringUtils.isNotBlank(startDateDisp)) {
			try {
				startDate = YMDHMSUtil.get().parse(this.startDateDisp);
			} catch (ParseException e) {
				startDate = null;
				e.printStackTrace();
			}
		}
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
		this.startDateDisp = null;
		if (startDate != null) {
			try {
				this.startDateDisp = YMDHMSUtil.get().format(startDate);
			} catch (Exception e) {
				this.startDateDisp = null;
				e.printStackTrace();
			}
		}
	}
	
	
	public Integer getAlarmType() {
		return alarmType;
	}


	public void setAlarmType(Integer alarmType) {
		this.alarmType = alarmType;
	}


	/**
	 * @return the treeFlType
	 */
	public String getTreeFlType() {
		return treeFlType;
	}


	/**
	 * @param treeFlType the treeFlType to set
	 */
	public void setTreeFlType(String treeFlType) {
		this.treeFlType = treeFlType;
	}


	public String getStartDateDisp() {
		return startDateDisp;
	}

	public void setStartDateDisp(String startDateDisp) {
		this.startDateDisp = startDateDisp;
	}
	
	public Date getEndDate() {
		endDate = null;
		if (StringUtils.isNotBlank(endDateDisp)) {
			try {
				endDate = YMDHMSUtil.get().parse(this.endDateDisp);
			} catch (ParseException e) {
				endDate = null;
				e.printStackTrace();
			}
		}
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
		this.endDateDisp = null;
		if (endDate != null) {
			try {
				this.endDateDisp = YMDHMSUtil.get().format(endDate);
			} catch (Exception e) {
				this.endDateDisp = null;
				e.printStackTrace();
			}
		}
	}

	public String getEndDateDisp() {
		return endDateDisp;
	}

	public void setEndDateDisp(String endDateDisp) {
		this.endDateDisp = endDateDisp;
	}


	
	
	/**
	 * @return the str
	 */
	public String getStr() {
		return str;
	}


	/**
	 * @param str the str to set
	 */
	public void setStr(String str) {
		this.str = str;
	}


	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}


	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
}
