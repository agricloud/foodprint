package foodprint

import common.*
import org.junit.*
import grails.test.mixin.*

@TestFor(WorkstationController)
@Mock([Workstation, 
    DomainService, TestService])
class WorkstationControllerTests {

    void setUp(){
        def testService = new TestService()
        testService.createTestMessage(messageSource)
        
    }

    def populateValidParams(params) {
        assert params != null
        params["name"] = 'workstation'
        params["title"] = 'workstation'
    }

    void testIndex() {

        populateValidParams(params)
        def workstation = new Workstation(params).save(failOnError: true)
        controller.index()

        assert response.json.data.size() == 1   
        assert response.json.total == 1   
        assert response.json.data[0].name == "workstation"
    }

    void testShow(){
        populateValidParams(params)
        def workstation = new Workstation(params).save(failOnError: true)

        params.id = 1
        controller.show()

        assert response.json.success
        assert response.json.data.class == "foodprint.Workstation"
        assert response.json.data.name == "workstation"

    }

    void testCreate() {
        controller.create()
        assert response.json.success
        assert response.json.data.class == "foodprint.Workstation"
    }

    void testSave(){
        populateValidParams(params)
        controller.save()

        assert response.json.success
        assert Workstation.list().size() == 1
        assert Workstation.get(1).name == 'workstation'
    }

    void testUpdate(){
        populateValidParams(params)
        def workstation = new Workstation(params).save(failOnError: true)

        params.id = 1
        params.title="newWorkstation"

        controller.update()
        
        assert response.json.success
        assert Workstation.list().size() == 1
        assert Workstation.get(1).name == 'workstation'
        assert Workstation.get(1).title == 'newWorkstation'
    }

    void testDelete(){
        populateValidParams(params)
        def workstation = new Workstation(params).save(failOnError: true)

        params.id = 1
        controller.delete()
           
        assert response.json.success
        assert Workstation.list().size() == 0
    }

}
