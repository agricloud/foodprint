package foodprint

import common.*
import org.junit.*
import grails.test.mixin.*
import spock.lang.Specification

@TestFor(BatchController)
@Mock([Item, Batch, 
    DomainService, TestService])
class BatchControllerSpec extends Specification {

    void setup(){
        def testService = new TestService()
        testService.createTestMessage(messageSource)
    }

    void "測試 index action，並且 response 為 json 格式"() {
        setup: "建立 Batch 測試資料"
            def item = new Item(name: 'item').save(failOnError: true)
            def batch = new Batch(name: 'batch', item: item).save(failOnError: true)

        when: "執行 BatchController 提供的 index action"
            controller.index()

        then: "response 要能取得 Batch json 格式初始資料"
            assert response.json

        then: "json 裡有 batchInstanceList 屬性，且有一筆資料 name 屬性為 batch"
            assert response.json.batchInstanceList[0].name == "batch"

        then: "json 裡有 batchInstanceTotal 屬性為 1"
            assert response.json.batchInstanceTotal == 1   

    }

    void "測試 show action，並且 response 為 json 格式"() {

        setup: "建立測試資料"
            def item = new Item(name: 'item').save(failOnError: true)
            def batch = new Batch(name: 'batch', item: item).save(failOnError: true)

        and: "前端傳入資料，定義 id 為測試資料的 id"
            params.id = batch.id

        when: "執行 BatchController 提供的 show action"
            controller.show()

        then: "response 要能取得 Batch json 格式初始資料"
            assert response.json

        then: "json 裡有 success 屬性，且為 true"
            assert response.json.success

        then: "json 裡有 data.class 屬性為 foodprint.Batch"
            assert response.json.data.class == "foodprint.Batch"

    }

    void "測試 create action，並且回傳為 json 格式(尚未儲存)"() {

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

        setup: "前端傳入資料"
            def item = new Item(name: 'item').save(failOnError: true)
            params["item.id"] = item.id
            params["name"] = 'batch'

        when: "執行 save action"
            controller.save()

        then: "response 要能取得 json 格式初始資料"
            assert response.json

        then: "json 裡有 success 屬性為 true"
            assert response.json.success

        then: "資料庫將有筆新增資料"
            assert Batch.list().size() == 1
            assert Batch.get(1)   
    }

    void "測試 update action，並且回傳為 json 格式"() {

        setup: "建立測試資料"
            def item = new Item(name: 'item').save(failOnError: true)
            def batch = new Batch(name: 'batch', item: item).save(failOnError: true)

        and: "前端傳入資料，定義 id 為測試資料的 id，並且修改屬性"
            params.id = batch.id
            params.name = "batchNewName"

        when: "執行 update action"
            controller.update()
        
        then: "response 要能取得 json 格式初始資料"
            assert response.json

        then: "json 裡有 success 屬性，且為 true"
            assert response.json.success

        then: "將有筆新增資料"
            assert Batch.list().size() == 1

        then: "修改後的屬性有正確寫入"
            assert Batch.get(1).name == 'batchNewName'
    }

    void "測試 delete action，並且回傳為 json 格式"() {

        setup: "建立測試資料"
            def item = new Item(name: 'item').save(failOnError: true)
            def batch = new Batch(name: 'batch', item: item).save(failOnError: true)
        
        and: "前端傳入資料，定義 id 為測試資料的 id"
            params.id = batch.id

        when: "執行 delete action"
            controller.delete()

        then: "response 要能取得 json 格式初始資料"
            assert response.json

        then: "json 裡有 success 屬性，且為 true"
            assert response.json.success        
 
        then: "該筆資料已移除"
            assert Batch.list().size() == 0
    }

}
