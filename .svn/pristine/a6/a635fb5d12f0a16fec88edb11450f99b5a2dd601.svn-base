package com.xjgc.wind.util;

import java.util.List;
import java.util.Map;


/**
 * 发电功率与发电量的单位添加与收益的单位添加
 * @author djl
 *
 */

public class UnitAnalyse {

	public UnitAnalyse() {
		// TODO Auto-generated constructor stub
	}

	
	public static String powerFormat(double power) {

		double _result=0;
		if(power<1000){
			_result=power;
		}else if(power<1000000){
			_result=power/1000;
			_result=DoubleRound.formatDouble1(_result);
		}else{
			_result=power/1000000;
			_result=DoubleRound.formatDouble1(_result);
		}
		
		return Double.toString(_result);
		
	}

	public static String powerFormat(String power) {

		double _power=Double.parseDouble(power);
		double _result=0;
		if(_power<1000){
			_result=_power;
		}else if(_power<1000000){
			_result=_power/1000;
			_result=DoubleRound.formatDouble1(_result);
		}else{
			_result=_power/1000000;
			_result=DoubleRound.formatDouble1(_result);
		}
		
		return Double.toString(_result);
		
	}
	
	public static String powerFormatUnit(double power) {
		
		String _result;
		if(power<1000){
			_result="kW";
		}else if(power<1000000){
			_result="MW";
			
		}else{
			_result="GW";
		}
		return _result;
	}

	public static String powerFormatUnit(String power) {
		
		double _power=Double.parseDouble(power);
		String _result;
		if(_power<1000){
			_result="kW";
		}else if(_power<1000000){
			_result="MW";
			
		}else{
			_result="GW";
		}
		return _result;
	}
	
/////////*************************************************************************************************
	public static String generationFormat(double generation) {

		double _result=0;
		if(generation<1000){
			_result=generation;
		}else if(generation<1000000){
			_result=generation/1000;
			_result=DoubleRound.formatDouble1(_result);
		}else{
			_result=generation/1000000;
			_result=DoubleRound.formatDouble1(_result);
		}
		
		return Double.toString(_result);
	}

	public static String generationFormat(String generation) {

		double _generation=Double.parseDouble(generation);
		double _result=0;
		if(_generation<1000){
			_result=_generation;
		}else if(_generation<1000000){
			_result=_generation/1000;
			_result=DoubleRound.formatDouble1(_result);
		}else{
			_result=_generation/1000000;
			_result=DoubleRound.formatDouble1(_result);
		}
		
		return Double.toString(_result);
	}
	
	public static String generationFormatUnit(double generation) {

		String _result;
		if(generation<1000){
			_result="kWh";
		}else if(generation<1000000){
			_result="MWh";
			
		}else{
			_result="GWh";
		}
		return _result;
	}

	public static String generationFormatUnit(String generation) {

		double _generation=Double.parseDouble(generation);
		String _result;
		if(_generation<1000){
			_result="kWh";
		}else if(_generation<1000000){
			_result="MWh";
			
		}else{
			_result="GWh";
		}
		return _result;
	}
	
	
///////////**************************************************************************************************
	public static String profitFormat(double num) {

		
		double _result=0;
		if(num<10000){
			_result=num;
		}else{
			_result=num/10000;
			_result=DoubleRound.formatDouble1(_result);
		
		}
		
		return Double.toString(_result);
	}

	public static String profitFormat(String num) {

		double _num=Double.parseDouble(num);
		double _result=0;
		if(_num<10000){
			_result=_num;
		}else{
			_result=_num/10000;
			_result=DoubleRound.formatDouble1(_result);
		}
		
		return Double.toString(_result);
	}

	public static String profitFormatUnit(double num) {

		String _result;
		if(num<10000){
			_result="元";
		}else{
			_result="万元";
		}
		return _result;
	}

