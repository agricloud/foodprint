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
        params["name"] = 'userNewName'
        params["username"] = 'userNewName'
        params["password"] = 'password'
    }

    void testIndex() {
        new User(name: 'user', username: 'user', password:'user').save(failOnError: true)
        controller.index()

        assert response.json.data.size() == 1   
        assert response.json.total == 1   
        assert response.json.data[0].username == "user"
    }

    void testShow(){
        def user = new User(name: 'user', username: 'user', password:'user').save(failOnError: true)

        params.id = user.id
        controller.show()

        assert response.json.success
        assert response.json.data.class == "foodprint.User"

    }

    void testCreate() {
        controller.create()
        assert response.json.success
        assert response.json.data.class == "foodprint.User"
    }

    void testSave(){
        populateValidParams(params)
        controller.save()

        assert response.json.success
        assert User.list().size() == 1
        assert User.get(1).username == 'userNewName'   
    }

    //測試非英文、數字之密碼
    void testSaveWithInvalidPassword(){
        populateValidParams(params)
        params["password"] = '密碼123'
        controller.save()

        assert response.json.success == false
        assert User.list().size() == 0

    }

    def testUpdate(){
        def user = new User(name: 'user', username: 'user', password:'user').save(failOnError: true)

        populateValidParams(params)
        params.id = user.id

        controller.update()
        
        assert response.json.success
        assert User.list().size() == 1
        assert User.get(1).username == 'userNewName'
    }

    //測試非英文、數字之密碼
    void testUpdateWithInvalidPassword(){
        def user = new User(name: 'user', username: 'user', password:'user').save(failOnError: true)
        populateValidParams(params)
        params.id = user.id
        params["password"] = '密碼123'
        controller.save()

        assert response.json.success == false
        assert User.list().size() == 1
    }

    def testDelete(){
        def user = new User(name: 'user', username: 'user', password:'user').save(failOnError: true)
        params.id = user.id

        controller.delete()
        assert response.json.success
        assert User.list().size() == 0
    }

}
