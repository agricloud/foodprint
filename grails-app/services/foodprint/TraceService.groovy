package foodprint

class TraceService {

	def foodpaintService

	def backwardTraceRoot(batchId){
        def batch=Batch.findById(batchId)

        def rootJson = [:]
        def sourceSheetType
        def sourceSheet
        def returnSheetType
        def returnSheet

        rootJson.type = "批號"
        rootJson.class = "Batch"
        rootJson.name = batch.name
        rootJson.item = batch.item
        rootJson.qty = 0

        sourceSheet=foodpaintService.querySaleSheetDetByBatch(batch.name)
        if(sourceSheet.data){
            rootJson.note = "銷貨"//實際上該批號可能還有部分存於庫存
            sourceSheetType = "銷貨單: "
            returnSheetType = "銷退單: "
            returnSheet=foodpaintService.querySaleReturnSheetDetByBatch(batch.name)
        }
        else{
            sourceSheet=foodpaintService.queryStockInSheetDetByBatch(batch.name)
            if(sourceSheet.data){
                rootJson.note = "自製"
                sourceSheetType = "入庫單: "
            }
            else{
                sourceSheet=foodpaintService.queryOutSrcPurchaseSheetDetByBatch(batch.name)
                if(sourceSheet.data){
                    rootJson.note = "託外"
                    sourceSheetType = "託外進貨單: "
                    returnSheetType = "託外退貨單: "
                    returnSheet=foodpaintService.queryOutSrcPurchaseReturnSheetDetByBatch(batch.name)
                }
                else{
                    sourceSheet=foodpaintService.queryPurchaseSheetDetByBatch(batch.name)
                    if(sourceSheet.data){
                        rootJson.note = "採購"
                        sourceSheetType = "進貨單: "
                        returnSheetType = "退貨單: "
                        returnSheet=foodpaintService.queryPurchaseReturnSheetDetByBatch(batch.name)
                    }
                    else{
                        //子節點
                       sourceSheet=foodpaintService.queryManufactureOrderByBatch(batch.name)
                        if(sourceSheet.data){
                            rootJson.note = "在製"
                            sourceSheetType = "製令: "
                            rootJson.children = []
                        } 
                    }
                }
            }
        }
        rootJson = processNodeSheet(rootJson, sourceSheetType, sourceSheet, returnSheetType, returnSheet)
        
        return rootJson
    }

    //逆溯批號來源，可能是由製令製造或由供應商進貨。
    //由於foodprint製令有記錄批號，因此可能會有在製狀態未入庫的製令，尚未處理。
    def backwardTraceByBatch(batchName){
        def batch=Batch.findByName(batchName)

        def childJson = []
        def sourceSheetType
        def returnSheetType

        def manufactureOrders=foodpaintService.queryManufactureOrderFromStockInSheetDetByBatch(batch.name)
        if(manufactureOrders.data){
            manufactureOrders.data.each(){ manufactureOrder ->
                def node = [:]
                node.note = "自製"
                node.type = "製令"
                node.class = "ManufactureOrder"
                node.name = manufactureOrder.typeName+"-"+manufactureOrder.name
                node.item = batch.item
                node.qty = 0
                sourceSheetType = "入庫單: "

                def stockInSheetDets=foodpaintService.queryStockInSheetDetByBatchAndManufactureOrder(batch.name,manufactureOrder.typeName,manufactureOrder.name)

                node = processNodeSheet(node, sourceSheetType, stockInSheetDets, null, null)

                childJson << node
            }
        }

        manufactureOrders=foodpaintService.queryManufactureOrderFromOutSrcPurchaseSheetDetByBatch(batch.name) 
        if(manufactureOrders.data){
            manufactureOrders.data.each(){ manufactureOrder ->
                def node = [:]
                node.note = "託外"   
                node.type = "製令"
                node.class = "ManufactureOrder"
                node.name = manufactureOrder.typeName+"-"+manufactureOrder.name
                node.item = batch.item
                node.qty = 0
                sourceSheetType = "託外進貨單: "
                returnSheetType = "託外退貨單: "

                
                def outSrcPurchaseSheetDets=foodpaintService.queryOutSrcPurchaseSheetDetByBatchAndManufactureOrder(batch.name,manufactureOrder.typeName,manufactureOrder.name)
                def outSrcPurchaseReturnSheetDets=foodpaintService.queryOutSrcPurchaseReturnSheetDetByBatchAndManufactureOrder(batch.name,manufactureOrder.typeName,manufactureOrder.name)

                node = processNodeSheet(node, sourceSheetType, outSrcPurchaseSheetDets, returnSheetType, outSrcPurchaseReturnSheetDets)

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
                node.type = "供應商"
                node.class = "Supplier"
                node.name = supplier.name+"/"+supplier.title
                node.tel = supplier.tel
                node.fax = supplier.fax
                node.contact = supplier.contact
                node.address = supplier.address
                node.item = batch.item
                node.qty = 0
                sourceSheetType = "進貨單: "
                returnSheetType = "退貨單: "

                def purchaseSheetDets=foodpaintService.queryPurchaseSheetDetBySupplierAndBatch(supplier.name,batch.name)
                def purchaseReturnSheetDets=foodpaintService.queryPurchaseReturnSheetDetBySupplierAndBatch(supplier.name,batch.name)

                node = processNodeSheet(node, sourceSheetType, purchaseSheetDets, returnSheetType, purchaseReturnSheetDets)

                childJson << node
            }
        }

        return childJson

    }

