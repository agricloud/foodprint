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
                for(int i=0;i<100;i++){
                    def item = new Item(name:"item"+i,title:"華珍玉米"+i,spec:"華珍甜玉米，高糖分、皮薄",unit:"kg",description:"非基因轉殖品種").save(failOnError: true)
                    def ws = new Workstation(name:"workstation"+i,title:"民雄線"+i).save(failOnError: true, flush: true)
                    def op = new Operation(name:"operation"+i,title:"施肥"+i).save(failOnError: true)
                    def sp = new Supplier(name:"supplier"+i,title:"福智麻園"+i,country:Country.TAIWAN).save(failOnError: true, flush: true)

                    if(i==70){
                        def itemRoute=new ItemRoute(item:item,sequence:1,operation:op,workstation:ws,supplier:sp)
                        item.addToItemRoutes(itemRoute).save(failOnError: true)
                    }
                }
  
                /*
                def testService = new TestService()

                testService.createStdTestData()
                testService.createExtraRouteData()
                testService.createOtherReport()
                testService.createNutritionReport()
                testService.createInspectReport()
                testService.createBatchSource()
                testService.createOtherDomain()
                */
                // 除了標準測試資料外，額外的測試資料定義於此
            }
        }
    }
    def destroy = {

    }



    private jsonParseDefine(){
        JSON.registerObjectMarshaller(User) {
            convertService.userParseJson(it)
        }
        JSON.registerObjectMarshaller(Customer) {
            convertService.customerParseJson(it)
        }
        JSON.registerObjectMarshaller(Batch) {
            convertService.batchParseJson(it)
        }
        JSON.registerObjectMarshaller(BatchRoute) {
            convertService.batchRouteParseJson(it)
        }
        JSON.registerObjectMarshaller(BatchSource) {
            convertService.batchSourceParseJson(it)
        }
        JSON.registerObjectMarshaller(Item) {
            convertService.itemParseJson(it)
        }
        JSON.registerObjectMarshaller(ItemRoute) {
            convertService.itemRouteParseJson(it)
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


}
