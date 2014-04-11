package foodprint
import grails.converters.JSON
import org.codehaus.groovy.grails.commons.DefaultGrailsDomainClass

class TraceTreeController {
    def foodpaintService

    def backwardTraceRoot(){
        def batch=Batch.findById(params.id)

        def rootJson = [:]

        rootJson.type = "批號"
        rootJson.class = "Batch"
        rootJson.name = batch.name
        rootJson.item = batch.item
        rootJson.qty = 0

        def sourceSheet=foodpaintService.querySaleSheetDetByBatch(batch.name)
        if(sourceSheet.data){
            rootJson.note = "銷貨"//實際上該批號可能還有部分存於庫存
            rootJson.sheet = "銷貨單: "
        }
        else{
            sourceSheet=foodpaintService.queryStockInSheetDetByBatch(batch.name)
            if(sourceSheet.data){
                rootJson.note = "自製"
                rootJson.sheet = "入庫單: "
            }
            else{
                sourceSheet=foodpaintService.queryOutSrcPurchaseSheetDetByBatch(batch.name)
                if(sourceSheet.data){
                    rootJson.note = "託外"
                    rootJson.sheet = "託外進貨單: "
                }
                else{
                    sourceSheet=foodpaintService.queryPurchaseSheetDetByBatch(batch.name)
                    if(sourceSheet.data){
                        rootJson.note = "採購"
                        rootJson.sheet = "進貨單: "
                    }
                    else{
                        //子節點
                       sourceSheet=foodpaintService.queryManufactureOrderByBatch(batch.name)
                        if(sourceSheet.data){
                            rootJson.note = "在製"
                            rootJson.sheet = "製令: "
                            rootJson.children = []
                        } 
                    }
                }
            }
        }

        sourceSheet.data.eachWithIndex{ sheet , i->
            if(rootJson.note != "在製")
                rootJson.sheet += sheet.typeName+"-"+sheet.name+"-"+sheet.sequence
            else
                rootJson.sheet += sheet.typeName+"-"+sheet.name

            if(i != sourceSheet.data.size()-1)
                rootJson.sheet += ","

            rootJson.qty = rootJson.qty.toLong()+sheet.qty.toLong()
        }
        
        render (contentType: 'application/json') {
            rootJson
        }
    }

    //逆溯批號來源，可能是由製令製造或由供應商進貨。
    //由於foodprint製令有記錄批號，因此可能會有在製狀態未入庫的製令，尚未處理。
    def backwardTraceByBatch(){
        def batch=Batch.findByName(params.name)

        def childJson = []

        def manufactureOrders=foodpaintService.queryManufactureOrderFromStockInSheetDetByBatch(batch.name)
        if(manufactureOrders.data){
            manufactureOrders.data.each(){ manufactureOrder ->
                def node = [:]
                node.note = "自製"
                node.sheet = "入庫單: "
                node.type = "製令"
                node.class = "ManufactureOrder"
                node.name = manufactureOrder.typeName+"-"+manufactureOrder.name
                node.item = batch.item
                node.qty = manufactureOrder.qty
                manufactureOrder.stockInSheetDets.eachWithIndex(){ stockInSheetDet, i ->
                    node.sheet += stockInSheetDet.typeName+"-"+stockInSheetDet.name+"-"+stockInSheetDet.sequence
                    if(i != manufactureOrder.stockInSheetDets.size()-1)
                        node.sheet += ","
                }
                childJson << node
            }
        }

        manufactureOrders=foodpaintService.queryManufactureOrderFromOutSrcPurchaseSheetDetByBatch(batch.name) 
        if(manufactureOrders.data){
            manufactureOrders.data.each(){ manufactureOrder ->
                def node = [:]
                node.note = "託外"
                node.sheet = "託外進貨單: "
                node.type = "製令"
                node.class = "ManufactureOrder"
                node.name = manufactureOrder.typeName+"-"+manufactureOrder.name
                node.item = batch.item
                node.qty = manufactureOrder.qty
                manufactureOrder.outSrcPurchaseSheetDets.eachWithIndex(){ outSrcPurchaseSheetDet, i ->
                    node.sheet += outSrcPurchaseSheetDet.typeName+"-"+outSrcPurchaseSheetDet.name+"-"+outSrcPurchaseSheetDet.sequence
                    if(i != manufactureOrder.outSrcPurchaseSheetDets.size()-1)
                        node.sheet += ","
                }
                childJson << node
            }
        }

        //葉節點：供應商
        def suppliers=foodpaintService.querySupplierFromPurchaseSheetDetByBatch(batch.name)
        if(suppliers.data){
            suppliers.data.each(){ supplier->
                def node = [:]
                node.leaf =true
                node.note = "廠商"
                node.sheet = "進貨單: "
                node.type = "供應商"
                node.class = "Supplier"
                node.name = supplier.name+"/"+supplier.title
                node.item = batch.item
                node.qty = 0
                def purchaseSheetDets=foodpaintService.queryPurchaseSheetDetBySupplierAndBatch(supplier.name,batch.name)
                purchaseSheetDets.data.eachWithIndex(){ purchaseSheetDet, i ->
                    node.sheet += purchaseSheetDet.typeName+"-"+purchaseSheetDet.name+"-"+purchaseSheetDet.sequence
                    if(i != purchaseSheetDets.data.size()-1)
                        node.sheet += ","
                    node.qty = node.qty.toLong()+purchaseSheetDet.qty.toLong()
                }
                childJson << node
            }
        }

        return childJson

    }

