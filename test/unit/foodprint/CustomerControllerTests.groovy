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
        params["name"] = 'customerNewName'
        params["title"] = 'customerNewName'
    }

    void testIndex() {
        new Customer(name: 'customer', title: 'customer').save(failOnError: true)
        controller.index()

        assert response.json.customerInstanceList.size() == 1   
        assert response.json.customerInstanceTotal == 1   
        assert response.json.customerInstanceList[0].name == "customer"
    }

    void testShow(){
        def customer = new Customer(name: 'customer', title: 'customer').save(failOnError: true)

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
        assert Customer.get(1).name == 'customerNewName'   
    }

    def testUpdate(){
        def customer = new Customer(name: 'customer', title: 'customer').save(failOnError: true)

        populateValidParams(params)
        params.id = customer.id

        controller.update()
        
        assert response.json.success
        assert Customer.list().size() == 1
        assert Customer.get(1).name == 'customerNewName'
    }

    def testDelete(){
        def customer = new Customer(name: 'customer', title: 'customer').save(failOnError: true)
        params.id = customer.id

        controller.delete()
        assert response.json.success
        assert Customer.list().size() == 0
    }

}
