
<%@ page import="finder.Item" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'item.label', default: 'Item')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-item" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-item" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list item">
			
				<g:if test="${itemInstance?.dueDays}">
				<li class="fieldcontain">
					<span id="dueDays-label" class="property-label"><g:message code="item.dueDays.label" default="Due Days" /></span>
					
						<span class="property-value" aria-labelledby="dueDays-label"><g:fieldValue bean="${itemInstance}" field="dueDays"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${itemInstance?.effectStartDate}">
				<li class="fieldcontain">
					<span id="effectStartDate-label" class="property-label"><g:message code="item.effectStartDate.label" default="Effect Start Date" /></span>
					
						<span class="property-value" aria-labelledby="effectStartDate-label"><g:formatDate date="${itemInstance?.effectStartDate}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${itemInstance?.effectEndDate}">
				<li class="fieldcontain">
					<span id="effectEndDate-label" class="property-label"><g:message code="item.effectEndDate.label" default="Effect End Date" /></span>
					
						<span class="property-value" aria-labelledby="effectEndDate-label"><g:formatDate date="${itemInstance?.effectEndDate}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${itemInstance?.creator}">
				<li class="fieldcontain">
					<span id="creator-label" class="property-label"><g:message code="item.creator.label" default="Creator" /></span>
					
						<span class="property-value" aria-labelledby="creator-label"><g:fieldValue bean="${itemInstance}" field="creator"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${itemInstance?.dateCreated}">
				<li class="fieldcontain">
					<span id="dateCreated-label" class="property-label"><g:message code="item.dateCreated.label" default="Date Created" /></span>
					
						<span class="property-value" aria-labelledby="dateCreated-label"><g:formatDate date="${itemInstance?.dateCreated}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${itemInstance?.description}">
				<li class="fieldcontain">
					<span id="description-label" class="property-label"><g:message code="item.description.label" default="Description" /></span>
					
						<span class="property-value" aria-labelledby="description-label"><g:fieldValue bean="${itemInstance}" field="description"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${itemInstance?.editor}">
				<li class="fieldcontain">
					<span id="editor-label" class="property-label"><g:message code="item.editor.label" default="Editor" /></span>
					
						<span class="property-value" aria-labelledby="editor-label"><g:fieldValue bean="${itemInstance}" field="editor"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${itemInstance?.itemImages}">
				<li class="fieldcontain">
					<span id="itemImages-label" class="property-label"><g:message code="item.itemImages.label" default="Item Images" /></span>
					
						<g:each in="${itemInstance.itemImages}" var="i">
						<span class="property-value" aria-labelledby="itemImages-label"><g:link controller="itemImage" action="show" id="${i.id}">${i?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${itemInstance?.lastUpdated}">
				<li class="fieldcontain">
					<span id="lastUpdated-label" class="property-label"><g:message code="item.lastUpdated.label" default="Last Updated" /></span>
					
						<span class="property-value" aria-labelledby="lastUpdated-label"><g:formatDate date="${itemInstance?.lastUpdated}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${itemInstance?.name}">
				<li class="fieldcontain">
					<span id="name-label" class="property-label"><g:message code="item.name.label" default="Name" /></span>
					
						<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${itemInstance}" field="name"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${itemInstance?.title}">
				<li class="fieldcontain">
					<span id="title-label" class="property-label"><g:message code="item.title.label" default="Title" /></span>
					
						<span class="property-value" aria-labelledby="title-label"><g:fieldValue bean="${itemInstance}" field="title"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${itemInstance?.id}" />
					<g:link class="edit" action="edit" id="${itemInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