    //逆溯製令領用了哪些批號
    def backwardTraceByManufactureOrder(){

        String typeName = params.name.split("-")[0]
        String name = params.name.split("-")[1]
        def batchs = foodpaintService.queryBatchFromMaterialSheetDetByManufactureOrder(typeName, name).data

        def childJson = []
        batchs.each(){ batch ->
            def node = [:]
            node.note="領用"
            node.type = "批號"
            node.class = "Batch"
            node.name = batch.name
            node.item = batch.item
            node.qty = 0

            def sourceSheet=foodpaintService.queryStockInSheetDetByBatch(batch.name)
            if(sourceSheet.data){
                node.sheet = "入庫單: "
            }
            else{
                sourceSheet=foodpaintService.queryOutSrcPurchaseSheetDetByBatch(batch.name)
                if(sourceSheet.data){
                    node.sheet = "託外進貨單: "
                }
                else{
                    sourceSheet=foodpaintService.queryPurchaseSheetDetByBatch(batch.name)
                    if(sourceSheet.data){
                        node.sheet = "進貨單: "
                    }
                    else{
                        //查無此批號！
                    }
                }
            }

            sourceSheet.data.eachWithIndex{ sheet , i->
                node.sheet += sheet.typeName+"-"+sheet.name+"-"+sheet.sequence
                if(i != sourceSheet.data.size()-1)
                    node.sheet += ","
                node.qty = node.qty.toLong()+sheet.qty.toLong()
            }
            childJson << node
        }

        return childJson

    }
    def backwardTrace(){

        def childJson
        if(params.class == "Batch")
            childJson = backwardTraceByBatch()
        else if(params.class == "ManufactureOrder")
            childJson = backwardTraceByManufactureOrder()

        render (contentType: 'application/json') {
            childJson
        }
    }

    def forwardTraceRoot(){
        def batch=Batch.findById(params.id)

        def rootJson = [:]

        rootJson.type = "批號"
        rootJson.class = "Batch"
        rootJson.name = batch.name
        rootJson.item = batch.item
        rootJson.qty = 0

        def sourceSheet=foodpaintService.queryPurchaseSheetDetByBatch(batch.name)
        if(sourceSheet.data){
            rootJson.note = "採購"
            rootJson.sheet = "進貨單: "
        }
        else{
            sourceSheet=foodpaintService.queryStockInSheetDetByBatch(batch.name)
            if(sourceSheet.data){
                rootJson.note = "自製"
                rootJson.sheet = "入庫單: "
            }
            else{
                sourceSheet=foodpaintService.queryOutSrcPurchaseSheetDetByBatch(batch.name)
                if(sourceSheet.data){
                    rootJson.note = "託外"
                    rootJson.sheet = "託外進貨單: "
                }
                else{
                    //子節點
                   sourceSheet=foodpaintService.queryManufactureOrderByBatch(batch.name)
                    if(sourceSheet.data){
                        rootJson.note = "在製"
                        rootJson.sheet = "製令: "
                        rootJson.children = []
                    }
                }
            }
        }

        sourceSheet.data.eachWithIndex{ sheet , i->
            if(rootJson.note != "在製")
                rootJson.sheet += sheet.typeName+"-"+sheet.name+"-"+sheet.sequence
            else
                rootJson.sheet += sheet.typeName+"-"+sheet.name
            if(i != sourceSheet.data.size()-1)
                rootJson.sheet += ","
            rootJson.qty = rootJson.qty.toLong()+sheet.qty.toLong()
        }

        render (contentType: 'application/json') {
            rootJson
        }
    }

