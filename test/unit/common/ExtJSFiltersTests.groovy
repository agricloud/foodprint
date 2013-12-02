package common


import foodprint.*
import grails.test.mixin.*

@TestFor(ItemController)
@Mock([ExtJSFilters, Item])
class ExtJSFiltersTests {

	void testInvocationOfListActionIsFiltered() {
		params.sort = '[{"property":"name","direction":"ASC"}]'
		params.limit = 50
		params.page = 1
		params.start = 0

	    withFilters(controller:'*', action:'*') {
	        controller.index()
	    }


	    assert params.sort == "name"
	    assert params.order == "ASC"
	}
}
