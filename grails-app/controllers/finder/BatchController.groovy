package finder
import grails.converters.JSON

class BatchController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [batchInstanceList: Batch.list(params), batchInstanceTotal: Batch.count()]
    }

    def listJson(Integer max) {

        render (contentType: 'text/json') {
            list(max)        
        }
        
    }

    def listAll = {
		
        def batch=Batch.list()

        render (contentType: 'text/json') {
        [
	batchs: batch,
	total: batch.size()
        ]
        }	
    }
}
