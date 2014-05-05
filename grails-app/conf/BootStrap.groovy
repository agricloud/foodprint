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
                
                def testService = new TestService()

                testService.createStdTestData()
                // testService.createExtraRouteData()
                // testService.createOtherReport()
                // testService.createNutritionReport()
                // testService.createInspectReport()
                // testService.createBatchSource()
                // testService.createOtherDomain()
                
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
