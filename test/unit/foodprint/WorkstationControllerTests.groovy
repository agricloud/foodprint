package foodprint



import org.junit.*
import grails.test.mixin.*

@TestFor(WorkstationController)
@Mock(Workstation)
class WorkstationControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/workstation/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.workstationInstanceList.size() == 0
        assert model.workstationInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.workstationInstance != null
    }

    void testSave() {
        controller.save()

        assert model.workstationInstance != null
        assert view == '/workstation/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/workstation/show/1'
        assert controller.flash.message != null
        assert Workstation.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/workstation/list'

        populateValidParams(params)
        def workstation = new Workstation(params)

        assert workstation.save() != null

        params.id = workstation.id

        def model = controller.show()

        assert model.workstationInstance == workstation
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/workstation/list'

        populateValidParams(params)
        def workstation = new Workstation(params)

        assert workstation.save() != null

        params.id = workstation.id

        def model = controller.edit()

        assert model.workstationInstance == workstation
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/workstation/list'

        response.reset()

        populateValidParams(params)
        def workstation = new Workstation(params)

        assert workstation.save() != null

        // test invalid parameters in update
        params.id = workstation.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/workstation/edit"
        assert model.workstationInstance != null

        workstation.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/workstation/show/$workstation.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        workstation.clearErrors()

        populateValidParams(params)
        params.id = workstation.id
        params.version = -1
        controller.update()

        assert view == "/workstation/edit"
        assert model.workstationInstance != null
        assert model.workstationInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/workstation/list'

        response.reset()

        populateValidParams(params)
        def workstation = new Workstation(params)

        assert workstation.save() != null
        assert Workstation.count() == 1

        params.id = workstation.id

        controller.delete()

        assert Workstation.count() == 0
        assert Workstation.get(workstation.id) == null
        assert response.redirectedUrl == '/workstation/list'
    }
}
