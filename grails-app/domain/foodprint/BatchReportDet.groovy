package foodprint

class BatchReportDet {

	Site site
	String editor
	String creator
	Date dateCreated
	Date lastUpdated

	static belongsTo=[batch:Batch]
	ReportParams reportParams
	String value
	BatchRoute batchRoute

    static constraints = {
    	reportParams unique: 'batch'
    	site nullable:true
    	editor nullable:true
		creator nullable:true
    	value nullable:true
    	batchRoute nullable:true
    }
}
