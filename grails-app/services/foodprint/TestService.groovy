package foodprint

class TestService {


    def createStdTestData = {
        //標準測試資料，作為驗證以及 unit test 用

        def item = new Item(name:"item1",title:"華珍玉米",spec:"華珍甜玉米，高糖分、皮薄",unit:"kg",description:"非基因轉殖品種").save(failOnError: true)

        def batch = new Batch(name:"batch1",item:item,dueDate:new Date(), 
                manufactureDate: new Date(), expirationDate: new Date(), remark: '備註').save(failOnError: true)

        def workstation = new Workstation(name:"workstation1",title:"檢驗站01").save(failOnError: true)
        def operation = new Operation(name:"operation1",title:"施肥").save(failOnError: true)

        def itemRoute = new ItemRoute(item:item,sequence:1,operation:operation,workstation:workstation)
        item.addToItemRoutes(itemRoute).save(failOnError: true)

        def batchRoute = new BatchRoute(batch:batch,workstation:workstation,sequence:1,operation:operation)
        batch.addToBatchRoutes(batchRoute).save(failOnError: true)
                        



    }
    def createExtraRouteData = {
        def item = Item.get(1)
        def batch = Batch.get(1)

        def workstation1 = Workstation.get(1)
        def operation1 = Operation.get(1)

        def workstation2 = new Workstation(name:"workstation2",title:"檢驗站02").save(failOnError: true)
        def operation2 = new Operation(name:"operation2",title:"翻土").save(failOnError: true)

        new ItemRoute(item:item,sequence:2,operation:operation1,workstation:workstation2).save(failOnError: true)
        new ItemRoute(item:item,sequence:3,operation:operation2,workstation:workstation1).save(failOnError: true)
        new ItemRoute(item:item,sequence:4,operation:operation2,workstation:workstation2).save(failOnError: true)

        new BatchRoute(batch:batch,workstation:workstation1,sequence:2,operation:operation2,startDate:new Date()).save(failOnError: true,flush: true)
        new BatchRoute(batch:batch,workstation:workstation2,sequence:3,operation:operation1).save(failOnError: true,flush: true)
        new BatchRoute(batch:batch,workstation:workstation2,sequence:4,operation:operation2).save(failOnError: true,flush: true)
    }
    def createOtherReport = {
        def item = Item.get(1)
        def batch = Batch.get(1)
        def workstation = Workstation.get(1)
        def operation = Operation.get(1)
        def param = new Param(name:"paramOther",title:"PackageCapacity",defaultValue:"100",paramType:ParamType.INTEGER).save(failOnError: true)
        def report = new Report(name:"reportOther",title:"檢驗紀錄集").save(failOnError: true)
        def reportparam = new ReportParams (report:report,param:param,workstation:workstation,operation:operation,item:item).save(failOnError: true)
        new BatchReportDet (batch:batch,reportParams:reportparam).save(failOnError: true, flush: true)

    }
    def createNutritionReport = {
        def item = Item.get(1)
        def batch = Batch.get(1)
        def workstation = Workstation.get(1)
        def operation = Operation.get(2)
        def param= new Param(name:"paramNutrition",title:"Pack", unit:"玻璃瓶[60 毫升/62g]",paramType:ParamType.INTEGER).save(failOnError: true)
        def report=new Report(name:"reportNutrition",title:"營養標示履歷", reportType: ReportType.NUTRITION).save(failOnError: true)
        def reportparam=new  ReportParams (report:report,param:param,workstation:workstation,operation:operation,item:item).save(failOnError: true)
    }
    def createInspectReport = {
        def item = Item.get(1)
        def batch = Batch.get(1)
        def workstation = Workstation.get(2)
        def operation = Operation.get(1)
        def param1 = new Param(name:"A",title:"益多松",defaultValue:"0.0",paramType:ParamType.INTEGER).save(failOnError: true)
        def param2 = new Param(name:"B",title:"芬殺蟎",defaultValue:false,paramType:ParamType.BOOLEAN).save(failOnError: true)
        def param3 = new Param(name:"C",title:"芬普蟎",defaultValue:"0.5",paramType:ParamType.STRING).save(failOnError: true)
        def report=new Report(name:"report3",title:"成品檢驗報告集", reportType: ReportType.INSPECT).save(failOnError: true)
        def reportparam1=new  ReportParams (report:report,param:param1,workstation:workstation,operation:operation,item:item).save(failOnError: true)
        def reportparam2=new  ReportParams (report:report,param:param2,workstation:workstation,operation:operation,item:item).save(failOnError: true)
        def reportparam3=new  ReportParams (report:report,param:param3,workstation:workstation,operation:operation,item:item).save(failOnError: true)
    }
    def createBatchSource = {
        def mainBatch = Batch.get(1)
        def item = new Item(name:"item2",title:"橘子").save(failOnError: true)
        def batch = new Batch(name:"batch2",item:item).save(failOnError: true)
        new BatchSource(batch:mainBatch,childBatch:batch).save(failOnError: true)

    }
    def createOtherDomain = {
        def user = new User(username: 'user', password: 'user', enabled: true).save(failOnError: true)
        def customer = new Customer(name:"cutstomer",title:"A先生").save(failOnError: true)
        def supplier = new Supplier(name:"supplier",title:"A公司",email:"A@xx.com",address:"台北市忠孝東路222號").save(failOnError: true)

    }

    def createTestMessage = { messageSource ->
        messageSource.addMessage("default.message.save.success", Locale.getDefault(), "儲存成功")
        messageSource.addMessage("default.message.delete.success", Locale.getDefault(), "刪除成功")
        messageSource.addMessage("default.message.update.failed", Locale.getDefault(), "更新失敗")


        messageSource.addMessage("default.message.notfound", Locale.getDefault(), "查無資料") 
        messageSource.addMessage("country.TAIWAN.label", Locale.getDefault(), "台灣")
		messageSource.addMessage("country.JAPAN.label", Locale.getDefault(), "日本")
		messageSource.addMessage("country.CHINA.label", Locale.getDefault(), "中國")
		messageSource.addMessage("country.HONGKONG.label", Locale.getDefault(), "香港")
        messageSource.addMessage("country.KOREA.label", Locale.getDefault(), "韓國") 
        messageSource.addMessage("country.PHILIPPINES.label", Locale.getDefault(), "菲律賓") 
        messageSource.addMessage("country.AMERICA.label", Locale.getDefault(), "美國") 
        messageSource.addMessage("country.AUSTRALIA.label", Locale.getDefault(), "澳洲") 
        messageSource.addMessage("country.FRENCE.label", Locale.getDefault(), "法國") 
        messageSource.addMessage("country.BRITAIN.label", Locale.getDefault(), "英國") 
	
    }


}
