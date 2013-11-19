package common

import grails.test.mixin.*
import org.junit.*
import foodprint.*
import grails.converters.JSON
/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(EnumService)
@Mock([Supplier])
class EnumServiceTests {

	//似乎不支援enum in domain的測試
	void testEnumValues() {
		messageSource.addMessage("country.TAIWAN.label", Locale.getDefault(), "台灣")
		messageSource.addMessage("country.JAPAN.label", Locale.getDefault(), "日本")
		messageSource.addMessage("country.CHINA.label", Locale.getDefault(), "中國")
		messageSource.addMessage("country.HONGKONG.label", Locale.getDefault(), "香港")

		println service.values(Country)
	}

	void testEnumName() {
		def supplier1 = new Supplier(name:"supplier1",title:"A公司",email:"A@xx.com",address:"台北市忠孝東路222號",country:Country.JAPAN).save(failOnError: true, flush: true)
		
		assert service.name(supplier1.country).name == "JAPAN"
		assert service.name(supplier1.country).title == "日本"
	}

}