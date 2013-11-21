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
        params["name"] = 'itemNewName'
    }

    void testIndex() {
        new Item(name: 'item').save(failOnError: true)
        controller.index()

        assert response.json.itemInstanceList.size() == 1   
        assert response.json.itemInstanceTotal == 1   
        assert response.json.itemInstanceList[0].name == "item"
    }

    void testShow(){
        def item = new Item(name: 'item').save(failOnError: true)

        params.id = item.id
        controller.show()

        assert response.json.success
        assert response.json.data.class == "foodprint.Item"

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
        assert Item.get(1).name == 'itemNewName'   
    }

    def testUpdate(){
        def item = new Item(name: 'item').save(failOnError: true)

        populateValidParams(params)
        params.id = item.id

        controller.update()
        
        assert response.json.success
        assert Item.list().size() == 1
        assert Item.get(1).name == 'itemNewName'
    }

	def testDelete(){
		def item = new Item(name: 'item').save(failOnError: true)
		params.id = item.id

		controller.delete()
        assert response.json.success
        assert Item.list().size() == 0

	}

}
