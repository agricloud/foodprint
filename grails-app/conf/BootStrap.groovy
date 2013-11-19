import foodprint.*
import common.*
import grails.converters.JSON

class BootStrap {
    def convertService
    def init = { servletContext ->

        // 預設時區，避免 json 轉換自動扣除 8 小時(台灣 +8:00)
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"))
        jsonParseDefine()



        environments {
            def role1 = Role.findOrSaveByAuthority('ROLE_ADMIN')
            def user1 = User.findByUsername('admin')

            if (!user1) {
                user1 = new User(username: 'admin', password: 'admin', enabled: true).save(failOnError: true, flush: true)
                UserRole.create(user1, role1)

            }

            development {
                createStdTestData()
                createExtraRouteData()
                createOtherReport()
                createNutritionReport()
                createInspectReport()
                createBatchSource()
                // 除了標準測試資料外，額外的測試資料定義於此
            }
        }
    }
    def destroy = {

    }



    private jsonParseDefine(){
        JSON.registerObjectMarshaller(Batch) {
            convertService.batchParseJson(it)
        }
        JSON.registerObjectMarshaller(BatchRoute) {
            convertService.batchRouteParseJson(it)
        }

        JSON.registerObjectMarshaller(Item) {
            convertService.itemParseJson(it)
        }
        JSON.registerObjectMarshaller(Workstation) {
            convertService.workstationParseJson(it)
        }
        JSON.registerObjectMarshaller(Supplier) {
            convertService.supplierParseJson(it)
        }
        JSON.registerObjectMarshaller(Operation) {
            convertService.operationParseJson(it)
        }
        JSON.registerObjectMarshaller(Param) {
            convertService.paramParseJson(it)
        }
        JSON.registerObjectMarshaller(Report) {
            convertService.reportParseJson(it)
        }
        JSON.registerObjectMarshaller(ReportParams) {
            convertService.reportParamsParseJson(it)
        }
        JSON.registerObjectMarshaller(BatchReportDet) {
            convertService.batchReportDetParseJson(it)
        }
    }

