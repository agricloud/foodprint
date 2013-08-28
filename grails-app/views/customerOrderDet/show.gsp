
<%@ page import="foodprint.CustomerOrderDet" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'customerOrderDet.label', default: 'CustomerOrderDet')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-customerOrderDet" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-customerOrderDet" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list customerOrderDet">
			
				<g:if test="${customerOrderDetInstance?.head}">
				<li class="fieldcontain">
					<span id="head-label" class="property-label"><g:message code="customerOrderDet.head.label" default="Head" /></span>
					
						<span class="property-value" aria-labelledby="head-label"><g:link controller="customerOrder" action="show" id="${customerOrderDetInstance?.head?.id}">${customerOrderDetInstance?.head?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${customerOrderDetInstance?.item}">
				<li class="fieldcontain">
					<span id="item-label" class="property-label"><g:message code="customerOrderDet.item.label" default="Item" /></span>
					
						<span class="property-value" aria-labelledby="item-label"><g:link controller="item" action="show" id="${customerOrderDetInstance?.item?.id}">${customerOrderDetInstance?.item?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${customerOrderDetInstance?.qty}">
				<li class="fieldcontain">
					<span id="qty-label" class="property-label"><g:message code="customerOrderDet.qty.label" default="Qty" /></span>
					
						<span class="property-value" aria-labelledby="qty-label"><g:fieldValue bean="${customerOrderDetInstance}" field="qty"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${customerOrderDetInstance?.sequence}">
				<li class="fieldcontain">
					<span id="sequence-label" class="property-label"><g:message code="customerOrderDet.sequence.label" default="Sequence" /></span>
					
						<span class="property-value" aria-labelledby="sequence-label"><g:fieldValue bean="${customerOrderDetInstance}" field="sequence"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${customerOrderDetInstance?.id}" />
					<g:link class="edit" action="edit" id="${customerOrderDetInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
