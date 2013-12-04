package common
import org.codehaus.groovy.grails.commons.DefaultGrailsDomainClass
import grails.converters.JSON
class ConvertService {

	def enumService

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
	    result.manufactureDate = batch.manufactureDate
	    result.expirationDate = batch.expirationDate
	    result.remark = batch.remark
	    if(batch.item){
	        result["item.id"] = batch.item.id
	        result["item.name"] = batch.item.name
	        result["item.title"] = batch.item.title
	        result["item.spec"] = batch.item.spec
	        result["item.unit"] = batch.item.unit
	        result["item.description"] = batch.item.description
	    }
        if(batch.supplier){
        	result.supplier = batch.supplier
            result["supplier.id"] = batch.supplier.id
            result["supplier.name"] = batch.supplier.name
            result["supplier.title"] = batch.supplier.title
        }
        if(batch.country){
        	def country=enumService.name(batch.country)
        	result["country"] = country.name
        	result["countryTitle"] = country.title
        }
	    
	    result
    }

    def batchSourceParseJson(batchSource){
	    def result = [:]

	    result.id = batchSource.id

	    result.batch = batchSource.batch
	    result["batch.id"]= batchSource.batch.id
	    result["batch.name"] =batchSource.batch.name
	    result["batch.dueDate"] = batchSource.batch.dueDate
	    result["batch.expectQty"] = batchSource.batch.expectQty
	    result["batch.manufactureDate"] = batchSource.batch.manufactureDate
	    result["batch.expirationDate"] = batchSource.batch.expirationDate
	    result["batch.remark"] = batchSource.batch.remark
	    if(batchSource.batch.item){
	        result["batch.item.id"] = batchSource.batch.item.id
	        result["batch.item.name"] = batchSource.batch.item.name
	        result["batch.item.title"] = batchSource.batch.item.title
	        result["batch.item.spec"] = batchSource.batch.item.spec
	        result["batch.item.unit"] = batchSource.batch.item.unit
	        result["batch.item.description"] = batchSource.batch.item.description
	    }
        if(batchSource.batch.supplier){
        	result.batch.supplier = batchSource.batch.supplier
            result["batch.supplier.id"] = batchSource.batch.supplier.id
            result["batch.batch.supplier.name"] = batchSource.batch.supplier.name
            result["batch.supplier.title"] = batchSource.batch.supplier.title
        }
        if(batchSource.batch.country){
        	def country=enumService.name(batchSource.batch.country)
        	result["batch.country"] = country.name
        	result["batch.countryTitle"] = country.title
        }
     	result.childBatch = batchSource.childBatch
        result["childBatch.id"]= batchSource.childBatch.id
	    result["childBatch.name"] =batchSource.childBatch.name
	    result["childBatch.dueDate"] = batchSource.childBatch.dueDate
	    result["childBatch.expectQty"] = batchSource.childBatch.expectQty
	    result["childBatch.manufactureDate"] = batchSource.childBatch.manufactureDate
	    result["childBatch.expirationDate"] = batchSource.childBatch.expirationDate
	    result["childBatch.remark"] = batchSource.childBatch.remark
	    if(batchSource.childBatch.item){
	        result["childBatch.item.id"] = batchSource.childBatch.item.id
	        result["childBatch.item.name"] = batchSource.childBatch.item.name
	        result["childBatch.item.title"] = batchSource.childBatch.item.title
	        result["childBatch.item.spec"] = batchSource.childBatch.item.spec
	        result["childBatch.item.unit"] = batchSource.childBatch.item.unit
	        result["childBatch.item.description"] = batchSource.childBatch.item.description
	    }
        if(batchSource.childBatch.supplier){
        	result.childBatch.supplier = batchSource.childBatch.supplier
            result["childBatch.supplier.id"] = batchSource.childBatch.supplier.id
            result["childBatch.supplier.name"] = batchSource.childBatch.supplier.name
            result["childBatch.supplier.title"] = batchSource.childBatch.supplier.title
        }
        if(batchSource.childBatch.country){
        	def country=enumService.name(batchSource.childBatch.country)
        	result["childBatch.country"] = country.name
        	result["childBatch.countryTitle"] = country.title
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

    def itemRouteParseJson(itemRoute){
   		def result = [:]
   		result.id=itemRoute.id
   		result.sequence=itemRoute.sequence
   		result.item=itemRoute.item
   		result["item.id"] = itemRoute.item.id
   		result["item.name"] = itemRoute.item.name
   		result["item.title"] = itemRoute.item.title
   		if(itemRoute.operation){
	   		result.operation=itemRoute.operation
	        result["operation.id"] = itemRoute.operation.id
	        result["operation.name"] = itemRoute.operation.name
	        result["operation.title"] = itemRoute.operation.title
	    }

        if(itemRoute.workstation){
        	result.workstation=itemRoute.workstation
            result["workstation.id"] = itemRoute.workstation.id
            result["workstation.name"] = itemRoute.workstation.name
            result["workstation.title"] = itemRoute.workstation.title
        }

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
	    def country = enumService.name(supplier.country)
	    result.country = country.name
        result.countryTitle = country.title
	    result.tel = supplier.tel
	    result.email = supplier.email
	    result.address = supplier.address
	    
	    result
    }

   	def batchRouteParseJson(batchRoute){
   		def result = [:]
   		result.id=batchRoute.id
   		result.sequence=batchRoute.sequence
   		result.startDate=batchRoute.startDate
   		result.endDate=batchRoute.endDate
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

   	def paramParseJson(param){
	    def result = [:]
	    result.id= param.id
	    result.name = param.name
	    result.title = param.title
	    result.defaultValue = param.defaultValue
	    def paramType = enumService.name(param.paramType)
	    result.paramType = paramType.name
        result.paramTypeTitle = paramType.title
	    result.description = param.description
	    result.lower = param.lower
	    result.upper = param.upper
	    result.unit = param.unit
	    result
    }

    def reportParseJson(report){
	    def result = [:]
	    result.id= report.id
	    result.name = report.name
	    result.title = report.title
	    def reportType = enumService.name(report.reportType)
	    result.reportType = reportType.name
        result.reportTypeTitle = reportType.title
	    result.description = report.description
	    result.effectStartDate = report.effectStartDate
	    result.effectEndDate = report.effectEndDate
	    result
    }

    def reportParamsParseJson(reportParams){
	    def result = [:]
	    result.id= reportParams.id
	    result.report = reportParams.report
	    result["report.id"] = reportParams.report.id
        result["report.name"] = reportParams.report.name
        result["report.title"] = reportParams.report.title
        
        if(reportParams.param){
		    result.param = reportParams.param
	    	result["param.id"] = reportParams.param.id
	        result["param.name"] = reportParams.param.name
	        result["param.title"] = reportParams.param.title
	    }

	    if(reportParams.operation){
	   		result.operation=reportParams.operation
	        result["operation.id"] = reportParams.operation.id
	        result["operation.name"] = reportParams.operation.name
	        result["operation.title"] = reportParams.operation.title
	    }

        if(reportParams.workstation){
        	result.workstation=reportParams.workstation
            result["workstation.id"] = reportParams.workstation.id
            result["workstation.name"] = reportParams.workstation.name
            result["workstation.title"] = reportParams.workstation.title
        }
        if(reportParams.supplier){
        	result.supplier=reportParams.supplier
            result["supplier.id"] = reportParams.supplier.id
            result["supplier.name"] = reportParams.supplier.name
            result["supplier.title"] = reportParams.supplier.title
        }
        if(reportParams.item){
        	result.item=reportParams.item
            result["item.id"] = reportParams.item.id
            result["item.name"] = reportParams.item.name
            result["item.title"] = reportParams.item.title
        }
	    result
    }

    def batchReportDetParseJson(batchReportDet){
	    def result = [:]
	    result.id = batchReportDet.id
	    result.value = batchReportDet.value

	    result.reportParams = batchReportDet.reportParams
	    result["reportParams.id"] = batchReportDet.reportParams.id
	    result.reportParams.param = batchReportDet.reportParams.param
    	result["reportParams.param.id"] = batchReportDet.reportParams.param.id
        result["reportParams.param.name"] = batchReportDet.reportParams.param.name
        result["reportParams.param.title"] = batchReportDet.reportParams.param.title
        result["reportParams.param.defaultValue"] = batchReportDet.reportParams.param.defaultValue
        def paramType = enumService.name(batchReportDet.reportParams.param.paramType)
	    result["reportParams.param.paramType"] = paramType.name
	    result["reportParams.param.paramTypeTitle"] = paramType.title

	    result
    }

    def userParseJson(user){
    	def result = [:]
        result.id= user.id
        result.username = user.username
        result.password = user.password
        result.enabled= user.enabled
        result.accountExpired = user.accountExpired
        result.accountLocked = user.accountLocked
        result.passwordExpired = user.passwordExpired
        result.fullName = user.fullName
        result.email = user.email
        if(user.site){
	        result.site = user.site
	        result["site.id"] = user.site.id
	        result["site.name"] = user.site.name
	        result["site.title"] = user.site.title
	    }

        result
    }

    def customerParseJson(customer){
    	def result = [:]
        result.id = customer.id
    	result.name = customer.name
		result.title = customer.title
		result.email = customer.email
		result.address = customer.address

		result
    }


}
