package com.xjgc.wind.querytree.web.action;

import java.io.PrintWriter;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionServlet;
import org.apache.struts.actions.DispatchAction;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import bsh.This;

import com.csvreader.CsvReader;
import com.xjgc.wind.datastatistics.service.IBayService;
import com.xjgc.wind.querytree.service.IQueryTreeService;
import com.xjgc.wind.querytree.vo.ResultVO;

public class TreeAction extends DispatchAction {

	private static final Log log = LogFactory.getLog(TreeAction.class);
    String treeType=null;
	private IQueryTreeService queryTreeService;
	public void setServlet(ActionServlet servlet) {
		super.setServlet(servlet);

		WebApplicationContext wac = WebApplicationContextUtils
				.getRequiredWebApplicationContext(servlet.getServletContext());
		queryTreeService = (IQueryTreeService) wac.getBean("queryTreeService");

	}

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (log.isDebugEnabled())
			log.debug("entering 'execute' method ...");

		String method = request.getParameter("method");
		if (StringUtils.isBlank(method))
			return display(mapping, form, request, response);
		else
			return super.execute(mapping, form, request, response);
	}

	/**
	 * @deprecated
	 */
	public ActionForward show(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (log.isDebugEnabled())
			log.debug("entering 'show' method ...");

		StringBuffer sbXml = new StringBuffer();
		sbXml.append("<?xml version='1.0' encoding='GBK'?>");
		sbXml.append("<tree id='0'>");
		List<ResultVO> stations = queryTreeService.listStationByClass(2); //classid?����
		for (Iterator it = stations.iterator(); it.hasNext();) {
			ResultVO station = (ResultVO) it.next();
			
			// Level.1 -- stations
			sbXml.append("<item id='").append(station.getId())
				.append("' text='").append(station.getName()).append("'>");
			
			// Level.2 -- eu types
			sbXml.append( getEuTypeXml(station.getId()) );
			
			sbXml.append("</item>");
		}
		sbXml.append("</tree>");

		request.setAttribute("xmlString", sbXml.toString());
		return mapping.findForward("show");
	}

	/**
	 * @deprecated
	 */
	private String getEuTypeXml(Integer stationId) {
		StringBuffer sbXmlEuType = new StringBuffer();
		
		List<ResultVO> euTypes = queryTreeService.listEuTypeByStation(stationId);
		for (Iterator it = euTypes.iterator(); it.hasNext();) {
			ResultVO euType = (ResultVO) it.next();
			
			sbXmlEuType.append("<item id='").append(stationId).append("_").append(euType.getId())
					.append("' text='")
					.append(euType.getName())
					.append("'>");

			// Level.3 -- eu
			sbXmlEuType.append( getEuXml(stationId, euType.getId()) );
			
			sbXmlEuType.append("</item>");
		}
		
		return sbXmlEuType.toString();
	}

	/**
	 * @deprecated
	 */
	private String getEuXml(Integer stationId, Integer typeId) {
		StringBuffer sbXmlEu = new StringBuffer();
		
		List<ResultVO> eus = queryTreeService.listEuByStationAndType(stationId, typeId+"");
		for (Iterator it = eus.iterator(); it.hasNext();) {
			ResultVO eu = (ResultVO) it.next();
			
			String euNodeId = stationId + "_" + typeId + "_" + eu.getId();
			sbXmlEu.append("<item id='").append(euNodeId)
					.append("' text='").append(eu.getName()).append("'>");

			// Level.4 -- eu item
			sbXmlEu.append( getEuitemXml(eu.getId(), euNodeId) );

			sbXmlEu.append("</item>");
		}
		
		return sbXmlEu.toString();
	}

	/**
	 * @deprecated
	 */
	private String getEuitemXml(Integer euId, String euPrefix) {
		StringBuffer sbXmlEuitem = new StringBuffer();
		
		List<ResultVO> euitems = queryTreeService.listEuitemByEu(euId);
		for (Iterator it = euitems.iterator(); it.hasNext();) {
			ResultVO euitem = (ResultVO) it.next();

			sbXmlEuitem.append("<item id='").append(euPrefix).append("_").append(euitem.getId())
				.append("' text='").append(euitem.getName()).append("' />");
		}
		
		return sbXmlEuitem.toString();
	}


	public ActionForward display(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (log.isDebugEnabled())
			log.debug("entering 'display' method ...");

		String rootId = request.getParameter("rootId"); // root id
		String depth = request.getParameter("depth");
		
		StringBuffer sbXml = new StringBuffer();
		sbXml.append("<?xml version='1.0' encoding='GBK'?>");
		sbXml.append("<tree id='").append(rootId).append("'>");
		//List<ResultVO> stations = queryTreeService.listStationByClass(2); //classid?����
		
		List<ResultVO> stations=queryTreeService.getMicrogridList();
		for (Iterator it = stations.iterator(); it.hasNext();) {
			ResultVO station = (ResultVO) it.next();
			
			// Level.1 -- stations
			sbXml.append("<item id='district-").append(station.getId())
				.append("' text='").append(station.getName())
				.append("' child='");
			//�������Ϊ1����childΪ0
			if(Integer.parseInt(depth)==1)
				sbXml.append("0");	
			else
				sbXml.append(station.getChild());
			
			sbXml.append("'>");

			sbXml.append("</item>");
		}
		sbXml.append("</tree>");
		response.setContentType("text/xml; charset=GBK");
		response.setHeader("Cache-Control", "no-cache");

		PrintWriter out = response.getWriter();
		out.write(sbXml.toString());
		System.out.println(sbXml.toString());
		out.flush();
		out.close();
		return null;
	}

	public ActionForward expand(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (log.isDebugEnabled())
			log.debug("entering 'expand' method ...");
		  
		String parentId = request.getParameter("id");
		 treeType = request.getParameter("treetype");
		
		String depth = request.getParameter("depth");
		//�������Ĭ�����
		int nDepth = NumberUtils.toInt(depth, 5);
		//int curDepth = StringUtils.countMatches(parentId, "_");
		int curDepth =this.getCurDepth(parentId);
		//��ȡchildren���list
		List<ResultVO> children=this.getDataList(parentId); 
		
		//children = queryTreeService.getMicrogridList(); 
		
		StringBuffer sbXml = new StringBuffer();
		sbXml.append("<?xml version='1.0' encoding='GBK'?>");
		sbXml.append("<tree id='"+parentId+"'>");
		
		for (Iterator it = children.iterator(); it.hasNext();) {
			ResultVO element = (ResultVO) it.next();
			
			// Level.1 -- stations
				sbXml.append("<item id='")
				.append(this.getChildPrefix(parentId)).append("-");
				//if(curDepth==4||curDepth==5)
				//{
				sbXml.append(parentId.substring(parentId.indexOf('-')+1)).append('-');
				//}
				sbXml.append(element.getId())
				.append("' text='").append(element.getName())
				.append("' child='");
			//�ж�����ȣ���ﵽָ������ȣ�childΪ0	
			if(curDepth==(nDepth))
				sbXml.append("0");
			else
				sbXml.append(element.getChild());
				
				sbXml.append("'>");

			sbXml.append("</item>");
		}
		sbXml.append("</tree>");

		response.setContentType("text/xml; charset=gbk");
		response.setHeader("Cache-Control", "no-cache");

		PrintWriter out = response.getWriter();
		System.out.println(sbXml.toString());
		out.write(sbXml.toString());
		out.flush();
		out.close();
		return null;
	}
	
	/** ������ */
	public String getJzTreeXml(String parentId, boolean isLeaf) {
		String[] idArr = StringUtils.split(parentId, '_');
		
		List<ResultVO> result = new ArrayList<ResultVO>();
		// L1-�������/�����������
		if (1 == idArr.length) 
			result = queryTreeService.listEuTypeByStation(NumberUtils.toInt(idArr[0]));
		// L2-���/�����ܺ�
		else if (2 == idArr.length) 
			result = queryTreeService.listEuByStationAndType(NumberUtils.toInt(idArr[0]), idArr[1]);
		// L3-�豸/�������
		else if (3 == idArr.length) 
			result = queryTreeService.listEuitemByEu(NumberUtils.toInt(idArr[2]));
		
		StringBuffer sbXml = new StringBuffer();
		
		for (Iterator it = result.iterator(); it.hasNext();) {
			ResultVO element = (ResultVO) it.next();
			
			sbXml.append("<item id='").append(parentId).append("_").append(element.getId())
				.append("' text='").append(element.getName())
				.append("' child='").append(isLeaf ? "0" : "1")
				.append("'></item>");
		}

		return sbXml.toString();
	}
	
	private List<ResultVO> getDataList(String parentId) {
		int iCurDepth=this.getCurDepth(parentId);
		List<ResultVO> yaoxinyaocetree = new ArrayList<ResultVO>() ;
		if(treeType.compareTo("faultLogData")== 0){
		  String[] zhongwen = new String[]{
					"PLC主状态",
					"PLC上一个状态",
					"自检时当前子状态",
					"自检时上一个子状态",
					"停机程序",
					"复位程序",
					"系统ok",
					"并网开关",
					"风速1_1s平均值",
					"风速2_1s平均值",
					"风向1_1s平均值",
					"风向2_1s平均值",
					"机舱位置",
					"风轮转速1s平均值",
					"发电机转速瞬时值",
					"发电机转速1s平均值",
					"发电机转速30s平均值",
					"塔基控制柜内部温度30S平均值",
					"塔基控制柜外部温度30S平均值",
					"有功功率消耗",
					"机舱外环境温度30S平均值",
					"机舱柜外部温度30S平均值",
					"齿轮箱油槽油温30S平均值",
					"齿轮箱一级行星轴承温度30S平均值",
					"齿轮箱二级行星轴承温度30S平均值",
					"齿轮箱平行级轴承温度30S平均值",
					"发电机冷却水进口水温30S平均值",
					"机舱柜Ⅰ内部温度30S平均值",
					"机舱柜Ⅱ内部温度30S平均值",
					"齿轮箱润滑油外部加热器油温30S平均值",
					"齿轮箱润滑油入口油温30S平均值",
					"风轮主轴承温度1S平均值",
					"驱动侧发电机轴温1S平均值",
					"非驱动侧发电机轴温1S平均值",
					"发电机绕组U温度1S平均值",
					"发电机绕组V温度1S平均值",
					"发电机绕组W温度1S平均值",
					"齿轮箱润滑油入口压力1S平均值",
					"齿轮箱润滑油过滤器压差1S平均值",
					"发电机绕组温度U*10",
					"发电机绕组温度V*10",
					"发电机绕组温度W*10",
					"PLC计算变流器反馈的机侧散热器温度",
					"PLC计算变流器反馈的网侧散热器温度",
					"PLC通过编码器测得的轮毂转速",
					"PLC通过过速继电器测得的轮毂转速",
					"机舱前后方向振动加速度峰峰值",
					"机舱左右方向振动加速度峰峰值",
					"机舱前后方向振动加速度直流值",
					"机舱左右方向振动加速度直流值",
					"发电机定子温度",
					"60s平均风向，用于偏航启动的判断",
					"30s平均风向，用于偏航停止的判断",
					"Sineax测量瞬时总的有功功率",
					"Sineax测得的电压U12",
					"Sineax测得的电压U23",
					"Sineax测得的电压U31",
					"Sineax测得的电流I1",
					"Sineax测得的电流I2",
					"Sineax测得的电流I3",
					"Sineax测得瞬时的电流",
					"Sineax测得瞬时总的无功功率",
					"Sineax测得瞬时的电网频率",
					"变桨驱动柜1温度",
					"变桨驱动柜2温度",
					"变桨驱动柜3温度",
					"变桨变频器1温度",
					"变桨变频器2温度",
					"变桨变频器3温度",
					"变桨变频器1散热器温度",
					"变桨变频器2散热器温度",
					"变桨变频器3散热器温度",
					"变桨电机温度1",
					"变桨电机温度2",
					"变桨电机温度3",
					"电容柜1电容电压",
					"电容柜2电容电压",
					"电容柜3电容电压",
					"变桨变频器1母线电压",
					"变桨变频器2母线电压",
					"变桨变频器2母线电压",
					"叶片1角度",
					"叶片2角度", 
					"叶片3角度",
					"叶片1转矩", 
					"叶片2转矩", 
					"叶片3转矩",
					"偏航功率",
					"偏航驱动器温度",
					"偏航频率",
					"偏航计算速度",
					"电机转矩",
					"偏航给定转速",
					"偏航状态",
					"PLC与偏航系统通信(0：中断 1：正常)",
					"偏航达到需求速度",
					"偏航校准初始化完成标志",
					"整个模块IO状态值（变换为2进制可对应出相应IO点变量的高低电平）",
					"整个模块IO状态值（变换为2进制可对应出相应IO点变量的高低电平）",
					"整个模块IO状态值（变换为2进制可对应出相应IO点变量的高低电平）",
					"整个模块IO状态值（变换为2进制可对应出相应IO点变量的高低电平）",
					"整个模块IO状态值（变换为2进制可对应出相应IO点变量的高低电平）",
					"整个模块IO状态值（变换为2进制可对应出相应IO点变量的高低电平）",
					"整个模块IO状态值（变换为2进制可对应出相应IO点变量的高低电平）",
					"整个模块IO状态值（变换为2进制可对应出相应IO点变量的高低电平）",
					"整个模块IO状态值（变换为2进制可对应出相应IO点变量的高低电平）",
					"整个模块IO状态值（变换为2进制可对应出相应IO点变量的高低电平）",
					"整个模块IO状态值（变换为2进制可对应出相应IO点变量的高低电平）",
					"整个模块IO状态值（变换为2进制可对应出相应IO点变量的高低电平）",
					"整个模块IO状态值（变换为2进制可对应出相应IO点变量的高低电平）",
					"整个模块IO状态值（变换为2进制可对应出相应IO点变量的高低电平）",
					"整个模块IO状态值（变换为2进制可对应出相应IO点变量的高低电平）",
					"整个模块IO状态值（变换为2进制可对应出相应IO点变量的高低电平）",
					"整个模块IO状态值（变换为2进制可对应出相应IO点变量的高低电平）",
					"整个模块IO状态值（变换为2进制可对应出相应IO点变量的高低电平）",
					"整个模块IO状态值（变换为2进制可对应出相应IO点变量的高低电平）",
					"整个模块IO状态值（变换为2进制可对应出相应IO点变量的高低电平）",
					"整个模块IO状态值（变换为2进制可对应出相应IO点变量的高低电平）",
					"整个模块IO状态值（变换为2进制可对应出相应IO点变量的高低电平）",
					"整个模块IO状态值（变换为2进制可对应出相应IO点变量的高低电平）",
					"整个模块IO状态值（变换为2进制可对应出相应IO点变量的高低电平）",
					"整个模块IO状态值（变换为2进制可对应出相应IO点变量的高低电平）",
					"整个模块IO状态值（变换为2进制可对应出相应IO点变量的高低电平）",
					"整个模块IO状态值（变换为2进制可对应出相应IO点变量的高低电平）",
					"整个模块IO状态值（变换为2进制可对应出相应IO点变量的高低电平）",
					"整个模块IO状态值（变换为2进制可对应出相应IO点变量的高低电平）",
					"整个模块IO状态值（变换为2进制可对应出相应IO点变量的高低电平）",
					"整个模块IO状态值（变换为2进制可对应出相应IO点变量的高低电平）",
					"整个模块IO状态值（变换为2进制可对应出相应IO点变量的高低电平）",
					"整个模块IO状态值（变换为2进制可对应出相应IO点变量的高低电平）",
					"整个模块IO状态值（变换为2进制可对应出相应IO点变量的高低电平）",
					"整个模块IO状态值（变换为2进制可对应出相应IO点变量的高低电平）",
					"整个模块IO状态值（变换为2进制可对应出相应IO点变量的高低电平）",
					"整个模块IO状态值（变换为2进制可对应出相应IO点变量的高低电平）",
					"整个模块IO状态值（变换为2进制可对应出相应IO点变量的高低电平）",
					"整个模块IO状态值（变换为2进制可对应出相应IO点变量的高低电平）",
					"整个模块IO状态值（变换为2进制可对应出相应IO点变量的高低电平）",
					"整个模块IO状态值（变换为2进制可对应出相应IO点变量的高低电平）",
					"整个模块IO状态值（变换为2进制可对应出相应IO点变量的高低电平）",
					"整个模块IO状态值（变换为2进制可对应出相应IO点变量的高低电平）",
					"整个模块IO状态值（变换为2进制可对应出相应IO点变量的高低电平）",
					"整个模块IO状态值（变换为2进制可对应出相应IO点变量的高低电平）",
					"整个模块IO状态值（变换为2进制可对应出相应IO点变量的高低电平）",
					"整个模块IO状态值（变换为2进制可对应出相应IO点变量的高低电平）",
					"整个模块IO状态值（变换为2进制可对应出相应IO点变量的高低电平）",
					"整个模块IO状态值（变换为2进制可对应出相应IO点变量的高低电平）",
					"整个模块IO状态值（变换为2进制可对应出相应IO点变量的高低电平）",
					"整个模块IO状态值（变换为2进制可对应出相应IO点变量的高低电平）",
					"整个模块IO状态值（变换为2进制可对应出相应IO点变量的高低电平）",
					"整个模块IO状态值（变换为2进制可对应出相应IO点变量的高低电平）",
					"整个模块IO状态值（变换为2进制可对应出相应IO点变量的高低电平）",
					"整个模块IO状态值（变换为2进制可对应出相应IO点变量的高低电平）",
					"整个模块IO状态值（变换为2进制可对应出相应IO点变量的高低电平）",
					"整个模块IO状态值（变换为2进制可对应出相应IO点变量的高低电平）",
					"整个模块IO状态值（变换为2进制可对应出相应IO点变量的高低电平）",
					"整个模块IO状态值（变换为2进制可对应出相应IO点变量的高低电平）",
					"整个模块IO状态值（变换为2进制可对应出相应IO点变量的高低电平）",
					"整个模块IO状态值（变换为2进制可对应出相应IO点变量的高低电平）",
					"整个模块IO状态值（变换为2进制可对应出相应IO点变量的高低电平）",
					"整个模块IO状态值（变换为2进制可对应出相应IO点变量的高低电平）",
					"整个模块IO状态值（变换为2进制可对应出相应IO点变量的高低电平）",
					"PLC与变流器通信(0：中断 1：正常)",
					"主控下发给变流器的无功给定百分比*100",
					"主控下发给变流器的转矩百分比*100",
					"变流器测量的有功功率*10",
					"总的无功功率*10",
					"电网电压（U1-V1）u *10",
					"总的线电流*10",
					"转子侧变流器IGBT温度",
					"网侧变流器IGBT温度",
					"电机速度*10",
					"发电机转矩*10",
					"电网频率*100",
					"变流器主控制字",
					"变流器状态字1",
					"变流器故障字1",
					"告警字",
					"变流器故障字2",
					"变流器故障字3",
					"变流器故障字4",
					"变流器故障字5",
					"电容柜1状态",
					"电容柜2状态",
					"电容柜3状态",
					"PLC与变桨驱动器1通信(0：中断 1：正常)",
					"PLC与变桨驱动器2通信(0：中断 1：正常)",
					"PLC与变桨驱动器3通信(0：中断 1：正常)",
					"叶片1状态",
					"叶片2状态",
					"叶片3状态 ",
					"驱动器1故障字1_高16位",
					"驱动器2故障字1_高16位",
					"驱动器3故障字1_高16位",
					"驱动器1故障字2_高16位",
					"驱动器2故障字2_高16位",
					"驱动器3故障字2_高16位",
					"驱动器1故障字1_低16位",
					"驱动器2故障字1_低16位",
					"驱动器3故障字1_低16位",
					"驱动器1故障字2_低16位",
					"驱动器2故障字2_低16位",
					"驱动器3故障字2_低16位",
					"电容柜1故障字1",
					"电容柜2故障字1",
					"电容柜3故障字1",
					"变桨驱动器1控制字",
					"变桨驱动器2控制字",
					"变桨驱动器3控制字",
					"变桨驱动器1反馈的状态字0",
					"变桨驱动器1反馈的状态字1",
					"变桨驱动器2反馈的状态字0",
					"变桨驱动器2反馈的状态字1",
					"变桨驱动器3反馈的状态字0",
					"变桨驱动器3反馈的状态字1",
					"主控下发的变桨速度1(整型数据，其值是变桨速度的100倍)",
					"主控下发的变桨速度2(整型数据，其值是变桨速度的100倍)",
					"主控下发的变桨速度3(整型数据，其值是变桨速度的100倍)",
					"驱动器上传的变桨速度1(整型数据，其值是变桨速度的100倍)",
					"驱动器上传的变桨速度2(整型数据，其值是变桨速度的100倍)",
					"驱动器上传的变桨速度3(整型数据，其值是变桨速度的100倍)",
					"偏航变频器故障号 ",
					"偏航控制字_高16位",
					"偏航状态字_高16位",
					"偏航控制字_低16位",
					"偏航状态字_低16位",
					"主控与风速风向仪1通信(0：中断 1：正常)",
					"主控与风速风向仪2通信(0：中断 1：正常)",
					"变桨自检状态机",
					"轮毂故障字1",
					"轮毂故障字2",
					"轮毂故障字3",
					"安全运行结束1",
					"安全运行结束1",
					"安全运行结束1",
					"轮毂告警字1",
					"轮毂告警字2",
					"轮毂告警字3",
					"变桨1速度需求",
					"变桨2速度需求",
					"变桨3速度需求",
					"计算的电容值1",
					"计算的电容值2",
					"计算的电容值3",
					"轮毂的状态",
					"变桨位置控制转化速度需求1",
					"变桨位置控制转化速度需求2",
					"变桨位置控制转化速度需求3",
					"用电容值计算出来的能量消耗1",
					"用电容值计算出来的能量消耗2",
					"用电容值计算出来的能量消耗3",
					"变桨柜I摩擦力",
					"变桨柜II摩擦力",
					"变桨柜III摩擦力",
					"变桨自检故障字",
					"电容值累加值1",
					"电容值累加值2",
					"电容值累加值3",
					"计算直流母线电压平均值的计数1",
					"计算直流母线电压平均值的计数2",
					"计算直流母线电压平均值的计数3",
					"电容充电时间1",
					"电容充电时间2",
					"电容充电时间3",
					"变桨自检子状态",
					"刹车状态字",
					"PI控制器输入的功率额定限制值",
					"PI控制器输入的转速目标值",
					"1号加阻扭矩需求",
					"2号加阻扭矩需求",
					"加阻总扭矩需求",
					"解缆完成标志",
					"偏航位置偏差",
					"偏航刹车信号",
					"顺时针电机启动信号",
					"逆时针电机启动信号",
					"风向瞬时值风向1（未过0处理）",
					"风向瞬时值风向2",
					"环境温度1",
					"环境温度2",
					"驱动器1变桨子状态",
					"驱动器2变桨子状态",
					"驱动器3变桨子状态",
					"风机编号",
					"齿轮箱的限制导致的限制功率值",
					"因风速原因导致的限功率数值",
					"齿轮箱的限制导致的需求转速限制值",
					"电机目标转速限制值",
					"程序计算的目标转速值",
					"5分钟平均风速1",
					"5分钟平均风速2",
					"10s平均风向1",
					"10s平均风向2",
		};
			
		
			for(int i=0;i<zhongwen.length;i++){
					ResultVO resultVO=new ResultVO();
					resultVO.setId(i+1);
					resultVO.setChild(0);
					resultVO.setName(zhongwen[i]);
					yaoxinyaocetree.add(resultVO);
				} 
		}
		switch (iCurDepth) {
			case 1:
			
				return queryTreeService.getMicrogridList();
				
			case 2:
				if(treeType.compareTo("faultLogData")== 0){
					return yaoxinyaocetree;
				}
			    if(treeType.compareTo("runingInfo_SelectOneMinuteData")== 0){
				return queryTreeService.getBayList(parentId);
			    }
			    if(treeType.compareTo("runingInfo_OneMinuteReportData")== 0){
					return queryTreeService.getBayList(parentId);
				    }
			    if(treeType.compareTo("runingInfo_TenMinuteReportData")== 0){
					return queryTreeService.getBayList(parentId);
				    }
			    if(treeType.compareTo("runingInfo_OneDayReportData")== 0){
					return queryTreeService.getBayList(parentId);
				    }
			    if(treeType.compareTo("lossEleReportData")== 0){
					return queryTreeService.getBayList(parentId);
				    }
			    if(treeType.compareTo("runingInfo_SelectTenMinuteData")== 0){
				return queryTreeService.getBayList(parentId,2);
			    }
			    if(treeType.compareTo("runingInfo_SelectDayData")== 0){
				return queryTreeService.getBayList(parentId,3);
			    }
			   
			    if(treeType.compareTo("operatRecordData")== 0){
					return queryTreeService.getBayList(parentId);
				    }
			    else{
					 return queryTreeService.getGeneratorList(parentId);
				}
			case 3:	
			 return queryTreeService.getGeneratorList(parentId);
		
			case 4:	
				return queryTreeService.getMesurePointSubList();
			case 5:
				return queryTreeService.getMesurePointList(parentId);
			default:
				return null;
		}
		
	}
	
	private String getChildPrefix(String parentId) {
		if(parentId.indexOf("r0_")>=0)
			return "district";
		String parentIdPrefixStr=parentId.substring(0,parentId.indexOf('-'));
		if(parentIdPrefixStr.equals("district"))
			return "microgrid";
		if(parentIdPrefixStr.equals("microgrid"))
			return "generator";
		if(parentIdPrefixStr.equals("generator"))
			return "measurepointSub";
		if(parentIdPrefixStr.equals("measurepointSub"))
			return "measurepoint";
		return null;
	}
	
	private int getCurDepth(String parentId) {
		//System.out.println(parentId.substring(0,parentId.indexOf('-')));
		//System.out.println(parentId.indexOf("r0_"));
		if(parentId.indexOf("r0_")>=0)
			return 1;
		String parentIdPrefixStr=parentId.substring(0,parentId.indexOf('-'));
		if(parentIdPrefixStr.equals("district"))
			return 2;
		if(parentIdPrefixStr.equals("microgrid"))
			return 3;
		if(parentIdPrefixStr.equals("generator"))
			return 4;
		if(parentIdPrefixStr.equals("measurepointSub"))
			return 5;
		return -1;
	}
	
	/** ��������� */
	public String getFlfxTreeXml(String parentId, boolean isLeaf) {
		String[] idArr = StringUtils.split(parentId, '_');

		List<ResultVO> result = new ArrayList<ResultVO>();
		// L1-�������/�����������
		if (1 == idArr.length) 
			result = queryTreeService.listEuTypeByStation(NumberUtils.toInt(idArr[0]));
		// L2-�豸����/����������
		else if (2 == idArr.length) 
			result = queryTreeService.listEuitemClassByType(NumberUtils.toInt(idArr[1]));
		// L3-�豸/�������
		else if (3 == idArr.length) 
			result = queryTreeService.listEuitemByClass(NumberUtils.toInt(idArr[0]),NumberUtils.toInt(idArr[2]));

		StringBuffer sbXml = new StringBuffer();
		
		for (Iterator it = result.iterator(); it.hasNext();) {
			ResultVO element = (ResultVO) it.next();
			
			sbXml.append("<item id='").append(parentId).append("_").append(element.getId())
				.append("' text='").append(element.getName())
				.append("' child='").append(isLeaf ? "0" : "1")
				.append("'></item>");
		}

		return sbXml.toString();
	}
	
	/** ������ */
	public String getFlTreeXml(String parentId, String flTypes, boolean isLeaf) {
		String[] idArr = StringUtils.split(parentId, '_');

		List<ResultVO> result = new ArrayList<ResultVO>();
		// L1-���/�����ܺ�
		if (1 == idArr.length) 
			result = queryTreeService.listEuByStationAndType(NumberUtils.toInt(idArr[0]), flTypes);
		// L2-�豸/�������
		else if (2 == idArr.length) 
			result = queryTreeService.listEuitemByEu(NumberUtils.toInt(idArr[1]));

		StringBuffer sbXml = new StringBuffer();
		
		for (Iterator it = result.iterator(); it.hasNext();) {
			ResultVO element = (ResultVO) it.next();
			
			sbXml.append("<item id='").append(parentId).append("_").append(element.getId())
				.append("' text='").append(element.getName())
				.append("' child='").append(isLeaf ? "0" : "1")
				.append("'></item>");
		}

		return sbXml.toString();
	}

	/**
	 * �����ܺ�ģ���ѯ
	 * TODO smart loading / database paging
	 */
	public ActionForward query(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (log.isDebugEnabled())
			log.debug("entering 'query' method ...");
		
		StringBuffer sbXml = new StringBuffer();
		sbXml.append("<?xml version='1.0' encoding='GBK'?>");
		int totalCount = 0;

		String eqName = URLDecoder.decode(request.getParameter("eqName"), "UTF-8");
		if (StringUtils.isNotBlank(eqName)) {
			totalCount = queryTreeService.getEuitemCountByName(eqName);
			
			List<ResultVO> eqList = queryTreeService.listEuitemByName(eqName);
			for (Iterator it = eqList.iterator(); it.hasNext();) {
				ResultVO eq = (ResultVO) it.next();
				
				sbXml.append("<item id=\"").append(eq.getId()).append("\">");
				sbXml.append("<name><![CDATA[").append(eq.getName()).append("]]></name>");
				sbXml.append("</item>");
			}
		}
		
		sbXml.insert(0, "<data total_count=\"" + totalCount + "\" pos=\"0\">");
		sbXml.append("</data>");

		response.setContentType("text/xml; charset=gbk");
		response.setHeader("Cache-Control", "no-cache");

		PrintWriter out = response.getWriter();
		out.write(sbXml.toString());
		out.flush();
		out.close();
		return null;
	}


	public ActionForward test(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		if (log.isDebugEnabled())
			log.debug("entering 'show' method ...");
		
		String xmlString = "<?xml version=\"1.0\" encoding=\"GBK\"?>"
			+ "<tree id=\"0\" >"
			+ "<item text=\"����ϵͳ\" open=\"2\" id=\"lb\">"
			+ "<item text=\"��������õ���\" open=\"1\" id=\"lb1\">"
			+ "<item text=\"4¥����\" id=\"lb1-1\"/>"
			+ "<item text=\"5¥����\" id=\"lb1-2\"/>"
			+ "</item>"
			+ "<item text=\"�յ��õ���\"  open=\"1\" id=\"lb2\">"
			+ "<item text=\"����վ\" id=\"lb2-1\"/>"
			+ "<item text=\"�յ�ĩ��\" id=\"lb2-2\"/>"
			+ "</item>"
			+ "<item text=\"��f�õ���\" open=\"1\" id=\"lb3\">"
			+ "<item text=\"�����õ�\" id=\"lb3-1\"/>"
			+ "<item text=\"ˮ���õ�\" id=\"lb3-2\"/>"
			+ "</item>"
			+ "<item text=\"�����õ���\" open=\"1\" id=\"lb4\">"
			+ "<item text=\"��Ϣ����\" id=\"lb4-1\"/>"
			+ "<item text=\"���?\" id=\"lb4-2\"/> "
			+ "</item>"
			+ "</item>"
			+ "</tree>";
		PrintWriter out = response.getWriter();
		out.write(xmlString);
		out.flush();
		out.close();
		return null;
		//request.setAttribute("xmlString", xmlString);
		//return mapping.findForward("show");
	}

}
