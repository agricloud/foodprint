/*
 * 記錄各種履歷類型主檔
*/

package foodprint

class Report {

	String name
	String title
	String decription=""
	Date effectStartDate
	Date effectEndDate

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
    	effectStartDate nullable:true
    	effectEndDate nullable:true
    	site nullable:true
    }
}
