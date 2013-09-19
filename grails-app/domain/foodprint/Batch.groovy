package foodprint

 public enum BatchType {
    PRODUCT
}
class Batch {

	static belongsTo =[item:Item]
	static hasMany =[batchRoutes:BatchRoute,batchSources:BatchSource]

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
    * 類型
    */
    BatchType batchType=foodprint.BatchType.PRODUCT


    /*
    * 供應商
    */
   	Supplier supplier

	Site site
	String editor=""	//修改者
	String creator=""	//建立者
	Date dateCreated    //建立日期
	Date lastUpdated    //修改日期

	static constraints = {
		name unique:true

		dueDate nullable:true
		expectQty min:0L
		
		site nullable:true
		manufactureDate nullable:true
		expirationDate nullable:true
		supplier nullable:true
	}
	
}
