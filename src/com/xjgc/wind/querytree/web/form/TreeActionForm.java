package com.xjgc.wind.querytree.web.form;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;

import com.xjgc.wind.util.YMDHMSUtil;

public class TreeActionForm extends ActionForm {

	
	String treeFlType;
	int tableId;
	
	public TreeActionForm(){
		
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


	/**
	 * @return the tableId
	 */
	public int getTableId() {
		return tableId;
	}


	/**
	 * @param tableId the tableId to set
	 */
	public void setTableId(int tableId) {
		this.tableId = tableId;
	}
	
	
	
}
