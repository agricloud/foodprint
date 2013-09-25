/*
 * 記錄各種履歷類型主檔
*/

package foodprint

class Report extends DefaultTable{

	String name
	String title
	String decription=""
	Date effectStartDate
	Date effectEndDate


    static constraints = {
    	effectStartDate nullable:true
    	effectEndDate nullable:true
    }
}
