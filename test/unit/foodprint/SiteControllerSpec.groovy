package foodprint

import common.*
import org.junit.*
import grails.test.mixin.*
import spock.lang.Specification

@TestFor(SiteController)
@Mock([Site, 
    DomainService, TestService])
class SiteControllerSpec extends Specification {

    void setup(){
        def testService = new TestService()
        testService.createTestMessage(messageSource)
    }

    void "測試 index action，並且 response 為 json 格式"() {
        setup: "建立 Site 測試資料"
            new Site(name: 'site', title:"創毅").save(failOnError: true)

        when: "執行 SiteController 提供的 index action"
            controller.index()

        then: "response 要能取得 Site json 格式初始資料"
            assert response.json

        then: "json 裡有 siteInstanceList 屬性，且有一筆資料 name 屬性為 site"
            assert response.json.siteInstanceList[0].name == "site"

        then: "json 裡有 siteInstanceTotal 屬性為 1"
            assert response.json.siteInstanceTotal == 1   

    }

    void "測試 show action，並且 response 為 json 格式"() {

        setup: "建立測試資料"
            def site = new Site(name: 'site', title:"創毅").save(failOnError: true)

        and: "前端傳入資料，定義 id 為測試資料的 id"
            params.id = site.id

        when: "執行 SiteController 提供的 show action"
            controller.show()

        then: "response 要能取得 Site json 格式初始資料"
            assert response.json

        then: "json 裡有 success 屬性，且為 true"
            assert response.json.success

        then: "json 裡有 data.class 屬性為 foodprint.Site"
            assert response.json.data.class == "foodprint.Site"

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
            params["name"] = 'site'

        when: "執行 save action"
            controller.save()

        then: "response 要能取得 json 格式初始資料"
            assert response.json

        then: "json 裡有 success 屬性為 true"
            assert response.json.success

        then: "資料庫將有筆新增資料"
            assert Site.list().size() == 1
            assert Site.get(1)   
    }

    void "測試 update action，並且回傳為 json 格式"() {

        setup: "建立測試資料"
            def site = new Site(name: 'site', title:"創毅").save(failOnError: true)

        and: "前端傳入資料，定義 id 為測試資料的 id，並且修改屬性"
            params.id = site.id
            params.name = "siteNewName"

        when: "執行 update action"
            controller.update()
        
        then: "response 要能取得 json 格式初始資料"
            assert response.json

        then: "json 裡有 success 屬性，且為 true"
            assert response.json.success

        then: "將有筆新增資料"
            assert Site.list().size() == 1

        then: "修改後的屬性有正確寫入"
            assert Site.get(1).name == 'siteNewName'
    }

    void "測試 delete action，並且回傳為 json 格式"() {

        setup: "建立測試資料"
            def site = new Site(name: 'site', title:"創毅").save(failOnError: true)
        
        and: "前端傳入資料，定義 id 為測試資料的 id"
            params.id = site.id

        when: "執行 delete action"
            controller.delete()

        then: "response 要能取得 json 格式初始資料"
            assert response.json

        then: "json 裡有 success 屬性，且為 true"
            assert response.json.success        
 
        then: "該筆資料已移除"
            assert Site.list().size() == 0
    }

}