	public static String profitFormatUnit(String num) {

		double _numn=Double.parseDouble(num);
		String _result;
		if(_numn<10000){
			_result="元";
		}else{
			_result="万元";
		}
		return _result;
	}
	
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * 获取sql查询返回的最大值
	 * @param list
	 * @return
	 */
	public static double getlistMaxValue(List list){
		
		double _result=0;
		for (int i=0;i<list.size();i++){
			Map map=(Map)list.get(i);
			String _getstr=(String)map.get("data");
			double _nowDouble=Double.parseDouble(_getstr);
			if(_nowDouble>_result){
				_result=_nowDouble;
			}
		}
		return _result;
	}
	
	
	public static String handlePowerWithUnit(String power,String unit){
		double _power=Double.parseDouble(power);
		double _result=0;
		if (unit.equals("kW")){
			_result=_power;
		}else if(unit.equals("MW")){
			_result=_power/1000;
			_result=DoubleRound.formatDouble1(_result);
		}else if(unit.equals("GW")){
			_result=_power/1000000;
			_result=DoubleRound.formatDouble1(_result);
		}
		
		return Double.toString(_result);
	}
	
	
	
	public static String handleGenerationWithUnit(String generation,String unit){
		double _generation=Double.parseDouble(generation);
		double _result=0;
		if (unit.equals("kWh")){
			_result=_generation;
		}else if(unit.equals("MWh")){
			_result=_generation/1000;
			_result=DoubleRound.formatDouble1(_result);
		}else if(unit.equals("GWh")){
			_result=_generation/1000000;
			_result=DoubleRound.formatDouble1(_result);
		}
		
		return Double.toString(_result);
	}
	
	public static void handlePowerListWithUnit(List list ,String unit){
		
		if (unit.equals("kW")){
			
		}else if(unit.equals("MW")){
			for (int i=0;i<list.size();i++){
				Map _map=(Map)list.get(i);
				String _getstr=(String)_map.get("data");
				double _nowDouble=Double.parseDouble(_getstr);
				_nowDouble=_nowDouble/1000;
				_nowDouble=DoubleRound.formatDouble1(_nowDouble);
				_map.put("data", Double.toString(_nowDouble));
			}
		}else if(unit.equals("GW")){
			for (int i=0;i<list.size();i++){
				Map _map=(Map)list.get(i);
				String _getstr=(String)_map.get("data");
				double _nowDouble=Double.parseDouble(_getstr);
				_nowDouble=_nowDouble/1000000;
				_nowDouble=DoubleRound.formatDouble1(_nowDouble);
				_map.put("data", Double.toString(_nowDouble));
			}
		}
			
	}
	
	
	public static void handleProfitListWithUnit(List list ,String unit){
		
		if (unit.equals("元")){
			
		}else if(unit.equals("万元")){
			for (int i=0;i<list.size();i++){
				Map _map=(Map)list.get(i);
				String _getstr=(String)_map.get("data");
				double _nowDouble=Double.parseDouble(_getstr);
				_nowDouble=_nowDouble/10000;
				_nowDouble=DoubleRound.formatDouble1(_nowDouble);
				_map.put("data", Double.toString(_nowDouble));
			}
		}
			
	}
	
	
	public static void handleGenerationListWithUnit(List list ,String unit){
		
		if (unit.equals("kWh")){
			
		}else if(unit.equals("MWh")){
			for (int i=0;i<list.size();i++){
				Map _map=(Map)list.get(i);
				String _getstr=(String)_map.get("data");
				double _nowDouble=Double.parseDouble(_getstr);
				_nowDouble=_nowDouble/1000;
				_nowDouble=DoubleRound.formatDouble1(_nowDouble);
				_map.put("data", Double.toString(_nowDouble));
			}
		}else if(unit.equals("GWh")){
			for (int i=0;i<list.size();i++){
				Map _map=(Map)list.get(i);
				String _getstr=(String)_map.get("data");
				double _nowDouble=Double.parseDouble(_getstr);
				_nowDouble=_nowDouble/1000000;
				_nowDouble=DoubleRound.formatDouble1(_nowDouble);
				_map.put("data", Double.toString(_nowDouble));
			}
		}
			
	}
	
	
	
}
