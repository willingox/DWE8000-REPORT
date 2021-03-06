package com.xjgc.wind.datastatistics.vo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * ���ڷ��繦��Ԥ����ʷ�����
 */
public final class DataStatisticsDataVo {
	double hoursSum;
	double lossGenSum;
	double windHours;
	Date curTime;
	Date endTime;
	String plcState;
	int configure;
	int bayId;
	String description;
	double gridErrStopHour;
	double weaErrStopHour;
	double hmiStopHour;
	double remoteStopHour;
	double errBreakHour;
	double powLimHour;
	double gridErrPowSum;
	double weaErrPowSum;
	double hmiStopPowSum;
	double remoteStopPowSum;
	double errBreakPowSum;
	double powLimPowSum;
	double hiddenPow;
	double capAva;
	double avaHours;
	
	public double getGridErrStopHour() {
		return gridErrStopHour;
	}









	public int getBayId() {
		return bayId;
	}









	public void setBayId(int bayId) {
		this.bayId = bayId;
	}









	public void setGridErrStopHour(double gridErrStopHour) {
		this.gridErrStopHour = gridErrStopHour;
	}









	public double getHoursSum() {
		return hoursSum;
	}









	public void setHoursSum(double hoursSum) {
		this.hoursSum = hoursSum;
	}









	public double getLossGenSum() {
		return lossGenSum;
	}









	public void setLossGenSum(double lossGenSum) {
		this.lossGenSum = lossGenSum;
	}









	public double getWeaErrStopHour() {
		return weaErrStopHour;
	}









	public void setWeaErrStopHour(double weaErrStopHour) {
		this.weaErrStopHour = weaErrStopHour;
	}
















	public double getRemoteStopHour() {
		return remoteStopHour;
	}









	public void setRemoteStopHour(double remoteStopHour) {
		this.remoteStopHour = remoteStopHour;
	}









	public double getErrBreakHour() {
		return errBreakHour;
	}









	public void setErrBreakHour(double errBreakHour) {
		this.errBreakHour = errBreakHour;
	}









	public double getPowLimHour() {
		return powLimHour;
	}









	public void setPowLimHour(double powLimHour) {
		this.powLimHour = powLimHour;
	}









	public double getGridErrPowSum() {
		return gridErrPowSum;
	}









	public void setGridErrPowSum(double gridErrPowSum) {
		this.gridErrPowSum = gridErrPowSum;
	}









	public double getWeaErrPowSum() {
		return weaErrPowSum;
	}









	public void setWeaErrPowSum(double weaErrPowSum) {
		this.weaErrPowSum = weaErrPowSum;
	}
















	public double getHmiStopHour() {
		return hmiStopHour;
	}









	public void setHmiStopHour(double hmiStopHour) {
		this.hmiStopHour = hmiStopHour;
	}









	public double getHmiStopPowSum() {
		return hmiStopPowSum;
	}









	public void setHmiStopPowSum(double hmiStopPowSum) {
		this.hmiStopPowSum = hmiStopPowSum;
	}









	public double getRemoteStopPowSum() {
		return remoteStopPowSum;
	}









	public void setRemoteStopPowSum(double remoteStopPowSum) {
		this.remoteStopPowSum = remoteStopPowSum;
	}









	public double getErrBreakPowSum() {
		return errBreakPowSum;
	}









	public void setErrBreakPowSum(double errBreakPowSum) {
		this.errBreakPowSum = errBreakPowSum;
	}









	public double getPowLimPowSum() {
		return powLimPowSum;
	}









	public void setPowLimPowSum(double powLimPowSum) {
		this.powLimPowSum = powLimPowSum;
	}









	public double getHiddenPow() {
		return hiddenPow;
	}









	public void setHiddenPow(double hiddenPow) {
		this.hiddenPow = hiddenPow;
	}









	public double getCapAva() {
		return capAva;
	}









	public void setCapAva(double capAva) {
		this.capAva = capAva;
	}









	public double getAvaHours() {
		return avaHours;
	}









	public void setAvaHours(double avaHours) {
		this.avaHours = avaHours;
	}









	public int getConfigure() {
		return configure;
	}









	public String getDescription() {
		return description;
	}









	public void setDescription(String description) {
		this.description = description;
	}









	public void setConfigure(int configure) {
		this.configure = configure;
	}









	public double getWindHours() {
		return windHours;
	}









	public Date getCurTime() {
		return curTime;
	}









	public void setCurTime(Date curTime) {
		this.curTime = curTime;
	}