    //逆溯製令領用了哪些批號
    def backwardTraceByManufactureOrder(typeName, name){
        def batchs = foodpaintService.queryBatchFromMaterialSheetDetByManufactureOrder(typeName, name).data

        def childJson = []
        def sourceSheetType
        def sourceSheet
        def returnSheetType
        def returnSheet

        batchs.each(){ batch ->
            def node = [:]
            node.note="領用"
            node.type = "批號"
            node.class = "Batch"
            node.name = batch.name
            node.item = batch.item
            node.qty = 0

            sourceSheet=foodpaintService.queryStockInSheetDetByBatch(batch.name)
            if(sourceSheet.data){
                node.sheet = "入庫單: "
            }
            else{
                sourceSheet=foodpaintService.queryOutSrcPurchaseSheetDetByBatch(batch.name)
                if(sourceSheet.data){
                    sourceSheetType = "託外進貨單: "
                    returnSheetType = "託外退貨單: "
                    returnSheet=foodpaintService.queryOutSrcPurchaseReturnSheetDetByBatch(batch.name)
                }
                else{
                    sourceSheet=foodpaintService.queryPurchaseSheetDetByBatch(batch.name)
                    if(sourceSheet.data){
                        sourceSheetType = "進貨單: "
                        returnSheetType = "退貨單: "
                        returnSheet=foodpaintService.queryPurchaseReturnSheetDetByBatch(batch.name)
                    }
                    else{
                        log.error "逆溯查無此批號${batch.name}！"
                    }
                }
            }

            node = processNodeSheet(node, sourceSheetType, sourceSheet, returnSheetType, returnSheet)
            childJson << node
        }

        return childJson

    }

    def forwardTraceRoot(batchId){
        def batch=Batch.findById(batchId)

        def rootJson = [:]
        def sourceSheetType
        def sourceSheet
        def returnSheetType
        def returnSheet

        rootJson.type = "批號"
        rootJson.class = "Batch"
        rootJson.name = batch.name
        rootJson.item = batch.item
        rootJson.qty = 0

        sourceSheet=foodpaintService.queryPurchaseSheetDetByBatch(batch.name)
        if(sourceSheet.data){
            rootJson.note = "採購"
            sourceSheetType = "進貨單: "
            returnSheetType = "退貨單: "
            returnSheet=foodpaintService.queryPurchaseReturnSheetDetByBatch(batch.name)
        }
        else{
            sourceSheet=foodpaintService.queryStockInSheetDetByBatch(batch.name)
            if(sourceSheet.data){
                rootJson.note = "自製"
                sourceSheetType = "入庫單: "
            }
            else{
                sourceSheet=foodpaintService.queryOutSrcPurchaseSheetDetByBatch(batch.name)
                if(sourceSheet.data){
                    rootJson.note = "託外"
                    sourceSheetType = "託外進貨單: "
                    returnSheetType = "託外退貨單: "
                    returnSheet=foodpaintService.queryOutSrcPurchaseReturnSheetDetByBatch(batch.name)
                }
                else{
                    //子節點
                   sourceSheet=foodpaintService.queryManufactureOrderByBatch(batch.name)
                    if(sourceSheet.data){
                        rootJson.note = "在製"
                        sourceSheetType = "製令: "
                        rootJson.children = []
                    }
                }
            }
        }
        rootJson = processNodeSheet(rootJson, sourceSheetType, sourceSheet, returnSheetType, returnSheet)
        return rootJson
    }

