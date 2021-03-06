<%@ page language="java" isErrorPage="true" %>
<%@ page contentType="text/html;charset=gbk"language="java" %>
<%@ include file="./pages/common/taglibs.jsp"%>



<!-- I do not integrate this page as a tile, but rather as a standalone-page -->
<html>
<head>
	<title><fmt:message key="errorPage.title"/></title>
    <link rel="stylesheet" type="text/css" media="all" href="<c:url value="/styles/default.css"/>" /> 
</head>

<body>
<div id="screen">
    <div id="content">
    <h1><fmt:message key="errorPage.heading"/></h1>
    <%-- Error Messages --%>
    <logic:messagesPresent>\
        <logic:present name="error">    
        <div class="error">	
            <html:messages id="error">
              <c:out value="${error}" escapeXml="false"/><br/>
            </html:messages>
        </div>
        </logic:present>
    </logic:messagesPresent>
 <% if (exception != null) { %>
    <pre><% exception.printStackTrace(new java.io.PrintWriter(out)); %></pre>
 <% } else if ((Exception)request.getAttribute("javax.servlet.error.exception") != null) { %>
    <pre><% ((Exception)request.getAttribute("javax.servlet.error.exception"))
                           .printStackTrace(new java.io.PrintWriter(out)); %></pre>
 <% } else if (pageContext.findAttribute("org.apache.struts.action.EXCEPTION") != null) { %>
    <bean:define id="exception2" name="org.apache.struts.action.EXCEPTION"
     type="java.lang.Exception"/>
    <c:if test="${exception2 != null}">
        <pre><% exception2.printStackTrace(new java.io.PrintWriter(out));%></pre>
    </c:if>
    <%-- only show this if no error messages present --%>
    <c:if test="${exception2 == null}">
        <fmt:message key="errors.none"/>
    </c:if>
 <% } %>
    </div>
</body>
</html>
