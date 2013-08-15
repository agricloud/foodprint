package foodprint



import org.junit.*
import grails.test.mixin.*

@TestFor(BatchRouteController)
@Mock(BatchRoute)
class BatchRouteControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/batchRoute/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.batchRouteInstanceList.size() == 0
        assert model.batchRouteInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.batchRouteInstance != null
    }

    void testSave() {
        controller.save()

        assert model.batchRouteInstance != null
        assert view == '/batchRoute/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/batchRoute/show/1'
        assert controller.flash.message != null
        assert BatchRoute.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/batchRoute/list'

        populateValidParams(params)
        def batchRoute = new BatchRoute(params)

        assert batchRoute.save() != null

        params.id = batchRoute.id

        def model = controller.show()

        assert model.batchRouteInstance == batchRoute
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/batchRoute/list'

        populateValidParams(params)
        def batchRoute = new BatchRoute(params)

        assert batchRoute.save() != null

        params.id = batchRoute.id

        def model = controller.edit()

        assert model.batchRouteInstance == batchRoute
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/batchRoute/list'

        response.reset()

        populateValidParams(params)
        def batchRoute = new BatchRoute(params)

        assert batchRoute.save() != null

        // test invalid parameters in update
        params.id = batchRoute.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/batchRoute/edit"
        assert model.batchRouteInstance != null

        batchRoute.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/batchRoute/show/$batchRoute.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        batchRoute.clearErrors()

        populateValidParams(params)
        params.id = batchRoute.id
        params.version = -1
        controller.update()

        assert view == "/batchRoute/edit"
        assert model.batchRouteInstance != null
        assert model.batchRouteInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/batchRoute/list'

        response.reset()

        populateValidParams(params)
        def batchRoute = new BatchRoute(params)

        assert batchRoute.save() != null
        assert BatchRoute.count() == 1

        params.id = batchRoute.id

        controller.delete()

        assert BatchRoute.count() == 0
        assert BatchRoute.get(batchRoute.id) == null
        assert response.redirectedUrl == '/batchRoute/list'
    }
}
