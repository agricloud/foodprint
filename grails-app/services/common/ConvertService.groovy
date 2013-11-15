package common
import org.codehaus.groovy.grails.commons.DefaultGrailsDomainClass
import grails.converters.JSON
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
	    result.name=batch.name
	    result.item = batch.item
	    result.dueDate = batch.dueDate
	    result.expectQty = batch.expectQty
        result["item.id"] = batch.item.id
        result["item.name"] = batch.item.name
        result["item.title"] = batch.item.title
        result["item.spec"] = batch.item.spec
        result["item.unit"] = batch.item.unit
        result["item.description"] = batch.item.description
        if(batch.supplier){
        	result.supplier = batch.supplier
            result["supplier.id"] = batch.supplier.id
            result["supplier.name"] = batch.supplier.name
            result["supplier.title"] = batch.supplier.title
        }
        if(batch.country){
        	result["country"] = batch.country.name()   
        }
	    
	    result
    }
    def itemParseJson(item){
	    def result = [:]

	    result.id = item.id
	    result.name = item.name
	    result.title = item.title
	    result.spec = item.spec
	    result.unit = item.unit
	    result.description = item.description

	    result
    }

    def operationParseJson(operation){
	    def result = [:]
	    result.id= operation.id
	    result.name = operation.name
	    result.title = operation.title
	    result.description = operation.description
	    
	    result
    }

    def workstationParseJson(workstation){
	    def result = [:]
	    result.id= workstation.id
	    result.name = workstation.name
	    result.title = workstation.title
	    result.description = workstation.description
	    
	    result
    }

    def supplierParseJson(supplier){
	    def result = [:]
	    result.id= supplier.id
	    result.name = supplier.name
	    result.title = supplier.title
	    result.country = supplier.country
	    result.tel = supplier.tel
	    result.email = supplier.email
	    result.address = supplier.address
	    
	    result
    }

   	def batchRouteParseJson(batchRoute){
   		def result = [:]
   		result.id=batchRoute.id
   		result.sequence=batchRoute.sequence
   		result.batch=batchRoute.batch
   		result["batch.id"] = batchRoute.batch.id
   		result["batch.name"] = batchRoute.batch.name
   		if(batchRoute.operation){
	   		result.operation=batchRoute.operation
	        result["operation.id"] = batchRoute.operation.id
	        result["operation.name"] = batchRoute.operation.name
	        result["operation.title"] = batchRoute.operation.title
	    }

        if(batchRoute.workstation){
        	result.workstation=batchRoute.workstation
            result["workstation.id"] = batchRoute.workstation.id
            result["workstation.name"] = batchRoute.workstation.name
            result["workstation.title"] = batchRoute.workstation.title
        }
        if(batchRoute.supplier){
        	result.supplier=batchRoute.supplier
            result["supplier.id"] = batchRoute.supplier.id
            result["supplier.name"] = batchRoute.supplier.name
            result["supplier.title"] = batchRoute.supplier.title
        }

        result
   	}
}
