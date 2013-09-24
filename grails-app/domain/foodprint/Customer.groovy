package foodprint

class Customer {
	String name
	String title
	String email=""
	String address=""

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
    	site nullable:true
    	name unique:true
    }



}
