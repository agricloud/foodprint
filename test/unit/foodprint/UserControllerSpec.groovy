package foodprint

import common.*
import org.junit.*
import grails.test.mixin.*
import spock.lang.Specification

@TestFor(UserController)
@Mock([User, Site,
    DomainService, TestService])
class UserControllerSpec extends Specification {

    void setup(){
        def testService = new TestService()
        testService.createTestMessage(messageSource)
        User.metaClass.encodePassword = {
            password = 'password'
        }
    }

    void "測試 index action，並且 response 為 json 格式"() {
        setup: "建立 User 測試資料"
            new User(username: 'user', password:'user', fullName:'user').save(failOnError: true)

        when: "執行 UserController 提供的 index action"
            controller.index()

        then: "response 要能取得 User json 格式初始資料"
            assert response.json

        then: "json 裡有 userInstanceList 屬性，且有一筆資料 name 屬性為 user"
            assert response.json.data[0].username == "user"

        then: "json 裡有 userInstanceTotal 屬性為 1"
            assert response.json.total == 1   

    }

    void "測試 show action，並且 response 為 json 格式"() {

        setup: "建立測試資料"
            def user = new User(username: 'user', password:'user', fullName:'user').save(failOnError: true)

        and: "前端傳入資料，定義 id 為測試資料的 id"
            params.id = user.id

        when: "執行 UserController 提供的 show action"
            controller.show()

        then: "response 要能取得 User json 格式初始資料"
            assert response.json

        then: "json 裡有 success 屬性，且為 true"
            assert response.json.success

        then: "json 裡有 data.class 屬性為 foodprint.User"
            assert response.json.data.class == "foodprint.User"

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
            params["username"] = 'user'
            params["password"] = 'user'
            params["fullName"] = 'user'

        when: "執行 save action"  
            controller.save()

        then: "response 要能取得 json 格式初始資料"
            assert response.json

        then: "json 裡有 success 屬性為 true"
            assert response.json.success

        then: "資料庫將有筆新增資料"
            assert User.list().size() == 1
            assert User.get(1)   
    }

    void "測試 update action，並且回傳為 json 格式"() {

        setup: "建立測試資料"
            def user = new User(username: 'user', password:'user', fullName:'user').save(failOnError: true)

        and: "前端傳入資料，定義 id 為測試資料的 id，並且修改屬性"
            params.id = user.id
            params.username = "userNewName"

        when: "執行 update action"
            controller.update()
        
        then: "response 要能取得 json 格式初始資料"
            assert response.json

        then: "json 裡有 success 屬性，且為 true"
            assert response.json.success

        then: "將有筆新增資料"
            assert User.list().size() == 1

        then: "修改後的屬性有正確寫入"
            assert User.get(1).username == 'userNewName'
    }

    void "測試 delete action，並且回傳為 json 格式"() {

        setup: "建立測試資料"
            def user = new User(username: 'user', password:'user', fullName:'user').save(failOnError: true)
        
        and: "前端傳入資料，定義 id 為測試資料的 id"
            params.id = user.id

        when: "執行 delete action"
            controller.delete()

        then: "response 要能取得 json 格式初始資料"
            assert response.json

        then: "json 裡有 success 屬性，且為 true"
            assert response.json.success        
 
        then: "該筆資料已移除"
            assert User.list().size() == 0
    }

}
