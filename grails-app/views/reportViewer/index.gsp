<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="bootstrap">
		<title><g:message code="reportViewer.title.label" default="履歷檢視" /></title>

	</head>
	<body>
<div class="panel panel-default">
        <!-- Default panel contents -->
        <div class="panel-heading">Item List</div>
        <!-- Table -->
        <table class="table">
          <thead>
            <tr>
              <th><g:message code="item.id.label" default="item.id" /></th>
              <th><g:message code="item.title.label" default="item.title" /></th>
              <th><g:message code="item.name.label" default="item.name" /></th>
              <th><g:message code="item.description.label" default="item.description" /></th>
            </tr>
          </thead>
          <tbody>
			<g:each in="${itemInstanceList}" var="item" >
	            <tr>
	              <td>${item.id}</td>
	              <td>${item.title}</td>
	              <td>${item.name}</td>
	              <td>${item.description}</td>
	            </tr>
			</g:each>
          </tbody>
        </table>
      </div>


	</body>
</html>