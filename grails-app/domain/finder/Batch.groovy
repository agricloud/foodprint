package finder

class Batch {


		static belongsTo =[item:Item]

		Long expectQty=0
		String name

		Date dueDate

		String editor=""			//修改者
		String creator=""		//建立者
    Date dateCreated    //建立日期
    Date lastUpdated    //修改日期
    static constraints = {
    	dueDate nullable:true
    }
}
