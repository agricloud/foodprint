package foodprint

import common.*
import org.junit.*
import grails.test.mixin.*

@TestFor(OperationController)
@Mock([Operation, 
    DomainService, TestService])
class OperationControllerTests {

    void setUp(){
        def testService = new TestService()
        testService.createTestMessage(messageSource)
        
    }

    def populateValidParams(params) {
        assert params != null
        params["name"] = 'operation'
        params["title"] = 'operation'
    }

    void testIndex() {

        populateValidParams(params)
        def operation = new Operation(params).save(failOnError: true)
        controller.index()

        assert response.json.data.size() == 1   
        assert response.json.total == 1   
        assert response.json.data[0].name == "operation"
    }

    void testShow(){
        populateValidParams(params)
        def operation = new Operation(params).save(failOnError: true)

        params.id = 1
        controller.show()

        assert response.json.success
        assert response.json.data.class == "foodprint.Operation"
        assert response.json.data.name == "operation"

    }

    void testCreate() {
        controller.create()
        assert response.json.success
        assert response.json.data.class == "foodprint.Operation"
    }

    void testSave(){
        populateValidParams(params)
        controller.save()

        assert response.json.success
        assert Operation.list().size() == 1
        assert Operation.get(1).name == 'operation'
    }

    void testUpdate(){
        populateValidParams(params)
        def operation = new Operation(params).save(failOnError: true)

        params.id = 1
        params.title="newOperation"

        controller.update()
        
        assert response.json.success
        assert Operation.list().size() == 1
        assert Operation.get(1).name == 'operation'
        assert Operation.get(1).title == 'newOperation'
    }

    void testDelete(){
        populateValidParams(params)
        def operation = new Operation(params).save(failOnError: true)

        params.id = 1
        controller.delete()
           
        assert response.json.success
        assert Operation.list().size() == 0
    }

}
