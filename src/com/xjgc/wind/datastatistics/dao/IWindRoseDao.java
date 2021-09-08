package com.xjgc.wind.datastatistics.dao;

import java.util.List;

import com.xjgc.wind.datastatistics.vo.DataStatisticsDataVo;
import com.xjgc.wind.datastatistics.web.form.WindRoseDataForm;

public interface IWindRoseDao {


	List<DataStatisticsDataVo> list(WindRoseDataForm queryFilter);

	
}
