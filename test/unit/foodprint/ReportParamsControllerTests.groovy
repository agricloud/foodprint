package foodprint



import org.junit.*
import grails.test.mixin.*

@TestFor(ReportParamsController)
@Mock(ReportParams)
class ReportParamsControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/reportParams/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.reportParamsInstanceList.size() == 0
        assert model.reportParamsInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.reportParamsInstance != null
    }

    void testSave() {
        controller.save()

        assert model.reportParamsInstance != null
        assert view == '/reportParams/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/reportParams/show/1'
        assert controller.flash.message != null
        assert ReportParams.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/reportParams/list'

        populateValidParams(params)
        def reportParams = new ReportParams(params)

        assert reportParams.save() != null

        params.id = reportParams.id

        def model = controller.show()

        assert model.reportParamsInstance == reportParams
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/reportParams/list'

        populateValidParams(params)
        def reportParams = new ReportParams(params)

        assert reportParams.save() != null

        params.id = reportParams.id

        def model = controller.edit()

        assert model.reportParamsInstance == reportParams
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/reportParams/list'

        response.reset()

        populateValidParams(params)
        def reportParams = new ReportParams(params)

        assert reportParams.save() != null

        // test invalid parameters in update
        params.id = reportParams.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/reportParams/edit"
        assert model.reportParamsInstance != null

        reportParams.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/reportParams/show/$reportParams.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        reportParams.clearErrors()

        populateValidParams(params)
        params.id = reportParams.id
        params.version = -1
        controller.update()

        assert view == "/reportParams/edit"
        assert model.reportParamsInstance != null
        assert model.reportParamsInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/reportParams/list'

        response.reset()

        populateValidParams(params)
        def reportParams = new ReportParams(params)

        assert reportParams.save() != null
        assert ReportParams.count() == 1

        params.id = reportParams.id

        controller.delete()

        assert ReportParams.count() == 0
        assert ReportParams.get(reportParams.id) == null
        assert response.redirectedUrl == '/reportParams/list'
    }
}
