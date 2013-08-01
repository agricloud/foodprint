package foodprint

class Report {

	String name
	String title
	String decription=""
	Date effectStartDate
	Date effectEndDate

	String editor=""			//修改者
	String creator=""		//建立者
    Date dateCreated    //建立日期
    Date lastUpdated    //修改日期
    static constraints = {
    	effectStartDate nullable:true
    	effectEndDate nullable:true
    }
}
