package foodprint

class Operation {

	Site site
	String editor = ""
	String creator = ""
	Date dateCreated
	Date lastUpdated	

	String name //製程編號
	String title //製程名稱
	String description="" // 製程敘述

    static constraints = {
		site nullable:true
    	name unique:true, blank: false
    }
}