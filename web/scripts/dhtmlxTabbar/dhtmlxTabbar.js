/*
 * dhtmlxTabbar v2.0
 * http://icewee.cnblogs.com
 *
 * Copyright (c) 2011
 * author: IceWee
 * blog: http://icewee.cnblogs.com
 * Dual licensed under the MIT and GPL licenses.
 *
 * Date: 2011-05-06 10:23:39 -0500 (Fri, 06 May 2011)
 */

/**
 * @desc: tabbar object
 * 
 * @param: htmlObject - parent html object or id of parent html object
 * @param: width - object width
 * @param: height - object height
 * 
 * @type: public
 */
function dhtmlxTabbar(htmlObject, width, height) {
	this._tabsCount = 0;
	this._tabHrefIdTemp = "dhtmlxTabBar_a_";
	this._tabDivIdTemp = "dhtmlxTabBar_div_";
	this._tabIframeIdTemp = "dhtmlxTabBar_iframe_";
	this._containerWidth = width;
	this._containerHeight = height;
	if (typeof (htmlObject) != "object") {
		this._parentObject = document.getElementById(htmlObject);
	} else {
		this._parentObject = htmlObject;
	}
	this._containerCSS = "dhx_container";
	this._tabNavCSS = "dhx_tab_nav";
	this._tabPanelCSS = "dhx_tab_panel";
	this._tabActiveCSS = "selected";
	this._lastActiveTab = null; // initialize activeTab
	this._lastActivePage = null; // initialize activePage
	this._imgPath = "images/";
	this._createSelf();
	if(navigator.userAgent.indexOf("MSIE") > 0) {
		this._isIE = true;
	} else {
		this._isIE = false;
	}
	return this;
};

/**
 * @desc: set margin between tab
 * 
 * @param: id - id of tab
 * @param: text - text of tab
 * @param: width - size of tab
 * @param: position - tab position
 * 
 * @type: public
 */
dhtmlxTabbar.prototype.addTab = function(id, text, width, position) {
	if (isNaN(position)) {
		position = this._tabsCount;
	}
	if (this._offset) {
		this._tabNode.style.paddingLeft = this._offset;
	}
	var aimTab = this._getTabByPosition(position);
	var aimTabPage = this._getTabPageByPosition(position);
	var tab = this._createTab(id, text);
	var tabPage = this._createTabPage(id);
	if (position == this._tabsCount || position > this._tabsCount) {
		this._tabNode.appendChild(tab);
		this._pageNode.appendChild(tabPage);
	} else {
		this._tabNode.insertBefore(tab, aimTab);
		this._pageNode.insertBefore(tabPage, aimTabPage);
	}
	this._initCommonTabStyle(id, width);
	this._tabsCount++;
};

/**
 * @desc: get active tab id
 * 
 * @type: public
 */
dhtmlxTabbar.prototype.getActiveTabId = function() {
	if (this._lastActiveTab) {
		var tabId = this._lastActiveTab.id;
		return this._getId(tabId);
	}
	return null;
};

/**
 * @desc: event called when any tab selected
 * 
 * @param: func - dispose function which user support
 * 
 * @type: public
 */
dhtmlxTabbar.prototype.setOnSelectHandler = function(func) {
	if (typeof (func) == "function") {
		this._onSelect = func;
	} else {
		this._onSelect = eval(func);
	}
};

/**
 * @desc: set tab active and call call-back function
 * 
 * @param: id - id of the tab which user support
 * 
 * @type: public
 */
dhtmlxTabbar.prototype.setTabActive = function(id) {
	var tabHrefId = this._tabHrefIdTemp + id;
	var tab = document.getElementById(tabHrefId);
	var tabPageId = this._tabDivIdTemp + id;
	var tabPage = document.getElementById(tabPageId);
	this._setTabSelected(tab, tabPage);
};

/**
 * @desc: set tab selected without call call-back function
 * 
 * @param: id - id of the tab which user support
 * 
 * @type: public
 */
