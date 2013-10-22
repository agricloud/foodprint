<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="bootstrap">
		<title><g:message code="reportViewer.title.label" default="履歷檢視" /></title>

	</head>
	<body>


        <h2 class="heading"><span aria-hidden="true" class="icon-icon-01"></span> ${product.title}</h2>
          <div class="right-icon text-right">Products</div>
          
          
%{--           <div class="row search-row">
            <div class="col-md-12 search">

                <div class="pull-left text">農產品批號:</div>
                <div class="col-md-10">
                  <div class="input-group">
                    <input type="text" class="form-control">
                    <div class="input-group-btn">
                      <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown"><span class="caret"></span></button>
                      <ul class="dropdown-menu pull-right">
                        <li><a href="#">2015-A</a></li>
                        <li><a href="#">2015-B</a></li>
                        <li><a href="#">2015-C</a></li>
                        <li><a href="#">FTINAA-20121217</a></li>
                      </ul>
                    </div><!-- /btn-group -->
                  </div><!-- /input-group -->
                </div><!-- /.col-md-12 -->
                
              </div>
              <div class="dashline"></div>  
          </div> --}%
        
          <!--banner-->
          <div id="myCarousel"  class="slider">
              <div class="flexslider">
                <ul class="slides">
                  <li>
                    <g:img uri='/attachment/show/${batch.item.id}?domainName=item' />
                  </li>
                </ul>
              </div>        
          </div>
          <!--banner-end-->

          
          <div class="row product-info clearfix">
            <div class="dashline"></div>  
              
            <div class="col-md-4 col-sm-4"><g:img uri='/attachment/show/${batch.item.id}?domainName=item' class="thumbnail" /></div>
              <div class="col-md-8 col-sm-8">
                <table>

                  <g:each in="${product.head}" var="entry" >
                   <tr>
                      <td width="100"><span class="icon-table"><g:img dir="images" file="icon-table.png" /></span> <span class="font-brown"><g:message code="${entry.key}.label" /></span></td>
                        <td>${entry.value}</td>
                    </tr>
                  </g:each>

                </table>
                
              </div>
          </div>
          
          <div id="split-tables">      
    <table class="table-style" width="100%" border="0" cellspacing="0" cellpadding="0">
          <thead>
            <tr>
              <g:each in="${product.body}" var="entry" >
                <th><g:message code="${entry.key}.label" /><span class="icon-table"><g:img dir="images" file="icon-table.png" /></span></th>
              </g:each>
            </tr>
          </thead>
          <tbody>
            <tr>
              <g:each in="${product.body}" var="entry" >
                <td data-title='<g:message code="${entry.key}.label" />'>${entry.value}</td>
              </g:each>
            </tr>
          </tbody>
        </table>
        </div>


      



	</body>
</html>