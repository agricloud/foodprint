package foodprint

class Supplier {

    Site site
    String editor
    String creator
    Date dateCreated
    Date lastUpdated

	String name
	String title
	Country country=Country.TAIWAN
    String tel
    String email
    String address

    static constraints = {
        site nullable:true
        editor nullable:true
        creator nullable:true
    	name unique: true, blank: false
        tel nullable:true
        email nullable:true
        address nullable:true
    }
}
