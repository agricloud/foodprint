package foodprint

class BatchAnalyzeService {


	def forwardTrace(batch){
		def batchs = []
		BatchSource.findAllByChildBatch(batch).each{ batchSource ->
			batchs << batchSource.batch
		}
		[batchHead:batchs]
	}

	def backwardTrace(batch){
		def batchs=[]
		batch.batchSources.each{ batchSource ->
			batchs << batchSource.childBatch
		}
		[batchChild:batchs]
	}

	def backwardTraceToFinal(batch){
		def batchs = [batch]
		def batchFinal = [batchChilds:[]]
		while(batchs.size()>0){
			def batchChilds=backwardTrace(batchs.get(0))
			if((batchChilds.batchChild==null || batchChilds.batchChild.size()==0) && batchs.get(0) != batch){
				batchFinal.batchChilds << batchs.get(0)
			}
			else{
				batchChilds.batchChild.each{
					batchs << it
				}
			}
			batchs.remove(0)
		}//end while

		return batchFinal
	}

	def isForwardEndBatch(batch){//暫時
		if(forwardTrace(batch).batchHead)
			[isEndBatch:false]
		else
			[isEndBatch:true]

	}



}