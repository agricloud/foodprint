package foodprint

class DefaultSheet extends DefaultTable{

	static hasMany=[details:DefaultSheetDet]
    /*
    * 單別
    */
	String typeName=""


    /*
    * 單號
    */
    String name=""

    static mapping = {
        tablePerHierarchy false
    }

    static constraints = {
    	name unique:'typeName'
    }
}
