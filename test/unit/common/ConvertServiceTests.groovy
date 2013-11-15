package common

import grails.test.mixin.*
import org.junit.*
import foodprint.*
import grails.converters.JSON
/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(ConvertService)
@Mock([Batch, Item])
class ConvertServiceTests {

    void testJsonConvert() {
    	def item = new Item(name: 'item1').save()
    	def batch = new Batch(name:'batch1', item: item, expectQty:10).save()

		JSON.registerObjectMarshaller(Batch) {
		    service.batchParseJson(it)
		}

		JSON.registerObjectMarshaller(Item) {
		    service.itemParseJson(it)
		}

		println batch as JSON
    }
}

