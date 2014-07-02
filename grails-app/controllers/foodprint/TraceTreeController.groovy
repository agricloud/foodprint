package foodprint
import grails.converters.JSON
import org.codehaus.groovy.grails.commons.DefaultGrailsDomainClass
//generate irepoet
import org.codehaus.groovy.grails.plugins.jasper.JasperExportFormat
import org.codehaus.groovy.grails.plugins.jasper.JasperReportDef
import org.apache.commons.io.FileUtils
//ireport sorting list
import net.sf.jasperreports.engine.JRSortField
import net.sf.jasperreports.engine.design.JRDesignSortField
import net.sf.jasperreports.engine.type.SortOrderEnum
import net.sf.jasperreports.engine.type.SortFieldTypeEnum

class TraceTreeController {
    def traceService
    def jasperService
    def dateService

    def backwardTraceRoot(){
        
        render (contentType: 'application/json') {
            traceService.backwardTraceRoot(params.id)
        }
    }

    
    def backwardTrace(){

        def childJson
        if(params.className == "Batch")
            childJson = traceService.backwardTraceByBatch(params.name)
        else if(params.className == "ManufactureOrder"){
            String typeName = params.name.split("-")[0]
            String name = params.name.split("-")[1]
            childJson = traceService.backwardTraceByManufactureOrder(typeName,name)
        }

        render (contentType: 'application/json') {
            childJson
        }
    }

    def forwardTraceRoot(){
        render (contentType: 'application/json') {
            traceService.forwardTraceRoot(params.id)
        }
    }

    def forwardTrace(){

        def childJson
        if(params.className == "Batch")
            childJson = traceService.forwardTraceByBatch(params.name)
        else if(params.className == "ManufactureOrder"){
            String typeName = params.name.split("-")[0]
            String name = params.name.split("-")[1]
            childJson = traceService.forwardTraceByManufactureOrder(typeName,name)
        }

        render (contentType: 'application/json') {
            childJson
        }

    }

    def backwardTracePrint(){
        def root = traceService.backwardTraceRoot(params.id)
        def nodes=[root]
        def leafs = []
        def reportTitle = message(code: 'backwardTrace.report.title.label')
        def detailReportTitle = message(code: 'backwardTrace.detailReport.title.label')

        //回溯出葉節點
        int index = 0
        while(index < nodes.size()){
            def node=nodes[index]
            if(node.leaf){
                // println "i'm leaf"+ node.note+"/"+node.type+"/"+node.name
                //不加入重複的葉節點
                def repeat = false
                for(leaf in leafs){
                    if(node.name==leaf.name && node.batch==leaf.batch && node.sheetDetail==leaf.sheetDetail){
                        repeat = true
                        break
                    }
                }
                if(!repeat)
                    leafs << node 
            }
            else if(!node.children || node.children!=[]){
                params.name = node.name
                if(node.className == "Batch"){
                    nodes += traceService.backwardTraceByBatch(params.name)
                }
                else if(node.className == "ManufactureOrder"){
                    String typeName = node.name.split("-")[0]
                    String name = node.name.split("-")[1]
                    nodes += traceService.backwardTraceByManufactureOrder(typeName,name)
                }
            }
            index++
        }

        def fileName = print(reportTitle, detailReportTitle, root, leafs)

        render (contentType: 'application/json') {
            [fileName:fileName]
        }

    }

    def forwardTracePrint(){
        def root = traceService.backwardTraceRoot(params.id)
        def nodes=[root]
        def leafs = []
        def reportTitle = message(code: 'forwardTrace.report.title.label')
        def detailReportTitle = message(code: 'forwardTrace.detailReport.title.label')

        //回溯出葉節點
        int index = 0
        while(index < nodes.size()){
            def node=nodes[index]
            if(node.leaf){
                // println "i'm leaf"+ node.note+"/"+node.type+"/"+node.name
                //不加入重複的葉節點
                def repeat = false
                for(leaf in leafs){
                    if(node.name==leaf.name && node.batch==leaf.batch && node.sheetDetail==leaf.sheetDetail){
                        repeat = true
                        break
                    }
                }
                if(!repeat)
                    leafs << node 
            }
            else if(!node.children || node.children!=[]){
                params.name = node.name
                if(node.className == "Batch"){
                    nodes += traceService.forwardTraceByBatch(params.name)
                }
                else if(node.className == "ManufactureOrder"){
                    String typeName = node.name.split("-")[0]
                    String name = node.name.split("-")[1]
                    nodes += traceService.forwardTraceByManufactureOrder(typeName,name)
                }
            }
            index++
        }

        def fileName = print(reportTitle, detailReportTitle, root, leafs)

        render (contentType: 'application/json') {
            [fileName:fileName]
        }

    }

