package foodprint

class BatchRoute {

    Site site
    String editor
    String creator
    Date dateCreated
    Date lastUpdated

	static belongsTo=[batch:Batch]
    static hasMany = [
        batchReportDets: BatchReportDet
    ]
	Workstation workstation
    Supplier supplier
	Operation operation
	int sequence
	Date startDate
	Date endDate

    static constraints = {
        site nullable:true
        editor nullable:true
        creator nullable:true
    	sequence unique:'batch'
    	startDate nullable:true
    	endDate nullable:true
        workstation nullable:true
        supplier  nullable:true
    }
}
