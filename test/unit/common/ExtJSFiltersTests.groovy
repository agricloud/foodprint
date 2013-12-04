package common


import foodprint.*
import grails.test.mixin.*

@TestFor(ItemController)
@Mock([ExtJSFilters, Item, Site, User])
class ExtJSFiltersTests {
	void setUp(){

		User.metaClass.encodePassword = {
			password = 'password'
		}

		def site = new Site(name:"site", title:"創毅公司").save(failOnError: true)
		def user = new User(username: 'loginUser', password:'pass',site:site, enabled: true).save(failOnError: true)
		site.addToUser(user).save(failOnError: true)

		ExtJSFilters.metaClass.springSecurityService = [ 
	    	currentUser: user
    	]
	}

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

	void testItemIndexByCurrentUserAndSite(){
		def site = Site.get(1)

		def item1 = new Item(name:"item1",title:"華珍玉米1",spec:"華珍甜玉米，高糖分、皮薄",unit:"kg",description:"非基因轉殖品種").save(failOnError: true)

		def item2 = new Item(name:"item2",title:"華珍玉米2",site:site,spec:"華珍甜玉米，高糖分、皮薄",unit:"kg",description:"非基因轉殖品種").save(failOnError: true)

    	def item3 = new Item(name:"item3",title:"華珍玉米3",spec:"華珍甜玉米，高糖分、皮薄",unit:"kg",description:"非基因轉殖品種").save(failOnError: true)

	    withFilters(controller:'*', action:'*') {
	        controller.index()
	        println response.json
	        assert response.json.itemInstanceTotal == 1
	    }

	}
}
