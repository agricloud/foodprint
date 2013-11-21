package foodprint

public enum BatchType {
    PRODUCT
}


class Batch  {

	Site site
	String editor = ""
	String creator = ""
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
	Long expectQty = 0
	Date dueDate
	Date manufactureDate
	Date expirationDate
    BatchType batchType = foodprint.BatchType.PRODUCT
   	Supplier supplier
	Country country = foodprint.Country.TAIWAN
	String remark



	static constraints = {
		site nullable:true
		name unique: true, blank: false
		dueDate nullable: true
		expectQty min: 0L
		manufactureDate nullable: true
		expirationDate nullable: true
		supplier nullable: true
		uuid nullable: true
		remark nullable: true
	}

	String uuid
	
    def beforeInsert() {
        // optionally, replace the dashes by adding .replaceAll('-','')
        uuid = UUID.randomUUID().toString()
    }
	public String toString(){
    	"批號編號：${name}, 品項名稱：${item.title}"
    }

    static mapping = {
        batchRoutes sort:'sequence'
        batchSources sort:'childBatch'
    }

}
