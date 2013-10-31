package foodprint



import grails.test.mixin.*
import org.junit.*
import grails.converters.*

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(FoodpaintService)
@Mock([
	Site,
    Item,
    Customer,
    Workstation,
    Operation,
    Supplier,
    Batch,
    BatchSource])
class FoodpaintServiceTests {

    void testSomething() {
    	service.doDataImport()

        //fail "Implement me"
    }
}
