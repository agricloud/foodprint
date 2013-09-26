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

class Param extends DefaultTable{

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



    static constraints = {
    	paramType nullable:true
    	name unique:true


    }
}
