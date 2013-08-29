<%@ page import="foodprint.CustomerOrder" %>



<div class="fieldcontain ${hasErrors(bean: customerOrderInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="customerOrder.name.label" default="Name" />
		
	</label>
	<g:textField name="name" value="${customerOrderInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: customerOrderInstance, field: 'dueDate', 'error')} ">
	<label for="dueDate">
		<g:message code="customerOrder.dueDate.label" default="Due Date" />
		
	</label>
	<g:datePicker name="dueDate" precision="day"  value="${customerOrderInstance?.dueDate}" default="none" noSelection="['': '']" />
</div>

<div class="fieldcontain ${hasErrors(bean: customerOrderInstance, field: 'customer', 'error')} ">
	<label for="customer">
		<g:message code="customerOrder.customer.label" default="Customer" />
		
	</label>
	<g:select id="customer" name="customer.id" from="${foodprint.Customer.list()}" optionKey="id" value="${customerOrderInstance?.customer?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: customerOrderInstance, field: 'details', 'error')} ">
	<label for="details">
		<g:message code="customerOrder.details.label" default="Details" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${customerOrderInstance?.details?}" var="d">
    <li><g:link controller="customerOrderDet" action="show" id="${d.id}">${d?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="customerOrderDet" action="create" params="['customerOrder.id': customerOrderInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'customerOrderDet.label', default: 'CustomerOrderDet')])}</g:link>
</li>
</ul>

</div>

