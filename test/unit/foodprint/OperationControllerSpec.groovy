package foodprint

import common.*
import org.junit.*
import grails.test.mixin.*
import spock.lang.Specification

@TestFor(OperationController)
@Mock([Operation, 
    DomainService, TestService])
class OperationControllerSpec extends Specification {

    void setup(){
        def testService = new TestService()
        testService.createTestMessage(messageSource)
    }

    void "測試 index action，並且 response 為 json 格式"() {
        setup: "建立 Operation 測試資料"
            new Operation(name: 'operation',title:'施肥').save(failOnError: true)

        when: "執行 OperationController 提供的 index action"
            controller.index()

        then: "response 要能取得 Operation json 格式初始資料"
            assert response.json

        then: "json 裡有 operationInstanceList 屬性，且有一筆資料 name 屬性為 operation"
            assert response.json.data[0].name == "operation"

        then: "json 裡有 operationInstanceTotal 屬性為 1"
            assert response.json.total == 1   

    }

    void "測試 show action，並且 response 為 json 格式"() {

        setup: "建立測試資料"
            def operation = new Operation(name: 'operation',title:'施肥').save(failOnError: true)

        and: "前端傳入資料，定義 id 為測試資料的 id"
            params.id = operation.id

        when: "執行 OperationController 提供的 show action"
            controller.show()

        then: "response 要能取得 Operation json 格式初始資料"
            assert response.json

        then: "json 裡有 success 屬性，且為 true"
            assert response.json.success

        then: "json 裡有 data.class 屬性為 foodprint.Operation"
            assert response.json.data.class == "foodprint.Operation"

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
            params["name"] = 'operation'
            params["title"] = 'title'

        when: "執行 save action"
            controller.save()

        then: "response 要能取得 json 格式初始資料"
            assert response.json

        then: "json 裡有 success 屬性為 true"
            assert response.json.success

        then: "資料庫將有筆新增資料"
            assert Operation.list().size() == 1
            assert Operation.get(1)   
    }

    void "測試 update action，並且回傳為 json 格式"() {

        setup: "建立測試資料"
            def operation = new Operation(name: 'operation',title:'施肥').save(failOnError: true)

        and: "前端傳入資料，定義 id 為測試資料的 id，並且修改屬性"
            params.id = operation.id
            params.name = "operationNewName"

        when: "執行 update action"
            controller.update()
        
        then: "response 要能取得 json 格式初始資料"
            assert response.json

        then: "json 裡有 success 屬性，且為 true"
            assert response.json.success

        then: "將有筆新增資料"
            assert Operation.list().size() == 1

        then: "修改後的屬性有正確寫入"
            assert Operation.get(1).name == 'operationNewName'
    }

    void "測試 delete action，並且回傳為 json 格式"() {

        setup: "建立測試資料"
            def operation = new Operation(name: 'operation',title:'施肥').save(failOnError: true)
        
        and: "前端傳入資料，定義 id 為測試資料的 id"
            params.id = operation.id

        when: "執行 delete action"
            controller.delete()

        then: "response 要能取得 json 格式初始資料"
            assert response.json

        then: "json 裡有 success 屬性，且為 true"
            assert response.json.success        
 
        then: "該筆資料已移除"
            assert Operation.list().size() == 0
    }

}
