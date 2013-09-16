<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="bootstrap">
		<title><g:message code="reportViewer.title.label" default="履歷檢視" /></title>

	</head>
	<body>
<div class="panel panel-default">
        <!-- Default panel contents -->
        <div class="panel-heading">產品說明</div>
        <!-- Table -->
        <table class="table">
          <thead>
            <tr>
              <th><g:message code="item.name.label" default="${reportData.form.field[0].'@label'}" /></th>
              <th><g:message code="item.title.label" default="${reportData.form.field[1].'@label'}" /></th>
              <th><g:message code="item.name.label" default="${reportData.form.field[2].'@label'}" /></th>
              <th><g:message code="item.unit.label" default="${reportData.form.field[3].'@label'}" /></th>
              <th><g:message code="item.description.label" default="${reportData.form.field[4].'@label'}" /></th>
              <th><g:message code="item.cost.label" default="${reportData.form.field[5].'@label'}" /></th>
              <th><g:message code="item.image.label" default="${reportData.form.field[6].'@label'}" /></th>
              <th><g:message code="item.weight.label" default="${reportData.form.field[7].'@label'}" /></th>
              <th><g:message code="item.workstation.label" default="${reportData.form.field[8].'@label'}" /></th>
            </tr>
          </thead>
          <tbody>
	            <tr>
	              <td>${reportData.form.field[0].text()}</td>
                <td>${reportData.form.field[1].text()}</td>
                <td>${reportData.form.field[2].text()}</td>
                <td>${reportData.form.field[3].text()}</td>
                <td>${reportData.form.field[4].text()}</td>
                <td>${reportData.form.field[5].text()}</td>
                <td>${reportData.form.field[6].text()}</td>
                <td>${reportData.form.field[7].text()}</td>
                <td>${reportData.form.field[8].text()}</td>
	            </tr>
      <g:each in="${reportData}" var="reportData" >
			</g:each>
          </tbody>
        </table>
      </div>

<div class="panel panel-default">
       <!-- Default panel contents -->
      <div class="panel-heading">${reportData.tabs.tab[0].'@title'}</div>
        <!-- Table -->
        <table class="table">
          <thead>
            <tr>
                <th><g:message code="item.name.label" default="${reportData.tabs.tab[0].table.head.column[0].text()}" /></th>
                <th><g:message code="item.name.label" default="${reportData.tabs.tab[0].table.head.column[1].text()}" /></th>
                <th><g:message code="item.name.label" default="${reportData.tabs.tab[0].table.head.column[2].text()}" /></th>
                <th><g:message code="item.name.label" default="${reportData.tabs.tab[0].table.head.column[3].text()}" /></th>
                <th><g:message code="item.name.label" default="${reportData.tabs.tab[0].table.head.column[4].text()}" /></th>
                <th><g:message code="item.name.label" default="${reportData.tabs.tab[0].table.head.column[5].text()}" /></th>
                <th><g:message code="item.name.label" default="${reportData.tabs.tab[0].table.head.column[6].text()}" /></th>
                <th><g:message code="item.name.label" default="${reportData.tabs.tab[0].table.head.column[7].text()}" /></th>
                <th><g:message code="item.name.label" default="${reportData.tabs.tab[0].table.head.column[8].text()}" /></th>
                <th><g:message code="item.name.label" default="${reportData.tabs.tab[0].table.head.column[9].text()}" /></th>
                <th><g:message code="item.name.label" default="${reportData.tabs.tab[0].table.head.column[10].text()}" /></th>
                <th><g:message code="item.name.label" default="${reportData.tabs.tab[0].table.head.column[11].text()}" /></th>
                <th><g:message code="item.name.label" default="${reportData.tabs.tab[0].table.head.column[12].text()}" /></th>
                <th><g:message code="item.name.label" default="${reportData.tabs.tab[0].table.head.column[13].text()}" /></th>
            </tr>
          </thead>
          <tbody>
            <g:each in="${reportData.tabs.tab[0].detail.row}" status="i" var="reportData">
              <tr>
                <td>${reportData.tabs.tab[0].detail.row.cell[i].text()}</td>
              </tr>
            </g:each>
                </tbody>
        </table>
      </div>

	</body>
</html>