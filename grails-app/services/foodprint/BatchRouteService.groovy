package foodprint

import org.springframework.transaction.annotation.Transactional

class BatchRouteService {

	@Transactional
    def deleteBatchRoute(batchRouteInstance) {
    	batchRouteInstance.delete(flush:true)
    }
}
