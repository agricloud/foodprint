
<%@ page import="finder.Item" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'item.label', default: 'Item')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-item" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-item" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="dueDays" title="${message(code: 'item.dueDays.label', default: 'Due Days')}" />
					
						<g:sortableColumn property="effectStartDate" title="${message(code: 'item.effectStartDate.label', default: 'Effect Start Date')}" />
					
						<g:sortableColumn property="effectEndDate" title="${message(code: 'item.effectEndDate.label', default: 'Effect End Date')}" />
					
						<g:sortableColumn property="creator" title="${message(code: 'item.creator.label', default: 'Creator')}" />
					
						<g:sortableColumn property="dateCreated" title="${message(code: 'item.dateCreated.label', default: 'Date Created')}" />
					
						<g:sortableColumn property="description" title="${message(code: 'item.description.label', default: 'Description')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${itemInstanceList}" status="i" var="itemInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${itemInstance.id}">${fieldValue(bean: itemInstance, field: "dueDays")}</g:link></td>
					
						<td><g:formatDate date="${itemInstance.effectStartDate}" /></td>
					
						<td><g:formatDate date="${itemInstance.effectEndDate}" /></td>
					
						<td>${fieldValue(bean: itemInstance, field: "creator")}</td>
					
						<td><g:formatDate date="${itemInstance.dateCreated}" /></td>
					
						<td>${fieldValue(bean: itemInstance, field: "description")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${itemInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