    //順溯批號流向，可能是被製令領用、銷貨給客戶或存於庫存中。
    def forwardTraceByBatch(){
        def batch=Batch.findByName(params.name)

        def childJson = []

        def manufactureOrders=foodpaintService.queryManufactureOrderFromMaterialSheetDetByBatch(batch.name)
        if(manufactureOrders.data){

            manufactureOrders.data.each{ manufactureOrder->
                def node = [:]
                node.note = "製造領用"//目前無法經製令判斷是自製或託外
                node.sheet = "領料單: "
                node.type = "製令"
                node.class = "ManufactureOrder"
                node.name = manufactureOrder.typeName+"-"+manufactureOrder.name
                node.item = manufactureOrder.item
                node.qty = manufactureOrder.qty

                manufactureOrder.materialSheetDets.eachWithIndex(){ materialSheetDet, i ->
                    node.sheet += materialSheetDet.typeName+"-"+materialSheetDet.name+"-"+materialSheetDet.sequence
                    if(i != manufactureOrder.materialSheetDets.size()-1)
                        node.sheet += ","
                }
                childJson << node
            }
        }
        //葉節點：客戶
        def customers=foodpaintService.queryCustomerFromSaleSheetDetByBatch(batch.name)
        if(customers.data){
            customers.data.each(){ customer->
                def node = [:]
                node.leaf =true
                node.note = "客戶"
                node.sheet = "銷貨單: "
                node.type = "客戶"
                node.class = "Customer"
                node.name = customer.name+"/"+customer.title
                node.item = batch.item
                node.qty = 0

                def saleSheetDets=foodpaintService.querySaleSheetDetByCustomerAndBatch(customer.name,batch.name)
                saleSheetDets.data.eachWithIndex(){ saleSheetDet, i ->
                    node.sheet += saleSheetDet.typeName+"-"+saleSheetDet.name+"-"+saleSheetDet.sequence
                    if(i != saleSheetDets.data.size()-1)
                        node.sheet += ","
                    node.qty = node.qty.toLong()+saleSheetDet.qty.toLong()
                }
                childJson << node
            }
        }
        //葉節點：庫存
        def inventoryDetails=foodpaintService.queryInventoryByBatchAndGroupByWarehouse(batch.name)
        if(inventoryDetails.data){
            inventoryDetails.data.each(){ inventoryDetail ->
                //0:warehouse.id,1:warehouse.name,2:warehouse.title,3:item.id,4:item.name,5:item.title,6:batch.id,7:batch.name,8:sum(qty)
                def node = [:]
                node.leaf = true
                node.note = "庫存"
                node.sheet = null
                node.type = "倉庫"
                node.class = "Warehouse"
                // node.name = inventoryDetail.warehouse.name+"/"+inventoryDetail.warehouse.title
                node.name = inventoryDetail[1]+"/"+inventoryDetail[2]
                node.item = batch.item
                // node.qty = inventoryDetail.qty
                node.qty = inventoryDetail[8]
                childJson << node
            }
        }

        return childJson

    }
    //順溯製令流向，可能入庫至批號或仍在製造中。
    def forwardTraceByManufactureOrder(){

        String typeName = params.name.split("-")[0]
        String name = params.name.split("-")[1]
        
        def childJson = []

        def batchs = foodpaintService.queryBatchFormStockInSheetDetByManufactureOrder(typeName, name).data

        batchs.each(){ batch ->
            def node = [:]
            node.note="入庫"
            node.type = "批號"
            node.class = "Batch"
            node.name = batch.name
            node.item = batch.item
            node.sheet = "入庫單: "
            node.qty = 0

            def sourceSheet=foodpaintService.queryStockInSheetDetByBatch(batch.name)

            sourceSheet.data.eachWithIndex{ sheet , i->
                node.sheet += sheet.typeName+"-"+sheet.name+"-"+sheet.sequence
                if(i != sourceSheet.data.size()-1)
                    node.sheet += ","
                node.qty = node.qty.toLong()+sheet.qty.toLong()
            }
            childJson << node
        }

        batchs = foodpaintService.queryBatchFormOutSrcPurchaseSheetDetByManufactureOrder(typeName, name).data
        batchs.each(){ batch ->
            def node = [:]
            node.note="託外"
            node.type = "批號"
            node.class = "Batch"
            node.name = batch.name
            node.item = batch.item
            node.sheet = "託外進貨單: "
            node.qty = 0

            def sourceSheet=foodpaintService.queryOutSrcPurchaseSheetDetByBatch(batch.name)

            sourceSheet.data.eachWithIndex{ sheet , i->
                node.sheet += sheet.typeName+"-"+sheet.name+"-"+sheet.sequence
                if(i != sourceSheet.data.size()-1)
                    node.sheet += ","
                node.qty = node.qty.toLong()+sheet.qty.toLong()
            }
            childJson << node
        }
        //葉節點：查詢該製令是否仍有在製品

        return childJson

    }

    def forwardTrace(){

        def childJson
        if(params.class == "Batch")
            childJson = forwardTraceByBatch()
        else if(params.class == "ManufactureOrder")
            childJson = forwardTraceByManufactureOrder()

        render (contentType: 'application/json') {
            childJson
        }

    }
}
