package foodprint

class Customer {
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
	String name
	String title
	String email=""
	String address=""


    static constraints = {
		site nullable:true
    	name unique:true
    }



}
