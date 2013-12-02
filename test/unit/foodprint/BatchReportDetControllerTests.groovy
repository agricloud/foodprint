package foodprint



import org.junit.*
import grails.test.mixin.*
import common.*

@TestFor(BatchReportDetController)
@Mock([BatchReportDet, TestService, DomainService,
    Item, ItemRoute,
    Workstation, Operation,
    Batch, BatchRoute,
    Report, Param, ReportParams])
class BatchReportDetControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
        def batchReportDet = BatchReportDet.get(1)
        def batchRoute = BatchRoute.get(1)

        params["id"] = batchRoute.id
        params[batchReportDet.id] = 1

    }


    void testSaveOrUpdate() {

        def testService = new TestService()
        testService.createTestMessage(messageSource)
        testService.createStdTestData() 
        testService.createOtherReport()

        populateValidParams(params)
        controller.doSaveOrUpdate()

        assert BatchReportDet.count() == 1
        assert BatchReportDet.get(1).batchRoute
    }
}
