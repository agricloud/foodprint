package foodprint

class Operation extends DefaultTable{
	
	String name //製程編號
	String title //製程名稱
	String description="" // 製程敘述

    static constraints = {
    	name unique:true, blank: false
    }
}