
<%@ page import="foodprint.CustomerOrder" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'customerOrder.label', default: 'CustomerOrder')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-customerOrder" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-customerOrder" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list customerOrder">
			
				<g:if test="${customerOrderInstance?.name}">
				<li class="fieldcontain">
					<span id="name-label" class="property-label"><g:message code="customerOrder.name.label" default="Name" /></span>
					
						<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${customerOrderInstance}" field="name"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${customerOrderInstance?.dueDate}">
				<li class="fieldcontain">
					<span id="dueDate-label" class="property-label"><g:message code="customerOrder.dueDate.label" default="Due Date" /></span>
					
						<span class="property-value" aria-labelledby="dueDate-label"><g:formatDate date="${customerOrderInstance?.dueDate}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${customerOrderInstance?.customer}">
				<li class="fieldcontain">
					<span id="customer-label" class="property-label"><g:message code="customerOrder.customer.label" default="Customer" /></span>
					
						<span class="property-value" aria-labelledby="customer-label"><g:link controller="customer" action="show" id="${customerOrderInstance?.customer?.id}">${customerOrderInstance?.customer?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${customerOrderInstance?.details}">
				<li class="fieldcontain">
					<span id="details-label" class="property-label"><g:message code="customerOrder.details.label" default="Details" /></span>
					
						<g:each in="${customerOrderInstance.details}" var="d">
						<span class="property-value" aria-labelledby="details-label"><g:link controller="customerOrderDet" action="show" id="${d.id}">${d?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${customerOrderInstance?.id}" />
					<g:link class="edit" action="edit" id="${customerOrderInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
