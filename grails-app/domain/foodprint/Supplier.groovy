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
    String fax
    String contact
    String email
    String address

    static constraints = {
        site nullable:true
        editor nullable:true
        creator nullable:true
    	name(unique:['site'])
        name blank: false
        tel nullable:true
        fax nullable:true
        contact nullable:true
        email nullable:true
        address nullable:true
    }
}
