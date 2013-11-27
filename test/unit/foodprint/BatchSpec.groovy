package foodprint

import grails.test.mixin.TestMixin
import grails.test.mixin.support.GrailsUnitTestMixin
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestMixin(GrailsUnitTestMixin)
class BatchSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "簡單測試一下"() {
   		expect:
      		1 == 1
      		1 == 2

    }
}
