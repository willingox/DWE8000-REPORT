package com.xjgc.wind.datastatistics.dao;

import java.util.List;


import com.xjgc.wind.datastatistics.vo.DataStatisticsDataVo;
import com.xjgc.wind.datastatistics.vo.GeneratorStatisticsReportVo;


public interface IGeneratorStatisticsDayReportDao {

	
	List<GeneratorStatisticsReportVo> listcount(String date,Integer smgID);			//���еķ�����
	List<GeneratorStatisticsReportVo> list(String date,Integer smgID);				//�ձ���������
	List<GeneratorStatisticsReportVo> listyesterday(String date,Integer smgID);		//�ձ�����������
	List<GeneratorStatisticsReportVo> listWind(String date,Integer smgID);			//��Ч��ʱ������
}
