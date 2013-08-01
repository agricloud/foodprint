package foodprint

class Item {

	///*
	String name
	String title=""
	String description=""
	Long effectiveDays
	Date effectiveDate
	Date ecxpiryDate
	//*/
	/*
	String name
	String title=""
	String description=""
	Long dueDays
	Date effectStartDate
	Date effectEndDate
	*/
	String editor=""			//修改者
	String creator=""		//建立者
	Date dateCreated    //建立日期
	Date lastUpdated    //修改日期

	static hasMany=[itemImages:ItemImage]
	  
	static constraints = {
		///*
		effectiveDays nullable:true
		effectiveDate nullable:true
		ecxpiryDate nullable:true
		//*/
		/*
		dueDays nullable:true
		effectStartDate nullable:true
		effectEndDate nullable:true
		*/
	}
}
