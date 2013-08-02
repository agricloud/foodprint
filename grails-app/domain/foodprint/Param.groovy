/*
* 收集項目定義
*/

package foodprint

public enum ParamType {
    INTEGER,
    STRING,
    BOOLEAN,
    LIST;
}

class Param {

	String title
	String defaultValue //預設值
	ParamType paramType //收集類型
	String description

	// Site site
	String editor=""	//修改者
	String creator=""	//建立者
	Date dateCreated    //建立日期
	Date lastUpdated    //修改日期	


    static constraints = {
    	// site nullable:true

    }
}
