/*
 * 履歷要收集的資料項目，其中可以設定項目要收集的對象
*/

package foodprint

class ReportParams {

	Report report
	Param param

	Workstation workstation // 設定在某工作站需要進行資料收集
	Item item 				// 設定在某品相要進行資料收集
	Operation operation
	Site site


	// Site site
	String editor=""	//修改者
	String creator=""	//建立者
	Date dateCreated    //建立日期
	Date lastUpdated    //修改日期
    static constraints = {
    	// site nullable:true
    	workstation nullable:true
    	item nullable:true
    	operation nullable:true
    	site nullable:true
    }
}
