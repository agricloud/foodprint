package foodprint

import common.*
import org.junit.*
import grails.test.mixin.*
import spock.lang.Specification

@TestFor(BatchReportDetController)
@Mock([BatchReportDet,
    Workstation, Supplier, Operation,
    Item, Batch, BatchRoute,
    Report, Param, ReportParams,
    DomainService, TestService])
class BatchReportDetControllerSpec extends Specification {

    void setup(){
        def testService = new TestService()
        testService.createTestMessage(messageSource)
    }

    void "測試 showBatchRouteParams action，並且 response 為 json 格式"() {
        setup: "建立測試資料"
            def item = new Item(name:"item1", title: 'item1').save(failOnError: true)
            def batch = new Batch(name:"batch1",item:item,).save(failOnError: true)
            def workstation = new Workstation(name: 'workstation',title:'workstation').save(failOnError: true)
            def operation = new Operation(name:"operation1",title:"施肥").save(failOnError: true)
            def batchRoute = new BatchRoute(batch:batch, sequence:1, operation:operation,workstation:workstation).save(failOnError: true)

            def param1 = new Param(name: 'param1',title:'param1').save(failOnError: true)
            def param2 = new Param(name: 'param2',title:'param2').save(failOnError: true)
            def report = new Report(name: 'report', title:'report').save(failOnError: true)
            def reportParams1 = new ReportParams(report:report, param:param1,operation:operation,workstation:workstation,item:item).save(failOnError: true)
            def reportParams2 = new ReportParams(report:report, param:param2,operation:operation,workstation:workstation,item:item).save(failOnError: true)

            def batchReportDet = new BatchReportDet(batch:batch,reportParams:reportParams1,batchRoute:batchRoute)

        and: "前端傳入資料"
            params["batch.id"]=batch.id
            params["workstation.id"]=workstation.id
            params["operation.id"]=operation.id
            params["supplier.id"]=null

        when: "執行 BatchReportDetController 提供的 index action"
            controller.showBatchRouteParams()

        then: "response 要能取得 BatchReportDet json 格式初始資料"
            assert response.json

        then: "json 裡有 success 屬性，且為 true"
            assert response.json.success

        then: "json 裡有 data.class 屬性為 foodprint.BatchReportDet"
            assert response.json.data[0].class == "foodprint.BatchReportDet" 

        then: "json 裡有 data 屬性，且有一筆資料 name 屬性為 batchReportDet"
            assert response.json.data[0].batch.id == batch.id

        then: "json 裡有 batchReportDetInstanceTotal 屬性為 2"
            assert response.json.total == 2


    }


    void "測試 doSaveOrUpdate action，並且回傳為 json 格式"() {

        setup: "建立測試資料"
            def item = new Item(name:"item1", title: 'item1').save(failOnError: true)
            def batch = new Batch(name:"batch1",item:item,).save(failOnError: true)
            def workstation = new Workstation(name: 'workstation',title:'workstation').save(failOnError: true)
            def operation = new Operation(name:"operation1",title:"施肥").save(failOnError: true)
            def batchRoute = new BatchRoute(batch:batch, sequence:1, operation:operation,workstation:workstation).save(failOnError: true)

            def param = new Param(name: 'param1',title:'param1').save(failOnError: true)
            def report = new Report(name: 'report', title:'report').save(failOnError: true)
            def reportParams = new ReportParams(report:report, param:param,operation:operation,workstation:workstation,item:item).save(failOnError: true)
            def batchReportDet = new BatchReportDet(batch:batch,reportParams:reportParams,batchRoute:batchRoute).save(failOnError: true)

        and: "前端傳入資料"
            params.id=batchRoute.id
            params.startDate = new Date()
            params.endDate = new Date()
            params["1"]="50.0"

        when: "執行 update action"
            controller.doSaveOrUpdate()
        
        then: "response 要能取得 json 格式初始資料"
            assert response.json

        then: "json 裡有 success 屬性，且為 true"
            assert response.json.success

        then: "將有筆新增資料"
            assert BatchReportDet.list().size() == 1

        then: "修改後的屬性有正確寫入"
            assert BatchRoute.get(1).startDate== params.startDate
            assert BatchRoute.get(1).endDate== params.endDate
            assert BatchReportDet.get(1).value == '50.0'
    }

}
