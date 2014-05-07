package foodprint

class TestService {


    // def createStdTestData = {
        //標準測試資料，作為驗證以及 unit test 用

        // def workstation1 = new Workstation(name:"workstation1",title:"檢驗站01").save(failOnError: true)
        // def operation = new Operation(name:"operation1",title:"施肥").save(failOnError: true)

        // def itemRoute = new ItemRoute(item:item,sequence:1,operation:operation,workstation:workstation)
        // item.addToItemRoutes(itemRoute).save(failOnError: true)

        // def batchRoute = new BatchRoute(batch:batch,workstation:workstation,sequence:1,operation:operation)
        // batch.addToBatchRoutes(batchRoute).save(failOnError: true)
    // }
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
        // def report = new Report(name:"reportOther",title:"檢驗紀錄集").save(failOnError: true)
        // def reportparam = new ReportParams (report:report,param:param,workstation:workstation,operation:operation,item:item).save(failOnError: true)
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
    def createCloudFarmData = {
        def site1 = new Site(name:"site1",title:"雲林斗六許家農場",description:"專門栽種符合TGAP柑桔類之果園",address:"雲林縣斗六市重光里").save(failOnError: true)
        def user2 = new User(username: 'huang', password: 'huang', enabled: true,site:site1).save(fai6lOnError: true)
        def workstation1 = new Workstation(name:"workstation1",title:"柳丁栽種區-1985",description:"地號：1111-1111").save(failOnError: true)
        
        def supplier1 = new Supplier(name:"supplier1",title:"農家樂",tel:"05-5342501",email:"long@agri.com",address:"雲林縣斗六市成功路").save(failOnError: true)
        def supplier2 = new Supplier(name:"supplier2",title:"金農友企業股份有限公司",tel:"05-5340123",email:"ging@agri.com",address:"雲林縣斗六市成功路").save(failOnError: true)
        def supplier3 = new Supplier(name:"supplier3",title:"SGS檢驗公司",tel:"05-5345555",email:"sgs@agri.com",address:"雲林縣斗南鎮雲林路").save(failOnError: true)

        def operation1 = new Operation(name:"operation1",title:"剪枝",description:"剪去多餘細枝，避免影響或傷害果實生長").save(failOnError: true)
        def operation2 = new Operation(name:"operation2",title:"病蟲害防治",description:"噴灑農藥").save(failOnError: true)
        def operation3 = new Operation(name:"operation3",title:"施肥",description:"施用肥料").save(failOnError: true)
        def operation4 = new Operation(name:"operation4",title:"除草",description:"以割草機割除雜草").save(failOnError: true)
        def operation5 = new Operation(name:"operation5",title:"施益肥",description:"施用益肥").save(failOnError: true)
        def operation6 = new Operation(name:"operation6",title:"灌水",description:"灌溉果園").save(failOnError: true)
        def operation7 = new Operation(name:"operation7",title:"檢驗",description:"農藥殘留檢驗").save(failOnError: true)

        
        
        def item1 = new Item(name:"item1",title:"履歷柳丁",description:"依台灣,好農業規範栽種之柳丁。",spec:"23級",unit:"台斤").save(failOnError: true)
        def batch1 = new Batch(name:"batch1",item:item1).save(failOnError: true)
        def item2 = new Item(name:"item2",title:"夏油",description:"越冬害蟲防治,95%礦物油乳劑",spec:"3kg",unit:"kg").save(failOnError: true)
        def batch2 = new Batch(name:"batch2",item:item2,supplier:supplier1).save(failOnError: true)
        def item3 = new Item(name:"item3",title:"大滅松",description:"越冬害蟲防治,44%大滅松乳劑",spec:"1000g/罐",unit:"罐").save(failOnError: true)
        def batch3 = new Batch(name:"batch3",item:item3,supplier:supplier1).save(failOnError: true)
        def item4 = new Item(name:"item4",title:"青苔剋",description:"除青苔",spec:"500g",unit:"g").save(failOnError: true)
        def batch4 = new Batch(name:"batch4",item:item4,supplier:supplier1).save(failOnError: true)
        def item5 = new Item(name:"item5",title:"台肥39號",description:"有機肥",spec:"40kg/包",unit:"包").save(failOnError: true)
        def batch5 = new Batch(name:"batch5",item:item5,supplier:supplier1).save(failOnError: true)
        def item6 = new Item(name:"item6",title:"重光六號",description:"全氫6.0%、全磷酐4.5%、全氫化鉀2.5%、有機質75.0%",spec:"20kg/包",unit:"包").save(failOnError: true)
        def batch6 = new Batch(name:"batch6",item:item6,supplier:supplier2).save(failOnError: true)
        def item7 = new Item(name:"item7",title:"耀祥豪牌",description:"全氮(N)3%、2. 全磷酐(P2O5) 3.0%、全氧鉀 (K2O) 2.0%、有機質60%、水分35%以下",spec:"25kg/包",unit:"包").save(failOnError: true)
        def batch7 = new Batch(name:"batch7",item:item7,supplier:supplier1).save(failOnError: true)
        def item8 = new Item(name:"item8",title:"陶斯寧",description:"防治潛夜蛾,50%混和乳劑",spec:"/罐",unit:"罐").save(failOnError: true)
        def batch8 = new Batch(name:"batch8",item:item8,supplier:supplier1).save(failOnError: true)
        def item9 = new Item(name:"item9",title:"三氟敏",description:"防治黑點病",spec:"50%水分散性粒劑",unit:"50g/包").save(failOnError: true)
        def batch9 = new Batch(name:"batch9",item:item9,supplier:supplier1).save(failOnError: true)
        def item10 = new Item(name:"item10",title:"勃激素A3",description:"植物生長調節劑,3.1%可濕性粉劑",spec:"1.6*8  g/盒",unit:"g").save(failOnError: true)
        def batch10 = new Batch(name:"batch10",item:item10,supplier:supplier1).save(failOnError: true)
        def item11 = new Item(name:"item11",title:"亞磷酸鉀",description:"抵禦病菌,45%亞磷酸",spec:"2kg",unit:"kg").save(failOnError: true)
        def batch11 = new Batch(name:"batch11",item:item11,supplier:supplier1).save(failOnError: true)
        def item12 = new Item(name:"item12",title:"丁基加保扶",description:"蚜蟲、薊馬,40%乳劑",spec:"500g/包",unit:"包").save(failOnError: true)
        def batch12 = new Batch(name:"batch12",item:item12,supplier:supplier1).save(failOnError: true)
        def item13 = new Item(name:"item13",title:"稱無限",description:"瘡痂病,23%水懸劑",spec:"250g/罐",unit:"罐").save(failOnError: true)
        def batch13 = new Batch(name:"batch13",item:item13,supplier:supplier1).save(failOnError: true)
        def item14 = new Item(name:"item14",title:"愛殺松",description:"蚜蟲、薊馬,46.5%水基性乳劑",spec:"250g/罐",unit:"罐").save(failOnError: true)
        def batch14 = new Batch(name:"batch14",item:item14,supplier:supplier1).save(failOnError: true)
        def item15 = new Item(name:"item15",title:"鋅錳乃浦",description:"殺菌,80%可濕性粉劑",spec:"25kg/包",unit:"包").save(failOnError: true)
        def batch15 = new Batch(name:"batch15",item:item15,supplier:supplier1).save(failOnError: true)
        
        def batchRoute1 = new BatchRoute(batch:batch1,sequence:1,workstation:workstation1,operation:operation1,startDate:"",endDate:"").save(failOnError: true)
        def batchRoute2 = new BatchRoute(batch:batch1,sequence:2,workstation:workstation1,operation:operation2,startDate:"",endDate:"").save(failOnError: true)
        def batchRoute3 = new BatchRoute(batch:batch1,sequence:3,workstation:workstation1,operation:operation3,startDate:"",endDate:"").save(failOnError: true)
        def batchRoute4 = new BatchRoute(batch:batch1,sequence:4,workstation:workstation1,operation:operation4,startDate:"",endDate:"").save(failOnError: true)
        def batchRoute5 = new BatchRoute(batch:batch1,sequence:5,workstation:workstation1,operation:operation2,startDate:"",endDate:"").save(failOnError: true)
        def batchRoute6 = new BatchRoute(batch:batch1,sequence:6,workstation:workstation1,operation:operation5,startDate:"",endDate:"").save(failOnError: true)
        def batchRoute7 = new BatchRoute(batch:batch1,sequence:7,workstation:workstation1,operation:operation2,startDate:"",endDate:"").save(failOnError: true)
        def batchRoute8 = new BatchRoute(batch:batch1,sequence:8,workstation:workstation1,operation:operation4,startDate:"",endDate:"").save(failOnError: true)
        def batchRoute9 = new BatchRoute(batch:batch1,sequence:9,workstation:workstation1,operation:operation6,startDate:"",endDate:"").save(failOnError: true)
        def batchRoute10 = new BatchRoute(batch:batch1,sequence:10,workstation:workstation1,operation:operation3,startDate:"",endDate:"").save(failOnError: true)
        def batchRoute11 = new BatchRoute(batch:batch1,sequence:11,workstation:workstation1,operation:operation7,startDate:"",endDate:"").save(failOnError: true)

        def batchSource2 = new BatchSource(batch:batch1,childBatch:batch2).save(failOnError: true)
        def batchSource3 = new BatchSource(batch:batch1,childBatch:batch3).save(failOnError: true)
        def batchSource4 = new BatchSource(batch:batch1,childBatch:batch4).save(failOnError: true)
        def batchSource5 = new BatchSource(batch:batch1,childBatch:batch5).save(failOnError: true)
        def batchSource6 = new BatchSource(batch:batch1,childBatch:batch6).save(failOnError: true)
        def batchSource7 = new BatchSource(batch:batch1,childBatch:batch7).save(failOnError: true)
        def batchSource8 = new BatchSource(batch:batch1,childBatch:batch8).save(failOnError: true)
        def batchSource9 = new BatchSource(batch:batch1,childBatch:batch9).save(failOnError: true)
        def batchSource10 = new BatchSource(batch:batch1,childBatch:batch10).save(failOnError: true)
        def batchSource11 = new BatchSource(batch:batch1,childBatch:batch11).save(failOnError: true)
        def batchSource12 = new BatchSource(batch:batch1,childBatch:batch12).save(failOnError: true)
        def batchSource13 = new BatchSource(batch:batch1,childBatch:batch13).save(failOnError: true)
        def batchSource14 = new BatchSource(batch:batch1,childBatch:batch14).save(failOnError: true)
        def batchSource15 = new BatchSource(batch:batch1,childBatch:batch15).save(failOnError: true)    

        def param1 = new Param(name:"param1",title:"大滅松",defaultValue:"0.0",paramType:ParamType.FLOAT).save(failOnError: true)
        def param2 = new Param(name:"param2",title:"青苔剋",defaultValue:"0.0",paramType:ParamType.FLOAT).save(failOnError: true)
        def param3 = new Param(name:"param3",title:"陶斯寧",defaultValue:"0.0",paramType:ParamType.FLOAT).save(failOnError: true)
        def param4 = new Param(name:"param4",title:"三氟敏",defaultValue:"0.0",paramType:ParamType.FLOAT).save(failOnError: true)
        def param5 = new Param(name:"param5",title:"勃激素A3",defaultValue:"0.0",paramType:ParamType.FLOAT).save(failOnError: true)
        def param6 = new Param(name:"param6",title:"亞磷酸鉀",defaultValue:"0.0",paramType:ParamType.FLOAT).save(failOnError: true)
        def param7 = new Param(name:"param7",title:"丁基加保扶",defaultValue:"0.0",paramType:ParamType.FLOAT).save(failOnError: true)
        def param8 = new Param(name:"param8",title:"稱無限",defaultValue:"0.0",paramType:ParamType.FLOAT).save(failOnError: true)
        def param9 = new Param(name:"param9",title:"愛殺松",defaultValue:"0.0",paramType:ParamType.FLOAT).save(failOnError: true)
        def param10 = new Param(name:"param10",title:"鋅錳乃浦",defaultValue:"0.0",paramType:ParamType.FLOAT).save(failOnError: true)
        def report1 = new Report(name:"report1",title:"檢驗報告").save(failOnError: true)
        def reportparam1 = new ReportParams (report:report1,param:param1,workstation:workstation1,operation:operation7,item:item1).save(failOnError: true)
        def reportparam2 = new ReportParams (report:report1,param:param2,workstation:workstation1,operation:operation7,item:item1).save(failOnError: true)
        def reportparam3 = new ReportParams (report:report1,param:param3,workstation:workstation1,operation:operation7,item:item1).save(failOnError: true)
        def reportparam4 = new ReportParams (report:report1,param:param4,workstation:workstation1,operation:operation7,item:item1).save(failOnError: true)
        def reportparam5 = new ReportParams (report:report1,param:param5,workstation:workstation1,operation:operation7,item:item1).save(failOnError: true)
        def reportparam6 = new ReportParams (report:report1,param:param6,workstation:workstation1,operation:operation7,item:item1).save(failOnError: true)
        def reportparam7 = new ReportParams (report:report1,param:param7,workstation:workstation1,operation:operation7,item:item1).save(failOnError: true)
        def reportparam8 = new ReportParams (report:report1,param:param8,workstation:workstation1,operation:operation7,item:item1).save(failOnError: true)
        def reportparam9 = new ReportParams (report:report1,param:param9,workstation:workstation1,operation:operation7,item:item1).save(failOnError: true)
        def reportparam10 = new ReportParams (report:report1,param:param10,workstation:workstation1,operation:operation7,item:item1).save(failOnError: true)

    } // end def createCloudFarmData
}
