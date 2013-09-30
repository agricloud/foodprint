/*
 * 記錄各種履歷類型主檔
*/

package foodprint

public enum ReportType {
	MATERIAL,
	INSPECT,
	NUTRITION,	// 營養
	OTHER

}

class Report extends DefaultTable{

	String name
	String title
	String decription = ""
	Date effectStartDate
	Date effectEndDate

	ReportType reportType = foodprint.ReportType.OTHER


    static constraints = {
    	effectStartDate nullable:true
    	effectEndDate nullable:true
    }
}
