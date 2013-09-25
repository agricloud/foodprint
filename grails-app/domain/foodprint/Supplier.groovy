package foodprint


public enum Country {
    TAIWAN
}


class Supplier extends DefaultTable{

    /*
    * 編號
    */
	String name


    /*
    * 名稱
    */
	String title


    /*
    * 供應商所屬國家
    */
	String country=foodprint.Country.TAIWAN



    static constraints = {
    	name unique: true
    }
}
