package foodprint


import org.springframework.dao.DataIntegrityViolationException

class SupplierController {

    def domainService
    def enumService

    def index() {
        def list = Supplier.createCriteria().list(params,params.criteria)
        render (contentType: 'application/json') {
            [data: list, total: list.totalCount]
        }
    }

    def show(){
        def supplierInstance=Supplier.get(params.id);  
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
        if(params.tel && !(params.tel ==~ /^[\d-]*$/)){
            render (contentType: 'application/json') {
                [success: false,message:message(code: 'supplier.tel.not.valid')]
            }
            return
        }

        if(params.fax && !(params.fax ==~ /^[\d-]*$/)){
            render (contentType: 'application/json') {
                [success: false,message:message(code: 'supplier.tel.not.valid')]
            }
            return
        }

        if(params.email && !(params.email ==~ /^[a-zA-Z0-9]*[@]+$/)){
            render (contentType: 'application/json') {
                [success: false,message:message(code: 'supplier.email.not.valid')]
            }
            return
        }

        def supplierInstance=new Supplier(params)
        render (contentType: 'application/json') {
            domainService.save(supplierInstance)
        }
    }

    def update(){
        if(params.tel && !(params.tel ==~ /^[\d-]*$/)){
            render (contentType: 'application/json') {
                [success: false,message:message(code: 'supplier.tel.not.valid')]
            }
            return
        }

        if(params.fax && !(params.fax ==~ /^[\d-]*$/)){
            render (contentType: 'application/json') {
                [success: false,message:message(code: 'supplier.tel.not.valid')]
            }
            return
        }
        if(params.email && !(params.email ==~ /^[a-zA-Z0-9]*[@]+$/)){
            render (contentType: 'application/json') {
                [success: false,message:message(code: 'supplier.email.not.valid')]
            }
            return
        }

        def  supplierInstance = Supplier.get(params.id)
        supplierInstance.properties = params
        render (contentType: 'application/json') {
            domainService.save(supplierInstance)
        }         
    }

    def delete(){
        
        def supplierInstance = Supplier.get(params.id)
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
