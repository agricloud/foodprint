package finder

class Item {


		String name
		String title=""
		String description=""
		Long dueDays
		Date effectStartDate
		Date effectEndDate

		static hasMany=[collectDatas:ItemCollectData, images:ItemImage]

		String editor=""			//修改者
		String creator=""		//建立者
    Date dateCreated    //建立日期
    Date lastUpdated    //修改日期
	
    static constraints = {
    	dueDays nullable:true
    	effectStartDate nullable:true
    	effectEndDate nullable:true
    }
}