    //順溯批號流向，可能是被製令領用、銷貨給客戶或存於庫存中。
    def forwardTraceByBatch(batchName){
        def batch=Batch.findByName(batchName)

        def childJson = []
        def sourceSheetType
        def returnSheetType

        def manufactureOrders=foodpaintService.queryManufactureOrderFromMaterialSheetDetByBatch(batch.name)
        if(manufactureOrders.data){

            manufactureOrders.data.each{ manufactureOrder->
                def node = [:]
                if(manufactureOrder.workstation)
                    node.note = "自製領用"
                if(manufactureOrder.supplier)
                    node.note = "託外領用"
                node.type = "製令"
                node.class = "ManufactureOrder"
                node.name = manufactureOrder.typeName+"-"+manufactureOrder.name
                node.item = batch.item
                node.qty = 0
                sourceSheetType = "領料單: "
                returnSheetType = "退料單: "

                def materialSheetDets=foodpaintService.queryMaterialSheetDetByBatchAndManufactureOrder(batch.name,manufactureOrder.typeName,manufactureOrder.name)
                def materialReturnSheetDets=foodpaintService.queryMaterialReturnSheetDetByBatchAndManufactureOrder(batch.name,manufactureOrder.typeName,manufactureOrder.name)
                
                node = processNodeSheet(node, sourceSheetType, materialSheetDets, returnSheetType, materialReturnSheetDets)

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
                node.tel = customer.tel
                node.fax = customer.fax
                node.contact = customer.contact
                node.address = customer.address
                node.item = batch.item
                node.qty = 0
                sourceSheetType = "銷貨單: "
                returnSheetType = "銷退單: "

                def saleSheetDets=foodpaintService.querySaleSheetDetByCustomerAndBatch(customer.name,batch.name)
                def saleReturnSheetDets=foodpaintService.querySaleReturnSheetDetByCustomerAndBatch(customer.name,batch.name)
                
                node = processNodeSheet(node, sourceSheetType, saleSheetDets, returnSheetType, saleReturnSheetDets)

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
                node.batch = batch
                node["warehouse.name"]=inventoryDetail[1]
                node["warehouse.title"]=inventoryDetail[2]
                // node.qty = inventoryDetail.qty
                node.qty = inventoryDetail[8]
                childJson << node
            }
        }

        return childJson

    }
    //順溯製令流向，可能入庫至批號或仍在製造中。
    def forwardTraceByManufactureOrder(typeName,name){

        def childJson = []
        def sourceSheetType
        def sourceSheet
        def returnSheetType
        def returnSheet

        def batchs = foodpaintService.queryBatchFormStockInSheetDetByManufactureOrder(typeName, name)
        batchs.data.each(){ batch ->
            def node = [:]
            node.note="入庫"
            node.type = "批號"
            node.class = "Batch"
            node.name = batch.name
            node.item = batch.item
            node.qty = 0
            sourceSheetType = "入庫單: "

            sourceSheet=foodpaintService.queryStockInSheetDetByBatchAndManufactureOrder(batch.name,typeName,name)

            childJson << node
        }

        batchs = foodpaintService.queryBatchFormOutSrcPurchaseSheetDetByManufactureOrder(typeName, name)
        batchs.data.each(){ batch ->
            def node = [:]
            node.note="託外"
            node.type = "批號"
            node.class = "Batch"
            node.name = batch.name
            node.item = batch.item
            node.qty = 0
            sourceSheetType = "託外進貨單: "
            returnSheetType = "託外退貨單: "
            
            sourceSheet=foodpaintService.queryOutSrcPurchaseSheetDetByBatchAndManufactureOrder(batch.name,typeName,name)
            returnSheet=foodpaintService.queryOutSrcPurchaseReturnSheetDetByBatchAndManufactureOrder(batch.name,typeName,name)

            node = processNodeSheet(node, sourceSheetType, sourceSheet, returnSheetType, returnSheet)

            childJson << node
        }
        //葉節點：查詢該製令是否仍有在製品

        return childJson

    }

    def processNodeSheet(node, String sourceSheetType, sourceSheet, String returnSheetType, returnSheet){
        node.sheet = sourceSheetType
        sourceSheet.data.eachWithIndex{ sheet , i->
            if(node.note == "在製")
                node.sheet += sheet.typeName+"-"+sheet.name
            else
                node.sheet += sheet.typeName+"-"+sheet.name+"-"+sheet.sequence
                
            if(i != sourceSheet.data.size()-1)
                node.sheet += ","

            node.qty = node.qty.toLong()+sheet.qty.toLong()
        }
        node.sheetDetail=sourceSheet.data

        if(returnSheet?.data){
            node.sheet += " "+returnSheetType
            returnSheet.data.eachWithIndex{ sheet , i->
                node.sheet += sheet.typeName+"-"+sheet.name+"-"+sheet.sequence

                if(i != returnSheet.data.size()-1)
                    node.sheet += ","

                node.qty = node.qty.toLong()-sheet.qty.toLong()
            }
            node.sheetDetail+=returnSheet.data
        }

        node
    }
}