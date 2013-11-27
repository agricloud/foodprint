/*
* 收集項目定義
*/

package foodprint

class Param {

    Site site
    String editor = ""
    String creator = ""
    Date dateCreated
    Date lastUpdated

	String name
	String title
	String defaultValue="" //預設值
	ParamType paramType=ParamType.FLOAT //收集類型
	String description=""
	String lower=""
	String upper=""
	String unit=""

    static constraints = {
        site nullable:true
    	paramType nullable:true
    	name unique:true, blank: false


    }
}
