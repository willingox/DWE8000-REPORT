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



public class GeneratorStatisticsDayReportDataForm extends ActionForm {


	String dateDisp;
    int  smgId;

	public GeneratorStatisticsDayReportDataForm() {
	}


	public String getDateDisp() {
		return dateDisp;
	}


	public void setDateDisp(String dateDisp) {
		this.dateDisp = dateDisp;
	}


	/**
	 * @return the smgId
	 */
	public int getSmgId() {
		return smgId;
	}


	/**
	 * @param smgId the smgId to set
	 */
	public void setSmgId(int smgId) {
		this.smgId = smgId;
	}

	
}

