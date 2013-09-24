package foodprint

 public enum BatchType {
    PRODUCT
}
class Batch {

	static belongsTo =[item: Item, materialSheetDet:MaterialSheetDet, manufactureOrder:ManufactureOrder]
	static hasMany =[batchRoutes:BatchRoute,batchSources:BatchSource]
	// static hasOne =[materialSheetDet:MaterialSheetDet, manufactureOrder:ManufactureOrder]

	String name
	Long expectQty=0
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
    BatchType batchType=foodprint.BatchType.PRODUCT


    /*
    * 供應商，無 erp 時使用
    */
   	Supplier supplier

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

	static constraints = {
		name unique:true

		dueDate nullable:true
		expectQty min:0L
		
		site nullable:true
		manufactureDate nullable:true
		expirationDate nullable:true
		// supplier nullable:true
		manufactureOrder nullable:true
		materialSheetDet nullable:true
	}
	
}