dhtmlxTabbar.prototype.selectTab = function(id) {
	var tabHrefId = this._tabHrefIdTemp + id;
	var tab = document.getElementById(tabHrefId);
	var tabPageId = this._tabDivIdTemp + id;
	var tabPage = document.getElementById(tabPageId);
	if (!this._lastActiveTab) {
		this._setTabActive(tab, tabPage);
	} else {
		this._setTabUnActive(this._lastActiveTab, this._lastActivePage);
		this._setTabActive(tab, tabPage);
	}
	this._lastActiveTab = tab;
	this._lastActivePage = tabPage;
};

/**
 * @desc: set offset of left
 * 
 * @param: offset - offset value
 * 
 * @type: public
 */
dhtmlxTabbar.prototype.setOffset = function(offset) {
	this._offset = offset;
};

/**
 * @desc: set margin between tab
 * 
 * @param: margin - margin value
 * 
 * @type: public
 */
dhtmlxTabbar.prototype.setMargin = function(margin) {
	this._margin = margin;
};

/**
 * @desc: set image path
 * 
 * @param: path - path to image folder
 * 
 * @type: public
 */
dhtmlxTabbar.prototype.setImagePath = function(path) {
	this._imgPath = path;
};

/**
 * @desc: get id of iframe with tab init id
 * 
 * @param: id - init id of tab
 * 
 * @type: public
 */
dhtmlxTabbar.prototype.getTabIframeId = function(id) {
	return this._tabIframeIdTemp + id;
};

/**
 * @desc: get window object with id
 * 
 * @param: id - init id of tab
 * 
 * @type: public
 */
dhtmlxTabbar.prototype.getTabWindow = function(id) {
	var iframeId = this._tabIframeIdTemp + id;
	var iframe = document.getElementById(iframeId);
	return iframe.contentWindow;
};

/**
 * @desc: submit href to target
 * 
 * @param: id - id of tab which user support
 * @param: href - href
 * 
 * @type: public
 */
dhtmlxTabbar.prototype.submitHref = function(id, href) {
	var targetIframeId = this._tabIframeIdTemp + id;
	var iframe = document.getElementById(targetIframeId);
	iframe.src = href;
};

/**
 * @desc: create object HTML
 * 
 * @type: private
 */
dhtmlxTabbar.prototype._createSelf = function() {
	var div = document.createElement("DIV");
	div.innerHTML = '<table cellspacing="0" cellpadding="0" class="' + this._containerCSS + '" width="' + this._containerWidth + '" height="' + this._containerHeight + '" border="0">'
				+ '<tr><td><ul class="' + this._tabNavCSS + '"></ul></td></tr>'
				+ '<tr>'
				+ '<td class="' + this._tabPanelCSS + '" height="100%">'
				+ '<TABLE height="100%" width="100%" align="left" border="0" cellspacing="0" cellpadding="0">'
				+ '<TR><TD height="100%" width="100%"></TD></TR>'
				+ '</TABLE>'
				+ '</td>'
				+ '</tr>'
				+ '</table>';
	var container = div.childNodes[0];
	this._rootNode = container;
	this._tabNode = container.childNodes[0].childNodes[0].childNodes[0].childNodes[0];
	this._pageNode = container.childNodes[0].childNodes[1].childNodes[0].childNodes[0].childNodes[0].childNodes[0].childNodes[0];
	this._parentObject.appendChild(container);
};

/**
 * @desc: set tab selected and call call-back function
 * 
 * @param: tab - tab object
 * @param: tabPage - tab page
 * 
 * @type: private
 */
dhtmlxTabbar.prototype._setTabSelected = function(tab, tabPage) {
	if (tab && tabPage) {
		var idn = this._getId(tab.id);
		var ido = this._lastActiveTab ? this._getId(this._lastActiveTab.id) : null;
		if ((this._onSelect) && (!this._onSelect(idn, ido)))
			return;
		if (!this._lastActiveTab) {
			this._setTabActive(tab, tabPage);
		} else {
			this._setTabUnActive(this._lastActiveTab, this._lastActivePage);
			this._setTabActive(tab, tabPage);
		}
		this._lastActiveTab = tab;
		this._lastActivePage = tabPage;
	}
};

