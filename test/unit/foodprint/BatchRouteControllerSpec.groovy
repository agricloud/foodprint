package foodprint

import common.*
import org.junit.*
import grails.test.mixin.*
import spock.lang.Specification

@TestFor(BatchRouteController)
@Mock([BatchRoute, Item, Batch, Operation,
    DomainService, TestService])
class BatchRouteControllerSpec extends Specification {

    void setup(){
        def testService = new TestService()
        testService.createTestMessage(messageSource)
    }

    void "測試 index action，並且 response 為 json 格式"() {
        setup: "建立 BatchRoute 測試資料"
            def item = new Item(name:"item1", title: 'item1', unit:'kg').save(failOnError: true)
            def batch = new Batch(name:"batch1",item:item,).save(failOnError: true)
            def operation = new Operation(name:"operation1",title:"施肥").save(failOnError: true)
            def batchRoute = new BatchRoute(batch:batch, sequence:1, operation:operation).save(failOnError: true)
            batch.addToBatchRoutes(batchRoute).save(failOnError: true)

        and: "前端傳入資料，定義 batch.id 為測試資料的 batch.id"
            params["batch.id"]=batch.id

        when: "執行 BatchRouteController 提供的 index action"
            controller.index()

        then: "response 要能取得 BatchRoute json 格式初始資料"
            assert response.json

        then: "json 裡有 batchRouteInstanceList 屬性，且有一筆資料 sequence 屬性為 1"
            assert response.json.data[0].sequence == 1

        then: "json 裡有 batchRouteInstanceTotal 屬性為 1"
            assert response.json.total == 1   

    }

    void "測試 show action，並且 response 為 json 格式"() {

        setup: "建立測試資料"
            def item = new Item(name:"item1", title: 'item1', unit:'kg').save(failOnError: true)
            def batch = new Batch(name:"batch1",item:item,).save(failOnError: true)
            def operation = new Operation(name:"operation1",title:"施肥").save(failOnError: true)
            def batchRoute = new BatchRoute(batch:batch, sequence:1, operation:operation).save(failOnError: true)

        and: "前端傳入資料，定義 id 為測試資料的 id"
            params.id = batchRoute.id

        when: "執行 BatchRouteController 提供的 show action"
            controller.show()

        then: "response 要能取得 BatchRoute json 格式初始資料"
            assert response.json

        then: "json 裡有 success 屬性，且為 true"
            assert response.json.success

        then: "json 裡有 data.class 屬性為 foodprint.BatchRoute"
            assert response.json.data.class == "foodprint.BatchRoute"

    }

    void "測試 create action，並且回傳為 json 格式(尚未儲存)"() {

        setup: "建立測試資料"
            def item = new Item(name:"item1", title: 'item1', unit:'kg').save(failOnError: true)
            def batch = new Batch(name:"batch1",item:item,).save(failOnError: true)

        and: "前端傳入資料，定義 item.id 為測試資料的 item.id"
            params["batch.id"]=batch.id

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
            def item = new Item(name:"item1", title: 'item1', unit:'kg').save(failOnError: true)
            def batch = new Batch(name:"batch1",item:item,).save(failOnError: true)
            def operation = new Operation(name:"operation1",title:"施肥").save(failOnError: true)

        and: "前端傳入資料，定義 item.id 為測試資料的 item.id"
            params["sequence"] = 1  
            params["batch.id"]=batch.id
            params["operation.id"] = operation.id

        when: "執行 save action"
            controller.save()

        then: "response 要能取得 json 格式初始資料"
            assert response.json

        then: "json 裡有 success 屬性為 true"
            assert response.json.success

        then: "資料庫將有筆新增資料"
            assert BatchRoute.list().size() == 1
            assert BatchRoute.get(1)   
    }

    void "測試 update action，並且回傳為 json 格式"() {

        setup: "建立測試資料"
            def item = new Item(name:"item1", title: 'item1', unit:'kg').save(failOnError: true)
            def batch1 = new Batch(name:"batch1",item:item,).save(failOnError: true)
            def batch2 = new Batch(name:"batch2",item:item,).save(failOnError: true)
            def operation = new Operation(name:"operation1",title:"施肥").save(failOnError: true)
            def batchRoute = new BatchRoute(batch:batch1, sequence:1, operation:operation).save(failOnError: true)

        and: "前端傳入資料，定義 id 為測試資料的 id，並且修改屬性"
            params.id = batchRoute.id
            params["batch.id"] = batch2.id

        when: "執行 update action"
            controller.update()
        
        then: "response 要能取得 json 格式初始資料"
            assert response.json

        then: "json 裡有 success 屬性，且為 true"
            assert response.json.success

        then: "將有筆新增資料"
            assert BatchRoute.list().size() == 1

        then: "修改後的屬性有正確寫入"
            assert BatchRoute.get(1).batch == batch2
    }

    void "測試 delete action，並且回傳為 json 格式"() {

        setup: "建立測試資料"
            def item = new Item(name:"item1", title: 'item1', unit:'kg').save(failOnError: true)
            def batch = new Batch(name:"batch1",item:item,).save(failOnError: true)
            def operation = new Operation(name:"operation1",title:"施肥").save(failOnError: true)
            def batchRoute = new BatchRoute(batch:batch, sequence:1, operation:operation).save(failOnError: true)
        
        and: "前端傳入資料，定義 id 為測試資料的 id"
            params.id = batchRoute.id

        when: "執行 delete action"
            controller.delete()

        then: "response 要能取得 json 格式初始資料"
            assert response.json

        then: "json 裡有 success 屬性，且為 true"
            assert response.json.success        
 
        then: "該筆資料已移除"
            assert BatchRoute.list().size() == 0
    }

}
