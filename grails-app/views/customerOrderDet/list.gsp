
<%@ page import="foodprint.CustomerOrderDet" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'customerOrderDet.label', default: 'CustomerOrderDet')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-customerOrderDet" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-customerOrderDet" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<th><g:message code="customerOrderDet.head.label" default="Head" /></th>
					
						<th><g:message code="customerOrderDet.item.label" default="Item" /></th>
					
						<g:sortableColumn property="qty" title="${message(code: 'customerOrderDet.qty.label', default: 'Qty')}" />
					
						<g:sortableColumn property="sequence" title="${message(code: 'customerOrderDet.sequence.label', default: 'Sequence')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${customerOrderDetInstanceList}" status="i" var="customerOrderDetInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${customerOrderDetInstance.id}">${fieldValue(bean: customerOrderDetInstance, field: "head")}</g:link></td>
					
						<td>${fieldValue(bean: customerOrderDetInstance, field: "item")}</td>
					
						<td>${fieldValue(bean: customerOrderDetInstance, field: "qty")}</td>
					
						<td>${fieldValue(bean: customerOrderDetInstance, field: "sequence")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${customerOrderDetInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
