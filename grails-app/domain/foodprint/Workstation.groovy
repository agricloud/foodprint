package foodprint

class Workstation extends DefaultTable{

	String name
	String title
	String description=''



	
    static constraints = {
    	name unique:true, blank: false
    }
}
