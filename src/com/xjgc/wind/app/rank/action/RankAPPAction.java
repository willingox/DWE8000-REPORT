package com.xjgc.wind.app.rank.action;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionServlet;
import org.apache.struts.actions.DispatchAction;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.bjxj.usermgr.util.JsonUtils;
import com.xjgc.wind.app.overview.service.OverviewAPPService;
import com.xjgc.wind.app.rank.service.RankAPPService;
import com.xjgc.wind.app.util.HtmlUtil;

public class RankAPPAction extends DispatchAction  {
	
	RankAPPService rank_APP_Service;
	
	public void setServlet(ActionServlet servlet) {
		super.setServlet(servlet);

		WebApplicationContext wac = WebApplicationContextUtils
		.getRequiredWebApplicationContext(servlet.getServletContext());
		rank_APP_Service= (RankAPPService) wac.getBean("rankAPPService");																														
	}
	
	
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (log.isDebugEnabled())
			log.debug("entering 'execute' method ...");

		String method = request.getParameter("method");
		if (StringUtils.isBlank(method))
			//return null;
			return dayRank(mapping, form, request, response);
		else
			return super.execute(mapping, form, request, response);
	}
	
	//风电场本日数据排行榜
	public ActionForward dayRank(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			String dataItem=request.getParameter("dataItem");
			String sortDescStr=request.getParameter("sortDesc");
			
			Boolean sortDesc=false;
			
			if(dataItem==null||dataItem.equals(""))
			dataItem="genWh";
			if(sortDescStr==null||sortDescStr.equals(""))
				sortDescStr="false";
			if(sortDescStr.equals("true"))
				sortDesc=true;
			
			HtmlUtil.writeStrToHtml(rank_APP_Service.getDayRankStr(request.getMethod(),dataItem, sortDesc), response);
			return null;
	}
	
	//风电场30日数据排行榜
		public ActionForward monthRank(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
				throws Exception {
				String dataItem=request.getParameter("dataItem");
				String sortDescStr=request.getParameter("sortDesc");
				
				Boolean sortDesc=false;
				
				if(dataItem==null||dataItem.equals(""))
				dataItem="genWh";
				if(sortDescStr==null||sortDescStr.equals(""))
					sortDescStr="false";
				if(sortDescStr.equals("true"))
					sortDesc=true;
				
				HtmlUtil.writeStrToHtml(rank_APP_Service.getMonthRankStr(request.getMethod(),dataItem, sortDesc), response);
				return null;
		}
	
		//风电场1年数据排行榜
				public ActionForward yearRank(ActionMapping mapping, ActionForm form,
						HttpServletRequest request, HttpServletResponse response)
						throws Exception {
						String dataItem=request.getParameter("dataItem");
						String sortDescStr=request.getParameter("sortDesc");
						
						Boolean sortDesc=false;
						
						if(dataItem==null||dataItem.equals(""))
						dataItem="genWh";
						if(sortDescStr==null||sortDescStr.equals(""))
							sortDescStr="false";
						if(sortDescStr.equals("true"))
							sortDesc=true;
						
						HtmlUtil.writeStrToHtml(rank_APP_Service.getYearRankStr(request.getMethod(),dataItem, sortDesc), response);
						return null;
				}
}
