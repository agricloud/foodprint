package foodprint
import grails.converters.JSON

class BatchService {

    def parseJsonAddRelationDomainProperties(Batch batch){

    	def batchJson =  JSON.parse((batch as JSON).toString())            
        batchJson["item.id"] = batch.item.id
        batchJson["item.name"] = batch.item.name
        batchJson["item.title"] = batch.item.title
        batchJson["item.spec"] = batch.item.spec
        batchJson["item.unit"] = batch.item.unit
        batchJson["item.description"] = batch.item.description
        if(batch.supplier){
            batchJson["supplier.id"] = batch.supplier.id
            batchJson["supplier.name"] = batch.supplier.name
            batchJson["supplier.title"] = batch.supplier.title
        }
        if(batch.country){
            batchJson["country"] = batch.country.name()
        }

        batchJson
    }


}
