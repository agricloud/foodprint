package foodprint

import common.*
import org.junit.*
import grails.test.mixin.*
import spock.lang.Specification

@TestFor(ItemController)
@Mock([Item, 
    DomainService, TestService])
class ItemControllerSpec extends Specification {

    void setup(){
        def testService = new TestService()
        testService.createTestMessage(messageSource)
    }

    void "測試 index action，並且 response 為 json 格式"() {
        setup: "建立 Item 測試資料"
            new Item(name: 'item', title: 'item').save(failOnError: true)

        when: "執行 ItemController 提供的 index action"
            controller.index()

        then: "response 要能取得 Item json 格式初始資料"
            assert response.json

        then: "json 裡有 itemInstanceList 屬性，且有一筆資料 name 屬性為 item"
            assert response.json.itemInstanceList[0].name == "item"

        then: "json 裡有 itemInstanceTotal 屬性為 1"
            assert response.json.itemInstanceTotal == 1   

    }

    void "測試 show action，並且 response 為 json 格式"() {

        setup: "建立測試資料"
            def item = new Item(name: 'item', title: 'item').save(failOnError: true)

        and: "前端傳入資料，定義 id 為測試資料的 id"
            params.id = item.id

        when: "執行 ItemController 提供的 show action"
            controller.show()

        then: "response 要能取得 Item json 格式初始資料"
            assert response.json

        then: "json 裡有 success 屬性，且為 true"
            assert response.json.success

        then: "json 裡有 data.class 屬性為 foodprint.Item"
            assert response.json.data.class == "foodprint.Item"

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
            params["name"] = 'item'
            params["title"] = 'item'

        when: "執行 save action"
            controller.save()

        then: "response 要能取得 json 格式初始資料"
            assert response.json

        then: "json 裡有 success 屬性為 true"
            assert response.json.success

        then: "資料庫將有筆新增資料"
            assert Item.list().size() == 1
            assert Item.get(1)   
    }

    void "測試 update action，並且回傳為 json 格式"() {

        setup: "建立測試資料"
            def item = new Item(name: 'item', title: 'item').save(failOnError: true)

        and: "前端傳入資料，定義 id 為測試資料的 id，並且修改屬性"
            params.id = item.id
            params.name = "itemNewName"

        when: "執行 update action"
            controller.update()
        
        then: "response 要能取得 json 格式初始資料"
            assert response.json

        then: "json 裡有 success 屬性，且為 true"
            assert response.json.success

        then: "將有筆新增資料"
            assert Item.list().size() == 1

        then: "修改後的屬性有正確寫入"
            assert Item.get(1).name == 'itemNewName'
    }

    void "測試 delete action，並且回傳為 json 格式"() {

        setup: "建立測試資料"
            def item = new Item(name: 'item', title: 'item').save(failOnError: true)
        
        and: "前端傳入資料，定義 id 為測試資料的 id"
            params.id = item.id

        when: "執行 delete action"
            controller.delete()

        then: "response 要能取得 json 格式初始資料"
            assert response.json

        then: "json 裡有 success 屬性，且為 true"
            assert response.json.success        
 
        then: "該筆資料已移除"
            assert Item.list().size() == 0
    }

}
