<%@ include file="./pages/common/taglibs.jsp"%>

<page:applyDecorator name="default">

<title><bean:message key="403.title"/></title>
<content tag="heading"><fmt:message key="403.title"/></content>

<p>
    <fmt:message key="403.message">
        <fmt:param><c:url value="/"/></fmt:param>
    </fmt:message>
</p>
<p style="text-align: center; margin-top: 20px">
    <img style="border: 0" 
        src='<c:url value="/images/403.jpg"/>' 
        alt="Hawaii" />
</p>
</page:applyDecorator>
