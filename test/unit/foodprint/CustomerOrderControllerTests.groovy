package foodprint



import org.junit.*
import grails.test.mixin.*

@TestFor(CustomerOrderController)
@Mock(CustomerOrder)
class CustomerOrderControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/customerOrder/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.customerOrderInstanceList.size() == 0
        assert model.customerOrderInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.customerOrderInstance != null
    }

    void testSave() {
        controller.save()

        assert model.customerOrderInstance != null
        assert view == '/customerOrder/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/customerOrder/show/1'
        assert controller.flash.message != null
        assert CustomerOrder.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/customerOrder/list'

        populateValidParams(params)
        def customerOrder = new CustomerOrder(params)

        assert customerOrder.save() != null

        params.id = customerOrder.id

        def model = controller.show()

        assert model.customerOrderInstance == customerOrder
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/customerOrder/list'

        populateValidParams(params)
        def customerOrder = new CustomerOrder(params)

        assert customerOrder.save() != null

        params.id = customerOrder.id

        def model = controller.edit()

        assert model.customerOrderInstance == customerOrder
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/customerOrder/list'

        response.reset()

        populateValidParams(params)
        def customerOrder = new CustomerOrder(params)

        assert customerOrder.save() != null

        // test invalid parameters in update
        params.id = customerOrder.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/customerOrder/edit"
        assert model.customerOrderInstance != null

        customerOrder.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/customerOrder/show/$customerOrder.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        customerOrder.clearErrors()

        populateValidParams(params)
        params.id = customerOrder.id
        params.version = -1
        controller.update()

        assert view == "/customerOrder/edit"
        assert model.customerOrderInstance != null
        assert model.customerOrderInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/customerOrder/list'

        response.reset()

        populateValidParams(params)
        def customerOrder = new CustomerOrder(params)

        assert customerOrder.save() != null
        assert CustomerOrder.count() == 1

        params.id = customerOrder.id

        controller.delete()

        assert CustomerOrder.count() == 0
        assert CustomerOrder.get(customerOrder.id) == null
        assert response.redirectedUrl == '/customerOrder/list'
    }
}
