package foodprint

import common.*
import org.junit.*
import grails.test.mixin.*
import spock.lang.Specification

@TestFor(WorkstationController)
@Mock([Workstation, 
    DomainService, TestService])
class WorkstationControllerSpec extends Specification {

    void setup(){
        def testService = new TestService()
        testService.createTestMessage(messageSource)
    }

    void "測試 index action，並且 response 為 json 格式"() {
        setup: "建立 Workstation 測試資料"
            new Workstation(name: 'workstation',title:'workstation').save(failOnError: true)

        when: "執行 WorkstationController 提供的 index action"
            controller.index()

        then: "response 要能取得 Workstation json 格式初始資料"
            assert response.json

        then: "json 裡有 workstationInstanceList 屬性，且有一筆資料 name 屬性為 workstation"
            assert response.json.data[0].name == "workstation"

        then: "json 裡有 workstationInstanceTotal 屬性為 1"
            assert response.json.total == 1   

    }

    void "測試 show action，並且 response 為 json 格式"() {

        setup: "建立測試資料"
            def workstation = new Workstation(name: 'workstation',title:'workstation').save(failOnError: true)

        and: "前端傳入資料，定義 id 為測試資料的 id"
            params.id = workstation.id

        when: "執行 WorkstationController 提供的 show action"
            controller.show()

        then: "response 要能取得 Workstation json 格式初始資料"
            assert response.json

        then: "json 裡有 success 屬性，且為 true"
            assert response.json.success

        then: "json 裡有 data.class 屬性為 foodprint.Workstation"
            assert response.json.data.class == "foodprint.Workstation"

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
            params["name"] = 'workstation'
            params["title"] = 'workstation'

        when: "執行 save action"
            controller.save()

        then: "response 要能取得 json 格式初始資料"
            assert response.json

        then: "json 裡有 success 屬性為 true"
            assert response.json.success

        then: "資料庫將有筆新增資料"
            assert Workstation.list().size() == 1
            assert Workstation.get(1)   
    }

    void "測試 update action，並且回傳為 json 格式"() {

        setup: "建立測試資料"
            def workstation = new Workstation(name: 'workstation',title:'workstation').save(failOnError: true)

        and: "前端傳入資料，定義 id 為測試資料的 id，並且修改屬性"
            params.id = workstation.id
            params.name = "workstationNewName"

        when: "執行 update action"
            controller.update()
        
        then: "response 要能取得 json 格式初始資料"
            assert response.json

        then: "json 裡有 success 屬性，且為 true"
            assert response.json.success

        then: "將有筆新增資料"
            assert Workstation.list().size() == 1

        then: "修改後的屬性有正確寫入"
            assert Workstation.get(1).name == 'workstationNewName'
    }

    void "測試 delete action，並且回傳為 json 格式"() {

        setup: "建立測試資料"
            def workstation = new Workstation(name: 'workstation',title:'workstation').save(failOnError: true)
        
        and: "前端傳入資料，定義 id 為測試資料的 id"
            params.id = workstation.id

        when: "執行 delete action"
            controller.delete()

        then: "response 要能取得 json 格式初始資料"
            assert response.json

        then: "json 裡有 success 屬性，且為 true"
            assert response.json.success        
 
        then: "該筆資料已移除"
            assert Workstation.list().size() == 0
    }

}
