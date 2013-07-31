<%@ page import="foodprint.Workstation" %>



<div class="fieldcontain ${hasErrors(bean: workstationInstance, field: 'description', 'error')} ">
	<label for="description">
		<g:message code="workstation.description.label" default="Description" />
		
	</label>
	<g:textField name="description" value="${workstationInstance?.description}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: workstationInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="workstation.name.label" default="Name" />
		
	</label>
	<g:textField name="name" value="${workstationInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: workstationInstance, field: 'title', 'error')} ">
	<label for="title">
		<g:message code="workstation.title.label" default="Title" />
		
	</label>
	<g:textField name="title" value="${workstationInstance?.title}"/>
</div>

