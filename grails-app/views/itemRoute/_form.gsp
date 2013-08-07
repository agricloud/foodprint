<%@ page import="foodprint.ItemRoute" %>



<div class="fieldcontain ${hasErrors(bean: itemRouteInstance, field: 'item', 'error')} required">
	<label for="item">
		<g:message code="itemRoute.item.label" default="Item" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="item" name="item.id" from="${foodprint.Item.list()}" optionKey="id" required="" value="${itemRouteInstance?.item?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: itemRouteInstance, field: 'sequence', 'error')} required">
	<label for="sequence">
		<g:message code="itemRoute.sequence.label" default="Sequence" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="sequence" type="number" value="${itemRouteInstance.sequence}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: itemRouteInstance, field: 'workstation', 'error')} required">
	<label for="workstation">
		<g:message code="itemRoute.workstation.label" default="Workstation" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="workstation" name="workstation.id" from="${foodprint.Workstation.list()}" optionKey="id" required="" value="${itemRouteInstance?.workstation?.id}" class="many-to-one"/>
</div>

