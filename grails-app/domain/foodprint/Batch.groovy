package foodprint

class Batch {

	static belongsTo =[item:Item]
	static hasMany =[batchRoute:BatchRoute]

	String name
	Long expectQty=0
	Date dueDate

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
	}
	
}
