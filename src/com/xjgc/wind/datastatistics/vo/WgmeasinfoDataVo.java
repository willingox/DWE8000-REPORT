package com.xjgc.wind.datastatistics.vo;

public class WgmeasinfoDataVo {

	
	String name; //name
	int id; //id
	
	double rctPowGri; //无功功率
	double meanRotorSpeed; //叶轮转速
	double positionActual1; //叶片1桨角 
	double batVoltageUdc1; //变桨电容1电压
	double bladeTorque1; //变桨电机1扭矩
	double motorTemp1;  //变桨电机1温度
	double cabTemp1;  //变桨柜1温度
	double moduleTemp1; //变桨变频器1温度
	double voltageUdc1; //变桨变频器1直流母线电压
	double heaterSinkTemp1; //变桨变频器1散热板温度
	
	double positionActual2; //叶片2桨角 
	double batVoltageUdc2; //变桨电容2电压
	double bladeTorque2; //变桨电机2扭矩
	double motorTemp2;  //变桨电机2温度
	double cabTemp2;  //变桨柜2温度
	double moduleTemp2; //变桨变频器2温度
	double voltageUdc2; //变桨变频器2直流母线电压
	double heaterSinkTemp2; //变桨变频器2散热板温度
	
	double positionActual3; //叶片3桨角 
	double batVoltageUdc3; //变桨电容3电压
	double bladeTorque3; //变桨电机3扭矩
	double motorTemp3;  //变桨电机3温度
	double cabTemp3;  //变桨柜3温度
	double moduleTemp3; //变桨变频器3温度
	double voltageUdc3; //变桨变频器3直流母线电压
	double heaterSinkTemp3; //变桨变频器3散热板温度
	
	
	double gearOilPumPres;//齿轮箱油泵压力
	double gearOilInPres;//齿轮箱进油口压力
	double gearOilInTemp;//齿轮箱进油口温度
	double geaOilTankTemp;//齿轮箱油箱温度
	double geaOilHeaTemp;//齿轮箱油加热器温度
	double geaBeaTemp1;//齿轮箱一级轴承温度
	double geaBeaTemp2;//齿轮箱二级轴承温度
	double geaBeaTemp3;//齿轮箱三级轴承温度
	double maxGeaBeaTemp;//齿轮箱最大轴承温度

	
	double genBearDETemp;//发电机轴承驱动端温度
	double genBearNDETemp;//发电机轴承非驱动端温度
	double genWatInTemp;//发电机进水口温度
	double genTempU;//发电机A相定子绕组温度
	double genTempV;//发电机B相定子绕组温度
	double genTempW;//发电机C相定子绕组温度

	double powMeaSorCurI;//电网相电流
	double powMeaLinFrq;//电网频率
	double actPowAuxiliary;//风机有功消耗
	double meanGscTemp;//机侧IGBT温度
	double meanLscTemp;//变流器入水口温度
	double winSpeTur0;//风速湍流强度
	double nacPsnErrDem;//风向与机舱方向的偏差
	double outsideTemp;//环境温度
	double nacBoxOutsideTemp;//机舱温度
	
	double yawCalspeed;//偏航电机转速
	double meanTBCInTemp;//塔基柜温度
	double meanNC310InTemp;//机舱柜温度
	double meanTBCOutTemp;//塔筒温度
	double powMeaPf;//功率因数
	double powRat;//有功功率限值
	double cableTwistTotal;//扭缆角度
	double volU12;//线电压A-B
	double volU23;//线电压B-C
	double volU31;//线电压C-A
	double curI1;	//相电流I1
	double curI2;	//相电流I2
	double curI3;//相电流I3
	
