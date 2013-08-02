package foodprint



import org.junit.*
import grails.test.mixin.*

@TestFor(ParamController)
@Mock(Param)
class ParamControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/param/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.paramInstanceList.size() == 0
        assert model.paramInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.paramInstance != null
    }

    void testSave() {
        controller.save()

        assert model.paramInstance != null
        assert view == '/param/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/param/show/1'
        assert controller.flash.message != null
        assert Param.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/param/list'

        populateValidParams(params)
        def param = new Param(params)

        assert param.save() != null

        params.id = param.id

        def model = controller.show()

        assert model.paramInstance == param
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/param/list'

        populateValidParams(params)
        def param = new Param(params)

        assert param.save() != null

        params.id = param.id

        def model = controller.edit()

        assert model.paramInstance == param
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/param/list'

        response.reset()

        populateValidParams(params)
        def param = new Param(params)

        assert param.save() != null

        // test invalid parameters in update
        params.id = param.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/param/edit"
        assert model.paramInstance != null

        param.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/param/show/$param.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        param.clearErrors()

        populateValidParams(params)
        params.id = param.id
        params.version = -1
        controller.update()

        assert view == "/param/edit"
        assert model.paramInstance != null
        assert model.paramInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/param/list'

        response.reset()

        populateValidParams(params)
        def param = new Param(params)

        assert param.save() != null
        assert Param.count() == 1

        params.id = param.id

        controller.delete()

        assert Param.count() == 0
        assert Param.get(param.id) == null
        assert response.redirectedUrl == '/param/list'
    }
}
