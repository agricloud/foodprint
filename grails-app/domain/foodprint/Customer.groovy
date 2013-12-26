package foodprint

class Customer {

	Site site
	String editor
	String creator
	Date dateCreated
	Date lastUpdated

	String name
	String title
	String email
	String address

    static constraints = {
		site nullable:true
		editor nullable:true
		creator nullable:true
    	name unique:true
    	title nullable:true
    	email nullable:true
    	address nullable:true
    }



}
