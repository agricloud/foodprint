package foodprint

class Workstation {

	Site site
	String editor = ""
	String creator = ""
	Date dateCreated
	Date lastUpdated

	String name
	String title
	String description=''

    static constraints = {
		site nullable:true
    	name unique:true, blank: false
    }
}
