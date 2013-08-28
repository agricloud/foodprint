<%@ page import="foodprint.Customer" %>



<div class="fieldcontain ${hasErrors(bean: customerInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="customer.name.label" default="Name" />
		
	</label>
	<g:textField name="name" value="${customerInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: customerInstance, field: 'address', 'error')} ">
	<label for="address">
		<g:message code="customer.address.label" default="Address" />
		
	</label>
	<g:textField name="address" value="${customerInstance?.address}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: customerInstance, field: 'email', 'error')} ">
	<label for="email">
		<g:message code="customer.email.label" default="Email" />
		
	</label>
	<g:textField name="email" value="${customerInstance?.email}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: customerInstance, field: 'title', 'error')} ">
	<label for="title">
		<g:message code="customer.title.label" default="Title" />
		
	</label>
	<g:textField name="title" value="${customerInstance?.title}"/>
</div>

