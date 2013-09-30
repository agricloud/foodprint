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
      <!-- form -->
      <form class="form-horizontal" role="form">

        <g:each in="${product}" var="entry" >

          <div class="form-group">
            <label class="col-lg-2 control-label"><g:message code="${entry.key}.label" /></label>

            <div class="col-lg-10">
              <p class="form-control-static">${entry.value}</p>
            </div>
          </div>

        </g:each>



       
      </form> 
    </div>



    <g:each in="${reports}" var="report">

      <div class="panel panel-default">
           <!-- Default panel contents -->
          <div class="panel-heading">${report.title}</div>
            <!-- Table -->
            <table class="table">
              <thead>

                <tr>
                  <g:each in="${report.params[0]}" var="entry" >
                      <th><g:message code="${entry.key}.label" /></th>
                  </g:each>

                </tr>
              </thead>
              <tbody>
                 <g:each in="${report.params}" var="param">
                  <tr>
                      <g:each in="${param}" var="entry" >
                        <g:if test="${entry.key == 'default.image'}" >
                          <td><g:img class="img-responsive" uri="${entry.value}" /></td>
                        </g:if>
                        <g:else>
                          <td>${entry.value}</td>
                        </g:else>

                      </g:each>
                  </tr>
                </g:each> 
              </tbody>
            </table>
      </div>
    </g:each>



	</body>
</html>