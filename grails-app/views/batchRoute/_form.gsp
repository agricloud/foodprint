<%@ page import="foodprint.BatchRoute" %>



<div class="fieldcontain ${hasErrors(bean: batchRouteInstance, field: 'batch', 'error')} required">
	<label for="batch">
		<g:message code="batchRoute.batch.label" default="Batch" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="batch" name="batch.id" from="${foodprint.Batch.list()}" optionKey="id" required="" value="${batchRouteInstance?.batch?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: batchRouteInstance, field: 'operation', 'error')} required">
	<label for="operation">
		<g:message code="batchRoute.operation.label" default="Operation" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="operation" name="operation.id" from="${foodprint.Operation.list()}" optionKey="id" required="" value="${batchRouteInstance?.operation?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: batchRouteInstance, field: 'sequence', 'error')} required">
	<label for="sequence">
		<g:message code="batchRoute.sequence.label" default="Sequence" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="sequence" type="number" value="${batchRouteInstance.sequence}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: batchRouteInstance, field: 'workstation', 'error')} required">
	<label for="workstation">
		<g:message code="batchRoute.workstation.label" default="Workstation" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="workstation" name="workstation.id" from="${foodprint.Workstation.list()}" optionKey="id" required="" value="${batchRouteInstance?.workstation?.id}" class="many-to-one"/>
</div>

