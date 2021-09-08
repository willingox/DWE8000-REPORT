package com.xjgc.wind.datastatistics.web.form;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;

import com.xjgc.wind.util.YMDHMSUtil;

public class FaultQueryDataForm extends ActionForm {

	Date startDate;	//��ʼ����
	String startDateDisp;//ҳ����ʾ
	Date endDate;	//��ֹ����
	String endDateDisp;	//ҳ����ʾ
	Integer equipId;
	Integer alarmType;
	String faultName;
	String str;
	/**
	 * @return the faultName
	 */
	public String getFaultName() {
		return faultName;
	}

	/**
	 * @param faultName the faultName to set
	 */
	public void setFaultName(String faultName) {
		this.faultName = faultName;
	}

	public FaultQueryDataForm(){
		
	}
	
	

	public Date getStartDate() {
		startDate = null;
		if (StringUtils.isNotBlank(startDateDisp)) {
			try {
				startDate = YMDHMSUtil .get().parse(this.startDateDisp);
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

	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}

	public String getEndDateDisp() {
		return endDateDisp;
	}

	public void setEndDateDisp(String endDateDisp) {
		this.endDateDisp = endDateDisp;
	}

	public Integer getEquipId() {
		return equipId;
	}

	public void setEquipId(Integer equipId) {
		this.equipId = equipId;
	}

	public Integer getAlarmType() {
		return alarmType;
	}

	public void setAlarmType(Integer alarmType) {
		this.alarmType = alarmType;
	}
	
	
}
