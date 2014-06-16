package foodprint

import common.*
import org.junit.*
import grails.test.mixin.*
import spock.lang.Specification

@TestFor(SupplierController)
@Mock([Supplier, 
    DomainService, TestService])
class SupplierControllerSpec extends Specification {

    void setup(){
        def testService = new TestService()
        testService.createTestMessage(messageSource)
    }

    void "測試 index action，並且 response 為 json 格式"() {
        setup: "建立 Supplier 測試資料"
            new Supplier(name: 'supplier',title:'supplier').save(failOnError: true)

        when: "執行 SupplierController 提供的 index action"
            controller.index()

        then: "response 要能取得 Supplier json 格式初始資料"
            assert response.json

        then: "json 裡有 supplierInstanceList 屬性，且有一筆資料 name 屬性為 supplier"
            assert response.json.data[0].name == "supplier"

        then: "json 裡有 supplierInstanceTotal 屬性為 1"
            assert response.json.total == 1   

    }

    void "測試 show action，並且 response 為 json 格式"() {

        setup: "建立測試資料"
            def supplier = new Supplier(name: 'supplier',title:'supplier').save(failOnError: true)

        and: "前端傳入資料，定義 id 為測試資料的 id"
            params.id = supplier.id

        when: "執行 SupplierController 提供的 show action"
            controller.show()

        then: "response 要能取得 Supplier json 格式初始資料"
            assert response.json

        then: "json 裡有 success 屬性，且為 true"
            assert response.json.success

        then: "json 裡有 data.class 屬性為 foodprint.Supplier"
            assert response.json.data.class == "foodprint.Supplier"

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
            params["name"] = 'supplier'
            params["title"] = 'supplier'

        when: "執行 save action"
            controller.save()

        then: "response 要能取得 json 格式初始資料"
            assert response.json

        then: "json 裡有 success 屬性為 true"
            assert response.json.success

        then: "資料庫將有筆新增資料"
            assert Supplier.list().size() == 1
            assert Supplier.get(1)   
    }

    void "測試 update action，並且回傳為 json 格式"() {

        setup: "建立測試資料"
            def supplier = new Supplier(name: 'supplier',title:'supplier').save(failOnError: true)

        and: "前端傳入資料，定義 id 為測試資料的 id，並且修改屬性"
            params.id = supplier.id
            params.name = "supplierNewName"

        when: "執行 update action"
            controller.update()
        
        then: "response 要能取得 json 格式初始資料"
            assert response.json

        then: "json 裡有 success 屬性，且為 true"
            assert response.json.success

        then: "將有筆新增資料"
            assert Supplier.list().size() == 1

        then: "修改後的屬性有正確寫入"
            assert Supplier.get(1).name == 'supplierNewName'
    }

    void "測試 delete action，並且回傳為 json 格式"() {

        setup: "建立測試資料"
            def supplier = new Supplier(name: 'supplier',title:'supplier').save(failOnError: true)
        
        and: "前端傳入資料，定義 id 為測試資料的 id"
            params.id = supplier.id

        when: "執行 delete action"
            controller.delete()

        then: "response 要能取得 json 格式初始資料"
            assert response.json

        then: "json 裡有 success 屬性，且為 true"
            assert response.json.success        
 
        then: "該筆資料已移除"
            assert Supplier.list().size() == 0
    }

}
