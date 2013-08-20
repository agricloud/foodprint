package foodprint



import org.junit.*
import grails.test.mixin.*

@TestFor(BatchReportDetController)
@Mock(BatchReportDet)
class BatchReportDetControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/batchReportDet/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.batchReportDetInstanceList.size() == 0
        assert model.batchReportDetInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.batchReportDetInstance != null
    }

    void testSave() {
        controller.save()

        assert model.batchReportDetInstance != null
        assert view == '/batchReportDet/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/batchReportDet/show/1'
        assert controller.flash.message != null
        assert BatchReportDet.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/batchReportDet/list'

        populateValidParams(params)
        def batchReportDet = new BatchReportDet(params)

        assert batchReportDet.save() != null

        params.id = batchReportDet.id

        def model = controller.show()

        assert model.batchReportDetInstance == batchReportDet
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/batchReportDet/list'

        populateValidParams(params)
        def batchReportDet = new BatchReportDet(params)

        assert batchReportDet.save() != null

        params.id = batchReportDet.id

        def model = controller.edit()

        assert model.batchReportDetInstance == batchReportDet
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/batchReportDet/list'

        response.reset()

        populateValidParams(params)
        def batchReportDet = new BatchReportDet(params)

        assert batchReportDet.save() != null

        // test invalid parameters in update
        params.id = batchReportDet.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/batchReportDet/edit"
        assert model.batchReportDetInstance != null

        batchReportDet.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/batchReportDet/show/$batchReportDet.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        batchReportDet.clearErrors()

        populateValidParams(params)
        params.id = batchReportDet.id
        params.version = -1
        controller.update()

        assert view == "/batchReportDet/edit"
        assert model.batchReportDetInstance != null
        assert model.batchReportDetInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/batchReportDet/list'

        response.reset()

        populateValidParams(params)
        def batchReportDet = new BatchReportDet(params)

        assert batchReportDet.save() != null
        assert BatchReportDet.count() == 1

        params.id = batchReportDet.id

        controller.delete()

        assert BatchReportDet.count() == 0
        assert BatchReportDet.get(batchReportDet.id) == null
        assert response.redirectedUrl == '/batchReportDet/list'
    }
}
