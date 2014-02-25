package foodprint

import common.*
import org.junit.*
import grails.test.mixin.*
import spock.lang.Specification

@TestFor(ReportParamsController)
@Mock([ReportParams, Item, Param, Report, Operation,
    DomainService, TestService])
class ReportParamsControllerSpec extends Specification {

    void setup(){
        def testService = new TestService()
        testService.createTestMessage(messageSource)
    }

    void "測試 index action，並且 response 為 json 格式"() {
        setup: "建立 ReportParams 測試資料"
            def item = new Item(name:"item1",title:"華珍玉米",spec:"華珍甜玉米，高糖分、皮薄",unit:"kg",description:"非基因轉殖品種").save(failOnError: true)
            def operation = new Operation(name:"operation1",title:"施肥").save(failOnError: true)
            def param = new Param(name: 'param',title:'param').save(failOnError: true)
            def report = new Report(name: 'report', title:'report').save(failOnError: true)
            new ReportParams(report:report, param:param, operation:operation,item:item).save(failOnError: true)

        and: "前端傳入資料，定義 report.id 為測試資料的 report.id"
            params["report.id"] = report.id

        when: "執行 ReportParamsController 提供的 index action"
            controller.index()

        then: "response 要能取得 ReportParams json 格式初始資料"
            assert response.json

        then: "json 裡有 reportParamsInstanceList 屬性，且有一筆資料 report.id 屬性與測試資料 report.id相同"
            assert response.json.reportParamsInstanceList[0].report.id == report.id

        then: "json 裡有 reportParamsInstanceTotal 屬性為 1"
            assert response.json.reportParamsInstanceTotal == 1   

    }

    void "測試 show action，並且 response 為 json 格式"() {

        setup: "建立測試資料"
            def item = new Item(name:"item1",title:"華珍玉米",spec:"華珍甜玉米，高糖分、皮薄",unit:"kg",description:"非基因轉殖品種").save(failOnError: true)
            def operation = new Operation(name:"operation1",title:"施肥").save(failOnError: true)
            def param = new Param(name: 'param',title:'param').save(failOnError: true)
            def report = new Report(name: 'report', title:'report').save(failOnError: true)
            def reportParams = new ReportParams(report:report, param:param, operation:operation, item:item).save(failOnError: true)

        and: "前端傳入資料，定義 id 為測試資料的 id"
            params.id = reportParams.id

        when: "執行 ReportParamsController 提供的 show action"
            controller.show()

        then: "response 要能取得 ReportParams json 格式初始資料"
            assert response.json

        then: "json 裡有 success 屬性，且為 true"
            assert response.json.success

        then: "json 裡有 data.class 屬性為 foodprint.ReportParams"
            assert response.json.data.class == "foodprint.ReportParams"

    }

    void "測試 create action，並且回傳為 json 格式(尚未儲存)"() {

        setup: "建立測試資料"
            def report = new Report(name: 'report', title:'report').save(failOnError: true)

        and: "前端傳入資料，定義 id 為測試資料的 id"
            params["report.id"] = report.id

        when: "執行 create action"
            controller.create()

        then: "response 要能取得 json 格式初始資料"
            assert response.json

        then: "json 裡有 success 屬性，且為 true"
            assert response.json.success

        then: "json 裡有 data.class 屬性"
            assert response.json.data.class
    }

    void "測試 save action，並且回傳為 json 格式(儲存完成)"() {

        setup: "建立測試資料"
            def item = new Item(name:"item1",title:"華珍玉米",spec:"華珍甜玉米，高糖分、皮薄",unit:"kg",description:"非基因轉殖品種").save(failOnError: true)
            def operation = new Operation(name:"operation1",title:"施肥").save(failOnError: true)
            def param = new Param(name: 'param',title:'param').save(failOnError: true)
            def report = new Report(name: 'report', title:'report', operation:operation).save(failOnError: true)

        and: "前端傳入資料"
            params["report.id"] = report.id
            params["param.id"] = param.id
            params["item.id"] = item.id
            params["operation.id"] = operation.id

        when: "執行 save action"
            controller.save()

        then: "response 要能取得 json 格式初始資料"
            assert response.json

        then: "json 裡有 success 屬性為 true"
            assert response.json.success

        then: "資料庫將有筆新增資料"
            assert ReportParams.list().size() == 1
            assert ReportParams.get(1)   
    }

    void "測試 update action，並且回傳為 json 格式"() {

        setup: "建立測試資料"
            def item = new Item(name:"item1",title:"華珍玉米",spec:"華珍甜玉米，高糖分、皮薄",unit:"kg",description:"非基因轉殖品種").save(failOnError: true)
            def operation = new Operation(name:"operation1",title:"施肥").save(failOnError: true)
            def param1 = new Param(name: 'param1',title:'param1').save(failOnError: true)
            def param2 = new Param(name: 'param2',title:'param2').save(failOnError: true)
            def report = new Report(name: 'report', title:'report').save(failOnError: true)
            def reportParams = new ReportParams(report:report, param:param1, operation:operation, item:item).save(failOnError: true)

        and: "前端傳入資料，定義 id 為測試資料的 id，並且修改屬性"
            params.id = reportParams.id
            params["param.id"] = param2.id

        when: "執行 update action"
            controller.update()
        
        then: "response 要能取得 json 格式初始資料"
            assert response.json

        then: "json 裡有 success 屬性，且為 true"
            assert response.json.success

        then: "將有筆新增資料"
            assert ReportParams.list().size() == 1

        then: "修改後的屬性有正確寫入"
            assert ReportParams.get(1).param == param2
    }

    void "測試 delete action，並且回傳為 json 格式"() {

        setup: "建立測試資料"
            def item = new Item(name:"item1",title:"華珍玉米",spec:"華珍甜玉米，高糖分、皮薄",unit:"kg",description:"非基因轉殖品種").save(failOnError: true)
            def operation = new Operation(name:"operation1",title:"施肥").save(failOnError: true)
            def param = new Param(name: 'param',title:'param').save(failOnError: true)
            def report = new Report(name: 'report', title:'report').save(failOnError: true)
            def reportParams = new ReportParams(report:report, param:param, operation:operation, item:item).save(failOnError: true)

        and: "前端傳入資料，定義 id 為測試資料的 id"
            params.id = reportParams.id

        when: "執行 delete action"
            controller.delete()

        then: "response 要能取得 json 格式初始資料"
            assert response.json

        then: "json 裡有 success 屬性，且為 true"
            assert response.json.success        
 
        then: "該筆資料已移除"
            assert ReportParams.list().size() == 0
    }

}