/**
 * @desc: get tab object throw position
 * 
 * @param: position - tab position
 * 
 * @type: private
 */
dhtmlxTabbar.prototype._getTabByPosition = function(position) {
	return this._tabNode.childNodes[position];
};

/**
 * @desc: get tab page object throw position
 * 
 * @param: position - tab page position
 * 
 * @type: private
 */
dhtmlxTabbar.prototype._getTabPageByPosition = function(position) {
	return this._pageNode.childNodes[position];
};

/**
 * @desc: initialize unselected tab style
 * 
 * @param: id - id of tab user support
 * @param: width - width of tab
 * 
 * @type: private
 */
dhtmlxTabbar.prototype._initCommonTabStyle = function(id, width) {
	var tabHrefId = this._tabHrefIdTemp + id;
	var tab = document.getElementById(tabHrefId);
	this._setCommonTabStyle(tab, width);
	this._setCommonTabBackgroundImg(tab);
};

/**
 * @desc: onTab Click handler
 * 
 * @type: private
 */
dhtmlxTabbar.prototype._onClickHandler = function(event) {
	if(this.tabbar._isIE) {
		event = window.event;
		var target = event.srcElement;
	} else {
		var target = event.srcElement ? event.srcElement : event.target;
	}
	event.cancelBubble = true;
	var tabId = target.id;
	this.tabbar.setTabActive(this.tabbar._getId(tabId));
};

/**
 * @desc: onTab mouse over handler
 * 
 * @type: private
 */
dhtmlxTabbar.prototype._onMouseOverHandler = function(event) {
	if(this.tabbar._isIE) {
		event = window.event;
		var target = event.srcElement;
	} else {
		var target = event.srcElement ? event.srcElement : event.target;
	}
	event.cancelBubble = true;
	var tabId = target.id;
	this.tabbar._setTabHoverStyle(tabId);
};

/**
 * @desc: onTab mouse out handler
 * 
 * @type: private
 */
dhtmlxTabbar.prototype._onMouseOutHandler = function(event) {
	if(this.tabbar._isIE) {
		event = window.event;
		var target = event.srcElement;
	} else {
		var target = event.srcElement ? event.srcElement : event.target;
	}
	event.cancelBubble = true;
	var tabId = target.id;
	this.tabbar._setTabDefaultStyle(tabId);
};

/**
 * @desc: get init id user supported use multiply id
 * 
 * @param: tabId - tab id
 * 
 * @type: private
 */
dhtmlxTabbar.prototype._getId = function(tabId) {
	return tabId.substring(15, tabId.length); // 15 : dhtmlxTabBar_a_ length
};

/**
 * @desc: create tab object
 * 
 * @param: id - tab init id
 * @param: text - text of tab
 * @param: size - size of tab
 * 
 * @type: private
 */
dhtmlxTabbar.prototype._createTab = function(id, text, size) {
	var tabHrefId = this._tabHrefIdTemp + id;
	var div = document.createElement("DIV");
	div.innerHTML = '<li>' + '<a id="' + tabHrefId + '" href="#">' + text + '</a>' + '</li>';
	var tab = div.childNodes[0];
	tab.onclick = this._onClickHandler;
	tab.onmouseover = this._onMouseOverHandler;
	tab.onmouseout = this._onMouseOutHandler;
	tab.tabbar = this;
	return tab;
};

/**
 * @desc: create tab page object
 * 
 * @param: id - init id of tab
 * 
 * @type: private
 */
dhtmlxTabbar.prototype._createTabPage = function(id) {
	var tabDivId = this._tabDivIdTemp + id;
	var tabIframeId = this._tabIframeIdTemp + id;
	var div = document.createElement("DIV");
	div.innerHTML = '<div id="' + tabDivId + '" style="display:none" height="100%" width="100%" valign="top">'
				  + '<iframe id="' + tabIframeId + '" name="' + tabIframeId + '"src="" align="left" frameBorder="0" height="100%" width="100%" scrolling="auto"></iframe>'
				  + '</div>';
	var page = div.childNodes[0];
	return page;
};

