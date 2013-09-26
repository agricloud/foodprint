package foodprint

class BatchReportDet extends DefaultTable{

	/*
	* 記錄是哪個批號收集的履歷資料
	*/
	static belongsTo=[batch:Batch]
	/*
	* 記錄收集的項目
	*/
	ReportParams reportParams

	/*
	* 記錄收集項目的值
	*/
	String value


	/*
	* 記錄是哪個途程做的收集
	*/
	// BatchRoute batchRoute

    static constraints = {
    	batch(unique: ['reportParams'])
    	value nullable:true
    }
}
