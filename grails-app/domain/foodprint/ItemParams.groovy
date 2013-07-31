package foodprint

class ItemParams {

	Item item
	String title
	String description
	String defaultValue

	String editor			//修改者
	String creator		//建立者
    Date dateCreated    //建立日期
    Date lastUpdated    //修改日期
    static constraints = {
    	editor nullable:true
    }
}
