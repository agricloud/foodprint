
<%@ page import="foodprint.ItemRoute" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'itemRoute.label', default: 'ItemRoute')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-itemRoute" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-itemRoute" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<th><g:message code="itemRoute.item.label" default="Item" /></th>
					
						<g:sortableColumn property="sequence" title="${message(code: 'itemRoute.sequence.label', default: 'Sequence')}" />
					
						<th><g:message code="itemRoute.workstation.label" default="Workstation" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${itemRouteInstanceList}" status="i" var="itemRouteInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${itemRouteInstance.id}">${fieldValue(bean: itemRouteInstance, field: "item")}</g:link></td>
					
						<td>${fieldValue(bean: itemRouteInstance, field: "sequence")}</td>
					
						<td>${fieldValue(bean: itemRouteInstance, field: "workstation")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${itemRouteInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
