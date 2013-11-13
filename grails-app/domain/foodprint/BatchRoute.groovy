package foodprint

class BatchRoute {
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
	static belongsTo=[batch:Batch]
	Workstation workstation
    Supplier supplier
	Operation operation
	int sequence


    /*
    * 開始時間
    */
	Date startDate


    /*
    * 結束時間
    */
	Date endDate

	 

    static constraints = {
        site nullable:true
    	sequence unique:'batch'
    	startDate nullable:true
    	endDate nullable:true
        workstation nullable:true
        supplier  nullable:true
    }
}
