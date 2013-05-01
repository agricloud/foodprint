package finder

class Resumes {

		String name
		String decription=""
		Date effectStartDate
		Date effectEndDate

		static hasMany=[collectDatas:ResumesCollectData, images:ResumesImage]

		String editor=""			//修改者
		String creator=""		//建立者
    Date dateCreated    //建立日期
    Date lastUpdated    //修改日期
    static constraints = {
    	effectStartDate nullable:true
    	effectEndDate nullable:true

    }
}
