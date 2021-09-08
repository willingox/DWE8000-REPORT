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
 * ���ڷ��繦��Ԥ����ʷ���ݣ���ѯ����
 * Date: 2012-11-18
 */
public class GeneratStatisticsDataForm extends ActionForm {

	Date startDate;	//����
	String startDateDisp;//ҳ����ʾ
	int smgId;//��վID

	public GeneratStatisticsDataForm() {
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

	public int getSmgId() {
		return smgId;
	}

	public void setSmgId(int smgId) {
		this.smgId = smgId;
	}


	
}

