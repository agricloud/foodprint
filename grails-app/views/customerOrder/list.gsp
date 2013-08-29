
<%@ page import="foodprint.CustomerOrder" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'customerOrder.label', default: 'CustomerOrder')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-customerOrder" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-customerOrder" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="name" title="${message(code: 'customerOrder.name.label', default: 'Name')}" />
					
						<g:sortableColumn property="dueDate" title="${message(code: 'customerOrder.dueDate.label', default: 'Due Date')}" />
					
						<th><g:message code="customerOrder.customer.label" default="Customer" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${customerOrderInstanceList}" status="i" var="customerOrderInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${customerOrderInstance.id}">${fieldValue(bean: customerOrderInstance, field: "name")}</g:link></td>
					
						<td><g:formatDate date="${customerOrderInstance.dueDate}" /></td>
					
						<td>${fieldValue(bean: customerOrderInstance, field: "customer")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${customerOrderInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
