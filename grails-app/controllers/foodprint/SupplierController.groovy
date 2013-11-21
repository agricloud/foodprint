package foodprint


import org.springframework.dao.DataIntegrityViolationException

class SupplierController {

    def domainService
    def enumService

    def index() {
        def list = Supplier.createCriteria().list(params,params.criteria)
        render (contentType: 'application/json') {
            [supplierInstanceList: list, supplierInstanceTotal: list.totalCount]
        }
    }

    def show(){
        def supplierInstance=Supplier.findById(params.id);  
        if(supplierInstance){   
            render (contentType: 'application/json') {
                [success: true,data:supplierInstance]
            }
        }else {
            render (contentType: 'application/json') {
                [success: false,message:message(code: 'default.message.show.failed')]
            }          
        }
    }
    
    def create(){
        def supplierInstance=new Supplier()        
        render (contentType: 'application/json') {
            [success: true,data:supplierInstance]
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
        supplierInstance.properties = params
        render (contentType: 'application/json') {
            domainService.save(supplierInstance)
        }         
    }

    def delete(){
        
        def supplierInstance = Supplier.findById(params.id)
        def result
        try {
            
            result = domainService.delete(supplierInstance)
        
        }catch(DataIntegrityViolationException e){
            log.error e
            def msg = message(code: 'default.message.delete.failed', args: [itemInstance, e])
            result = [success:false, message: msg] 
        }
        
        render (contentType: 'application/json') {
            result
        }
    }    
}
