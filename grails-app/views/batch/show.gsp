
<%@ page import="finder.Batch" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'batch.label', default: 'Batch')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-batch" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-batch" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list batch">
			
				<g:if test="${batchInstance?.dueDate}">
				<li class="fieldcontain">
					<span id="dueDate-label" class="property-label"><g:message code="batch.dueDate.label" default="Due Date" /></span>
					
						<span class="property-value" aria-labelledby="dueDate-label"><g:formatDate date="${batchInstance?.dueDate}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${batchInstance?.creator}">
				<li class="fieldcontain">
					<span id="creator-label" class="property-label"><g:message code="batch.creator.label" default="Creator" /></span>
					
						<span class="property-value" aria-labelledby="creator-label"><g:fieldValue bean="${batchInstance}" field="creator"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${batchInstance?.dateCreated}">
				<li class="fieldcontain">
					<span id="dateCreated-label" class="property-label"><g:message code="batch.dateCreated.label" default="Date Created" /></span>
					
						<span class="property-value" aria-labelledby="dateCreated-label"><g:formatDate date="${batchInstance?.dateCreated}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${batchInstance?.editor}">
				<li class="fieldcontain">
					<span id="editor-label" class="property-label"><g:message code="batch.editor.label" default="Editor" /></span>
					
						<span class="property-value" aria-labelledby="editor-label"><g:fieldValue bean="${batchInstance}" field="editor"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${batchInstance?.expectQty}">
				<li class="fieldcontain">
					<span id="expectQty-label" class="property-label"><g:message code="batch.expectQty.label" default="Expect Qty" /></span>
					
						<span class="property-value" aria-labelledby="expectQty-label"><g:fieldValue bean="${batchInstance}" field="expectQty"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${batchInstance?.item}">
				<li class="fieldcontain">
					<span id="item-label" class="property-label"><g:message code="batch.item.label" default="Item" /></span>
					
						<span class="property-value" aria-labelledby="item-label"><g:link controller="item" action="show" id="${batchInstance?.item?.id}">${batchInstance?.item?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${batchInstance?.lastUpdated}">
				<li class="fieldcontain">
					<span id="lastUpdated-label" class="property-label"><g:message code="batch.lastUpdated.label" default="Last Updated" /></span>
					
						<span class="property-value" aria-labelledby="lastUpdated-label"><g:formatDate date="${batchInstance?.lastUpdated}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${batchInstance?.name}">
				<li class="fieldcontain">
					<span id="name-label" class="property-label"><g:message code="batch.name.label" default="Name" /></span>
					
						<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${batchInstance}" field="name"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${batchInstance?.id}" />
					<g:link class="edit" action="edit" id="${batchInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
