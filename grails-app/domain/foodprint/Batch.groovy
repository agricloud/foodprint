package foodprint

public enum BatchType {
    PRODUCT
}


class Batch  {
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

	
	static belongsTo = [
		item: Item,
		
	]

	static hasMany = [
		batchRoutes: BatchRoute,
		batchSources: BatchSource,
		batchReportDets: BatchReportDet
	]



	String name
	Long expectQty = 0
	Date dueDate


    /*
    * 製造完成日期
    */
	Date manufactureDate


    /*
    * 過期日期
    */
	Date expirationDate


    /*
    * 類型，無 erp 時使用
    */
    BatchType batchType = foodprint.BatchType.PRODUCT


    /*
    * 供應商，無 erp 時使用
    */
   	Supplier supplier

   	/*
    * 供應商所屬國家
    */
	Country country = foodprint.Country.TAIWAN



	static constraints = {
		site nullable:true
		name 				unique: true, blank: false

		dueDate 			nullable: true
		expectQty 			min: 0L
		

		manufactureDate 	nullable: true
		expirationDate 		nullable: true
		supplier 			nullable: true
		uuid 				nullable: true
	}

	String uuid
	
    def beforeInsert() {
        // optionally, replace the dashes by adding .replaceAll('-','')
        uuid = UUID.randomUUID().toString()
    }
	public String toString(){
    	"批號編號：${name}, 品項名稱：${item.title}"
    }

}
