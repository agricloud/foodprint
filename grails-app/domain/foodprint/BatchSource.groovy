package foodprint

class BatchSource {

	Site site
	String editor
	String creator
	Date dateCreated
	Date lastUpdated

	static belongsTo = [batch: Batch]
	Batch childBatch

    static constraints = {
		site nullable:true
		editor nullable:true
		creator nullable:true
    	childBatch(unique:['batch','site'])
    }
}