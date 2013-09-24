@artifact.package@class @artifact.name@ {

	Site site 			//公司別
	String editor=""	//修改者
	String creator=""	//建立者
	Date dateCreated    //建立日期
	Date lastUpdated    //修改日期
    static constraints = {
    	site nullable:true
    }
}
