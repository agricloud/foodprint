package common

import grails.test.mixin.*
import org.junit.*
import foodprint.*
import grails.converters.JSON
/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(EnumService)
@Mock([
	Supplier,foodprint.Country])
class EnumServiceTests {

	//似乎不支援enum in domain的測試
	void testEnumValues() {
	
		println service.values(foodprint.Country)
	}

	void testEnumName() {
		def supplier1 = new Supplier(name:"supplier1",title:"A公司",email:"A@xx.com",address:"台北市忠孝東路222號",country:Country.JAPAN).save(failOnError: true, flush: true)
		
		println service.name(supplier1.country)
	}

}