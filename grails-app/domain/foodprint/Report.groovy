/*
 * 記錄各種履歷類型主檔
*/

package foodprint

public enum ReportType {
	INSPECT,
	NUTRITION,	// 營養
	OTHER

}

class Report {
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
	String description = ""
	Date effectStartDate
	Date effectEndDate

	ReportType reportType = foodprint.ReportType.OTHER


    static constraints = {
		site nullable:true
    	effectStartDate nullable:true
    	effectEndDate nullable:true
    }
}
