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
		List<ResultVO> stations = queryTreeService.listStationByClass(2); //classid?????????????
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
		//List<ResultVO> stations = queryTreeService.listStationByClass(2); //classid?????????????
		
		List<ResultVO> stations=queryTreeService.getMicrogridList();
		for (Iterator it = stations.iterator(); it.hasNext();) {
			ResultVO station = (ResultVO) it.next();
			
			// Level.1 -- stations
			sbXml.append("<item id='district-").append(station.getId())
				.append("' text='").append(station.getName())
				.append("' child='");
			//???????????????????????1????????????child??0
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
		//??????????????????????????????????????
		int nDepth = NumberUtils.toInt(depth, 5);
		//int curDepth = StringUtils.countMatches(parentId, "_");
		int curDepth =this.getCurDepth(parentId);
		//????????children?????????list
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
			//???????????????????????????????????????????????????????????child??0	
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
	
	/** ?????????????????? */
	public String getJzTreeXml(String parentId, boolean isLeaf) {
		String[] idArr = StringUtils.split(parentId, '_');
		
		List<ResultVO> result = new ArrayList<ResultVO>();
		// L1-?????????????????????/?????????????????????????????????
		if (1 == idArr.length) 
			result = queryTreeService.listEuTypeByStation(NumberUtils.toInt(idArr[0]));
		// L2-?????????/????????????????????
		else if (2 == idArr.length) 
			result = queryTreeService.listEuByStationAndType(NumberUtils.toInt(idArr[0]), idArr[1]);
		// L3-??????/?????????????????????
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
					"PLC?????????",
					"PLC???????????????",
					"????????????????????????",
					"???????????????????????????",
					"????????????",
					"????????????",
					"??????ok",
					"????????????",
					"??????1_1s?????????",
					"??????2_1s?????????",
					"??????1_1s?????????",
					"??????2_1s?????????",
					"????????????",
					"????????????1s?????????",
					"????????????????????????",
					"???????????????1s?????????",
					"???????????????30s?????????",
					"???????????????????????????30S?????????",
					"???????????????????????????30S?????????",
					"??????????????????",
					"?????????????????????30S?????????",
					"?????????????????????30S?????????",
					"?????????????????????30S?????????",
					"?????????????????????????????????30S?????????",
					"?????????????????????????????????30S?????????",
					"??????????????????????????????30S?????????",
					"??????????????????????????????30S?????????",
					"????????????????????????30S?????????",
					"????????????????????????30S?????????",
					"???????????????????????????????????????30S?????????",
					"??????????????????????????????30S?????????",
					"?????????????????????1S?????????",
					"????????????????????????1S?????????",
					"???????????????????????????1S?????????",
					"???????????????U??????1S?????????",
					"???????????????V??????1S?????????",
					"???????????????W??????1S?????????",
					"??????????????????????????????1S?????????",
					"?????????????????????????????????1S?????????",
					"?????????????????????U*10",
					"?????????????????????V*10",
					"?????????????????????W*10",
					"PLC?????????????????????????????????????????????",
					"PLC?????????????????????????????????????????????",
					"PLC????????????????????????????????????",
					"PLC??????????????????????????????????????????",
					"??????????????????????????????????????????",
					"??????????????????????????????????????????",
					"??????????????????????????????????????????",
					"??????????????????????????????????????????",
					"?????????????????????",
					"60s??????????????????????????????????????????",
					"30s??????????????????????????????????????????",
					"Sineax??????????????????????????????",
					"Sineax???????????????U12",
					"Sineax???????????????U23",
					"Sineax???????????????U31",
					"Sineax???????????????I1",
					"Sineax???????????????I2",
					"Sineax???????????????I3",
					"Sineax?????????????????????",
					"Sineax??????????????????????????????",
					"Sineax???????????????????????????",
					"???????????????1??????",
					"???????????????2??????",
					"???????????????3??????",
					"???????????????1??????",
					"???????????????2??????",
					"???????????????3??????",
					"???????????????1???????????????",
					"???????????????2???????????????",
					"???????????????3???????????????",
					"??????????????????1",
					"??????????????????2",
					"??????????????????3",
					"?????????1????????????",
					"?????????2????????????",
					"?????????3????????????",
					"???????????????1????????????",
					"???????????????2????????????",
					"???????????????2????????????",
					"??????1??????",
					"??????2??????", 
					"??????3??????",
					"??????1??????", 
					"??????2??????", 
					"??????3??????",
					"????????????",
					"?????????????????????",
					"????????????",
					"??????????????????",
					"????????????",
					"??????????????????",
					"????????????",
					"PLC?????????????????????(0????????? 1?????????)",
					"????????????????????????",
					"?????????????????????????????????",
					"????????????IO?????????????????????2????????????????????????IO???????????????????????????",
					"????????????IO?????????????????????2????????????????????????IO???????????????????????????",
					"????????????IO?????????????????????2????????????????????????IO???????????????????????????",
					"????????????IO?????????????????????2????????????????????????IO???????????????????????????",
					"????????????IO?????????????????????2????????????????????????IO???????????????????????????",
					"????????????IO?????????????????????2????????????????????????IO???????????????????????????",
					"????????????IO?????????????????????2????????????????????????IO???????????????????????????",
					"????????????IO?????????????????????2????????????????????????IO???????????????????????????",
					"????????????IO?????????????????????2????????????????????????IO???????????????????????????",
					"????????????IO?????????????????????2????????????????????????IO???????????????????????????",
					"????????????IO?????????????????????2????????????????????????IO???????????????????????????",
					"????????????IO?????????????????????2????????????????????????IO???????????????????????????",
					"????????????IO?????????????????????2????????????????????????IO???????????????????????????",
					"????????????IO?????????????????????2????????????????????????IO???????????????????????????",
					"????????????IO?????????????????????2????????????????????????IO???????????????????????????",
					"????????????IO?????????????????????2????????????????????????IO???????????????????????????",
					"????????????IO?????????????????????2????????????????????????IO???????????????????????????",
					"????????????IO?????????????????????2????????????????????????IO???????????????????????????",
					"????????????IO?????????????????????2????????????????????????IO???????????????????????????",
					"????????????IO?????????????????????2????????????????????????IO???????????????????????????",
					"????????????IO?????????????????????2????????????????????????IO???????????????????????????",
					"????????????IO?????????????????????2????????????????????????IO???????????????????????????",
					"????????????IO?????????????????????2????????????????????????IO???????????????????????????",
					"????????????IO?????????????????????2????????????????????????IO???????????????????????????",
					"????????????IO?????????????????????2????????????????????????IO???????????????????????????",
					"????????????IO?????????????????????2????????????????????????IO???????????????????????????",
					"????????????IO?????????????????????2????????????????????????IO???????????????????????????",
					"????????????IO?????????????????????2????????????????????????IO???????????????????????????",
					"????????????IO?????????????????????2????????????????????????IO???????????????????????????",
					"????????????IO?????????????????????2????????????????????????IO???????????????????????????",
					"????????????IO?????????????????????2????????????????????????IO???????????????????????????",
					"????????????IO?????????????????????2????????????????????????IO???????????????????????????",
					"????????????IO?????????????????????2????????????????????????IO???????????????????????????",
					"????????????IO?????????????????????2????????????????????????IO???????????????????????????",
					"????????????IO?????????????????????2????????????????????????IO???????????????????????????",
					"????????????IO?????????????????????2????????????????????????IO???????????????????????????",
					"????????????IO?????????????????????2????????????????????????IO???????????????????????????",
					"????????????IO?????????????????????2????????????????????????IO???????????????????????????",
					"????????????IO?????????????????????2????????????????????????IO???????????????????????????",
					"????????????IO?????????????????????2????????????????????????IO???????????????????????????",
					"????????????IO?????????????????????2????????????????????????IO???????????????????????????",
					"????????????IO?????????????????????2????????????????????????IO???????????????????????????",
					"????????????IO?????????????????????2????????????????????????IO???????????????????????????",
					"????????????IO?????????????????????2????????????????????????IO???????????????????????????",
					"????????????IO?????????????????????2????????????????????????IO???????????????????????????",
					"????????????IO?????????????????????2????????????????????????IO???????????????????????????",
					"????????????IO?????????????????????2????????????????????????IO???????????????????????????",
					"????????????IO?????????????????????2????????????????????????IO???????????????????????????",
					"????????????IO?????????????????????2????????????????????????IO???????????????????????????",
					"????????????IO?????????????????????2????????????????????????IO???????????????????????????",
					"????????????IO?????????????????????2????????????????????????IO???????????????????????????",
					"????????????IO?????????????????????2????????????????????????IO???????????????????????????",
					"????????????IO?????????????????????2????????????????????????IO???????????????????????????",
					"????????????IO?????????????????????2????????????????????????IO???????????????????????????",
					"????????????IO?????????????????????2????????????????????????IO???????????????????????????",
					"????????????IO?????????????????????2????????????????????????IO???????????????????????????",
					"????????????IO?????????????????????2????????????????????????IO???????????????????????????",
					"????????????IO?????????????????????2????????????????????????IO???????????????????????????",
					"????????????IO?????????????????????2????????????????????????IO???????????????????????????",
					"????????????IO?????????????????????2????????????????????????IO???????????????????????????",
					"????????????IO?????????????????????2????????????????????????IO???????????????????????????",
					"????????????IO?????????????????????2????????????????????????IO???????????????????????????",
					"????????????IO?????????????????????2????????????????????????IO???????????????????????????",
					"????????????IO?????????????????????2????????????????????????IO???????????????????????????",
					"PLC??????????????????(0????????? 1?????????)",
					"????????????????????????????????????????????????*100",
					"??????????????????????????????????????????*100",
					"??????????????????????????????*10",
					"??????????????????*10",
					"???????????????U1-V1???u *10",
					"???????????????*10",
					"??????????????????IGBT??????",
					"???????????????IGBT??????",
					"????????????*10",
					"???????????????*10",
					"????????????*100",
					"?????????????????????",
					"??????????????????1",
					"??????????????????1",
					"?????????",
					"??????????????????2",
					"??????????????????3",
					"??????????????????4",
					"??????????????????5",
					"?????????1??????",
					"?????????2??????",
					"?????????3??????",
					"PLC??????????????????1??????(0????????? 1?????????)",
					"PLC??????????????????2??????(0????????? 1?????????)",
					"PLC??????????????????3??????(0????????? 1?????????)",
					"??????1??????",
					"??????2??????",
					"??????3?????? ",
					"?????????1?????????1_???16???",
					"?????????2?????????1_???16???",
					"?????????3?????????1_???16???",
					"?????????1?????????2_???16???",
					"?????????2?????????2_???16???",
					"?????????3?????????2_???16???",
					"?????????1?????????1_???16???",
					"?????????2?????????1_???16???",
					"?????????3?????????1_???16???",
					"?????????1?????????2_???16???",
					"?????????2?????????2_???16???",
					"?????????3?????????2_???16???",
					"?????????1?????????1",
					"?????????2?????????1",
					"?????????3?????????1",
					"???????????????1?????????",
					"???????????????2?????????",
					"???????????????3?????????",
					"???????????????1??????????????????0",
					"???????????????1??????????????????1",
					"???????????????2??????????????????0",
					"???????????????2??????????????????1",
					"???????????????3??????????????????0",
					"???????????????3??????????????????1",
					"???????????????????????????1(???????????????????????????????????????100???)",
					"???????????????????????????2(???????????????????????????????????????100???)",
					"???????????????????????????3(???????????????????????????????????????100???)",
					"??????????????????????????????1(???????????????????????????????????????100???)",
					"??????????????????????????????2(???????????????????????????????????????100???)",
					"??????????????????????????????3(???????????????????????????????????????100???)",
					"???????????????????????? ",
					"???????????????_???16???",
					"???????????????_???16???",
					"???????????????_???16???",
					"???????????????_???16???",
					"????????????????????????1??????(0????????? 1?????????)",
					"????????????????????????2??????(0????????? 1?????????)",
					"?????????????????????",
					"???????????????1",
					"???????????????2",
					"???????????????3",
					"??????????????????1",
					"??????????????????1",
					"??????????????????1",
					"???????????????1",
					"???????????????2",
					"???????????????3",
					"??????1????????????",
					"??????2????????????",
					"??????3????????????",
					"??????????????????1",
					"??????????????????2",
					"??????????????????3",
					"???????????????",
					"????????????????????????????????????1",
					"????????????????????????????????????2",
					"????????????????????????????????????3",
					"???????????????????????????????????????1",
					"???????????????????????????????????????2",
					"???????????????????????????????????????3",
					"?????????I?????????",
					"?????????II?????????",
					"?????????III?????????",
					"?????????????????????",
					"??????????????????1",
					"??????????????????2",
					"??????????????????3",
					"??????????????????????????????????????????1",
					"??????????????????????????????????????????2",
					"??????????????????????????????????????????3",
					"??????????????????1",
					"??????????????????2",
					"??????????????????3",
					"?????????????????????",
					"???????????????",
					"PI???????????????????????????????????????",
					"PI?????????????????????????????????",
					"1?????????????????????",
					"2?????????????????????",
					"?????????????????????",
					"??????????????????",
					"??????????????????",
					"??????????????????",
					"???????????????????????????",
					"???????????????????????????",
					"?????????????????????1?????????0?????????",
					"?????????????????????2",
					"????????????1",
					"????????????2",
					"?????????1???????????????",
					"?????????2???????????????",
					"?????????3???????????????",
					"????????????",
					"??????????????????????????????????????????",
					"???????????????????????????????????????",
					"????????????????????????????????????????????????",
					"???????????????????????????",
					"??????????????????????????????",
					"5??????????????????1",
					"5??????????????????2",
					"10s????????????1",
					"10s????????????2",
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
	
	/** ??????????????????????????? */
	public String getFlfxTreeXml(String parentId, boolean isLeaf) {
		String[] idArr = StringUtils.split(parentId, '_');

		List<ResultVO> result = new ArrayList<ResultVO>();
		// L1-?????????????????????/?????????????????????????????????
		if (1 == idArr.length) 
			result = queryTreeService.listEuTypeByStation(NumberUtils.toInt(idArr[0]));
		// L2-??????????????????/??????????????????????????????
		else if (2 == idArr.length) 
			result = queryTreeService.listEuitemClassByType(NumberUtils.toInt(idArr[1]));
		// L3-??????/?????????????????????
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
	
	/** ?????????????????? */
	public String getFlTreeXml(String parentId, String flTypes, boolean isLeaf) {
		String[] idArr = StringUtils.split(parentId, '_');

		List<ResultVO> result = new ArrayList<ResultVO>();
		// L1-?????????/????????????????????
		if (1 == idArr.length) 
			result = queryTreeService.listEuByStationAndType(NumberUtils.toInt(idArr[0]), flTypes);
		// L2-??????/?????????????????????
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
	 * ?????????????????????????????????
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
			+ "<item text=\"????????????????\" open=\"2\" id=\"lb\">"
			+ "<item text=\"???????????????????????????????????\" open=\"1\" id=\"lb1\">"
			+ "<item text=\"4??????????????\" id=\"lb1-1\"/>"
			+ "<item text=\"5??????????????\" id=\"lb1-2\"/>"
			+ "</item>"
			+ "<item text=\"??????????????????????\"  open=\"1\" id=\"lb2\">"
			+ "<item text=\"??????????????\" id=\"lb2-1\"/>"
			+ "<item text=\"????????????????\" id=\"lb2-2\"/>"
			+ "</item>"
			+ "<item text=\"??????f??????????????\" open=\"1\" id=\"lb3\">"
			+ "<item text=\"????????????????????\" id=\"lb3-1\"/>"
			+ "<item text=\"????????????????\" id=\"lb3-2\"/>"
			+ "</item>"
			+ "<item text=\"??????????????????????????\" open=\"1\" id=\"lb4\">"
			+ "<item text=\"????????????????????\" id=\"lb4-1\"/>"
			+ "<item text=\"??????????\" id=\"lb4-2\"/> "
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
