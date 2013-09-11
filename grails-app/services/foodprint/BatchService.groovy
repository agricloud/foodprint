package foodprint

import org.springframework.transaction.annotation.Transactional

class BatchService {

	@Transactional
    def deleteBatch(batchInstance) {
    	batchInstance.delete(flush:true)
    }
}