/**
 * @desc: set tab active
 * 
 * @param: tab - tab object
 * @param: tabIframe - div
 * 
 * @type: private
 */
dhtmlxTabbar.prototype._setTabActive = function(tab, tabIframe) {
	this._setSelectedTabStyle(tab);
	this._setSelectedTabBackgroundImg(tab);
	tabIframe.style.display = "inline";
	window.focus();
};

/**
 * @desc: set tab unActive
 * 
 * @param: tab - tab object
 * @param: tabIframe - div
 * 
 * @type: private
 */
dhtmlxTabbar.prototype._setTabUnActive = function(tab, tabIframe) {
	this._setCommonTabStyle(tab);
	this._setCommonTabBackgroundImg(tab);
	tabIframe.style.display = "none";
	// window.blur();
};

/**
 * @desc: set unselected tab style
 * 
 * @param: tab - tab id or tab object
 * @param: width - width of tab
 * 
 * @type: private
 */
dhtmlxTabbar.prototype._setCommonTabStyle = function(tab, width) {
	if (typeof (tab) != "object") {
		tab = document.getElementById(tab);
	}
	tab.className = "";
	if (this._margin) {
		tab.style.marginRight = this._margin;
	}
	if (!isNaN(width)) {
		tab.style.width = width;
	}
};

/**
 * @desc: set selected tab style
 * 
 * @param: tab - tab id or tab object
 * 
 * @type: private
 */
dhtmlxTabbar.prototype._setSelectedTabStyle = function(tab) {
	if (typeof (tab) != "object") {
		tab = document.getElementById(tab);
	}
	tab.className = this._tabActiveCSS;
	if (this._margin) {
		tab.style.marginRight = this._margin;
	}
};

/**
 * @desc: set unselected tab background image
 * 
 * @param: tab - tab id or tab object
 * 
 * @type: private
 */
dhtmlxTabbar.prototype._setCommonTabBackgroundImg = function(tab) {
	if (typeof (tab) != "object") {
		tab = document.getElementById(tab);
	}
	var url = this._imgPath + "tab_bg_def.png";
	tab.style.backgroundImage = "url(" + url + ")";
	tab.style.backgroundRepeat = "repeat-x";
	tab.style.backgroundPosition = "50% 50%";
};

/**
 * @desc: set selected tab background image
 * 
 * @param: tab - tab id or tab object
 * 
 * @type: private
 */
dhtmlxTabbar.prototype._setSelectedTabBackgroundImg = function(tab) {
	if (typeof (tab) != "object") {
		tab = document.getElementById(tab);
	}
	var url = this._imgPath + "tab_bg_active.png";
	tab.style.backgroundImage = "url(" + url + ")";
	tab.style.backgroundRepeat = "repeat-x";
	tab.style.backgroundPosition = "50% 50%";
};

/**
 * @desc: set tab mouse over background image
 * 
 * @param: tab - tab id or tab object
 * 
 * @type: private
 */
dhtmlxTabbar.prototype._setTabHoverStyle = function(tabId) {
	if (tabId && tabId != "") {
		var tab = document.getElementById(tabId);
		if (tab && tab != this._lastActiveTab) {
			var url = this._imgPath + "tab_bg_hover.png";
			tab.style.backgroundImage = "url(" + url + ")";
			tab.style.backgroundRepeat = "repeat-x";
			tab.style.backgroundPosition = "50% 50%";
		}
	}
};

/**
 * @desc: set tab mouse out background image
 * 
 * @param: tab - tab id or tab object
 * 
 * @type: private
 */
dhtmlxTabbar.prototype._setTabDefaultStyle = function(tabId) {
	if (tabId && tabId != "") {
		var tab = document.getElementById(tabId);
		if (tab && tab != this._lastActiveTab) {
			var url = this._imgPath + "tab_bg_def.png";
			tab.style.backgroundImage = "url(" + url + ")";
			tab.style.backgroundRepeat = "repeat-x";
			tab.style.backgroundPosition = "50% 50%";
		}
	}
};
