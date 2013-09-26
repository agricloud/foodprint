package foodprint

class Customer extends DefaultTable{
	String name
	String title
	String email=""
	String address=""


    static constraints = {
    	name unique:true
    }



}
