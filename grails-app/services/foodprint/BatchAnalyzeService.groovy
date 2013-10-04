package foodprint

class BatchAnalyzeService {


	def forwardTrace(){

	}

	def backwardTrace(batch){
		[batchSources:batch.batchSources]
	}

	def backwardTraceToFinal(batch){
		def batchs = [batch]
		def batchSourcesFinal = [batchSourcesFinal:[]]
		while(batchs.size()>0){

			def batchSources=backwardTrace(batchs.get(0))
			if((batchSources.batchSources==null || batchSources.batchSources.size()==0) && batchs.get(0)!= batch){
				batchSourcesFinal.batchSourcesFinal << batchs.get(0)
			}
			else{
				batchSources.batchSources.each{
					batchs << it.childBatch
				}
			}

			batchs.remove(0)
		}//end while

		return batchSourcesFinal
	}



}