	double powLmtHMIWPC;//有功功率限制值
	double rePowLmtHMIWPC;//无功功率限制值
	double speLmtHMIWPC;//发电机转速限制值
	double winSpeVal0;//风速仪1瞬时风速
	double winSpeVal1;//风速仪2瞬时风速
	double auxTransforTemp;//辅助变压器温度
	double actPowGri;//有功功率
	double windSpeed;//风速
	double winDirNor;//风向
	double meanGenSpeed;//发电机转速
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
	 * @return the rctPowGri
	 */
	public double getRctPowGri() {
		return rctPowGri;
	}
	/**
	 * @param rctPowGri the rctPowGri to set
	 */
	public void setRctPowGri(double rctPowGri) {
		this.rctPowGri = rctPowGri;
	}
	/**
	 * @return the meanRotorSpeed
	 */
	public double getMeanRotorSpeed() {
		return meanRotorSpeed;
	}
	/**
	 * @param meanRotorSpeed the meanRotorSpeed to set
	 */
	public void setMeanRotorSpeed(double meanRotorSpeed) {
		this.meanRotorSpeed = meanRotorSpeed;
	}
	/**
	 * @return the positionActual1
	 */
	public double getPositionActual1() {
		return positionActual1;
	}
	/**
	 * @param positionActual1 the positionActual1 to set
	 */
	public void setPositionActual1(double positionActual1) {
		this.positionActual1 = positionActual1;
	}
	/**
	 * @return the batVoltageUdc1
	 */
	public double getBatVoltageUdc1() {
		return batVoltageUdc1;
	}
	/**
	 * @param batVoltageUdc1 the batVoltageUdc1 to set
	 */
	public void setBatVoltageUdc1(double batVoltageUdc1) {
		this.batVoltageUdc1 = batVoltageUdc1;
	}
	/**
	 * @return the bladeTorque1
	 */
	public double getBladeTorque1() {
		return bladeTorque1;
	}
	/**
	 * @param bladeTorque1 the bladeTorque1 to set
	 */
	public void setBladeTorque1(double bladeTorque1) {
		this.bladeTorque1 = bladeTorque1;
	}
	/**
	 * @return the motorTemp1
	 */
	public double getMotorTemp1() {
		return motorTemp1;
	}
	/**
	 * @param motorTemp1 the motorTemp1 to set
	 */
	public void setMotorTemp1(double motorTemp1) {
		this.motorTemp1 = motorTemp1;
	}
	/**
	 * @return the cabTemp1
	 */
	public double getCabTemp1() {
		return cabTemp1;
	}
	/**
	 * @param cabTemp1 the cabTemp1 to set
	 */
	public void setCabTemp1(double cabTemp1) {
		this.cabTemp1 = cabTemp1;
	}
	/**
	 * @return the moduleTemp1
	 */
	public double getModuleTemp1() {
		return moduleTemp1;
	}
	/**
	 * @param moduleTemp1 the moduleTemp1 to set
	 */
	public void setModuleTemp1(double moduleTemp1) {
		this.moduleTemp1 = moduleTemp1;
	}
	/**
	 * @return the voltageUdc1
	 */
	public double getVoltageUdc1() {
		return voltageUdc1;
	}
	/**
	 * @param voltageUdc1 the voltageUdc1 to set
	 */
	public void setVoltageUdc1(double voltageUdc1) {
		this.voltageUdc1 = voltageUdc1;
	}
	/**
	 * @return the heaterSinkTemp1
	 */
	public double getHeaterSinkTemp1() {
		return heaterSinkTemp1;
	}
	/**
	 * @param heaterSinkTemp1 the heaterSinkTemp1 to set
	 */
	public void setHeaterSinkTemp1(double heaterSinkTemp1) {
		this.heaterSinkTemp1 = heaterSinkTemp1;
	}
	/**
	 * @return the positionActual2
	 */
	public double getPositionActual2() {
		return positionActual2;
	}
	/**
	 * @param positionActual2 the positionActual2 to set
	 */
	public void setPositionActual2(double positionActual2) {
		this.positionActual2 = positionActual2;
	}
	/**
	 * @return the batVoltageUdc2
	 */
	public double getBatVoltageUdc2() {
		return batVoltageUdc2;
	}
	/**
	 * @param batVoltageUdc2 the batVoltageUdc2 to set
	 */
	public void setBatVoltageUdc2(double batVoltageUdc2) {
		this.batVoltageUdc2 = batVoltageUdc2;
	}
	/**
	 * @return the bladeTorque2
	 */
	public double getBladeTorque2() {
		return bladeTorque2;
	}
	/**
	 * @param bladeTorque2 the bladeTorque2 to set
	 */
	public void setBladeTorque2(double bladeTorque2) {
		this.bladeTorque2 = bladeTorque2;
	}
	/**
	 * @return the motorTemp2
	 */
	public double getMotorTemp2() {
		return motorTemp2;
	}
	/**
	 * @param motorTemp2 the motorTemp2 to set
	 */
	public void setMotorTemp2(double motorTemp2) {
		this.motorTemp2 = motorTemp2;
	}
	/**
	 * @return the cabTemp2
	 */
	public double getCabTemp2() {
		return cabTemp2;
	}
	/**
	 * @param cabTemp2 the cabTemp2 to set
	 */
	public void setCabTemp2(double cabTemp2) {
		this.cabTemp2 = cabTemp2;
	}
	/**
	 * @return the moduleTemp2
	 */
	public double getModuleTemp2() {
		return moduleTemp2;
	}
	/**
	 * @param moduleTemp2 the moduleTemp2 to set
	 */
	public void setModuleTemp2(double moduleTemp2) {
		this.moduleTemp2 = moduleTemp2;
	}
	/**
	 * @return the voltageUdc2
	 */
	public double getVoltageUdc2() {
		return voltageUdc2;
	}
	/**
	 * @param voltageUdc2 the voltageUdc2 to set
	 */
	public void setVoltageUdc2(double voltageUdc2) {
		this.voltageUdc2 = voltageUdc2;
	}
	/**
	 * @return the heaterSinkTemp2
	 */
	public double getHeaterSinkTemp2() {
		return heaterSinkTemp2;
	}
	/**
	 * @param heaterSinkTemp2 the heaterSinkTemp2 to set
	 */
	public void setHeaterSinkTemp2(double heaterSinkTemp2) {
		this.heaterSinkTemp2 = heaterSinkTemp2;
	}
	/**
	 * @return the positionActual3
	 */
	public double getPositionActual3() {
		return positionActual3;
	}
	/**
	 * @param positionActual3 the positionActual3 to set
	 */
	public void setPositionActual3(double positionActual3) {
		this.positionActual3 = positionActual3;
	}
	/**
	 * @return the batVoltageUdc3
	 */
	public double getBatVoltageUdc3() {
		return batVoltageUdc3;
	}
	/**
	 * @param batVoltageUdc3 the batVoltageUdc3 to set
	 */
	public void setBatVoltageUdc3(double batVoltageUdc3) {
		this.batVoltageUdc3 = batVoltageUdc3;
	}
	/**
	 * @return the bladeTorque3
	 */
	public double getBladeTorque3() {
		return bladeTorque3;
	}
	/**
	 * @param bladeTorque3 the bladeTorque3 to set
	 */
	public void setBladeTorque3(double bladeTorque3) {
		this.bladeTorque3 = bladeTorque3;
	}
	/**
	 * @return the motorTemp3
	 */
	public double getMotorTemp3() {
		return motorTemp3;
	}
	/**
	 * @param motorTemp3 the motorTemp3 to set
	 */
	public void setMotorTemp3(double motorTemp3) {
		this.motorTemp3 = motorTemp3;
	}
	/**
	 * @return the cabTemp3
	 */
	public double getCabTemp3() {
		return cabTemp3;
	}
	/**
	 * @param cabTemp3 the cabTemp3 to set
	 */
	public void setCabTemp3(double cabTemp3) {
		this.cabTemp3 = cabTemp3;
	}
	/**
	 * @return the moduleTemp3
	 */
	public double getModuleTemp3() {
		return moduleTemp3;
	}
	/**
	 * @param moduleTemp3 the moduleTemp3 to set
	 */
	public void setModuleTemp3(double moduleTemp3) {
		this.moduleTemp3 = moduleTemp3;
	}
	/**
	 * @return the voltageUdc3
	 */
	public double getVoltageUdc3() {
		return voltageUdc3;
	}
	/**
	 * @param voltageUdc3 the voltageUdc3 to set
	 */
	public void setVoltageUdc3(double voltageUdc3) {
		this.voltageUdc3 = voltageUdc3;
	}
	/**
	 * @return the heaterSinkTemp3
	 */
	public double getHeaterSinkTemp3() {
		return heaterSinkTemp3;
	}
	/**
	 * @param heaterSinkTemp3 the heaterSinkTemp3 to set
	 */
	public void setHeaterSinkTemp3(double heaterSinkTemp3) {
		this.heaterSinkTemp3 = heaterSinkTemp3;
	}
	/**
	 * @return the gearOilPumPres
	 */
	public double getGearOilPumPres() {
		return gearOilPumPres;
	}
	/**
	 * @param gearOilPumPres the gearOilPumPres to set
	 */
	public void setGearOilPumPres(double gearOilPumPres) {
		this.gearOilPumPres = gearOilPumPres;
	}
	/**
	 * @return the gearOilInPres
	 */
	public double getGearOilInPres() {
		return gearOilInPres;
	}
	/**
	 * @param gearOilInPres the gearOilInPres to set
	 */
	public void setGearOilInPres(double gearOilInPres) {
		this.gearOilInPres = gearOilInPres;
	}
	/**
	 * @return the gearOilInTemp
	 */
	public double getGearOilInTemp() {
		return gearOilInTemp;
	}
	/**
	 * @param gearOilInTemp the gearOilInTemp to set
	 */
	public void setGearOilInTemp(double gearOilInTemp) {
		this.gearOilInTemp = gearOilInTemp;
	}
	/**
	 * @return the geaOilTankTemp
	 */
	public double getGeaOilTankTemp() {
		return geaOilTankTemp;
	}
	/**
	 * @param geaOilTankTemp the geaOilTankTemp to set
	 */
	public void setGeaOilTankTemp(double geaOilTankTemp) {
		this.geaOilTankTemp = geaOilTankTemp;
	}
	/**
	 * @return the geaOilHeaTemp
	 */
	public double getGeaOilHeaTemp() {
		return geaOilHeaTemp;
	}
	/**
	 * @param geaOilHeaTemp the geaOilHeaTemp to set
	 */
	public void setGeaOilHeaTemp(double geaOilHeaTemp) {
		this.geaOilHeaTemp = geaOilHeaTemp;
	}
	/**
	 * @return the geaBeaTemp1
	 */
	public double getGeaBeaTemp1() {
		return geaBeaTemp1;
	}
	/**
	 * @param geaBeaTemp1 the geaBeaTemp1 to set
	 */
	public void setGeaBeaTemp1(double geaBeaTemp1) {
		this.geaBeaTemp1 = geaBeaTemp1;
	}
	/**
	 * @return the geaBeaTemp2
	 */
	public double getGeaBeaTemp2() {
		return geaBeaTemp2;
	}
	/**
	 * @param geaBeaTemp2 the geaBeaTemp2 to set
	 */
	public void setGeaBeaTemp2(double geaBeaTemp2) {
		this.geaBeaTemp2 = geaBeaTemp2;
	}
	/**
	 * @return the geaBeaTemp3
	 */
	public double getGeaBeaTemp3() {
		return geaBeaTemp3;
	}
	/**
	 * @param geaBeaTemp3 the geaBeaTemp3 to set
	 */
	public void setGeaBeaTemp3(double geaBeaTemp3) {
		this.geaBeaTemp3 = geaBeaTemp3;
	}
	/**
	 * @return the maxGeaBeaTemp
	 */
	public double getMaxGeaBeaTemp() {
		return maxGeaBeaTemp;
	}
	/**
	 * @param maxGeaBeaTemp the maxGeaBeaTemp to set
	 */
	public void setMaxGeaBeaTemp(double maxGeaBeaTemp) {
		this.maxGeaBeaTemp = maxGeaBeaTemp;
	}
	/**
	 * @return the genBearDETemp
	 */
	public double getGenBearDETemp() {
		return genBearDETemp;
	}
	/**
	 * @param genBearDETemp the genBearDETemp to set
	 */
	public void setGenBearDETemp(double genBearDETemp) {
		this.genBearDETemp = genBearDETemp;
	}
	/**
	 * @return the genBearNDETemp
	 */
	public double getGenBearNDETemp() {
		return genBearNDETemp;
	}
	/**
	 * @param genBearNDETemp the genBearNDETemp to set
	 */
	public void setGenBearNDETemp(double genBearNDETemp) {
		this.genBearNDETemp = genBearNDETemp;
	}
	/**
	 * @return the genWatInTemp
	 */
	public double getGenWatInTemp() {
		return genWatInTemp;
	}
	/**
	 * @param genWatInTemp the genWatInTemp to set
	 */
	public void setGenWatInTemp(double genWatInTemp) {
		this.genWatInTemp = genWatInTemp;
	}
	/**
	 * @return the genTempU
	 */
	public double getGenTempU() {
		return genTempU;
	}
	/**
	 * @param genTempU the genTempU to set
	 */
	public void setGenTempU(double genTempU) {
		this.genTempU = genTempU;
	}
	/**
	 * @return the genTempV
	 */
	public double getGenTempV() {
		return genTempV;
	}
	/**
	 * @param genTempV the genTempV to set
	 */
	public void setGenTempV(double genTempV) {
		this.genTempV = genTempV;
	}
	/**
	 * @return the genTempW
	 */
	public double getGenTempW() {
		return genTempW;
	}
	/**
	 * @param genTempW the genTempW to set
	 */
	public void setGenTempW(double genTempW) {
		this.genTempW = genTempW;
	}
	/**
	 * @return the powMeaSorCurI
	 */
	public double getPowMeaSorCurI() {
		return powMeaSorCurI;
	}
	/**
	 * @param powMeaSorCurI1 the powMeaSorCurI1 to set
	 */
	public void setPowMeaSorCurI(double powMeaSorCurI) {
		this.powMeaSorCurI = powMeaSorCurI;
	}
	/**
	 * @return the powMeaLinFrq
	 */
	public double getPowMeaLinFrq() {
		return powMeaLinFrq;
	}
	/**
	 * @param powMeaLinFrq the powMeaLinFrq to set
	 */
	public void setPowMeaLinFrq(double powMeaLinFrq) {
		this.powMeaLinFrq = powMeaLinFrq;
	}
	/**
	 * @return the actPowAuxiliary
	 */
	public double getActPowAuxiliary() {
		return actPowAuxiliary;
	}
	/**
	 * @param actPowAuxiliary the actPowAuxiliary to set
	 */
	public void setActPowAuxiliary(double actPowAuxiliary) {
		this.actPowAuxiliary = actPowAuxiliary;
	}
	/**
	 * @return the meanGscTemp
	 */
	public double getMeanGscTemp() {
		return meanGscTemp;
	}
	/**
	 * @param meanGscTemp the meanGscTemp to set
	 */
	public void setMeanGscTemp(double meanGscTemp) {
		this.meanGscTemp = meanGscTemp;
	}
	/**
	 * @return the meanLscTemp
	 */
	public double getMeanLscTemp() {
		return meanLscTemp;
	}
	/**
	 * @param meanLscTemp the meanLscTemp to set
	 */
	public void setMeanLscTemp(double meanLscTemp) {
		this.meanLscTemp = meanLscTemp;
	}
	/**
	 * @return the winSpeTur0
	 */
	public double getWinSpeTur0() {
		return winSpeTur0;
	}
	/**
	 * @param winSpeTur0 the winSpeTur0 to set
	 */
	public void setWinSpeTur0(double winSpeTur0) {
		this.winSpeTur0 = winSpeTur0;
	}
	/**
	 * @return the nacPsnErrDem
	 */
	public double getNacPsnErrDem() {
		return nacPsnErrDem;
	}
	/**
	 * @param nacPsnErrDem the nacPsnErrDem to set
	 */
	public void setNacPsnErrDem(double nacPsnErrDem) {
		this.nacPsnErrDem = nacPsnErrDem;
	}
	/**
	 * @return the outsideTemp
	 */
	public double getOutsideTemp() {
		return outsideTemp;
	}
	/**
	 * @param outsideTemp the outsideTemp to set
	 */
	public void setOutsideTemp(double outsideTemp) {
		this.outsideTemp = outsideTemp;
	}
	/**
	 * @return the nacBoxOutsideTemp
	 */
	public double getNacBoxOutsideTemp() {
		return nacBoxOutsideTemp;
	}
	/**
	 * @param nacBoxOutsideTemp the nacBoxOutsideTemp to set
	 */
	public void setNacBoxOutsideTemp(double nacBoxOutsideTemp) {
		this.nacBoxOutsideTemp = nacBoxOutsideTemp;
	}
	/**
	 * @return the yawCalspeed
	 */
	public double getYawCalspeed() {
		return yawCalspeed;
	}
	/**
	 * @param yawCalspeed the yawCalspeed to set
	 */
	public void setYawCalspeed(double yawCalspeed) {
		this.yawCalspeed = yawCalspeed;
	}
	/**
	 * @return the meanTBCInTemp
	 */
	public double getMeanTBCInTemp() {
		return meanTBCInTemp;
	}
	/**
	 * @param meanTBCInTemp the meanTBCInTemp to set
	 */
	public void setMeanTBCInTemp(double meanTBCInTemp) {
		this.meanTBCInTemp = meanTBCInTemp;
	}
	/**
	 * @return the meanNC310InTemp
	 */
	public double getMeanNC310InTemp() {
		return meanNC310InTemp;
	}
	/**
	 * @param meanNC310InTemp the meanNC310InTemp to set
	 */
	public void setMeanNC310InTemp(double meanNC310InTemp) {
		this.meanNC310InTemp = meanNC310InTemp;
	}
	/**
	 * @return the meanTBCOutTemp
	 */
	public double getMeanTBCOutTemp() {
		return meanTBCOutTemp;
	}
	/**
	 * @param meanTBCOutTemp the meanTBCOutTemp to set
	 */
	public void setMeanTBCOutTemp(double meanTBCOutTemp) {
		this.meanTBCOutTemp = meanTBCOutTemp;
	}
	/**
	 * @return the powMeaPf
	 */
	public double getPowMeaPf() {
		return powMeaPf;
	}
	/**
	 * @param powMeaPf the powMeaPf to set
	 */
	public void setPowMeaPf(double powMeaPf) {
		this.powMeaPf = powMeaPf;
	}
	/**
	 * @return the powRat
	 */
	public double getPowRat() {
		return powRat;
	}
	/**
	 * @param powRat the powRat to set
	 */
	public void setPowRat(double powRat) {
		this.powRat = powRat;
	}
	/**
	 * @return the cableTwistTotal
	 */
	public double getCableTwistTotal() {
		return cableTwistTotal;
	}
	/**
	 * @param cableTwistTotal the cableTwistTotal to set
	 */
	public void setCableTwistTotal(double cableTwistTotal) {
		cableTwistTotal = cableTwistTotal;
	}
	/**
	 * @return the volU12
	 */
	public double getVolU12() {
		return volU12;
	}
	/**
	 * @param volU12 the volU12 to set
	 */
	public void setVolU12(double volU12) {
		this.volU12 = volU12;
	}
	/**
	 * @return the volU23
	 */
	public double getVolU23() {
		return volU23;
	}
	/**
	 * @param volU23 the volU23 to set
	 */
	public void setVolU23(double volU23) {
		this.volU23 = volU23;
	}
	/**
	 * @return the volU31
	 */
	public double getVolU31() {
		return volU31;
	}
	/**
	 * @param volU31 the volU31 to set
	 */
	public void setVolU31(double volU31) {
		this.volU31 = volU31;
	}
	/**
	 * @return the curI1
	 */
	public double getCurI1() {
		return curI1;
	}
	/**
	 * @param curI1 the curI1 to set
	 */
	public void setCurI1(double curI1) {
		this.curI1 = curI1;
	}
	/**
	 * @return the curI2
	 */
	public double getCurI2() {
		return curI2;
	}
	/**
	 * @param curI2 the curI2 to set
	 */
	public void setCurI2(double curI2) {
		this.curI2 = curI2;
	}
	/**
	 * @return the curI3
	 */
	public double getCurI3() {
		return curI3;
	}
	/**
	 * @param curI3 the curI3 to set
	 */
	public void setCurI3(double curI3) {
		this.curI3 = curI3;
	}
	/**
	 * @return the powLmtHMIWPC
	 */
	public double getPowLmtHMIWPC() {
		return powLmtHMIWPC;
	}
	/**
	 * @param powLmtHMIWPC the powLmtHMIWPC to set
	 */
	public void setPowLmtHMIWPC(double powLmtHMIWPC) {
		this.powLmtHMIWPC = powLmtHMIWPC;
	}
	/**
	 * @return the rePowLmtHMIWPC
	 */
	public double getRePowLmtHMIWPC() {
		return rePowLmtHMIWPC;
	}
	/**
	 * @param rePowLmtHMIWPC the rePowLmtHMIWPC to set
	 */
	public void setRePowLmtHMIWPC(double rePowLmtHMIWPC) {
		this.rePowLmtHMIWPC = rePowLmtHMIWPC;
	}
	/**
	 * @return the speLmtHMIWPC
	 */
	public double getSpeLmtHMIWPC() {
		return speLmtHMIWPC;
	}
	/**
	 * @param speLmtHMIWPC the speLmtHMIWPC to set
	 */
	public void setSpeLmtHMIWPC(double speLmtHMIWPC) {
		this.speLmtHMIWPC = speLmtHMIWPC;
	}
	/**
	 * @return the winSpeVal0
	 */
	public double getWinSpeVal0() {
		return winSpeVal0;
	}
	/**
	 * @param winSpeVal0 the winSpeVal0 to set
	 */
	public void setWinSpeVal0(double winSpeVal0) {
		this.winSpeVal0 = winSpeVal0;
	}
	/**
	 * @return the winSpeVal1
	 */
	public double getWinSpeVal1() {
		return winSpeVal1;
	}
	/**
	 * @param winSpeVal1 the winSpeVal1 to set
	 */
	public void setWinSpeVal1(double winSpeVal1) {
		this.winSpeVal1 = winSpeVal1;
	}
	/**
	 * @return the auxTransforTemp
	 */
	public double getAuxTransforTemp() {
		return auxTransforTemp;
	}
	/**
	 * @param auxTransforTemp the auxTransforTemp to set
	 */
	public void setAuxTransforTemp(double auxTransforTemp) {
		this.auxTransforTemp = auxTransforTemp;
	}
	/**
	 * @return the actPowGri
	 */
	public double getActPowGri() {
		return actPowGri;
	}
	/**
	 * @param actPowGri the actPowGri to set
	 */
	public void setActPowGri(double actPowGri) {
		this.actPowGri = actPowGri;
	}
	/**
	 * @return the windSpeed
	 */
	public double getWindSpeed() {
		return windSpeed;
	}
	/**
	 * @param windSpeed the windSpeed to set
	 */
	public void setWindSpeed(double windSpeed) {
		this.windSpeed = windSpeed;
	}
	/**
	 * @return the winDirNor
	 */
	public double getWinDirNor() {
		return winDirNor;
	}
	/**
	 * @param winDirNor the winDirNor to set
	 */
	public void setWinDirNor(double winDirNor) {
		this.winDirNor = winDirNor;
	}
	/**
	 * @return the meanGenSpeed
	 */
	public double getMeanGenSpeed() {
		return meanGenSpeed;
	}
	/**
	 * @param meanGenSpeed the meanGenSpeed to set
	 */
	public void setMeanGenSpeed(double meanGenSpeed) {
		this.meanGenSpeed = meanGenSpeed;
	}

	

	
}