    private createStdTestData(){
        //標準測試資料，作為驗證以及 unit test 用

        def item = new Item(name:"item1",title:"華珍玉米",spec:"華珍甜玉米，高糖分、皮薄",unit:"kg",description:"非基因轉殖品種 (Non-Genetically Modifie) 生長強健，特別耐熱、耐濕及抗倒伏，抗病毒病、葉斑病、螟蟲， 果穗整齊飽滿，著粒完整，穗粒淡黃色， 皮非常薄(有無皮的感覺)，脆嫩香甜，品質非常優良。 糖分保持力較長，較耐貯運。").save(failOnError: true, flush: true)

        def batch = new Batch(name:"batch1",item:item,dueDate:new Date(), 
                manufactureDate: new Date(), expirationDate: new Date(), remark: '備註').save(failOnError: true, flush: true)

        def workstation = new Workstation(name:"workstation1",title:"檢驗站01").save(failOnError: true, flush: true)
        def operation = new Operation(name:"operation1",title:"施肥").save(failOnError: true, flush: true)

        def itemRoute = new ItemRoute(item:item,sequence:1,operation:operation,workstation:workstation).save(failOnError: true, flush: true)
        def batchRoute = new BatchRoute(batch:batch,workstation:workstation,sequence:1,operation:operation).save(failOnError: true,flush: true)

        def user = new User(username: 'user', password: 'user', enabled: true).save(failOnError: true, flush: true)
        def cutstomer = new Customer(name:"cutstomer",title:"A先生").save(failOnError: true, flush: true)
        def supplier = new Supplier(name:"supplier",title:"A公司",email:"A@xx.com",address:"台北市忠孝東路222號").save(failOnError: true, flush: true)
                        



    }
    private createExtraRouteData(){
        def item = Item.get(1)
        def batch = Batch.get(1)

        def workstation1 = Workstation.get(1)
        def operation1 = Operation.get(1)

        def workstation2 = new Workstation(name:"workstation2",title:"檢驗站02").save(failOnError: true, flush: true)
        def operation2 = new Operation(name:"operation2",title:"翻土").save(failOnError: true, flush: true)

        def itemRoute2=new ItemRoute(item:item,sequence:2,operation:operation1,workstation:workstation2).save(failOnError: true, flush: true)
        def itemRoute3=new ItemRoute(item:item,sequence:3,operation:operation2,workstation:workstation1).save(failOnError: true, flush: true)
        def itemRoute4=new ItemRoute(item:item,sequence:4,operation:operation2,workstation:workstation2).save(failOnError: true, flush: true)

        def batchRoute2=new BatchRoute(batch:batch,workstation:workstation1,sequence:2,operation:operation2,startDate:new Date()).save(failOnError: true,flush: true)
        def batchRoute3=new BatchRoute(batch:batch,workstation:workstation2,sequence:3,operation:operation1).save(failOnError: true,flush: true)
        def batchRoute4=new BatchRoute(batch:batch,workstation:workstation2,sequence:4,operation:operation2).save(failOnError: true,flush: true)
    }
    private createOtherReport(){
        def item = Item.get(1)
        def batch = Batch.get(1)
        def workstation = Workstation.get(1)
        def operation = Operation.get(1)
        def param = new Param(name:"paramOther",title:"PackageCapacity",defaultValue:"100",paramType:ParamType.INTEGER).save(failOnError: true, flush: true)
        def report = new Report(name:"reportOther",title:"檢驗紀錄集").save(failOnError: true, flush: true)
        def reportparam = new ReportParams (report:report,param:param,workstation:workstation,operation:operation,item:item).save(failOnError: true, flush: true)
        def batchReportDet = new BatchReportDet (batch:batch,reportParams:reportparam).save(failOnError: true, flush: true)
    }
    private createNutritionReport(){
        def item = Item.get(1)
        def batch = Batch.get(1)
        def workstation = Workstation.get(1)
        def operation = Operation.get(2)
        def param= new Param(name:"paramNutrition",title:"Pack", unit:"玻璃瓶[60 毫升/62g]",paramType:ParamType.INTEGER).save(failOnError: true, flush: true)
        def report=new Report(name:"reportNutrition",title:"營養標示履歷", reportType: ReportType.NUTRITION).save(failOnError: true, flush: true)
        def reportparam=new  ReportParams (report:report,param:param,workstation:workstation,operation:operation,item:item).save(failOnError: true, flush: true)
    }
    private createInspectReport(){
        def item = Item.get(1)
        def batch = Batch.get(1)
        def workstation = Workstation.get(2)
        def operation = Operation.get(1)
        def param1 = new Param(name:"A",title:"益多松",defaultValue:"0.0",paramType:ParamType.INTEGER).save(failOnError: true, flush: true)
        def param2 = new Param(name:"B",title:"芬殺蟎",defaultValue:false,paramType:ParamType.BOOLEAN).save(failOnError: true, flush: true)
        def param3 = new Param(name:"C",title:"芬普蟎",defaultValue:"0.5",paramType:ParamType.STRING).save(failOnError: true, flush: true)
        def report=new Report(name:"report3",title:"成品檢驗報告集", reportType: ReportType.INSPECT).save(failOnError: true, flush: true)
        def reportparam1=new  ReportParams (report:report,param:param1,workstation:workstation,operation:operation,item:item).save(failOnError: true, flush: true)
        def reportparam2=new  ReportParams (report:report,param:param2,workstation:workstation,operation:operation,item:item).save(failOnError: true, flush: true)
        def reportparam3=new  ReportParams (report:report,param:param3,workstation:workstation,operation:operation,item:item).save(failOnError: true, flush: true)
    }
    private createBatchSource(){
        def mainBatch = Batch.get(1)
        def item = new Item(name:"item2",title:"橘子").save(failOnError: true, flush: true)
        def batch = new Batch(name:"batch2",item:item).save(failOnError: true, flush: true)
        new BatchSource(batch:mainBatch,childBatch:batch).save(failOnError: true, flush: true)
    }
}
