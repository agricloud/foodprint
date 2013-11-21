package foodprint

import common.*
import org.junit.*
import grails.test.mixin.*

@TestFor(SupplierController)
@Mock([Supplier, 
    DomainService, TestService])
class SupplierControllerTests {

    void setUp(){
        def testService = new TestService()
        testService.createTestMessage(messageSource)
    }

    def populateValidParams(params) {
        assert params != null
        params["name"] = 'supplierNewName'
        params["title"] = 'supplierNewName'
    }

    void testIndex() {
        new Supplier(name: 'supplier', title: 'supplier').save(failOnError: true)
        controller.index()

        assert response.json.supplierInstanceList.size() == 1   
        assert response.json.supplierInstanceTotal == 1   
        assert response.json.supplierInstanceList[0].name == "supplier"
    }

    void testShow(){
        def supplier = new Supplier(name: 'supplier', title: 'supplier').save(failOnError: true)

        params.id = supplier.id
        controller.show()

        assert response.json.success
        assert response.json.data.class == "foodprint.Supplier"

    }

    void testCreate() {
        controller.create()
        assert response.json.success
        assert response.json.data.class == "foodprint.Supplier"
    }

    void testSave(){
        populateValidParams(params)
        controller.save()

        assert response.json.success
        assert Supplier.list().size() == 1
        assert Supplier.get(1).name == 'supplierNewName'   
    }

    def testUpdate(){
        def supplier = new Supplier(name: 'supplier', title: 'supplier').save(failOnError: true)

        populateValidParams(params)
        params.id = supplier.id

        controller.update()
        
        assert response.json.success
        assert Supplier.list().size() == 1
        assert Supplier.get(1).name == 'supplierNewName'
    }

    def testDelete(){
        def supplier = new Supplier(name: 'supplier', title: 'supplier').save(failOnError: true)
        params.id = supplier.id

        controller.delete()
        assert response.json.success
        assert Supplier.list().size() == 0
    }

}
