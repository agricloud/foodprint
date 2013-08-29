<%@ page import="foodprint.CustomerOrderDet" %>



<div class="fieldcontain ${hasErrors(bean: customerOrderDetInstance, field: 'head', 'error')} required">
	<label for="head">
		<g:message code="customerOrderDet.head.label" default="Head" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="head" name="head.id" from="${foodprint.CustomerOrder.list()}" optionKey="id" required="" value="${customerOrderDetInstance?.head?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: customerOrderDetInstance, field: 'item', 'error')} required">
	<label for="item">
		<g:message code="customerOrderDet.item.label" default="Item" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="item" name="item.id" from="${foodprint.Item.list()}" optionKey="id" required="" value="${customerOrderDetInstance?.item?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: customerOrderDetInstance, field: 'qty', 'error')} required">
	<label for="qty">
		<g:message code="customerOrderDet.qty.label" default="Qty" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="qty" type="number" value="${customerOrderDetInstance.qty}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: customerOrderDetInstance, field: 'sequence', 'error')} required">
	<label for="sequence">
		<g:message code="customerOrderDet.sequence.label" default="Sequence" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="sequence" type="number" value="${customerOrderDetInstance.sequence}" required=""/>
</div>