	public Date getEndTime() {
		return endTime;
	}









	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}









	public String getPlcState() {
		return plcState;
	}









	public void setPlcState(String plcState) {
		this.plcState = plcState;
	}









	public void setWindHours(double windHours) {
		this.windHours = windHours;
	}
	double maxCurp;
	String minTime;
	double minWind;
	String maxTime;
	double maxWind;
	double weight;
	String strId;
	int equipId;
	BigDecimal gearOilPumPres;
	BigDecimal gearOilInPres;
	BigDecimal gearOilInTemp;
	BigDecimal geaOilTankTemp;
	BigDecimal geaOilHeaTemp;
	BigDecimal geaBeaTemp1;
	BigDecimal geaBeaTemp2;
	BigDecimal geaBeaTemp3;
	BigDecimal maxGeaBeaTemp;
	int CurcmpState;
	String windSpeed;
	String meanGenSpeed;
	String columndes;
	String columnName;
	double avaTime;
	double genratio;
	BigDecimal genpwd;
	double totalSWGenwhSY;
	double sunLightVal;
	int rand;
	Date saveTime1;
	double runTime;
	double faultTime;
	double efficiency;
	double dayTodayGenwh;
	double count;
	double countall;
	double carbon;
	double coal;
	double forest;
	double windHour;
	double yesterdaySWGenwh;
	double yesterdayShY;
	double hour;
	double todaySWGenwh;
	double totalSWGenwh;
	double todayShY;
	Float longitude;
	Float latitude;
	
	double calValue0;
	double calValue1;
	double calValue2;
	double calValue3;
	double calValue4;
	
	
	String value1;
	String value2;
	String value3;
	String value4;
	String value5;
	String value6;
	String value7;
	String value8;
	String value9;
	String value10;
	String value11;
	String value12;
	String value13;
	String value14;
	String value15;
	String value16;
	String name1;
	String name2;
	String name3;
	String name4;

	int curcmpState;
	int totalGenNumb;
	int runNumb;
	int standbyNum;
	int repairNum;
	int faultNum;
	double totalGencpty;
	double runGencpty;
	double totalGenwh;
	Date staticTime;
	Float todayGenth;
	Float point;
	Float lmtGenratio;
	double availability;
	BigDecimal sumMaxTgwh;
	BigDecimal avgMaxTgwh;
	double sumCurp;
	double curp;
	double swcurp;
	BigDecimal curq;
	double capacity;
	double todayGenwh;
	
	Float avgPower;
	Float power1;
	Float power2;
	double sumCurq;
	int id;
	int mgId;
	int counts;
	int windCounts;
	int equipCounts;
	double yesterdayGenwh;
	double avgWindVelval;
	double windPower;
	Float avgWindVelvalDu;
	String windVelval;
	Float windVelval1;
	Float frequency ;
	double frequency1 ;
	Float sumStopTime ;
	Float power ;
	int meastypeId;
	int measclassId;
	Date saveTime;
	double aveValue;
	double maxData;
	double minData;
	String name;
	String windName;
	int lastValue;
	Date happenTime;
	Date removeTime;
	BigDecimal durationTime;
	BigDecimal sumDurationTime;
	String windDirVal;
	BigDecimal pwdWindVelval;
	BigDecimal pwdCurp;
	public DataStatisticsDataVo() {
	}


	






	public BigDecimal getPwdCurp() {
		return pwdCurp;
	}









	public void setPwdCurp(BigDecimal pwdCurp) {
		this.pwdCurp = pwdCurp;
	}









	public BigDecimal getPwdWindVelval() {
		return pwdWindVelval;
	}









	public void setPwdWindVelval(BigDecimal pwdWindVelval) {
		this.pwdWindVelval = pwdWindVelval;
	}









	/**
	 * @return the calValue0
	 */
	public double getCalValue0() {
		return calValue0;
	}









	public int getEquipId() {
		return equipId;
	}









	public void setEquipId(int equipId) {
		this.equipId = equipId;
	}









	/**
	 * @param calValue0 the calValue0 to set
	 */
	public void setCalValue0(double calValue0) {
		this.calValue0 = calValue0;
	}









	public double getAvaTime() {
		return avaTime;
	}









	public void setAvaTime(double avaTime) {
		this.avaTime = avaTime;
	}









	/**
	 * @return the gearOilPumPres
	 */
	public BigDecimal getGearOilPumPres() {
		return gearOilPumPres;
	}









	/**
	 * @param gearOilPumPres the gearOilPumPres to set
	 */
	public void setGearOilPumPres(BigDecimal gearOilPumPres) {
		this.gearOilPumPres = gearOilPumPres;
	}









	/**
	 * @return the gearOilInPres
	 */
	public BigDecimal getGearOilInPres() {
		return gearOilInPres;
	}









	/**
	 * @param gearOilInPres the gearOilInPres to set
	 */
	public void setGearOilInPres(BigDecimal gearOilInPres) {
		this.gearOilInPres = gearOilInPres;
	}









	/**
	 * @return the gearOilInTemp
	 */
	public BigDecimal getGearOilInTemp() {
		return gearOilInTemp;
	}









	/**
	 * @param gearOilInTemp the gearOilInTemp to set
	 */
	public void setGearOilInTemp(BigDecimal gearOilInTemp) {
		this.gearOilInTemp = gearOilInTemp;
	}









	/**
	 * @return the geaOilTankTemp
	 */
	public BigDecimal getGeaOilTankTemp() {
		return geaOilTankTemp;
	}









	/**
	 * @param geaOilTankTemp the geaOilTankTemp to set
	 */
	public void setGeaOilTankTemp(BigDecimal geaOilTankTemp) {
		this.geaOilTankTemp = geaOilTankTemp;
	}









	/**
	 * @return the geaOilHeaTemp
	 */
	public BigDecimal getGeaOilHeaTemp() {
		return geaOilHeaTemp;
	}









	/**
	 * @param geaOilHeaTemp the geaOilHeaTemp to set
	 */
	public void setGeaOilHeaTemp(BigDecimal geaOilHeaTemp) {
		this.geaOilHeaTemp = geaOilHeaTemp;
	}









	/**
	 * @return the geaBeaTemp1
	 */
	public BigDecimal getGeaBeaTemp1() {
		return geaBeaTemp1;
	}









	/**
	 * @param geaBeaTemp1 the geaBeaTemp1 to set
	 */
	public void setGeaBeaTemp1(BigDecimal geaBeaTemp1) {
		this.geaBeaTemp1 = geaBeaTemp1;
	}









	/**
	 * @return the geaBeaTemp2
	 */
	public BigDecimal getGeaBeaTemp2() {
		return geaBeaTemp2;
	}









	/**
	 * @param geaBeaTemp2 the geaBeaTemp2 to set
	 */
	public void setGeaBeaTemp2(BigDecimal geaBeaTemp2) {
		this.geaBeaTemp2 = geaBeaTemp2;
	}









	/**
	 * @return the geaBeaTemp3
	 */
	public BigDecimal getGeaBeaTemp3() {
		return geaBeaTemp3;
	}









	/**
	 * @param geaBeaTemp3 the geaBeaTemp3 to set
	 */
	public void setGeaBeaTemp3(BigDecimal geaBeaTemp3) {
		this.geaBeaTemp3 = geaBeaTemp3;
	}









	/**
	 * @return the maxGeaBeaTemp
	 */
	public BigDecimal getMaxGeaBeaTemp() {
		return maxGeaBeaTemp;
	}









	/**
	 * @param maxGeaBeaTemp the maxGeaBeaTemp to set
	 */
	public void setMaxGeaBeaTemp(BigDecimal maxGeaBeaTemp) {
		this.maxGeaBeaTemp = maxGeaBeaTemp;
	}









	/**
	 * @return the calValue1
	 */
	public double getCalValue1() {
		return calValue1;
	}









	/**
	 * @param calValue1 the calValue1 to set
	 */
	public void setCalValue1(double calValue1) {
		this.calValue1 = calValue1;
	}









	/**
	 * @return the calValue2
	 */
	public double getCalValue2() {
		return calValue2;
	}









	/**
	 * @param calValue2 the calValue2 to set
	 */
	public void setCalValue2(double calValue2) {
		this.calValue2 = calValue2;
	}









	/**
	 * @return the calValue3
	 */
	public double getCalValue3() {
		return calValue3;
	}









	/**
	 * @param calValue3 the calValue3 to set
	 */
	public void setCalValue3(double calValue3) {
		this.calValue3 = calValue3;
	}









	/**
	 * @return the calValue4
	 */
	public double getCalValue4() {
		return calValue4;
	}









	/**
	 * @param calValue4 the calValue4 to set
	 */
	public void setCalValue4(double calValue4) {
		this.calValue4 = calValue4;
	}









	/**
	 * @return the staticTime
	 */
	public Date getStaticTime() {
		return staticTime;
	}





	/**
	 * @param staticTime the staticTime to set
	 */
	public void setStaticTime(Date staticTime) {
		this.staticTime = staticTime;
	}





	/**
	 * @return the todayGenth
	 */
	public Float getTodayGenth() {
		return todayGenth;
	}





	/**
	 * @param todayGenth the todayGenth to set
	 */
	public void setTodayGenth(Float todayGenth) {
		this.todayGenth = todayGenth;
	}





	





	/**
	 * @return the genratio
	 */
	public double getGenratio() {
		return genratio;
	}









	/**
	 * @param genratio the genratio to set
	 */
	public void setGenratio(double genratio) {
		this.genratio = genratio;
	}









	/**
	 * @return the lmtGenratio
	 */
	public Float getLmtGenratio() {
		return lmtGenratio;
	}





	/**
	 * @param lmtGenratio the lmtGenratio to set
	 */
	public void setLmtGenratio(Float lmtGenratio) {
		this.lmtGenratio = lmtGenratio;
	}





	/**
	 * @return the totalSWGenwh
	 */
	public double getTotalSWGenwh() {
		return totalSWGenwh;
	}





	/**
	 * @param totalSWGenwh the totalSWGenwh to set
	 */
	public void setTotalSWGenwh(double totalSWGenwh) {
		this.totalSWGenwh = totalSWGenwh;
	}





	public DataStatisticsDataVo(double maxCurp,String minTime,double minWind,String maxTime,double maxWind,double weight,String strId,int CurcmpState,String windSpeed,String meanGenSpeed,String columndes,String columnName,String value1,String value2,String value3,String value4,String value5,String value6,String value7,
	String value8,String value9,String value10,String value11,String value12,String value13,String value14,String value15,String value16,BigDecimal genpwd,double totalSWGenwhSY,double totalSWGenwh,double swcurp,double runTime,double faultTime,double efficiency,double dayTodayGenwh,int rand,double count,double countall,double hour,double windHour,double windPower,double yesterdaySWGenwh,double yesterdayShY,double todaySWGenwh,double todayShY,Float longitude,Float latitude,	double carbon,double coal,double forest,Date saveTime1,double calValue1,double calValue2,double calValue3,double calValue4,
	double calValue75,String name1,String name2,String name3,String name4,int curcmpState,int windCounts,int equipCounts,int totalGenNumb,int runNumb,int standbyNum,int repairNum,int faultNum,double totalGencpty,double runGencpty,double totalGenwh,double yesterdayGenwh,String windName,BigDecimal curq,double capacity,double calValue0
	,double todayGenwh,int mgId,
	Float point,Float avgPower,Float power1,Float power2,double curp,double sumCurp,double sumCurq,BigDecimal sumMaxTgwh,BigDecimal avgMaxTgwh,Float windVelval1,Float avgWindVelvalDu,String windDirVal,Date staticTime,Float todayGenth,Double genratio,Float lmtGenratio,double availability,String windVelval,Float power,int counts,double avgWindVelval,
	Float frequency,Float sumStopTime, int lastValue,Date happenTime,BigDecimal sumDurationTime,Date removeTime,BigDecimal durationTime,int id,int meastypeId,int measclassId,double aveValue,double maxData,double minData,String name,double frequency1,double sunLightVal) {
		super();
		this.maxCurp=maxCurp;
		this.minTime=minTime;
		this.minWind=minWind;
		this.maxTime=maxTime;
		this.maxWind=maxWind;
		this.weight = weight;
		this.strId = strId;
		this.CurcmpState = CurcmpState;
		this.windSpeed = windSpeed;
		this.meanGenSpeed = meanGenSpeed;
		this.columndes = columndes;
		this.columnName = columnName;
		this.value1 = value1;
		this.value2 = value2;
		this.value3 = value3;
		this.value4 = value4;
		this.value5 = value5;
		this.value6 = value6;
		this.value7 = value7;
		this.value8 = value8;
		this.value9 = value9;
		this.value10 = value10;
		this.value11 = value11;
		this.value12 = value12;
		this.value13 = value13;
		this.value14 = value14;
		this.value15 = value15;
		this.value16 = value16;
		
		this.genpwd = genpwd;
		this.totalSWGenwhSY = totalSWGenwhSY;
		this.totalSWGenwh = totalSWGenwh;
		this.swcurp = swcurp;
		this.runTime = runTime;
		this.faultTime = faultTime;
		this.efficiency = efficiency;
		this.count = count;
		this.dayTodayGenwh = dayTodayGenwh;
		this.rand = rand;
		this.countall = countall;
		this.yesterdaySWGenwh = yesterdaySWGenwh;
		this.hour = hour;
		this.windHour = windHour;
		this.windPower = windPower;
		this.yesterdayShY = yesterdayShY;
		this.todaySWGenwh = todaySWGenwh;
		this.todayShY = todayShY;
		this.longitude = longitude;
		this.latitude = latitude;
		this.carbon = carbon;
		this.coal = coal;
		this.forest = forest;
		this.saveTime1 = saveTime1;
		this.calValue1 = calValue1;
		this.calValue2 = calValue2;
		this.calValue3 = calValue3;
		this.calValue4 = calValue4;
		this.name1 = name1;
		this.name2 = name2;
		this.name3 = name3;
		this.name4 = name4;
		this.curcmpState = curcmpState;
		this.totalGenNumb = totalGenNumb;
		this.runNumb = runNumb;
		this.standbyNum = standbyNum;
		this.repairNum = repairNum;
		this.faultNum = faultNum;
		this.totalGencpty = totalGencpty;
		this.runGencpty = runGencpty;
		this.totalGenwh = totalGenwh;
		this.windCounts = windCounts;
		this.equipCounts = equipCounts;
		this.yesterdayGenwh = yesterdayGenwh;
		this.windName = windName;
		this.curq = curq;
		this.capacity = capacity;
		this.todayGenwh = todayGenwh;
		this.mgId = mgId;
		this.avgPower = avgPower;
		this.point = point;
		this.power1 = power1;
		this.power2 = power2;
		this.curp = curp;
		this.sumCurp = sumCurp;
		this.sumCurq = sumCurq;
		this.sumMaxTgwh = sumMaxTgwh;
		this.avgMaxTgwh = avgMaxTgwh;
		this.windVelval1 = windVelval1;
		this.avgWindVelvalDu = avgWindVelvalDu;
		this.windDirVal = windDirVal;
		this.staticTime = staticTime;
		this.todayGenth = todayGenth;
		this.genratio = genratio;
		this.lmtGenratio = lmtGenratio;
		this.availability = availability;
		this.id = id;
		this.counts = counts;
		this.sumStopTime = sumStopTime;
		this.lastValue = lastValue;
		this.happenTime = happenTime;
		this.removeTime = removeTime;
		this.durationTime = durationTime;
		this.sumDurationTime = sumDurationTime;
		this.avgWindVelval = avgWindVelval;
		this.power = power;
		this.windVelval = windVelval;
		this.frequency = frequency;
		this.meastypeId = meastypeId;
		this.measclassId = measclassId;
		this.aveValue = aveValue;
		this.maxData = maxData;
		this.minData = minData;
		this.name = name;
		this.frequency1 = frequency1;
		
		this.calValue0 = calValue0;
	
		this.sunLightVal = sunLightVal;
		
	}





	






	public String getMinTime() {
		return minTime;
	}









	public void setMinTime(String minTime) {
		this.minTime = minTime;
	}









	public double getMinWind() {
		return minWind;
	}









	public void setMinWind(double minWind) {
		this.minWind = minWind;
	}









	public String getMaxTime() {
		return maxTime;
	}









	public void setMaxTime(String maxTime) {
		this.maxTime = maxTime;
	}









	public double getMaxWind() {
		return maxWind;
	}









	public void setMaxWind(double maxWind) {
		this.maxWind = maxWind;
	}









	/**
	 * @return the windSpeed
	 */
	public String getWindSpeed() {
		return windSpeed;
	}









	/**
	 * @return the strId
	 */
	public String getStrId() {
		return strId;
	}









	/**
	 * @param strId the strId to set
	 */
	public void setStrId(String strId) {
		this.strId = strId;
	}









	/**
	 * @param windSpeed the windSpeed to set
	 */
	public void setWindSpeed(String windSpeed) {
		this.windSpeed = windSpeed;
	}









	/**
	 * @return the meanGenSpeed
	 */
	public String getMeanGenSpeed() {
		return meanGenSpeed;
	}









	/**
	 * @param meanGenSpeed the meanGenSpeed to set
	 */
	public void setMeanGenSpeed(String meanGenSpeed) {
		this.meanGenSpeed = meanGenSpeed;
	}









	/**
	 * @return the value1
	 */
	public String getValue1() {
		return value1;
	}





	/**
	 * @param value1 the value1 to set
	 */
	public void setValue1(String value1) {
		this.value1 = value1;
	}





	/**
	 * @return the value2
	 */
	public String getValue2() {
		return value2;
	}





	/**
	 * @param value2 the value2 to set
	 */
	public void setValue2(String value2) {
		this.value2 = value2;
	}





	/**
	 * @return the value3
	 */
	public String getValue3() {
		return value3;
	}





	/**
	 * @param value3 the value3 to set
	 */
	public void setValue3(String value3) {
		this.value3 = value3;
	}





	/**
	 * @return the value4
	 */
	public String getValue4() {
		return value4;
	}





	/**
	 * @param value4 the value4 to set
	 */
	public void setValue4(String value4) {
		this.value4 = value4;
	}





	/**
	 * @return the value5
	 */
	public String getValue5() {
		return value5;
	}





	/**
	 * @param value5 the value5 to set
	 */
	public void setValue5(String value5) {
		this.value5 = value5;
	}





	/**
	 * @return the value6
	 */
	public String getValue6() {
		return value6;
	}





	/**
	 * @param value6 the value6 to set
	 */
	public void setValue6(String value6) {
		this.value6 = value6;
	}





	/**
	 * @return the value7
	 */
	public String getValue7() {
		return value7;
	}





	/**
	 * @param value7 the value7 to set
	 */
	public void setValue7(String value7) {
		this.value7 = value7;
	}





	/**
	 * @return the value8
	 */
	public String getValue8() {
		return value8;
	}





	/**
	 * @param value8 the value8 to set
	 */
	public void setValue8(String value8) {
		this.value8 = value8;
	}





	/**
	 * @return the value9
	 */
	public String getValue9() {
		return value9;
	}





	/**
	 * @param value9 the value9 to set
	 */
	public void setValue9(String value9) {
		this.value9 = value9;
	}





	/**
	 * @return the value10
	 */
	public String getValue10() {
		return value10;
	}





	/**
	 * @param value10 the value10 to set
	 */
	public void setValue10(String value10) {
		this.value10 = value10;
	}





	/**
	 * @return the value11
	 */
	public String getValue11() {
		return value11;
	}





	/**
	 * @param value11 the value11 to set
	 */
	public void setValue11(String value11) {
		this.value11 = value11;
	}





	/**
	 * @return the value12
	 */
	public String getValue12() {
		return value12;
	}





	/**
	 * @param value12 the value12 to set
	 */
	public void setValue12(String value12) {
		this.value12 = value12;
	}





	/**
	 * @return the value13
	 */
	public String getValue13() {
		return value13;
	}





	/**
	 * @param value13 the value13 to set
	 */
	public void setValue13(String value13) {
		this.value13 = value13;
	}





	/**
	 * @return the value14
	 */
	public String getValue14() {
		return value14;
	}





	/**
	 * @param value14 the value14 to set
	 */
	public void setValue14(String value14) {
		this.value14 = value14;
	}





	/**
	 * @return the value15
	 */
	public String getValue15() {
		return value15;
	}





	/**
	 * @param value15 the value15 to set
	 */
	public void setValue15(String value15) {
		this.value15 = value15;
	}





	/**
	 * @return the value16
	 */
	public String getValue16() {
		return value16;
	}





	/**
	 * @param value16 the value16 to set
	 */
	public void setValue16(String value16) {
		this.value16 = value16;
	}





	/**
	 * @return the availability
	 */
	public double getAvailability() {
		return availability;
	}





	/**
	 * @param availability the availability to set
	 */
	public void setAvailability(double availability) {
		this.availability = availability;
	}





	/**
	 * @return the genpwd
	 */
	public BigDecimal getGenpwd() {
		return genpwd;
	}





	/**
	 * @param genpwd the genpwd to set
	 */
	public void setGenpwd(BigDecimal genpwd) {
		this.genpwd = genpwd;
	}





	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}





	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}





	/**
	 * @return the saveTime
	 */
	public Date getSaveTime() {
		return saveTime;
	}





	/**
	 * @param saveTime the saveTime to set
	 */
	public void setSaveTime(Date saveTime) {
		this.saveTime = saveTime;
	}





	/**
	 * @return the aveValue
	 */
	public double getAveValue() {
		return aveValue;
	}





	/**
	 * @param aveValue the aveValue to set
	 */
	public void setAveValue(double aveValue) {
		this.aveValue = aveValue;
	}





	/**
	 * @return the maxData
	 */
	public double getMaxData() {
		return maxData;
	}





	/**
	 * @param maxData the maxData to set
	 */
	public void setMaxData(double maxData) {
		this.maxData = maxData;
	}





	/**
	 * @return the minData
	 */
	public double getMinData() {
		return minData;
	}





	/**
	 * @param minData the minData to set
	 */
	public void setMinData(double minData) {
		this.minData = minData;
	}





	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}





	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}





	/**
	 * @return the meastypeId
	 */
	public int getMeastypeId() {
		return meastypeId;
	}





	/**
	 * @param meastypeId the meastypeId to set
	 */
	public void setMeastypeId(int meastypeId) {
		this.meastypeId = meastypeId;
	}





	/**
	 * @return the measclassId
	 */
	public int getMeasclassId() {
		return measclassId;
	}





	/**
	 * @param measclassId the measclassId to set
	 */
	public void setMeasclassId(int measclassId) {
		this.measclassId = measclassId;
	}


	/**
	 * @return the frequency
	 */
	public Float getFrequency() {
		return frequency;
	}





	public double getWeight() {
		return weight;
	}









	public void setWeight(double weight) {
		this.weight = weight;
	}









	/**
	 * @param frequency the frequency to set
	 */
	public void setFrequency(Float frequency) {
		this.frequency = frequency;
	}


	public String getWindVelval() {
		return windVelval;
	}





	public void setWindVelval(String windVelval) {
		this.windVelval = windVelval;
	}





	/**
	 * @return the power
	 */
	public Float getPower() {
		return power;
	}





	/**
	 * @param power the power to set
	 */
	public void setPower(Float power) {
		this.power = power;
	}





	/**
	 * @return the counts
	 */
	public int getCounts() {
		return counts;
	}





	/**
	 * @param counts the counts to set
	 */
	public void setCounts(int counts) {
		this.counts = counts;
	}





	/**
	 * @return the avgWindVelval
	 */
	public double getAvgWindVelval() {
		return avgWindVelval;
	}





	/**
	 * @param avgWindVelval the avgWindVelval to set
	 */
	public void setAvgWindVelval(double avgWindVelval) {
		this.avgWindVelval = avgWindVelval;
	}





	/**
	 * @return the sumStopTime
	 */
	public Float getSumStopTime() {
		return sumStopTime;
	}





	/**
	 * @param sumStopTime the sumStopTime to set
	 */
	public void setSumStopTime(Float sumStopTime) {
		this.sumStopTime = sumStopTime;
	}





	/**
	 * @return the lastValue
	 */
	public int getLastValue() {
		return lastValue;
	}





	/**
	 * @param lastValue the lastValue to set
	 */
	public void setLastValue(int lastValue) {
		this.lastValue = lastValue;
	}





	/**
	 * @return the happenTime
	 */
	public Date getHappenTime() {
		return happenTime;
	}





	/**
	 * @param happenTime the happenTime to set
	 */
	public void setHappenTime(Date happenTime) {
		this.happenTime = happenTime;
	}





	/**
	 * @return the removeTime
	 */
	public Date getRemoveTime() {
		return removeTime;
	}





	/**
	 * @param removeTime the removeTime to set
	 */
	public void setRemoveTime(Date removeTime) {
		this.removeTime = removeTime;
	}





	/**
	 * @return the durationTime
	 */
	public BigDecimal getDurationTime() {
		return durationTime;
	}





	/**
	 * @param durationTime the durationTime to set
	 */
	public void setDurationTime(BigDecimal durationTime) {
		this.durationTime = durationTime;
	}





	/**
	 * @return the sumDurationTime
	 */
	public BigDecimal getSumDurationTime() {
		return sumDurationTime;
	}





	/**
	 * @param sumDurationTime the sumDurationTime to set
	 */
	public void setSumDurationTime(BigDecimal sumDurationTime) {
		this.sumDurationTime = sumDurationTime;
	}





	/**
	 * @return the windDirVal
	 */
	public String getWindDirVal() {
		return windDirVal;
	}





	/**
	 * @param windDirVal the windDirVal to set
	 */
	public void setWindDirVal(String windDirVal) {
		this.windDirVal = windDirVal;
	}





	/**
	 * @return the avgWindVelvalDu
	 */
	public Float getAvgWindVelvalDu() {
		return avgWindVelvalDu;
	}





	/**
	 * @param avgWindVelvalDu the avgWindVelvalDu to set
	 */
	public void setAvgWindVelvalDu(Float avgWindVelvalDu) {
		this.avgWindVelvalDu = avgWindVelvalDu;
	}





	/**
	 * @return the windVelval1
	 */
	public Float getWindVelval1() {
		return windVelval1;
	}





	/**
	 * @param windVelval1 the windVelval1 to set
	 */
	public void setWindVelval1(Float windVelval1) {
		this.windVelval1 = windVelval1;
	}





	/**
	 * @return the sumMaxTgwh
	 */
	public BigDecimal getSumMaxTgwh() {
		return sumMaxTgwh;
	}





	/**
	 * @param sumMaxTgwh the sumMaxTgwh to set
	 */
	public void setSumMaxTgwh(BigDecimal sumMaxTgwh) {
		this.sumMaxTgwh = sumMaxTgwh;
	}





	/**
	 * @return the avgMaxTgwh
	 */
	public BigDecimal getAvgMaxTgwh() {
		return avgMaxTgwh;
	}





	/**
	 * @param avgMaxTgwh the avgMaxTgwh to set
	 */
	public void setAvgMaxTgwh(BigDecimal avgMaxTgwh) {
		this.avgMaxTgwh = avgMaxTgwh;
	}





	/**
	 * @return the sumCurp
	 */
	public double getSumCurp() {
		return sumCurp;
	}





	/**
	 * @param sumCurp the sumCurp to set
	 */
	public void setSumCurp(double sumCurp) {
		this.sumCurp = sumCurp;
	}





	/**
	 * @return the sumCurq
	 */
	public double getSumCurq() {
		return sumCurq;
	}





	/**
	 * @param sumCurq the sumCurq to set
	 */
	public void setSumCurq(double sumCurq) {
		this.sumCurq = sumCurq;
	}





	public double getMaxCurp() {
		return maxCurp;
	}









	public void setMaxCurp(double maxCurp) {
		this.maxCurp = maxCurp;
	}









	/**
	 * @return the curp
	 */
	public double getCurp() {
		return curp;
	}





	/**
	 * @param curp the curp to set
	 */
	public void setCurp(double curp) {
		this.curp = curp;
	}





	/**
	 * @return the avgPower
	 */
	public Float getAvgPower() {
		return avgPower;
	}





	/**
	 * @param avgPower the avgPower to set
	 */
	public void setAvgPower(Float avgPower) {
		this.avgPower = avgPower;
	}





	/**
	 * @return the power1
	 */
	public Float getPower1() {
		return power1;
	}





	/**
	 * @param power1 the power1 to set
	 */
	public void setPower1(Float power1) {
		this.power1 = power1;
	}





	/**
	 * @return the power2
	 */
	public Float getPower2() {
		return power2;
	}





	/**
	 * @param power2 the power2 to set
	 */
	public void setPower2(Float power2) {
		this.power2 = power2;
	}





	/**
	 * @return the point
	 */
	public Float getPoint() {
		return point;
	}





	/**
	 * @param point the point to set
	 */
	public void setPoint(Float point) {
		this.point = point;
	}





	/**
	 * @return the mgId
	 */
	public int getMgId() {
		return mgId;
	}





	/**
	 * @param mgId the mgId to set
	 */
	public void setMgId(int mgId) {
		this.mgId = mgId;
	}





	/**
	 * @return the curq
	 */
	public BigDecimal getCurq() {
		return curq;
	}





	/**
	 * @param curq the curq to set
	 */
	public void setCurq(BigDecimal curq) {
		this.curq = curq;
	}





	/**
	 * @return the capacity
	 */
	public double getCapacity() {
		return capacity;
	}





	/**
	 * @param capacity the capacity to set
	 */
	public void setCapacity(double capacity) {
		this.capacity = capacity;
	}





	/**
	 * @return the todayGenwh
	 */
	public double getTodayGenwh() {
		return todayGenwh;
	}





	/**
	 * @param todayGenwh the todayGenwh to set
	 */
	public void setTodayGenwh(double todayGenwh) {
		this.todayGenwh = todayGenwh;
	}





	/**
	 * @return the totalGenwh
	 */
	





	/**
	 * @return the windName
	 */
	public String getWindName() {
		return windName;
	}





	/**
	 * @param windName the windName to set
	 */
	public void setWindName(String windName) {
		this.windName = windName;
	}





	/**
	 * @return the windCounts
	 */
	public int getWindCounts() {
		return windCounts;
	}





	/**
	 * @param windCounts the windCounts to set
	 */
	public void setWindCounts(int windCounts) {
		this.windCounts = windCounts;
	}





	/**
	 * @return the equipCounts
	 */
	public int getEquipCounts() {
		return equipCounts;
	}





	/**
	 * @param equipCounts the equipCounts to set
	 */
	public void setEquipCounts(int equipCounts) {
		this.equipCounts = equipCounts;
	}





	/**
	 * @return the yesterdayGenwh
	 */
	public double getYesterdayGenwh() {
		return yesterdayGenwh;
	}





	/**
	 * @param yesterdayGenwh the yesterdayGenwh to set
	 */
	public void setYesterdayGenwh(double yesterdayGenwh) {
		this.yesterdayGenwh = yesterdayGenwh;
	}





	/**
	 * @return the totalGenNumb
	 */
	public int getTotalGenNumb() {
		return totalGenNumb;
	}





	/**
	 * @param totalGenNumb the totalGenNumb to set
	 */
	public void setTotalGenNumb(int totalGenNumb) {
		this.totalGenNumb = totalGenNumb;
	}





	/**
	 * @return the runNumb
	 */
	public int getRunNumb() {
		return runNumb;
	}





	/**
	 * @param runNumb the runNumb to set
	 */
	public void setRunNumb(int runNumb) {
		this.runNumb = runNumb;
	}





	/**
	 * @return the standbyNum
	 */
	public int getStandbyNum() {
		return standbyNum;
	}





	/**
	 * @param standbyNum the standbyNum to set
	 */
	public void setStandbyNum(int standbyNum) {
		this.standbyNum = standbyNum;
	}





	/**
	 * @return the repairNum
	 */
	public int getRepairNum() {
		return repairNum;
	}





	/**
	 * @param repairNum the repairNum to set
	 */
	public void setRepairNum(int repairNum) {
		this.repairNum = repairNum;
	}





	/**
	 * @return the faultNum
	 */
	public int getFaultNum() {
		return faultNum;
	}





	/**
	 * @param faultNum the faultNum to set
	 */
	public void setFaultNum(int faultNum) {
		this.faultNum = faultNum;
	}





	/**
	 * @return the totalGencpty
	 */
	public double getTotalGencpty() {
		return totalGencpty;
	}





	/**
	 * @param totalGencpty the totalGencpty to set
	 */
	public void setTotalGencpty(double totalGencpty) {
		this.totalGencpty = totalGencpty;
	}





	/**
	 * @return the rungencpty
	 */
	public double getRunGencpty() {
		return runGencpty;
	}





	/**
	 * @param rungencpty the rungencpty to set
	 */
	public void setRunGencpty(double runGencpty) {
		this.runGencpty = runGencpty;
	}





	/**
	 * @param totalGenwh the totalGenwh to set
	 */
	public void setTotalGenwh(double totalGenwh) {
		this.totalGenwh = totalGenwh;
	}





	/**
	 * @return the curcmpState
	 */
	public int getCurcmpState() {
		return curcmpState;
	}





	/**
	 * @param curcmpState the curcmpState to set
	 */
	public void setCurcmpState(int curcmpState) {
		this.curcmpState = curcmpState;
	}





	/**
	 * @return the totalGenwh
	 */
	public double getTotalGenwh() {
		return totalGenwh;
	}





	/**
	 * @return the saveTime1
	 */
	public Date getSaveTime1() {
		return saveTime1;
	}





	/**
	 * @param saveTime1 the saveTime1 to set
	 */
	public void setSaveTime1(Date saveTime1) {
		this.saveTime1 = saveTime1;
	}





	






	



	/**
	 * @return the name1
	 */
	public String getName1() {
		return name1;
	}





	/**
	 * @param name1 the name1 to set
	 */
	public void setName1(String name1) {
		this.name1 = name1;
	}





	/**
	 * @return the name2
	 */
	public String getName2() {
		return name2;
	}





	/**
	 * @param name2 the name2 to set
	 */
	public void setName2(String name2) {
		this.name2 = name2;
	}





	/**
	 * @return the name3
	 */
	public String getName3() {
		return name3;
	}





	/**
	 * @param name3 the name3 to set
	 */
	public void setName3(String name3) {
		this.name3 = name3;
	}





	/**
	 * @return the name4
	 */
	public String getName4() {
		return name4;
	}





	/**
	 * @param name4 the name4 to set
	 */
	public void setName4(String name4) {
		this.name4 = name4;
	}





	/**
	 * @return the carbon
	 */
	public double getCarbon() {
		return carbon;
	}





	/**
	 * @param carbon the carbon to set
	 */
	public void setCarbon(double carbon) {
		this.carbon = carbon;
	}





	/**
	 * @return the coal
	 */
	public double getCoal() {
		return coal;
	}





	/**
	 * @param coal the coal to set
	 */
	public void setCoal(double coal) {
		this.coal = coal;
	}





	/**
	 * @return the forest
	 */
	public double getForest() {
		return forest;
	}





	/**
	 * @param forest the forest to set
	 */
	public void setForest(double forest) {
		this.forest = forest;
	}





	/**
	 * @return the longitude
	 */
	public Float getLongitude() {
		return longitude;
	}





	/**
	 * @param longitude the longitude to set
	 */
	public void setLongitude(Float longitude) {
		this.longitude = longitude;
	}





	/**
	 * @return the latitude
	 */
	public Float getLatitude() {
		return latitude;
	}





	/**
	 * @param latitude the latitude to set
	 */
	public void setLatitude(Float latitude) {
		this.latitude = latitude;
	}





	/**
	 * @return the todaySWGenwh
	 */
	public double getTodaySWGenwh() {
		return todaySWGenwh;
	}





	/**
	 * @param todaySWGenwh the todaySWGenwh to set
	 */
	public void setTodaySWGenwh(double todaySWGenwh) {
		this.todaySWGenwh = todaySWGenwh;
	}





	/**
	 * @return the todayShY
	 */
	public double getTodayShY() {
		return todayShY;
	}





	/**
	 * @param todayShY the todayShY to set
	 */
	public void setTodayShY(double todayShY) {
		this.todayShY = todayShY;
	}





	/**
	 * @return the yesterdaySWGenwh
	 */
	public double getYesterdaySWGenwh() {
		return yesterdaySWGenwh;
	}





	/**
	 * @param yesterdaySWGenwh the yesterdaySWGenwh to set
	 */
	public void setYesterdaySWGenwh(double yesterdaySWGenwh) {
		this.yesterdaySWGenwh = yesterdaySWGenwh;
	}





	/**
	 * @return the yesterdayShY
	 */
	public double getYesterdayShY() {
		return yesterdayShY;
	}





	/**
	 * @param yesterdayShY the yesterdayShY to set
	 */
	public void setYesterdayShY(double yesterdayShY) {
		this.yesterdayShY = yesterdayShY;
	}





	/**
	 * @return the windPower
	 */
	public double getWindPower() {
		return windPower;
	}





	/**
	 * @param windPower the windPower to set
	 */
	public void setWindPower(double windPower) {
		this.windPower = windPower;
	}





	/**
	 * @return the windHour
	 */
	public double getWindHour() {
		return windHour;
	}





	/**
	 * @param windHour the windHour to set
	 */
	public void setWindHour(double windHour) {
		this.windHour = windHour;
	}





	/**
	 * @return the hour
	 */
	public double getHour() {
		return hour;
	}





	/**
	 * @param hour the hour to set
	 */
	public void setHour(double hour) {
		this.hour = hour;
	}





	/**
	 * @return the count
	 */
	public double getCount() {
		return count;
	}





	/**
	 * @param count the count to set
	 */
	public void setCount(double count) {
		this.count = count;
	}





	/**
	 * @return the columndes
	 */
	public String getColumndes() {
		return columndes;
	}









	/**
	 * @param columndes the columndes to set
	 */
	public void setColumndes(String columndes) {
		this.columndes = columndes;
	}









	/**
	 * @return the columnName
	 */
	public String getColumnName() {
		return columnName;
	}









	/**
	 * @param columnName the columnName to set
	 */
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}









	/**
	 * @return the countall
	 */
	public double getCountall() {
		return countall;
	}





	/**
	 * @param countall the countall to set
	 */
	public void setCountall(double countall) {
		this.countall = countall;
	}





	/**
	 * @return the rand
	 */
	public int getRand() {
		return rand;
	}





	/**
	 * @param rand the rand to set
	 */
	public void setRand(int rand) {
		this.rand = rand;
	}





	/**
	 * @return the dayTodayGenwh
	 */
	public double getDayTodayGenwh() {
		return dayTodayGenwh;
	}





	/**
	 * @param dayTodayGenwh the dayTodayGenwh to set
	 */
	public void setDayTodayGenwh(double dayTodayGenwh) {
		this.dayTodayGenwh = dayTodayGenwh;
	}





	/**
	 * @return the efficiency
	 */
	public double getEfficiency() {
		return efficiency;
	}





	/**
	 * @param efficiency the efficiency to set
	 */
	public void setEfficiency(double efficiency) {
		this.efficiency = efficiency;
	}





	/**
	 * @return the runTime
	 */
	public double getRunTime() {
		return runTime;
	}





	/**
	 * @param runTime the runTime to set
	 */
	public void setRunTime(double runTime) {
		this.runTime = runTime;
	}





	/**
	 * @return the faultTime
	 */
	public double getFaultTime() {
		return faultTime;
	}





	/**
	 * @param faultTime the faultTime to set
	 */
	public void setFaultTime(double faultTime) {
		this.faultTime = faultTime;
	}





	/**
	 * @return the swcurp
	 */
	public double getSwcurp() {
		return swcurp;
	}





	/**
	 * @param swcurp the swcurp to set
	 */
	public void setSwcurp(double swcurp) {
		this.swcurp = swcurp;
	}





	/**
	 * @return the frequency1
	 */
	public double getFrequency1() {
		return frequency1;
	}





	/**
	 * @param frequency1 the frequency1 to set
	 */
	public void setFrequency1(double frequency1) {
		this.frequency1 = frequency1;
	}





	/**
	 * @return the totalSWgenwhSY
	 */
	public double getTotalSWGenwhSY() {
		return totalSWGenwhSY;
	}





	/**
	 * @param totalSWgenwhSY the totalSWgenwhSY to set
	 */
	public void setTotalSWGenwhSY(double totalSWGenwhSY) {
		this.totalSWGenwhSY = totalSWGenwhSY;
	}



	public double getSunLightVal() {
		return sunLightVal;
	}

	/**
	 * @param sunLightVal the sunLightVal to set
	 */
	public void setSunLightVal(double sunLightVal) {
		this.sunLightVal = sunLightVal;
	}

	




	
}

	