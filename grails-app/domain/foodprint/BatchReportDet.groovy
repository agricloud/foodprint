package foodprint

class BatchReportDet {

	/*
	* 記錄是哪個批號收集的履歷資料
	*/
	Batch batch

	/*
	* 記錄收集的項目
	*/
	ReportParam reportParam

	/*
	* 記錄收集項目的值
	*/
	String value


	/*
	* 記錄是哪個途程做的收集
	*/
	// BatchRoute batchRoute

	String editor=""	//修改者
	String creator=""	//建立者
	Date dateCreated    //建立日期
	Date lastUpdated    //修改日期



    static constraints = {
    	batch(unique: ['reportParam'])
    }
}
