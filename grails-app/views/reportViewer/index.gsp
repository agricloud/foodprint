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
              <g:each in="${reportData.form.field}" var="field">
                <th> ${field.'@label'} </th>
              </g:each>
              %{--
              <th><g:message code="item.name.label" default="${reportData.form.field[0].'@label'}" /></th>
              <th><g:message code="item.title.label" default="${reportData.form.field[1].'@label'}" /></th>
              <th><g:message code="item.name.label" default="${reportData.form.field[2].'@label'}" /></th>
              <th><g:message code="item.unit.label" default="${reportData.form.field[3].'@label'}" /></th>
              <th><g:message code="item.description.label" default="${reportData.form.field[4].'@label'}" /></th>
              <th><g:message code="item.cost.label" default="${reportData.form.field[5].'@label'}" /></th>
              <th><g:message code="item.image.label" default="${reportData.form.field[6].'@label'}" /></th>
              <th><g:message code="item.weight.label" default="${reportData.form.field[7].'@label'}" /></th>
              <th><g:message code="item.workstation.label" default="${reportData.form.field[8].'@label'}" /></th>
              --}%
            </tr>
          </thead>
          <tbody>
	            <tr>
                <g:each in="${reportData.form.field}" var="field">
                  <th> ${field.text()} </th>
                </g:each>
                %{--
	              <td>${reportData.form.field[0].text()}</td>
                <td>${reportData.form.field[1].text()}</td>
                <td>${reportData.form.field[2].text()}</td>
                <td>${reportData.form.field[3].text()}</td>
                <td>${reportData.form.field[4].text()}</td>
                <td>${reportData.form.field[5].text()}</td>
                <td>${reportData.form.field[6].text()}</td>
                <td>${reportData.form.field[7].text()}</td>
                <td>${reportData.form.field[8].text()}</td>
                --}%
	            </tr>
          </tbody>
        </table>
    </div>

    <g:each in="${reportData.tabs.tab}" var="tab">

      <div class="panel panel-default">
           <!-- Default panel contents -->
          <div class="panel-heading">${tab.'@title'}</div>
            <!-- Table -->
            <table class="table">
              <thead>
                <tr>
                    <g:each in="${tab.table.head.column}" var="column">
                        <th> ${column.text()} </th>
                    </g:each>
                </tr>
              </thead>
              <tbody>
                 <g:each in="${tab.detail.row}" var="row">
                  <tr>
                      <g:each in="${row.cell}" var="cell">
                        <td>${cell.text()}</td>
                      </g:each>
                  </tr>
                </g:each> 
                    </tbody>
            </table>
      </div>
    </g:each>

	</body>
</html>