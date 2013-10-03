package foodprint

 public enum BatchType {
    PRODUCT
}
class Batch extends DefaultTable {

	static belongsTo = [
		item: Item,
		
	]

	static hasMany = [
		batchRoutes: BatchRoute,
		batchSources: BatchSource,
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
		name 				unique: true

		dueDate 			nullable: true
		expectQty 			min: 0L
		

		manufactureDate 	nullable: true
		expirationDate 		nullable: true
		supplier 			nullable: true
	}
	
}
