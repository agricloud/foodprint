package foodprint

class Customer {

	Site site
	String editor
	String creator
	Date dateCreated
	Date lastUpdated

	String name
	String title
	String tel
	String fax
	String contact
	String email
	String address
	/**
     * 送貨地址
     */
	String shippingAddress

    static constraints = {
		site nullable:true
		editor nullable:true
		creator nullable:true
    	name unique:true
    	tel nullable:true
    	fax nullable:true
    	contact nullable:true
    	email nullable:true
    	address nullable:true
    	shippingAddress nullable:true
    }



}
