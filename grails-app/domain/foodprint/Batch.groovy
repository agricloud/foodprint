package foodprint

public enum BatchType {
    PRODUCT
}


class Batch  {

	Site site
	String editor
	String creator
	Date dateCreated
	Date lastUpdated

	static belongsTo = [
		item: Item		
	]

	static hasMany = [
		batchRoutes: BatchRoute,
		batchSources: BatchSource
	]

	String name
	double expectQty = 0.0d
	Date dueDate
	Date manufactureDate
	Date expirationDate
    BatchType batchType = foodprint.BatchType.PRODUCT
   	Supplier supplier
	Country country = foodprint.Country.TAIWAN


	String remark



	static constraints = {
		site nullable:true
		editor nullable:true
		creator nullable:true
		name unique:true, blank: false
		expectQty min: 0.0d
		dueDate nullable: true
		manufactureDate nullable: true
		expirationDate nullable: true
		supplier nullable: true 
		remark nullable: true
		uuid nullable: true
	}

	String uuid
	
    def beforeInsert() {
        // optionally, replace the dashes by adding .replaceAll('-','')
        uuid = UUID.randomUUID().toString()
    }
	public String toString(){
    	"批號編號：${name}"
    }

    static mapping = {
        batchRoutes sort:'sequence'
        batchSources sort:'childBatch'
    }

}
