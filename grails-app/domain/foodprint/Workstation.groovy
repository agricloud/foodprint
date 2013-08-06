package foodprint

class Workstation {

	String name
	String title
	String description=''


	Site site
	String editor=""	//修改者
	String creator=""	//建立者
	Date dateCreated    //建立日期
	Date lastUpdated    //修改日期
	
    static constraints = {
    	site nullable:true
    }
}
