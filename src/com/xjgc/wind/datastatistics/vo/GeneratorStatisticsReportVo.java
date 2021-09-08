package com.xjgc.wind.datastatistics.vo;

import java.util.Date;

public class GeneratorStatisticsReportVo {

	
	String name; //name
	int id; //id
	Date saveTime;
	Double availability;//可利用率
	Double totalGenwh;//发电量
	Double winTurErrSec;//故障时间
	double hours;//有效利用小时数
	double minWeatherval;//切入风速
	double maxWeatherval;//切出风速
	double max_windspeed; //风速
	double avg_windspeed; //风速
	double min_windspeed; //风速 
	double max_P; //有功
	double avg_P; //有功
	double min_P;  //有功
	double max_Q; //无功
	double avg_Q; //无功
	double min_Q; //无功
	double max_Temp; //环境温度
	double avg_Temp; //环境温度
	double min_Temp; //环境温度
	
	double lftHour;//左偏航总时间
	double rtghHour;//右偏航总时间
	double yawHour; //偏航总时间
	double lftYawMotorCWCou; //左偏航总次数
	double rtghYawMotorCCWCou; //右偏航总次数
	double yawCWCou; //偏航总次数
	
	double daygenwh; //日发电量
	double monthgenwh; //月发电量
	double yeargenwh; //年发电量
	double winTurStCovCont; //停机总次数
	double serModTimes; //维护次数
	double winTurErrCovCont; //故障停机次数
	double convMaiSwitchCou; //并网次数
	double winHigErrTimes;  //大风停机次数
	double winTurCatInCont; //切入次数
	double winTurArtStpCont; //人工停机次数
	
