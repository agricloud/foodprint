<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title><g:layoutTitle default="foodprint"/> - 生產履歷</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="shortcut icon" href="${resource(dir: 'images', file: 'favicon.ico')}" type="image/x-icon" />

<r:require modules="jquery,art"/>

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="/js/html5shiv.js"></script>
      <script src="/js/respond.min.js"></script>
    <![endif]-->

<g:layoutHead/>
<r:layoutResources />

%{-- <g:justfont /> --}%
%{-- <google:analytics /> --}%

</head>
<body>

<%--畫面可視區域：起點--%>

  <header>
    <div class="container">
      <div class="row">
        <div class="col-md-2 foodprint"><g:img dir="images" file='foodprint.png'/><small>v1.0</small></div>
        
        <div class="col-md-3 logo">食品履歷系統 <small>v1.0</small></div>
        
        <div class="col-md-6 top-slogan"><g:img dir="images" file="top-slogan.png" /></div>
      </div>
      
        <div class="col-md-3 foodprint-board">
          <g:img dir="images" file="foodprint-board.png" /> 
         </div>

      <div class="col-md-12 right-slogan text-right">．擁有產品履歷．安全．信賴．e化管理</div>
    </div>

  </header>


  <nav>


    <div class="container pc">

      <g:link url="/reports/${params?.name}/index" class="menu-01"><g:img dir="images" file="menu-01.png" /></g:link>
      <g:link url="/reports/${params?.name}/material" class="menu-02"><g:img dir="images" file="menu-02.png" /></g:link>
      <g:link url="/reports/${params?.name}/cultivate" class="menu-03"><g:img dir="images" file="menu-03.png" /></g:link>
      <g:link url="/reports/${params?.name}/quality" class="menu-04"><g:img dir="images" file="menu-04.png" /></g:link>
    </div>
    
    <div class="phone">
      <div class="container">
        <div class="wrap clearfix">
          <g:link url="/reports/${params?.name}/index" class="menu-01 current">
          <div class="icon-menu-01 menu"></div><span>產品說明</span></g:link>
          <g:link url="/reports/${params?.name}/material" class="menu-02">
          <div class="icon-menu-02 menu"></div><span>原料履歷</span></g:link>
          <g:link url="/reports/${params?.name}/cultivate" class="menu-03">
          <div class="icon-menu-03 menu"></div><span>栽種履歷</span></g:link>
          <g:link url="/reports/${params?.name}/quality" class="menu-04">
          <div class="icon-menu-04 menu"></div><span>檢驗履歷</span></g:link>
          </div>
      </div>
      
      <div class="dashline"></div>
    </div>
    
    
  </nav>
  <div class="dashline"></div>


  <%--主畫面內容--%>
  <div class="main container">

    <div class="col-md-8 content box-shadow">
      <g:layoutBody/>


    </div>

    <div class="col-md-8 content">
        <div id="alert_placeholder">
 
        </div>
    </div>
     <div class="bottomnav col-md-8">

        <div class="row">

            <g:if test="${batch?.id}">
              <div class="col-md-2 btn-download">
                <g:link action= 'showPdf' controller="attachment" params="[domainName:'batch', id:batch?.id]">
                  <g:img dir="images" file="btn-download.png" />
                </g:link>
              </div>
            </g:if>

            
            <div class="col-md-4 contact">
              <a href="tel:05-5354765" class="phone">(05) 5354765</a>
              <a href="mailto:acloud@yuntech.edu.tw" class="mail">acloud@yuntech.edu.tw</a>
            </div>
%{--             
            <div class="col-md-6 pagination-wrap">
              <div class="pagination grey-gradient">
                <a href="#" class="disabled">第一頁</a>
                <a href="#"><span class="icon-angle-left"></span></a>
                <span>...</span>
                <a class="active"href="#">1</a>
                <a href="#">2</a>
                <a href="#">3</a>
                <a href="#">4</a>
                <span>...</span>
                <a href="#"><span class="icon-angle-right"></span></a>
                <a href="#">末頁</a>
              </div>
            </div>
 --}%        
        </div>
          
          <div class="col-md-2 logo-agri"><a href="http://agricloud.cc/" target="_blank"><g:img dir="images" file="logo-agri.png" /></a></div>
          
      </div>

  </div>




  <div class="bottomele">
    <div class="trees-left"><g:img dir="images" file="left-trees.png" /></div>
    <div class="signature"><g:img dir="images" file="signature.png" /></div>
    <div class="box"><g:img dir="images" file="box.png" /></div>
    <div class="btn-fb">
      <a href="https://www.facebook.com/AgriCloud">
        <g:img dir="images" file="btn-fb.png" />
      </a>
    </div>

    <div class="right-tree"><g:img dir="images" file="right-tree.png" /></div>
  </div>
    
  <footer>
    <div class="container">
      <div class="text">Copyright 2013．All rights reserved</div>
    </div>
  </footer>




<%--畫面可視區域：終點--%>

<r:script>

    bootstrap_alert = function() {}
    bootstrap_alert.warning = function(message) {
      $('#alert_placeholder').html(
        '<div class="alert alert-success fade in">'+
        '<button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>'+
        message+
        '</div>'
      )
    }

    if('${flash.message}'!=='') bootstrap_alert.warning('${flash.message}');


</r:script>

<r:layoutResources />


</body>
</html>
