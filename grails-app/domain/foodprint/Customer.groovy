package foodprint

class Customer {

	Site site
	String editor = ""
	String creator = ""
	Date dateCreated
	Date lastUpdated

	String name
	String title
	String email=""
	String address=""

    static constraints = {
		site nullable:true
    	name unique:true
    }



}
