package foodprint

class Operation {
	
	String name //製程編號
	String title //製程名稱
	String description="" // 製程敘述
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
    static constraints = {
    	name unique:true
    	site nullable:true
    }
}