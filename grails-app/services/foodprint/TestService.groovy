package foodprint

class TestService {


    def createStdTestData = {
        //標準測試資料，作為驗證以及 unit test 用

        def site1 = new Site(name:"site1",title:"雲林斗六許家農場",description:"專門栽種符合TGAP柑桔類之果園",address:"雲林縣斗六市重光里").save(failOnError: true)
        def user2 = new User(username: 'huang', password: 'huang', enabled: true,site:site1).save(failOnError: true)
        def workstation1 = new Workstation(name:"workstation1",title:"柳丁栽種區-1985").save(failOnError: true)
        
        def operation1 = new Operation(name:"operation1",title:"剪枝").save(failOnError: true)
        def operation2 = new Operation(name:"operation2",title:"病蟲害防治").save(failOnError: true)
        def operation3 = new Operation(name:"operation3",title:"施肥").save(failOnError: true)
        def operation4 = new Operation(name:"operation4",title:"除草").save(failOnError: true)
        def operation5 = new Operation(name:"operation5",title:"施益肥").save(failOnError: true)
        def operation6 = new Operation(name:"operation6",title:"灌水").save(failOnError: true)

        def supplier1 = new Supplier(name:"supplier1",title:"農家樂",address:"雲林縣斗六市成功路").save(failOnError: true)
        def supplier2 = new Supplier(name:"supplier2",title:"金農友企業股份有限公司",address:"雲林縣斗六市成功路").save(failOnError: true)
        
        def item1 = new Item(name:"item1",title:"履歷柳丁").save(failOnError: true)
        def batch1 = new Batch(name:"batch1",item:item1).save(failOnError: true)
        def item2 = new Item(name:"item2",title:"夏油").save(failOnError: true)
        def batch2 = new Batch(name:"batch2",item:item2).save(failOnError: true)
        def item3 = new Item(name:"item3",title:"大滅松").save(failOnError: true)
        def batch3 = new Batch(name:"batch3",item:item3).save(failOnError: true)
        def item4 = new Item(name:"item4",title:"青苔剋").save(failOnError: true)
        def batch4 = new Batch(name:"batch4",item:item4).save(failOnError: true)
        def item5 = new Item(name:"item5",title:"台肥39號").save(failOnError: true)
        def batch5 = new Batch(name:"batch5",item:item5).save(failOnError: true)
        def item6 = new Item(name:"item6",title:"重光六號").save(failOnError: true)
        def batch6 = new Batch(name:"batch6",item:item6).save(failOnError: true)
        def item7 = new Item(name:"item7",title:"耀祥豪牌").save(failOnError: true)
        def batch7 = new Batch(name:"batch7",item:item7).save(failOnError: true)
        def item8 = new Item(name:"item8",title:"陶斯寧").save(failOnError: true)
        def batch8 = new Batch(name:"batch8",item:item8).save(failOnError: true)
        def item9 = new Item(name:"item9",title:"三氟敏").save(failOnError: true)
        def batch9 = new Batch(name:"batch9",item:item9).save(failOnError: true)
        def item10 = new Item(name:"item10",title:"勃激素A3").save(failOnError: true)
        def batch10 = new Batch(name:"batch10",item:item10).save(failOnError: true)
        def item11 = new Item(name:"item11",title:"亞磷酸鉀").save(failOnError: true)
        def batch11 = new Batch(name:"batch11",item:item11).save(failOnError: true)
        def item12 = new Item(name:"item12",title:"丁基加保扶").save(failOnError: true)
        def batch12 = new Batch(name:"batch12",item:item12).save(failOnError: true)
        def item13 = new Item(name:"item13",title:"稱無限").save(failOnError: true)
        def batch13 = new Batch(name:"batch13",item:item13).save(failOnError: true)
        def item14 = new Item(name:"item14",title:"愛殺松").save(failOnError: true)
        def batch14 = new Batch(name:"batch14",item:item14).save(failOnError: true)
        def item15 = new Item(name:"item15",title:"鋅錳乃浦").save(failOnError: true)
        def batch15 = new Batch(name:"batch15",item:item15).save(failOnError: true)

        
        // def workstation1 = new Workstation(name:"workstation1",title:"檢驗站01").save(failOnError: true)
        // def operation = new Operation(name:"operation1",title:"施肥").save(failOnError: true)

        // def itemRoute = new ItemRoute(item:item,sequence:1,operation:operation,workstation:workstation)
        // item.addToItemRoutes(itemRoute).save(failOnError: true)

        // def batchRoute = new BatchRoute(batch:batch,workstation:workstation,sequence:1,operation:operation)
        // batch.addToBatchRoutes(batchRoute).save(failOnError: true)
    }
    // def createExtraRouteData = {
    //     def item = Item.get(1)
    //     def batch = Batch.get(1)

    //     def workstation1 = Workstation.get(1)
    //     def operation1 = Operation.get(1)

