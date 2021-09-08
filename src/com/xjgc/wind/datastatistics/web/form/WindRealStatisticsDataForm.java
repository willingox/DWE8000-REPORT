package com.xjgc.wind.datastatistics.web.form;

import org.apache.struts.action.ActionForm;

public class WindRealStatisticsDataForm extends ActionForm {
	
	//排序指标种类
	int targetType;
	//排序指标种类
	int stateType;
	//升降序
	int sortType;
	
	public int getTargetType() {
		return targetType;
	}
	public void setTargetType(int targetType) {
		this.targetType = targetType;
	}
	public int getSortType() {
		return sortType;
	}
	public void setSortType(int sortType) {
		this.sortType = sortType;
	}
	public int getStateType() {
		return stateType;
	}
	public void setStateType(int stateType) {
		this.stateType = stateType;
	}
	
	
	
}
