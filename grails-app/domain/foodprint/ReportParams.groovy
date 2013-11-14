/*
 * 履歷要收集的資料項目，其中可以設定項目要收集的對象
*/

package foodprint

class ReportParams {
    /**
     * 廠別
     */
    Site site

    /**
     * 修改者
     */
    String editor = ""

    /**
     * 建立者
     */
    String creator = ""

    /**
     * 建立日期（自動欄位）
     */
    Date dateCreated

    /**
     * 修改日期（自動欄位）
     */
    Date lastUpdated

    /*
    * 履歷收集單頭
    */
	static belongsTo=[report:Report]


    /*
    * 收集參數
    */
	Param param

    /*
    * 設定在某工作站需要進行資料收集
    */
	Workstation workstation 

    /*
    * 設定在某託外廠商需要進行資料收集
    */
    Supplier supplier

    /*
    * 設定在某品項要進行資料收集
    */
	Item item 	

	/*
    * 設定在某製程要進行資料收集
    */
	Operation operation

    /*
    * 設定在某批號要進行資料收集
    */
	Batch batch


    static constraints = {
        site nullable:true
    	workstation nullable:true
        supplier nullable:true
    	item nullable:true
    	operation nullable:true
    	param unique:'report'
    	batch nullable:true
    }
}
