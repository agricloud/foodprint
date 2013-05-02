package finder
import grails.converters.JSON

class BatchController {

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
