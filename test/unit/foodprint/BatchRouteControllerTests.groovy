package foodprint

import common.*
import org.junit.*
import grails.test.mixin.*

@TestFor(BatchRouteController)
@Mock([Item, Batch, BatchRoute, Workstation, Operation,
       DomainService, TestService])
class BatchRouteControllerTests {

    void setUp(){
        def testService = new TestService()
        testService.createTestMessage(messageSource)

        def item1 = new Item(name:"item1",title:"item1",unit:"kg").save(failOnError: true, flush: true)
        def batch1 = new Batch(name:"batch1", item:item1).save(failOnError: true, flush: true)
        def workstation = new Workstation(name:"workstation1",title:"workstation1").save(failOnError: true)
        def operation = new Operation(name:"operation1",title:"operation1").save(failOnError: true)

    }

    def populateValidParams(params) {
        assert params != null
        params["batch.id"] = 1
        params["sequence"] = 1
        params["item.id"] = 1
        params["workstation.id"] = 1
        params["operation.id"] = 1
    }

    void testIndex() {

        populateValidParams(params)
        def batchRoute = new BatchRoute(params).save(failOnError: true)
        controller.index()

        assert response.json.data.size() == 1   
        assert response.json.total == 1  
        assert response.json.data[0].batch.id == 1
        assert response.json.data[0].sequence == 1 
    }

    void testShow(){
        populateValidParams(params)
        def batchRoute = new BatchRoute(params).save(failOnError: true)

        params.id = 1
        controller.show()

        assert response.json.success
        assert response.json.data.class == "foodprint.BatchRoute"
        assert response.json.data.batch.id == 1
        assert response.json.data.sequence == 1

    }

    void testCreate() {
        params["batch.id"]=1
        controller.create()
        assert response.json.success
        assert response.json.data.class == "foodprint.BatchRoute"
    }

    void testSave(){
        populateValidParams(params)
        controller.save()

        assert response.json.success
        assert BatchRoute.list().size() == 1
        assert BatchRoute.get(1).batch.id == 1
        assert BatchRoute.get(1).sequence == 1
    }

    void testUpdate(){
        populateValidParams(params)
        def batchRoute = new BatchRoute(params).save(failOnError: true)
        def operation2 = new Operation(name:"operation2",title:"operation2").save(failOnError: true)

        params.id = 1
        params.operation.id=2

        controller.update()
        
        assert response.json.success
        assert BatchRoute.list().size() == 1
        assert BatchRoute.get(1).batch.id == 1
        assert BatchRoute.get(1).sequence == 1
        assert BatchRoute.get(1).operation.id == 2
    }

    void testDelete(){
        populateValidParams(params)
        def batchRoute = new BatchRoute(params).save(failOnError: true)

        params.id = 1
        controller.delete()
           
        assert response.json.success
        assert BatchRoute.list().size() == 0
    }

}
