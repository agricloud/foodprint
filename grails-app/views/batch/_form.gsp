<%@ page import="foodprint.Batch" %>



<div class="fieldcontain ${hasErrors(bean: batchInstance, field: 'dueDate', 'error')} ">
	<label for="dueDate">
		<g:message code="batch.dueDate.label" default="Due Date" />
		
	</label>
	<g:datePicker name="dueDate" precision="day"  value="${batchInstance?.dueDate}" default="none" noSelection="['': '']" />
</div>

<div class="fieldcontain ${hasErrors(bean: batchInstance, field: 'creator', 'error')} ">
	<label for="creator">
		<g:message code="batch.creator.label" default="Creator" />
		
	</label>
	<g:textField name="creator" value="${batchInstance?.creator}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: batchInstance, field: 'editor', 'error')} ">
	<label for="editor">
		<g:message code="batch.editor.label" default="Editor" />
		
	</label>
	<g:textField name="editor" value="${batchInstance?.editor}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: batchInstance, field: 'expectQty', 'error')} required">
	<label for="expectQty">
		<g:message code="batch.expectQty.label" default="Expect Qty" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="expectQty" type="number" value="${batchInstance.expectQty}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: batchInstance, field: 'item', 'error')} required">
	<label for="item">
		<g:message code="batch.item.label" default="Item" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="item" name="item.id" from="${foodprint.Item.list()}" optionKey="id" required="" value="${batchInstance?.item?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: batchInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="batch.name.label" default="Name" />
		
	</label>
	<g:textField name="name" value="${batchInstance?.name}"/>
</div>

