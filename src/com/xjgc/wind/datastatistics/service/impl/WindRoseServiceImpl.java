package com.xjgc.wind.datastatistics.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.xjgc.wind.datastatistics.dao.IWindRoseDao;
import com.xjgc.wind.datastatistics.service.IWindRoseService;
import com.xjgc.wind.datastatistics.vo.DataStatisticsDataVo;
import com.xjgc.wind.datastatistics.web.form.WindRoseDataForm;

public class WindRoseServiceImpl implements IWindRoseService {

	IWindRoseDao windRoseDao;

	public IWindRoseDao getWindRoseDao() {
		return windRoseDao;
	}
	
	public void setWindRoseDao(IWindRoseDao windRoseDao) {
		this.windRoseDao = windRoseDao;
	}

	
	public List<DataStatisticsDataVo> list(WindRoseDataForm queryFilter) {
		List<DataStatisticsDataVo>  resultListdata=new ArrayList<DataStatisticsDataVo>();
		for (int i=0;i<16;i++){
			DataStatisticsDataVo obj=new DataStatisticsDataVo();
			obj.setWindDirVal(String.valueOf(i+1));
			resultListdata.add(obj);
		}
		
		List<DataStatisticsDataVo> listdata=windRoseDao.list(queryFilter);
		int sumNum=0;
		for(int j=0;j<listdata.size();j++){
			DataStatisticsDataVo obj=listdata.get(j);
			sumNum+=obj.getCounts();
			
		}
		
		
		for(int j=0;j<listdata.size();j++){
			DataStatisticsDataVo obj=listdata.get(j);
			if(0!=sumNum){
				obj.setFrequency1((double)obj.getCounts()*100/sumNum);
			}
			int jiaodu=Integer.parseInt(obj.getWindDirVal());
			resultListdata.get(jiaodu-1).setWindDirVal(obj.getWindDirVal());  			//角度
			resultListdata.get(jiaodu-1).setAvgWindVelvalDu(obj.getAvgWindVelvalDu()); 	//平均风速
			resultListdata.get(jiaodu-1).setFrequency1(obj.getFrequency1()); 			//频率
			resultListdata.get(jiaodu-1).setAvgWindVelval(obj.getAvgWindVelvalDu()*obj.getAvgWindVelvalDu()*obj.getAvgWindVelvalDu()*obj.getFrequency1());		//风能
			resultListdata.get(jiaodu-1).setPower(obj.getPower());						//功率
			resultListdata.get(jiaodu-1).setCounts(obj.getCounts());					//频次
		}
		
		return resultListdata;
	}

	
	
}
