<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title><g:layoutTitle default="foodprint"/> - 生產履歷</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="shortcut icon" href="${resource(dir: 'images', file: 'favicon.ico')}" type="image/x-icon" />

<r:require modules="bootstrap"/>

<g:layoutHead/>
<r:layoutResources />

%{-- <g:justfont /> --}%
%{-- <google:analytics /> --}%

</head>
<body data-spy="scroll" data-target=".bs-docs-sidebar">

<%--畫面可視區域：起點--%>

<header>
</header>


<div role="main" class="main-content">

    <%--GoogleChromeFrame--%>
    <g:render template="/layouts/alert_chromeframe" />

    <%--主畫面內容--%>
    <div class="container">

      <g:layoutBody/>

    </div>

</div>


<footer id="footer">
</footer>




<%--畫面可視區域：終點--%>



<r:layoutResources />



</body>
</html>