    def print(reportTitle, detailReportTitle, root, leafs){
        
        def site
        if(params.site.id && params.site.id!="null")
            site = Site.get(params.site.id)
        
        //報表依指定欄位排序
        List<JRSortField> sortList = new ArrayList<JRSortField>();
        JRDesignSortField sortField = new JRDesignSortField();
        sortField.setName('dateCreated');
        sortField.setOrder(SortOrderEnum.DESCENDING);
        sortField.setType(SortFieldTypeEnum.FIELD);
        sortList.add(sortField);   

        //子報表1資料 = 根節點單據
        def detailData1=[]
        root.sheetDetail.each{  sheet ->
            sheet.dateCreated = dateService.parseISO8601ToUTC(sheet.dateCreated)
            detailData1<< sheet
        }
        //子報表1參數
        def detailParams1 = [:]
        detailParams1["SORT_FIELDS"]=sortList

        //子報表2資料 = 葉節點單據        
        def detailData2=[]
        leafs.each{ leaf ->

            if(leaf.sheetDetail){
                leaf.sheetDetail.each{ sheet ->
                    sheet.nodeType=leaf.type
                    sheet.nodeName=leaf.name
                    sheet.nodeTel=leaf.tel
                    sheet.nodeFax=leaf.fax
                    sheet.nodeAddress=leaf.address
                    sheet.nodeContact=leaf.contact
                    sheet.dateCreated = dateService.parseISO8601ToUTC(sheet.dateCreated)
                    detailData2<< sheet
                }
            }
            else{//無單據的葉節點 ex: 庫存
                def data=[:]
                data.nodeType=leaf.type
                data.nodeName=leaf.name
                data.nodeTel=leaf.tel
                data.nodeFax=leaf.fax
                data.nodeAddress=leaf.address
                data.nodeContact=leaf.contact
                data["item.name"]=leaf.item.name
                data["item.title"]=leaf.item.title
                data["item.spec"]=leaf.item.spec
                data["item.unit"]=leaf.item.unit
                data["batch.name"]=leaf.batch.name
                data.qty=leaf.qty
                data["warehouse.name"]=leaf["warehouse.name"]
                data["warehouse.title"]=leaf["warehouse.title"]
                detailData2<< data
            }
        }
        //子報表2參數
        def detailParams2 = [:]
        detailParams2["report.title"]=detailReportTitle
        detailParams2["SORT_FIELDS"]=sortList

        //設定額外傳入參數
        def parameters=[:]
        parameters["site.title"]=site?.title
        parameters["report.title"]=reportTitle
        parameters["REPORT_TIME_ZONE"]=dateService.getTimeZone()
        parameters["SUBREPORT_DIR"]=servletContext.getResource("/reports/")

        parameters["detailData1"]=detailData1
        parameters["detailData2"]=detailData2
        parameters["detailParams1"]=detailParams1
        parameters["detailParams2"]=detailParams2

        def reportDef = new JasperReportDef(name:'TraceReport.jasper',parameters:parameters,reportData:[root],fileFormat:JasperExportFormat.PDF_FORMAT)

        def fileName=dateService.getStrDate('yyyy-MM-dd HHmmss')+" "+reportTitle+".pdf"
        
        FileUtils.writeByteArrayToFile(new File("web-app/reportFiles/"+fileName), jasperService.generateReport(reportDef).toByteArray())

        return fileName
    }

}
