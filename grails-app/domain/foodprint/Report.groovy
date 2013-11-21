/*
 * 記錄各種履歷類型主檔
*/

package foodprint

class Report {

	Site site
	String editor = ""
	String creator = ""
	Date dateCreated
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
