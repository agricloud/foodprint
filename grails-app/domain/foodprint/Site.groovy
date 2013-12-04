package foodprint

class Site {

	String editor = ""
	String creator = ""
	Date dateCreated
	Date lastUpdated

	String name
	String title
	String description=""
	String address=""

	static hasMany = [
		user: User
	]

    static constraints = {
    	name unique:true, blank: false
    }
}
