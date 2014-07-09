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
        params["name"] = 'supplier'
        params["title"] = 'supplier'
    }

    void testIndex() {
        populateValidParams(params)
        def supplier = new Supplier(params).save(failOnError: true)
        controller.index()

        assert response.json.data.size() == 1   
        assert response.json.total == 1   
        assert response.json.data[0].name == "supplier"
    }

    void testShow(){
        populateValidParams(params)
        def supplier = new Supplier(params).save(failOnError: true)

        params.id = 1
        controller.show()

        assert response.json.success
        assert response.json.data.class == "foodprint.Supplier"
        assert response.json.data.name == "supplier"

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
        assert Supplier.get(1).name == 'supplier'   
    }

    void testSaveWithCorrectTelData(){
        params.tel = "02-29953221"
        populateValidParams(params)

        controller.save()
        
        assert response.json.success
        assert Supplier.list().size() == 1
        assert Supplier.get(1).name == 'supplier'
        assert Supplier.get(1).title == 'supplier'
        assert Supplier.get(1).tel == '02-29953221'
    }
    void testSaveWithIncorrectTelData(){
        params.tel = "abc02-29953221"
        populateValidParams(params)

        controller.save()
        
        println Supplier.list()
        assert response.json.success == false
        assert Supplier.list().size() == 0
    }

    void testSaveWithCorrectFaxData(){
        params.fax = "02-29953221"
        populateValidParams(params)

        controller.save()
        
        assert response.json.success
        assert Supplier.list().size() == 1
        assert Supplier.get(1).name == 'supplier'
        assert Supplier.get(1).title == 'supplier'
        assert Supplier.get(1).fax == '02-29953221'
    }

    void testSaveWithIncorrectFaxData(){
        params.fax = "abc02-29953221"
        populateValidParams(params)

        controller.save()
        
        assertFalse response.json.success
        assert Supplier.list().size() == 0
    }

    void testSaveWithCorrectEmailData(){
        params.email = "aaa@yuntech.edu.tw"
        populateValidParams(params)

        controller.save()
        
        assert response.json.success
        assert Supplier.list().size() == 1
        assert Supplier.get(1).name == 'supplier'
        assert Supplier.get(1).title == 'supplier'
        assert Supplier.get(1).email == 'aaa@yuntech.edu.tw'
    }
    void testSaveWithIncorrectEmailData(){
        params.fax = "aaa.yuntech.edu.tw"
        populateValidParams(params)

        controller.save()
        
        assertFalse response.json.success
        assert Supplier.list().size() == 0
    }

    void testUpdate(){
        populateValidParams(params)
        def supplier = new Supplier(params).save(failOnError: true)

        params.id = 1
        params.title = "newSupplier"
        controller.update()
        
        assert response.json.success
        assert Supplier.list().size() == 1
        assert Supplier.get(1).name == 'supplier'
        assert Supplier.get(1).title == 'newSupplier'
    }

    void testUpdateWithCorrectTelData(){
        populateValidParams(params)
        def supplier = new Supplier(params).save(failOnError: true)

        params.id = 1
        params.tel = "02-29953221"
        controller.update()
        
        assert response.json.success
        assert Supplier.list().size() == 1
        assert Supplier.get(1).name == 'supplier'
        assert Supplier.get(1).title == 'supplier'
        assert Supplier.get(1).tel == '02-29953221'
    }
    void testUpdateWithIncorrectTelData(){
        populateValidParams(params)
        def supplier = new Supplier(params).save(failOnError: true)

        params.id = 1
        params.tel = "abc02-29953221"
        controller.update()
        
        assertFalse response.json.success
        assert Supplier.list().size() == 1
        assert Supplier.get(1).name == 'supplier'
        assert Supplier.get(1).title == 'supplier'
        assert Supplier.get(1).tel == null
    }

    void testUpdateWithCorrectFaxData(){
        populateValidParams(params)
        def supplier = new Supplier(params).save(failOnError: true)

        params.id = 1
        params.fax = "02-29953221"
        controller.update()
        
        assert response.json.success
        assert Supplier.list().size() == 1
        assert Supplier.get(1).name == 'supplier'
        assert Supplier.get(1).title == 'supplier'
        assert Supplier.get(1).fax == '02-29953221'
    }
    void testUpdateWithIncorrectFaxData(){
        populateValidParams(params)
        def supplier = new Supplier(params).save(failOnError: true)

        params.id = 1
        params.fax = "abc02-29953221"
        controller.update()
        
        assertFalse response.json.success
        assert Supplier.list().size() == 1
        assert Supplier.get(1).name == 'supplier'
        assert Supplier.get(1).title == 'supplier'
        assert Supplier.get(1).fax == null
    }


    void testUpdateWithCorrectEmailData(){
        populateValidParams(params)
        def supplier = new Supplier(params).save(failOnError: true)

        params.id = 1
        params.email = "aaa@yuntech.edu.tw"
        controller.update()
        
        assert response.json.success
        assert Supplier.list().size() == 1
        assert Supplier.get(1).name == 'supplier'
        assert Supplier.get(1).title == 'supplier'
        assert Supplier.get(1).email == 'aaa@yuntech.edu.tw'
    }
    void testUpdateWithIncorrectEmailData(){
        populateValidParams(params)
        def supplier = new Supplier(params).save(failOnError: true)

        params.id = 1
        params.email = "aaa.yuntech.edu.tw"
        controller.update()
        
        assertFalse response.json.success
        assert Supplier.list().size() == 1
        assert Supplier.get(1).name == 'supplier'
        assert Supplier.get(1).title == 'supplier'
        assert Supplier.get(1).email == null
    }

    void testDelete(){
        populateValidParams(params)
        def supplier = new Supplier(params).save(failOnError: true)
        
        params.id = 1
        controller.delete()
        assert response.json.success
        assert Supplier.list().size() == 0
    }

}