    //     def workstation2 = new Workstation(name:"workstation2",title:"檢驗站02").save(failOnError: true)
    //     def operation2 = new Operation(name:"operation2",title:"翻土").save(failOnError: true)

    //     new ItemRoute(item:item,sequence:2,operation:operation1,workstation:workstation2).save(failOnError: true)
    //     new ItemRoute(item:item,sequence:3,operation:operation2,workstation:workstation1).save(failOnError: true)
    //     new ItemRoute(item:item,sequence:4,operation:operation2,workstation:workstation2).save(failOnError: true)

    //     new BatchRoute(batch:batch,workstation:workstation1,sequence:2,operation:operation2,startDate:new Date()).save(failOnError: true,flush: true)
    //     new BatchRoute(batch:batch,workstation:workstation2,sequence:3,operation:operation1).save(failOnError: true,flush: true)
    //     new BatchRoute(batch:batch,workstation:workstation2,sequence:4,operation:operation2).save(failOnError: true,flush: true)
    // }
    // def createOtherReport = {
    //     def item = Item.get(1)
    //     def batch = Batch.get(1)
    //     def workstation = Workstation.get(1)
    //     def operation = Operation.get(1)
    //     def param = new Param(name:"paramOther",title:"PackageCapacity",defaultValue:"100",paramType:ParamType.INTEGER).save(failOnError: true)
    //     def report = new Report(name:"reportOther",title:"檢驗紀錄集").save(failOnError: true)
    //     def reportparam = new ReportParams (report:report,param:param,workstation:workstation,operation:operation,item:item).save(failOnError: true)
    //     new BatchReportDet (batch:batch,reportParams:reportparam).save(failOnError: true, flush: true)

    // }
    // def createNutritionReport = {
    //     def item = Item.get(1)
    //     def batch = Batch.get(1)
    //     def workstation = Workstation.get(1)
    //     def operation = Operation.get(2)
    //     def param= new Param(name:"paramNutrition",title:"Pack", unit:"毫升/g",paramType:ParamType.INTEGER).save(failOnError: true)
    //     def report=new Report(name:"reportNutrition",title:"營養標示履歷", reportType: ReportType.NUTRITION).save(failOnError: true)
    //     def reportparam=new  ReportParams (report:report,param:param,workstation:workstation,operation:operation,item:item).save(failOnError: true)
    //     new BatchReportDet (batch:batch,reportParams:reportparam,value:123).save(failOnError: true, flush: true)
    // }
    // def createInspectReport = {
    //     def item = Item.get(1)
    //     def batch = Batch.get(1)
    //     def workstation = Workstation.get(2)
    //     def operation = Operation.get(1)
    //     def param1 = new Param(name:"A",title:"益多松",defaultValue:"0.0",paramType:ParamType.INTEGER).save(failOnError: true)
    //     def param2 = new Param(name:"B",title:"芬殺蟎",defaultValue:false,paramType:ParamType.BOOLEAN).save(failOnError: true)
    //     def param3 = new Param(name:"C",title:"芬普蟎",defaultValue:"0.5",paramType:ParamType.STRING).save(failOnError: true)
    //     def report=new Report(name:"report3",title:"成品檢驗報告集", reportType: ReportType.INSPECT).save(failOnError: true)
    //     def reportparam1=new  ReportParams (report:report,param:param1,workstation:workstation,operation:operation,item:item).save(failOnError: true)
    //     def reportparam2=new  ReportParams (report:report,param:param2,workstation:workstation,operation:operation,item:item).save(failOnError: true)
    //     def reportparam3=new  ReportParams (report:report,param:param3,workstation:workstation,operation:operation,item:item).save(failOnError: true)
    // }
    // def createBatchSource = {
    //     def mainBatch = Batch.get(1)
    //     def item = new Item(name:"item2",title:"橘子").save(failOnError: true)
    //     def batch = new Batch(name:"batch2",item:item).save(failOnError: true)
    //     new BatchSource(batch:mainBatch,childBatch:batch).save(failOnError: true)

    // }
    // def createOtherDomain = {
    //     def site = new Site(name:"site", title:"創毅公司").save(failOnError: true)
    //     def user = new User(username: 'user', password: 'user', enabled: true,site:site).save(failOnError: true)
    //     site.addToUser(user).save(failOnError: true)

    //     def customer = new Customer(name:"cutstomer",title:"A先生").save(failOnError: true)
    //     def supplier = new Supplier(name:"supplier",title:"A公司",email:"A@xx.com",address:"台北市忠孝東路222號").save(failOnError: true)

    // }

    def createTestMessage = { messageSource ->
        messageSource.addMessage("default.message.save.success", Locale.getDefault(), "儲存成功")
        messageSource.addMessage("default.message.save.failed", Locale.getDefault(), "儲存失敗")
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
        messageSource.addMessage("country.SPAIN.label", Locale.getDefault(), "西班牙") 
    }
}