	double monAvlbltRat; //月可利用率
	double yeaAvlbltRat; //年可利用率
	double monAvlbltHour; //月可利用小时
	double monNormTim; //月运行小时
	double monRunTim; //月发电小时数
	double monStopTim; //月停机小时数"
	double monErrEmeTim;//月故障小时数"
	double monSerTim; //月维护小时数
	double monGridErrTim ; //电网月故障时间
	
	
	/**
	 * @return the availability
	 */
	public Double getAvailability() {
		return availability;
	}
	/**
	 * @param availability the availability to set
	 */
	public void setAvailability(Double availability) {
		this.availability = availability;
	}
	/**
	 * @return the totalGenwh
	 */
	public Double getTotalGenwh() {
		return totalGenwh;
	}
	/**
	 * @param totalGenwh the totalGenwh to set
	 */
	public void setTotalGenwh(Double totalGenwh) {
		this.totalGenwh = totalGenwh;
	}
	/**
	 * @return the winTurErrSec
	 */
	public Double getWinTurErrSec() {
		return winTurErrSec;
	}
	/**
	 * @param winTurErrSec the winTurErrSec to set
	 */
	public void setWinTurErrSec(Double winTurErrSec) {
		this.winTurErrSec = winTurErrSec;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getMax_windspeed() {
		return max_windspeed;
	}
	public void setMax_windspeed(double max_windspeed) {
		this.max_windspeed = max_windspeed;
	}
	public double getAvg_windspeed() {
		return avg_windspeed;
	}
	public void setAvg_windspeed(double avg_windspeed) {
		this.avg_windspeed = avg_windspeed;
	}
	public double getMin_windspeed() {
		return min_windspeed;
	}
	public void setMin_windspeed(double min_windspeed) {
		this.min_windspeed = min_windspeed;
	}
	public double getMax_P() {
		return max_P;
	}
	public void setMax_P(double max_P) {
		this.max_P = max_P;
	}
	public double getAvg_P() {
		return avg_P;
	}
	public void setAvg_P(double avg_P) {
		this.avg_P = avg_P;
	}
	public double getMin_P() {
		return min_P;
	}
	public void setMin_P(double min_P) {
		this.min_P = min_P;
	}
	public double getMax_Q() {
		return max_Q;
	}
	public void setMax_Q(double max_Q) {
		this.max_Q = max_Q;
	}
	public double getAvg_Q() {
		return avg_Q;
	}
	public void setAvg_Q(double avg_Q) {
		this.avg_Q = avg_Q;
	}
	public double getMin_Q() {
		return min_Q;
	}
	public void setMin_Q(double min_Q) {
		this.min_Q = min_Q;
	}
	public double getMax_Temp() {
		return max_Temp;
	}
	public void setMax_Temp(double max_Temp) {
		this.max_Temp = max_Temp;
	}
	public double getAvg_Temp() {
		return avg_Temp;
	}
	public void setAvg_Temp(double avg_Temp) {
		this.avg_Temp = avg_Temp;
	}
	public double getMin_Temp() {
		return min_Temp;
	}
	public void setMin_Temp(double min_Temp) {
		this.min_Temp = min_Temp;
	}
	public double getLftHour() {
		return lftHour;
	}
	public void setLftHour(double lftHour) {
		this.lftHour = lftHour;
	}
	public double getRtghHour() {
		return rtghHour;
	}
	public void setRtghHour(double rtghHour) {
		this.rtghHour = rtghHour;
	}
	public double getYawHour() {
		return yawHour;
	}
	public void setYawHour(double yawHour) {
		this.yawHour = yawHour;
	}

	public double getDaygenwh() {
		return daygenwh;
	}
	public void setDaygenwh(double daygenwh) {
		this.daygenwh = daygenwh;
	}
	public double getMonthgenwh() {
		return monthgenwh;
	}
	public void setMonthgenwh(double monthgenwh) {
		this.monthgenwh = monthgenwh;
	}
	public double getYeargenwh() {
		return yeargenwh;
	}
	public void setYeargenwh(double yeargenwh) {
		this.yeargenwh = yeargenwh;
	}
	
	public double getLftYawMotorCWCou() {
		return lftYawMotorCWCou;
	}
	public void setLftYawMotorCWCou(double lftYawMotorCWCou) {
		this.lftYawMotorCWCou = lftYawMotorCWCou;
	}
	public double getRtghYawMotorCCWCou() {
		return rtghYawMotorCCWCou;
	}
	public void setRtghYawMotorCCWCou(double rtghYawMotorCCWCou) {
		this.rtghYawMotorCCWCou = rtghYawMotorCCWCou;
	}
	public double getYawCWCou() {
		return yawCWCou;
	}
	public void setYawCWCou(double yawCWCou) {
		this.yawCWCou = yawCWCou;
	}
	public double getWinTurStCovCont() {
		return winTurStCovCont;
	}
	public void setWinTurStCovCont(double winTurStCovCont) {
		this.winTurStCovCont = winTurStCovCont;
	}
	public double getSerModTimes() {
		return serModTimes;
	}
	public void setSerModTimes(double serModTimes) {
		this.serModTimes = serModTimes;
	}
	public double getWinTurErrCovCont() {
		return winTurErrCovCont;
	}
	public void setWinTurErrCovCont(double winTurErrCovCont) {
		this.winTurErrCovCont = winTurErrCovCont;
	}
	public double getConvMaiSwitchCou() {
		return convMaiSwitchCou;
	}
	public void setConvMaiSwitchCou(double convMaiSwitchCou) {
		this.convMaiSwitchCou = convMaiSwitchCou;
	}
	public double getWinHigErrTimes() {
		return winHigErrTimes;
	}
	public void setWinHigErrTimes(double winHigErrTimes) {
		this.winHigErrTimes = winHigErrTimes;
	}
	public double getWinTurCatInCont() {
		return winTurCatInCont;
	}
	public void setWinTurCatInCont(double winTurCatInCont) {
		this.winTurCatInCont = winTurCatInCont;
	}
	public double getWinTurArtStpCont() {
		return winTurArtStpCont;
	}
	public void setWinTurArtStpCont(double winTurArtStpCont) {
		this.winTurArtStpCont = winTurArtStpCont;
	}
	public double getMonAvlbltRat() {
		return monAvlbltRat;
	}
	public void setMonAvlbltRat(double monAvlbltRat) {
		this.monAvlbltRat = monAvlbltRat;
	}
	public double getYeaAvlbltRat() {
		return yeaAvlbltRat;
	}
	public void setYeaAvlbltRat(double yeaAvlbltRat) {
		this.yeaAvlbltRat = yeaAvlbltRat;
	}
	public double getMonAvlbltHour() {
		return monAvlbltHour;
	}
	public void setMonAvlbltHour(double monAvlbltHour) {
		this.monAvlbltHour = monAvlbltHour;
	}
	public double getMonNormTim() {
		return monNormTim;
	}
	public void setMonNormTim(double monNormTim) {
		this.monNormTim = monNormTim;
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
	public double getMonRunTim() {
		return monRunTim;
	}
	public void setMonRunTim(double monRunTim) {
		this.monRunTim = monRunTim;
	}
	public double getMonStopTim() {
		return monStopTim;
	}
	public void setMonStopTim(double monStopTim) {
		this.monStopTim = monStopTim;
	}
	public double getMonErrEmeTim() {
		return monErrEmeTim;
	}
	public void setMonErrEmeTim(double monErrEmeTim) {
		this.monErrEmeTim = monErrEmeTim;
	}
	public double getMonSerTim() {
		return monSerTim;
	}
	public void setMonSerTim(double monSerTim) {
		this.monSerTim = monSerTim;
	}
	public double getMonGridErrTim() {
		return monGridErrTim;
	}
	public void setMonGridErrTim(double monGridErrTim) {
		this.monGridErrTim = monGridErrTim;
	}
	@Override
	public String toString() {
		return "GeneratorStatisticsMonReportVo [name=" + name + ", id=" + id
				+ ", max_windspeed=" + max_windspeed + ", avg_windspeed="
				+ avg_windspeed + ", min_windspeed=" + min_windspeed
				+ ", max_P=" + max_P + ", avg_P=" + avg_P + ", min_P=" + min_P
				+ ", max_Q=" + max_Q + ", avg_Q=" + avg_Q + ", min_Q=" + min_Q
				+ ", max_Temp=" + max_Temp + ", avg_Temp=" + avg_Temp
				+ ", min_Temp=" + min_Temp + ", lftHour=" + lftHour
				+ ", rtghHour=" + rtghHour + ", yawHour=" + yawHour
				+ ", lftYawMotorCWCou=" + lftYawMotorCWCou
				+ ", rtghYawMotorCCWCou=" + rtghYawMotorCCWCou + ", yawCWCou="
				+ yawCWCou + ", daygenwh=" + daygenwh + ", monthgenwh="
				+ monthgenwh + ", yeargenwh=" + yeargenwh
				+ ", winTurStCovCont=" + winTurStCovCont + ", serModTimes="
				+ serModTimes + ", winTurErrCovCont=" + winTurErrCovCont
				+ ", convMaiSwitchCou=" + convMaiSwitchCou
				+ ", winHigErrTimes=" + winHigErrTimes + ", winTurCatInCont="
				+ winTurCatInCont + ", winTurArtStpCont=" + winTurArtStpCont
				+ ", monAvlbltRat=" + monAvlbltRat + ", yeaAvlbltRat="
				+ yeaAvlbltRat + ", monAvlbltHour=" + monAvlbltHour
				+ ", monNormTim=" + monNormTim + ", monRunTim=" + monRunTim
				+ ", monStopTim=" + monStopTim + ", monErrEmeTim="
				+ monErrEmeTim + ", monSerTim=" + monSerTim
				+ ", monGridErrTim=" + monGridErrTim + "]";
	}
	/**
	 * @return the minWeatherval
	 */
	public double getMinWeatherval() {
		return minWeatherval;
	}
	/**
	 * @param minWeatherval the minWeatherval to set
	 */
	public void setMinWeatherval(double minWeatherval) {
		this.minWeatherval = minWeatherval;
	}
	/**
	 * @return the maxWeatherval
	 */
	public double getMaxWeatherval() {
		return maxWeatherval;
	}
	/**
	 * @param maxWeatherval the maxWeatherval to set
	 */
	public void setMaxWeatherval(double maxWeatherval) {
		this.maxWeatherval = maxWeatherval;
	}
	/**
	 * @return the hours
	 */
	public double getHours() {
		return hours;
	}
	/**
	 * @param hours the hours to set
	 */
	public void setHours(double hours) {
		this.hours = hours;
	}
	
	
	
	

	
}






