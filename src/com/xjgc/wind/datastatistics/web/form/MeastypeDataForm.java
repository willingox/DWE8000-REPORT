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


/**
 * 短期发电功率预测历史数据：查询条件
 * Date: 2012-11-18
 */
public class MeastypeDataForm extends ActionForm {

	Date startDate;	//起始日期
	String startDateDisp;//页面显示
	Date endDate;	//终止日期
	String endDateDisp;	//页面显示
	Integer equipId;
	Integer meastypeId;
	



	public MeastypeDataForm() {
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
	public Integer getEquipId() {
		return equipId;
	}


	/**
	 * @param equipId the equipId to set
	 */
	public void setEquipId(Integer equipId) {
		this.equipId = equipId;
	}


	/**
	 * @return the meastypeId
	 */
	public Integer getMeastypeId() {
		return meastypeId;
	}


	/**
	 * @param meastypeId the meastypeId to set
	 */
	public void setMeastypeId(Integer meastypeId) {
		this.meastypeId = meastypeId;
	}
}
