<%@ page import="foodprint.Operation" %>



<div class="fieldcontain ${hasErrors(bean: operationInstance, field: 'description', 'error')} ">
	<label for="description">
		<g:message code="operation.description.label" default="Description" />
		
	</label>
	<g:textField name="description" value="${operationInstance?.description}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: operationInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="operation.name.label" default="Name" />
		
	</label>
	<g:textField name="name" value="${operationInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: operationInstance, field: 'title', 'error')} ">
	<label for="title">
		<g:message code="operation.title.label" default="Title" />
		
	</label>
	<g:textField name="title" value="${operationInstance?.title}"/>
</div>

