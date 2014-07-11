/*
 * 履歷要收集的資料項目，其中可以設定項目要收集的對象
*/

package foodprint

class ReportParams {

    Site site
    String editor
    String creator
    Date dateCreated
    Date lastUpdated

	static belongsTo=[report:Report]
	Param param
	Workstation workstation 
    Supplier supplier
	Item item 	
	Operation operation

    static constraints = {
        site nullable:true
        editor nullable:true
        creator nullable:true
        param(unique:['report','site'])
    	workstation nullable:true
        supplier nullable:true
    }
}
