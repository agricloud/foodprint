package foodprint



import org.junit.*
import grails.test.mixin.*

@TestFor(ItemRouteController)
@Mock(ItemRoute)
class ItemRouteControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/itemRoute/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.itemRouteInstanceList.size() == 0
        assert model.itemRouteInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.itemRouteInstance != null
    }

    void testSave() {
        controller.save()

        assert model.itemRouteInstance != null
        assert view == '/itemRoute/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/itemRoute/show/1'
        assert controller.flash.message != null
        assert ItemRoute.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/itemRoute/list'

        populateValidParams(params)
        def itemRoute = new ItemRoute(params)

        assert itemRoute.save() != null

        params.id = itemRoute.id

        def model = controller.show()

        assert model.itemRouteInstance == itemRoute
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/itemRoute/list'

        populateValidParams(params)
        def itemRoute = new ItemRoute(params)

        assert itemRoute.save() != null

        params.id = itemRoute.id

        def model = controller.edit()

        assert model.itemRouteInstance == itemRoute
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/itemRoute/list'

        response.reset()

        populateValidParams(params)
        def itemRoute = new ItemRoute(params)

        assert itemRoute.save() != null

        // test invalid parameters in update
        params.id = itemRoute.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/itemRoute/edit"
        assert model.itemRouteInstance != null

        itemRoute.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/itemRoute/show/$itemRoute.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        itemRoute.clearErrors()

        populateValidParams(params)
        params.id = itemRoute.id
        params.version = -1
        controller.update()

        assert view == "/itemRoute/edit"
        assert model.itemRouteInstance != null
        assert model.itemRouteInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/itemRoute/list'

        response.reset()

        populateValidParams(params)
        def itemRoute = new ItemRoute(params)

        assert itemRoute.save() != null
        assert ItemRoute.count() == 1

        params.id = itemRoute.id

        controller.delete()

        assert ItemRoute.count() == 0
        assert ItemRoute.get(itemRoute.id) == null
        assert response.redirectedUrl == '/itemRoute/list'
    }
}
