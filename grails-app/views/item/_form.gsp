<%@ page import="finder.Item" %>



<div class="fieldcontain ${hasErrors(bean: itemInstance, field: 'dueDays', 'error')} ">
	<label for="dueDays">
		<g:message code="item.dueDays.label" default="Due Days" />
		
	</label>
	<g:field name="dueDays" type="number" value="${itemInstance.dueDays}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: itemInstance, field: 'effectStartDate', 'error')} ">
	<label for="effectStartDate">
		<g:message code="item.effectStartDate.label" default="Effect Start Date" />
		
	</label>
	<g:datePicker name="effectStartDate" precision="day"  value="${itemInstance?.effectStartDate}" default="none" noSelection="['': '']" />
</div>

<div class="fieldcontain ${hasErrors(bean: itemInstance, field: 'effectEndDate', 'error')} ">
	<label for="effectEndDate">
		<g:message code="item.effectEndDate.label" default="Effect End Date" />
		
	</label>
	<g:datePicker name="effectEndDate" precision="day"  value="${itemInstance?.effectEndDate}" default="none" noSelection="['': '']" />
</div>

<div class="fieldcontain ${hasErrors(bean: itemInstance, field: 'creator', 'error')} ">
	<label for="creator">
		<g:message code="item.creator.label" default="Creator" />
		
	</label>
	<g:textField name="creator" value="${itemInstance?.creator}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: itemInstance, field: 'description', 'error')} ">
	<label for="description">
		<g:message code="item.description.label" default="Description" />
		
	</label>
	<g:textField name="description" value="${itemInstance?.description}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: itemInstance, field: 'editor', 'error')} ">
	<label for="editor">
		<g:message code="item.editor.label" default="Editor" />
		
	</label>
	<g:textField name="editor" value="${itemInstance?.editor}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: itemInstance, field: 'itemImages', 'error')} ">
	<label for="itemImages">
		<g:message code="item.itemImages.label" default="Item Images" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${itemInstance?.itemImages?}" var="i">
    <li><g:link controller="itemImage" action="show" id="${i.id}">${i?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="itemImage" action="create" params="['item.id': itemInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'itemImage.label', default: 'ItemImage')])}</g:link>
</li>
</ul>

</div>

<div class="fieldcontain ${hasErrors(bean: itemInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="item.name.label" default="Name" />
		
	</label>
	<g:textField name="name" value="${itemInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: itemInstance, field: 'title', 'error')} ">
	<label for="title">
		<g:message code="item.title.label" default="Title" />
		
	</label>
	<g:textField name="title" value="${itemInstance?.title}"/>
</div>

