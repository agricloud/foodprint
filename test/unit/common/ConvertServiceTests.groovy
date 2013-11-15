package common

import grails.test.mixin.*
import org.junit.*
import foodprint.*
import grails.converters.JSON
/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(ConvertService)
@Mock([Batch, Item,
	BatchRoute,
	Supplier,Workstation,Operation])
class ConvertServiceTests {

    void testBatchJsonConvert() {
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

    void testBatchRouteJsonConvert() {
    	def item = new Item(name: 'item1',title:'品項1').save()
    	def ws = new Workstation(name: 'workstation1',title:'生產線1').save()
    	def sp = [:];//new Supplier(name: 'supplier1',title:'供應商1').save()
    	sp.id=null
    	def op = new Operation(name: 'operation1',title:'製程1').save()
    	def batch = new Batch(name:'batch1', item: item, expectQty:10).save()
    	def batchRoute = new BatchRoute(batch:batch,sequence:1,workstation:ws,supplier:sp,operation:op).save()

		JSON.registerObjectMarshaller(BatchRoute) {
		    service.batchRouteParseJson(it)
		}
		JSON.registerObjectMarshaller(Batch) {
		    service.batchParseJson(it)
		}
		JSON.registerObjectMarshaller(Item) {
		    service.itemParseJson(it)
		}
		JSON.registerObjectMarshaller(Workstation) {
		    service.workstationParseJson(it)
		}
		JSON.registerObjectMarshaller(Supplier) {
		    service.supplierParseJson(it)
		}
		JSON.registerObjectMarshaller(Operation) {
		    service.operationParseJson(it)
		}

		println batchRoute as JSON
    }
}

