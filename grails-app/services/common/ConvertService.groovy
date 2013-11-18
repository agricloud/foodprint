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
	    def country = enumService.name(supplier.country)
	    result.country = [:]
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
	    result.paramType = param.paramType
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
	    result.reportType = report.reportType
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
	    result["reportParams.param.paramType"] = batchReportDet.reportParams.param.paramType

	    result
    }


}
