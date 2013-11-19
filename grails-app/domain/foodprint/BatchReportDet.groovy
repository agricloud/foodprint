package foodprint

class BatchReportDet {
    /**
     * 廠別
     */
	Site site

    /**
     * 修改者
     */
	String editor = ""

	/**
	 * 建立者
	 */
	String creator = ""

	/**
	 * 建立日期（自動欄位）
	 */
	Date dateCreated

	/**
	 * 修改日期（自動欄位）
	 */
	Date lastUpdated
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
	BatchRoute batchRoute


    static constraints = {
    	reportParams unique: 'batch'
    	site nullable:true
    	value nullable:true
    	batchRoute nullable:true
    }
}
