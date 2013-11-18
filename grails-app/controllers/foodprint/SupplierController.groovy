package foodprint

import org.springframework.dao.DataIntegrityViolationException
import org.apache.commons.lang.exception.ExceptionUtils
import grails.converters.JSON

class SupplierController {

    def domainService
    def enumService

    def index(params) {

        def list = Supplier.createCriteria().list(params,params.criteria)


        render (contentType: 'application/json') {
            [supplierInstanceList: list, supplierInstanceTotal: list.totalCount]
        }
        
    }

     def show(Long id){

        def supplier=Supplier.findById(id);  
        if(supplier){    

            render (contentType: 'application/json') {
                [success: true,data:supplier]
            }
        }else {
            render (contentType: 'application/json') {
                [success: false,message:message(code: 'default.message.show.failed')]
            }          
        }


    }
    def create(){

        def supplier=new Supplier()
        def supplierJson =  JSON.parse((supplier as JSON).toString())

        render (contentType: 'application/json') {
            [success: true,data:supplier]
        }
    }
    def save(){

        def supplierInstance=new Supplier(params)
        
        render (contentType: 'application/json') {
            domainService.save(supplierInstance)
        }
    }

    def update(){
        def  supplierInstance = Supplier.findById(params.id)
        supplierInstance.properties=params
        render (contentType: 'application/json') {
            domainService.save(supplierInstance)
        }         
    }


    def delete(){
        def supplierInstance = Supplier.findById(params.id)
        def result
        try {
            
            result = domainService.delete(supplierInstance)
        
        }catch(e){
            log.error e
            def msg = message(code: 'default.message.delete.failed', args: [supplierInstance, e.getMessage()])
            result = [success:false, message: msg] 
        }
        
        render (contentType: 'application/json') {
            result
        }
    }

    /*
    * 將定義在 supplier domain 中的 enum CountryType 轉換為 json
    */
    def indexCountry(){

        render (contentType: 'application/json') {
            [Country:enumService.values(foodprint.Country)]
        }
    }


}