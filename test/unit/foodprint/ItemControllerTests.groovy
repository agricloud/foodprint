package foodprint

import common.*
import org.junit.*
import grails.test.mixin.*

@TestFor(ItemController)
@Mock([Item, 
    DomainService, TestService])
class ItemControllerTests {

    void setUp(){
        def testService = new TestService()
        testService.createTestMessage(messageSource)
        
    }

    def populateValidParams(params) {
        assert params != null
        params["name"] = 'item'
        params["title"] = 'item'
        params["unit"] = 'unit'
    }

    void testIndex() {

        populateValidParams(params)
        def item = new Item(params).save(failOnError: true)
        controller.index()

        assert response.json.data.size() == 1   
        assert response.json.total == 1   
        assert response.json.data[0].name == "item"
    }

    void testShow(){
        populateValidParams(params)
        def item = new Item(params).save(failOnError: true)

        params.id = 1
        controller.show()

        assert response.json.success
        assert response.json.data.class == "foodprint.Item"
        assert response.json.data.name == "item"

    }

    void testCreate() {
        controller.create()
        assert response.json.success
        assert response.json.data.class == "foodprint.Item"
    }

    void testSave(){
        populateValidParams(params)
        controller.save()

        assert response.json.success
        assert Item.list().size() == 1
        assert Item.get(1).name == 'item'
    }

    def testUpdate(){
        populateValidParams(params)
        def item = new Item(params).save(failOnError: true)

        params.id = 1
        params.title="newItem"

        controller.update()
        
        assert response.json.success
        assert Item.list().size() == 1
        assert Item.get(1).name == 'item'
        assert Item.get(1).title == 'newItem'
    }

    def testDelete(){
        populateValidParams(params)
        def item = new Item(params).save(failOnError: true)

        params.id = 1
        controller.delete()
           
        assert response.json.success
        assert Supplier.list().size() == 0
    }

}
