package finder

class Item {


		String name
		String title
		String description
		Long dueDays
		Date effectStartDate
		Date effectEndDate

		User editor			//修改者
		User creator		//建立者
    Date dateCreated    //建立日期
    Date lastUpdated    //修改日期

    static constraints = {
    }
}
