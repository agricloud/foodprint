
<%@ page import="foodprint.BatchRoute" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'batchRoute.label', default: 'BatchRoute')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-batchRoute" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-batchRoute" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<th><g:message code="batchRoute.batch.label" default="Batch" /></th>
					
						<th><g:message code="batchRoute.operation.label" default="Operation" /></th>
					
						<g:sortableColumn property="sequence" title="${message(code: 'batchRoute.sequence.label', default: 'Sequence')}" />
					
						<th><g:message code="batchRoute.workstation.label" default="Workstation" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${batchRouteInstanceList}" status="i" var="batchRouteInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${batchRouteInstance.id}">${fieldValue(bean: batchRouteInstance, field: "batch")}</g:link></td>
					
						<td>${fieldValue(bean: batchRouteInstance, field: "operation")}</td>
					
						<td>${fieldValue(bean: batchRouteInstance, field: "sequence")}</td>
					
						<td>${fieldValue(bean: batchRouteInstance, field: "workstation")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${batchRouteInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
