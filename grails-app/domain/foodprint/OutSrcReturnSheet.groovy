package foodprint


    /*
    * 託外退貨單
    */
class OutSrcReturnSheet extends DefaultSheet{

	static hasMany=[details:OutSrcReturnSheetDet]


    /*
    * 退貨廠商
    */
	Supplier supplier



    static constraints = {

    }
}
