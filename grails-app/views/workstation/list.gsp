
<%@ page import="foodprint.Workstation" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'workstation.label', default: 'Workstation')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-workstation" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-workstation" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="dateCreated" title="${message(code: 'workstation.dateCreated.label', default: 'Date Created')}" />
					
						<g:sortableColumn property="description" title="${message(code: 'workstation.description.label', default: 'Description')}" />
					
						<g:sortableColumn property="lastUpdated" title="${message(code: 'workstation.lastUpdated.label', default: 'Last Updated')}" />
					
						<g:sortableColumn property="name" title="${message(code: 'workstation.name.label', default: 'Name')}" />
					
						<g:sortableColumn property="title" title="${message(code: 'workstation.title.label', default: 'Title')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${workstationInstanceList}" status="i" var="workstationInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${workstationInstance.id}">${fieldValue(bean: workstationInstance, field: "dateCreated")}</g:link></td>
					
						<td>${fieldValue(bean: workstationInstance, field: "description")}</td>
					
						<td><g:formatDate date="${workstationInstance.lastUpdated}" /></td>
					
						<td>${fieldValue(bean: workstationInstance, field: "name")}</td>
					
						<td>${fieldValue(bean: workstationInstance, field: "title")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${workstationInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
