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

public class FaultLogDataForm extends ActionForm {

	Date startDate;	//��ʼ����
	String startDateDisp;//ҳ����ʾ
	Date endDate;	//��ֹ����
	String endDateDisp;	//ҳ����ʾ
	Integer equipId;
	Integer alarmType;
	String str;
	Integer dateEquipId;
	Integer txtEquipId;
	
	public FaultLogDataForm(){
		
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
	 * @return the dateEquipId
	 */
	public Integer getDateEquipId() {
		return dateEquipId;
	}

	/**
	 * @param dateEquipId the dateEquipId to set
	 */
	public void setDateEquipId(Integer dateEquipId) {
		this.dateEquipId = dateEquipId;
	}

	/**
	 * @return the txtEquipId
	 */
	public Integer getTxtEquipId() {
		return txtEquipId;
	}

	/**
	 * @param txtEquipId the txtEquipId to set
	 */
	public void setTxtEquipId(Integer txtEquipId) {
		this.txtEquipId = txtEquipId;
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
