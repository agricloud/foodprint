package foodprint

import common.*
import org.junit.*
import grails.test.mixin.*

@TestFor(UserController)
@Mock([User, Site, 
    DomainService, TestService])
class UserControllerTests {

    void setUp(){
        def testService = new TestService()
        testService.createTestMessage(messageSource)
        User.metaClass.encodePassword = {
            password = 'password'
        }
    }

    def populateValidParams(params) {
        assert params != null
        params["name"] = 'user'
        params["username"] = 'user'
        params["fullName"] = 'user'
        params["password"] = 'password'
    }

    void testIndex() {
        populateValidParams(params)
        def user = new User(params).save(failOnError: true)
        controller.index()

        assert response.json.data.size() == 1   
        assert response.json.total == 1   
        assert response.json.data[0].username == "user"

    }

    void testShow(){
        populateValidParams(params)
        def user = new User(params).save(failOnError: true)

        params.id = 1
        controller.show()

        assert response.json.success
        assert response.json.data.class == "foodprint.User"

    }

    void testCreate() {
        controller.create()
        assert response.json.success
        assert response.json.data.class == "foodprint.User"
    }

    // void testSave(){
    //     populateValidParams(params)
    //     controller.save()

    //     assert response.json.success
    //     assert User.list().size() == 1
    //     assert User.get(1).username == 'user'   
    // }

    // //測試非英文、數字之密碼
    // void testSaveWithInvalidPassword(){
    //     populateValidParams(params)
    //     params["password"] = '密碼123'
    //     controller.save()

    //     assert response.json.success == false
    //     assert User.list().size() == 0

    // }

    def testUpdate(){
        populateValidParams(params)
        def user = new User(params).save(failOnError: true)

        params.id = 1
        params.fullName = "newUser"
        controller.update()
        
        assert response.json.success
        assert User.list().size() == 1
        assert User.get(1).name == 'user'
        assert User.get(1).fullName == 'newUser'
    }

    //測試正確英文、數字之密碼
    void testUpdateWithValidPassword(){
        populateValidParams(params)
        def user = new User(params).save(failOnError: true)

        params.id = 1
        params["password"] = 'pass123'
        controller.update()

        assert response.json.success == true
        assert User.list().size() == 1
    }

    //測試非英文、數字之密碼
    void testUpdateWithInvalidPassword(){
        populateValidParams(params)
        def user = new User(params).save(failOnError: true)

        params.id = 1
        params["password"] = '密碼123'
        controller.update()

        assert response.json.success == false
        assert User.list().size() == 1
    }

    def testDelete(){
        populateValidParams(params)
        def user = new User(params).save(failOnError: true)
        params.id = 1

        controller.delete()
        assert response.json.success
        assert User.list().size() == 0
    }

}
