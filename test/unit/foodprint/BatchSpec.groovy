package foodprint

import grails.test.mixin.TestMixin
import grails.test.mixin.support.GrailsUnitTestMixin
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestMixin(GrailsUnitTestMixin)
@TestFor(Batch)
@Mock([Item])
class BatchSpec extends Specification {

    void "測試新增批號與取得清單"() {
    	given: '產生一個測試批號 domain'
    	def item = new Item(name:'item').save()
    	new Batch(name: 'batch', item:item).save()

    	when: '要求取得批號清單'
    	def batchList = Batch.list()

   		then: '清單中要有一個批號資訊'
      	expect: batchList.size() == 1

    }
}
