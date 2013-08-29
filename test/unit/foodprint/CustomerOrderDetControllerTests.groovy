package foodprint



import org.junit.*
import grails.test.mixin.*

@TestFor(CustomerOrderDetController)
@Mock(CustomerOrderDet)
class CustomerOrderDetControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/customerOrderDet/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.customerOrderDetInstanceList.size() == 0
        assert model.customerOrderDetInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.customerOrderDetInstance != null
    }

    void testSave() {
        controller.save()

        assert model.customerOrderDetInstance != null
        assert view == '/customerOrderDet/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/customerOrderDet/show/1'
        assert controller.flash.message != null
        assert CustomerOrderDet.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/customerOrderDet/list'

        populateValidParams(params)
        def customerOrderDet = new CustomerOrderDet(params)

        assert customerOrderDet.save() != null

        params.id = customerOrderDet.id

        def model = controller.show()

        assert model.customerOrderDetInstance == customerOrderDet
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/customerOrderDet/list'

        populateValidParams(params)
        def customerOrderDet = new CustomerOrderDet(params)

        assert customerOrderDet.save() != null

        params.id = customerOrderDet.id

        def model = controller.edit()

        assert model.customerOrderDetInstance == customerOrderDet
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/customerOrderDet/list'

        response.reset()

        populateValidParams(params)
        def customerOrderDet = new CustomerOrderDet(params)

        assert customerOrderDet.save() != null

        // test invalid parameters in update
        params.id = customerOrderDet.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/customerOrderDet/edit"
        assert model.customerOrderDetInstance != null

        customerOrderDet.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/customerOrderDet/show/$customerOrderDet.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        customerOrderDet.clearErrors()

        populateValidParams(params)
        params.id = customerOrderDet.id
        params.version = -1
        controller.update()

        assert view == "/customerOrderDet/edit"
        assert model.customerOrderDetInstance != null
        assert model.customerOrderDetInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/customerOrderDet/list'

        response.reset()

        populateValidParams(params)
        def customerOrderDet = new CustomerOrderDet(params)

        assert customerOrderDet.save() != null
        assert CustomerOrderDet.count() == 1

        params.id = customerOrderDet.id

        controller.delete()

        assert CustomerOrderDet.count() == 0
        assert CustomerOrderDet.get(customerOrderDet.id) == null
        assert response.redirectedUrl == '/customerOrderDet/list'
    }
}
