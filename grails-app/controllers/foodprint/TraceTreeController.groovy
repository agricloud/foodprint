package foodprint
import grails.converters.JSON
import org.codehaus.groovy.grails.commons.DefaultGrailsDomainClass

class TraceTreeController {
    def batchAnalyzeService
    def foodpaintService

    def getBatchRoot(){
        def batch=Batch.findById(params.id)

        def batchJson =  JSON.parse((batch as JSON).toString())

        if(!batchJson.supplier){
            batchJson.supplier = [:]
            batchJson.supplier.id=""
        }
  
        //加入批號單據
        batchJson = addBatchSheet(batchJson)

        render (contentType: 'application/json') {
            batchJson
        }

    }

    def backwardTrace(){
        def batch = Batch.findById(params.node)
        def jsonTreeArray = []
        batchAnalyzeService.backwardTrace(batch).batchChild.each{ b ->

            def tempJson = b as JSON
            def jsonTree = JSON.parse(tempJson.toString())
                    

            if(batchAnalyzeService.isBackwardEndBatch(b).isEndBatch){
                jsonTree.leaf = true
                jsonTree.iconCls = 'task'
            }

            //加入批號單據
            jsonTree = addBatchSheet(jsonTree)
            
            jsonTree.batchSources = null
            jsonTreeArray << jsonTree

            //直接放入batch 樹便可展開 但無法加入children判斷是否為葉節點
            // jsonTreeArray << b
        }
        render (contentType: 'application/json') {
            jsonTreeArray
        }
    }

    def forwardTrace(){
        log.debug "${controllerName}--forwardTrace"

        def batch = Batch.findById(params.node)

        def jsonTreeArray = []

        batchAnalyzeService.forwardTrace(batch).batchHead.each{ b ->

            def tempJson = b as JSON
            def jsonTree = JSON.parse(tempJson.toString())
            
            
            if(batchAnalyzeService.isForwardEndBatch(b).isEndBatch){
                jsonTree.children=[]
            }

            //加入批號單據
            jsonTree = addBatchSheet(jsonTree)

            jsonTreeArray << jsonTree

            //直接放入batch 樹便可展開 但無法加入children判斷是否為葉節點
            // jsonTreeArray << b
        }

        render (contentType: 'application/json') {
            jsonTreeArray
        }

    }

    def addBatchSheet(batchJson){
        def sheet=foodpaintService.querySheetByBatch(batchJson.name)

        if(sheet){
            batchJson.sheet=[:]
            batchJson.sheet.typeName = sheet.sheet.typeName
            batchJson.sheet.name = sheet.sheet.name
        }

        batchJson

    }
}
