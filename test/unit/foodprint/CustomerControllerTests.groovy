package foodprint

import common.*
import org.junit.*
import grails.test.mixin.*

@TestFor(CustomerController)
@Mock([Customer, 
    DomainService, TestService])
class CustomerControllerTests {

    void setUp(){
        def testService = new TestService()
        testService.createTestMessage(messageSource)
    }

    def populateValidParams(params) {
        assert params != null
        params["name"] = 'customer'
        params["title"] = 'customer'
    }

    void testIndex() {
        populateValidParams(params)
        def customer = new Customer(params).save(failOnError: true)
        controller.index()

        assert response.json.data.size() == 1   
        assert response.json.total == 1   
        assert response.json.data[0].name == "customer"
    }

    void testShow(){
        populateValidParams(params)
        def customer = new Customer(params).save(failOnError: true)

        params.id = customer.id
        controller.show()

        assert response.json.success
        assert response.json.data.class == "foodprint.Customer"

    }

    void testCreate() {
        controller.create()
        assert response.json.success
        assert response.json.data.class == "foodprint.Customer"
    }

    void testSave(){
        populateValidParams(params)
        controller.save()

        assert response.json.success
        assert Customer.list().size() == 1
        assert Customer.get(1).name == 'customer'   
    }

    void testSaveWithCorrectTelData(){
        
        populateValidParams(params)
        params.tel = "02-29953221"

        controller.save()
        
        assert response.json.success
        assert Customer.list().size() == 1
        assert Customer.get(1).name == 'customer'
        assert Customer.get(1).title == 'customer'
        assert Customer.get(1).tel == '02-29953221'
    }
    void testSaveWithIncorrectTelData(){
        
        populateValidParams(params)
        params.tel = "abc02-29953221"

        controller.save()
        
        assert response.json.success == false
        assert Customer.list().size() == 0
    }

    void testSaveWithCorrectFaxData(){
        
        populateValidParams(params)
        params.fax = "02-29953221"

        controller.save()
        
        assert response.json.success
        assert Customer.list().size() == 1
        assert Customer.get(1).name == 'customer'
        assert Customer.get(1).title == 'customer'
        assert Customer.get(1).fax == '02-29953221'
    }
    void testSaveWithIncorrectFaxData(){
        
        populateValidParams(params)
        params.fax = "abc02-29953221"

        controller.save()
        
        assert response.json.success == false
        assert Customer.list().size() == 0
    }

    void testSaveWithCorrectEmailData(){
        
        populateValidParams(params)
        params.email = "aaa@yuntech.edu.tw"

        controller.save()
        
        assert response.json.success
        assert Customer.list().size() == 1
        assert Customer.get(1).name == 'customer'
        assert Customer.get(1).title == 'customer'
        assert Customer.get(1).email == 'aaa@yuntech.edu.tw'
    }
    void testSaveWithIncorrectEmailData(){
        
        populateValidParams(params)
        params.fax = "aaa.yuntech.edu.tw"

        controller.save()
        
        assert response.json.success == false
        assert Customer.list().size() == 0
    }

    void testUpdate(){
        populateValidParams(params)
        def customer = new Customer(params).save(failOnError: true)

        params.id = customer.id
        params.name = 'customerNewName'

        controller.update()
        
        assert response.json.success
        assert Customer.list().size() == 1
        assert Customer.get(1).name == 'customerNewName'
    }

    void testUpdateWithCorrectTelData(){
        populateValidParams(params)
        def customer = new Customer(params).save(failOnError: true)

        params.id = 1
        params.tel = "02-29953221"
        controller.update()
        
        assert response.json.success
        assert Customer.list().size() == 1
        assert Customer.get(1).name == 'customer'
        assert Customer.get(1).title == 'customer'
        assert Customer.get(1).tel == '02-29953221'
    }
    void testUpdateWithIncorrectTelData(){
        populateValidParams(params)
        def customer = new Customer(params).save(failOnError: true)

        params.id = 1
        params.tel = "abc02-29953221"
        controller.update()
        
        assert response.json.success == false
        assert Customer.list().size() == 1
        assert Customer.get(1).name == 'customer'
        assert Customer.get(1).title == 'customer'
        assert Customer.get(1).tel == null
    }

    void testUpdateWithCorrectFaxData(){
        populateValidParams(params)
        def customer = new Customer(params).save(failOnError: true)

        params.id = 1
        params.fax = "02-29953221"
        controller.update()
        
        assert response.json.success
        assert Customer.list().size() == 1
        assert Customer.get(1).name == 'customer'
        assert Customer.get(1).title == 'customer'
        assert Customer.get(1).fax == '02-29953221'
    }
    void testUpdateWithIncorrectFaxData(){
        populateValidParams(params)
        def customer = new Customer(params).save(failOnError: true)

        params.id = 1
        params.fax = "abc02-29953221"
        controller.update()
        
        assert response.json.success == false
        assert Customer.list().size() == 1
        assert Customer.get(1).name == 'customer'
        assert Customer.get(1).title == 'customer'
        assert Customer.get(1).fax == null
    }

    void testUpdateWithCorrectEmailData(){
        populateValidParams(params)
        def customer = new Customer(params).save(failOnError: true)

        params.id = 1
        params.email = "aaa@yuntech.edu.tw"
        controller.update()
        
        assert response.json.success
        assert Customer.list().size() == 1
        assert Customer.get(1).name == 'customer'
        assert Customer.get(1).title == 'customer'
        assert Customer.get(1).email == 'aaa@yuntech.edu.tw'
    }
    void testUpdateWithIncorrectEmailData(){
        populateValidParams(params)
        def customer = new Customer(params).save(failOnError: true)

        params.id = 1
        params.email = "aaa.yuntech.edu.tw"
        controller.update()
        
        assert response.json.success == false
        assert Customer.list().size() == 1
        assert Customer.get(1).name == 'customer'
        assert Customer.get(1).title == 'customer'
        assert Customer.get(1).email == null
    }

    void testDelete(){
        def customer = new Customer(name: 'customer', title: 'customer').save(failOnError: true)
        params.id = customer.id

        controller.delete()
        assert response.json.success
        assert Customer.list().size() == 0
    }

}
