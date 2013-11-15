package common
import org.codehaus.groovy.grails.commons.DefaultGrailsDomainClass

class ConvertService {

    def getDomainFields(domainClassName) {
    		def fields = []
    		def d = new DefaultGrailsDomainClass(domainClassName)
		    d.persistentProperties.collect {
		        fields << it.name
		    }


		    fields

    }

    def domainParseMap(domainObject){
    	log.info domainObject.class
	    def result = [:]
	    getDomainFields(domainObject.class).each { field ->
	        result[field] = domainObject[field]
	    }
	    result.id=domainObject.id

	    result
    }
    def batchParseJson(batch){
	    def result = [:]
	    result.id= batch.id
	    result.item = batch.item
        result["item.id"] = batch.item.id
        result["item.name"] = batch.item.name
        result["item.title"] = batch.item.title
        result["item.spec"] = batch.item.spec
        result["item.unit"] = batch.item.unit
        result["item.description"] = batch.item.description
	    
	    result
    }
    def itemParseJson(item){
	    def result = [:]
	    result.id = item.id
	    result.name = "test"

	    result
    }
}
