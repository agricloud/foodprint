/*
* 收集項目定義
*/

package foodprint

public enum ParamType {
    INTEGER,
    STRING,
    BOOLEAN,
    FILE,
    IMAGE
}

class Param {

	String name
	String title
	String defaultValue="" //預設值
	ParamType paramType //收集類型
	String description=""


    /*
    * 收集資料範圍下限
    */
	String lower=""

    /*
    * 收集資料範圍上限
    */
	String upper=""


    /*
    * 收集資料值單位
    */
	String unit=""

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
    	paramType nullable:true
    	name unique:true


    }
}
