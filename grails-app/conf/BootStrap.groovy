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
                stdTestDataCreate()
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

    private stdTestDataCreate(){
        //標準測試資料，作為驗證以及 unit test 用

        def item1 = new Item(name:"item1",title:"華珍玉米",spec:"華珍甜玉米，高糖分、皮薄",unit:"kg",description:"非基因轉殖品種 (Non-Genetically Modifie) 生長強健，特別耐熱、耐濕及抗倒伏，抗病毒病、葉斑病、螟蟲， 果穗整齊飽滿，著粒完整，穗粒淡黃色， 皮非常薄(有無皮的感覺)，脆嫩香甜，品質非常優良。 糖分保持力較長，較耐貯運。").save(failOnError: true, flush: true)

        def batch1 = new Batch(name:"batch1",item:item1,dueDate:new Date(), 
                manufactureDate: new Date(), expirationDate: new Date(), remark: '備註').save(failOnError: true, flush: true)

        def workstation1 = new Workstation(name:"workstation1",title:"檢驗站01").save(failOnError: true, flush: true)
        def workstation2 = new Workstation(name:"workstation2",title:"檢驗站02").save(failOnError: true, flush: true)

        def operation1 = new Operation(name:"operation1",title:"施肥").save(failOnError: true, flush: true)
        def operation2 = new Operation(name:"operation2",title:"翻土").save(failOnError: true, flush: true)

        def itemRoute1=new ItemRoute(item:item1,sequence:1,operation:operation1,workstation:workstation1).save(failOnError: true, flush: true)
        def itemRoute2=new ItemRoute(item:item1,sequence:2,operation:operation1,workstation:workstation2).save(failOnError: true, flush: true)
        def itemRoute3=new ItemRoute(item:item1,sequence:3,operation:operation2,workstation:workstation1).save(failOnError: true, flush: true)
        def itemRoute4=new ItemRoute(item:item1,sequence:4,operation:operation2,workstation:workstation2).save(failOnError: true, flush: true)

        def batchRoute1=new BatchRoute(batch:batch1,workstation:workstation1,sequence:1,operation:operation1).save(failOnError: true,flush: true)
        def batchRoute2=new BatchRoute(batch:batch1,workstation:workstation1,sequence:2,operation:operation2,startDate:new Date()).save(failOnError: true,flush: true)
        def batchRoute3=new BatchRoute(batch:batch1,workstation:workstation2,sequence:3,operation:operation1).save(failOnError: true,flush: true)
        def batchRoute4=new BatchRoute(batch:batch1,workstation:workstation2,sequence:4,operation:operation2).save(failOnError: true,flush: true)

        // ReportType.OTHER
        def param5 = new Param(name:"param5",title:"PackageCapacity",defaultValue:"100",paramType:ParamType.INTEGER).save(failOnError: true, flush: true)
        def report3=new Report(name:"report2",title:"檢驗紀錄集").save(failOnError: true, flush: true)
        def report3param5=new  ReportParams (report:report3,param:param5,workstation:workstation1,operation:operation1,item:item1).save(failOnError: true, flush: true)
        def batchReportDet2=new  BatchReportDet (batch:batch1,reportParams:report3param5).save(failOnError: true, flush: true)

        // ReportType.NUTRITION
        def param1= new Param(name:"param1",title:"Pack", unit:"玻璃瓶[60 毫升/62g]",paramType:ParamType.INTEGER).save(failOnError: true, flush: true)
        def report1=new Report(name:"report1",title:"營養標示履歷", reportType: ReportType.NUTRITION).save(failOnError: true, flush: true)
        def report1param1=new  ReportParams (report:report1,param:param1,workstation:workstation1,operation:operation2,item:item1).save(failOnError: true, flush: true)

        // ReportType.INSPECT
        def param2 = new Param(name:"A",title:"益多松",defaultValue:"0.0",paramType:ParamType.INTEGER).save(failOnError: true, flush: true)
        def param3 = new Param(name:"B",title:"芬殺蟎",defaultValue:false,paramType:ParamType.BOOLEAN).save(failOnError: true, flush: true)
        def param4 = new Param(name:"C",title:"芬普蟎",defaultValue:"0.5",paramType:ParamType.STRING).save(failOnError: true, flush: true)
        def report2=new Report(name:"report3",title:"成品檢驗報告集", reportType: ReportType.INSPECT).save(failOnError: true, flush: true)
        def report2param2=new  ReportParams (report:report2,param:param2,workstation:workstation2,operation:operation1,item:item1).save(failOnError: true, flush: true)
        def report2param3=new  ReportParams (report:report2,param:param3,workstation:workstation2,operation:operation1,item:item1).save(failOnError: true, flush: true)
        def report2param4=new  ReportParams (report:report2,param:param4,workstation:workstation2,operation:operation1,item:item1).save(failOnError: true, flush: true)


        def user01 = new User(username: 'user01', password: 'user01', enabled: true).save(failOnError: true, flush: true)
        def cutstomer1 = new Customer(name:"cutstomer1",title:"A先生").save(failOnError: true, flush: true)
        def supplier1 = new Supplier(name:"supplier1",title:"A公司",email:"A@xx.com",address:"台北市忠孝東路222號").save(failOnError: true, flush: true)
                        

        def item2 = new Item(name:"item2",title:"橘子").save(failOnError: true, flush: true)
        def batch2 = new Batch(name:"batch2",item:item1).save(failOnError: true, flush: true)
        new BatchSource(batch:batch1,childBatch:batch2).save(failOnError: true, flush: true)

    }
}
