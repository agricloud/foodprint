
<%@ page import="foodprint.BatchRoute" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'batchRoute.label', default: 'BatchRoute')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-batchRoute" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-batchRoute" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list batchRoute">
			
				<g:if test="${batchRouteInstance?.batch}">
				<li class="fieldcontain">
					<span id="batch-label" class="property-label"><g:message code="batchRoute.batch.label" default="Batch" /></span>
					
						<span class="property-value" aria-labelledby="batch-label"><g:link controller="batch" action="show" id="${batchRouteInstance?.batch?.id}">${batchRouteInstance?.batch?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${batchRouteInstance?.operation}">
				<li class="fieldcontain">
					<span id="operation-label" class="property-label"><g:message code="batchRoute.operation.label" default="Operation" /></span>
					
						<span class="property-value" aria-labelledby="operation-label"><g:link controller="operation" action="show" id="${batchRouteInstance?.operation?.id}">${batchRouteInstance?.operation?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${batchRouteInstance?.sequence}">
				<li class="fieldcontain">
					<span id="sequence-label" class="property-label"><g:message code="batchRoute.sequence.label" default="Sequence" /></span>
					
						<span class="property-value" aria-labelledby="sequence-label"><g:fieldValue bean="${batchRouteInstance}" field="sequence"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${batchRouteInstance?.workstation}">
				<li class="fieldcontain">
					<span id="workstation-label" class="property-label"><g:message code="batchRoute.workstation.label" default="Workstation" /></span>
					
						<span class="property-value" aria-labelledby="workstation-label"><g:link controller="workstation" action="show" id="${batchRouteInstance?.workstation?.id}">${batchRouteInstance?.workstation?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${batchRouteInstance?.id}" />
					<g:link class="edit" action="edit" id="${batchRouteInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
