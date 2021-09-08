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

public class RuningInfo_OneDayReportDataForm extends ActionForm{

	Date startDate;
	String startDateDisp;
	Date endDate;	
	String endDateDisp;	
	int equipId;
	int flag;
	String str;
	
	public RuningInfo_OneDayReportDataForm() {
	}
	
	public Date getStartDate() {
		startDate = null;
		if (StringUtils.isNotBlank(startDateDisp)) {
			try {
				startDate = DateFormatUtil .get().parse(this.startDateDisp);
			} catch (ParseException e) {
				startDate = null;
				e.printStackTrace();
			}
		}
		return startDate;
	}

	/**
	 * @return the flag
	 */
	public int getFlag() {
		return flag;
	}

	/**
	 * @param flag the flag to set
	 */
	public void setFlag(int flag) {
		this.flag = flag;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
		this.startDateDisp = null;
		if (startDate != null) {
			try {
				this.startDateDisp = DateFormatUtil.get().format(startDate);
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
				endDate = DateFormatUtil.get().parse(this.endDateDisp);
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
				this.endDateDisp = DateFormatUtil.get().format(endDate);
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
	 * @return the equipId
	 */
	public int getEquipId() {
		return equipId;
	}

	/**
	 * @param equipId the equipId to set
	 */
	public void setEquipId(int equipId) {
		this.equipId = equipId;
	}




	
	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}
	
	
}
