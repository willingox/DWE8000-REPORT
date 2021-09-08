package com.xjgc.wind.datastatistics.dao;

import java.util.ArrayList;
import java.util.List;

import com.xjgc.wind.datastatistics.vo.WgmeasinfoDataVo;
import com.xjgc.wind.datastatistics.web.form.RuningInfo_OneMinuteDataForm;

public interface IWindMeaInfoMonitorDao {

	List<WgmeasinfoDataVo> list();

	
}
