package foodprint


    /*
    * 客戶訂單
    */
class CustomerOrder extends DefaultSheet{



	/*
	* 客戶編號
	*/
	Customer customer

	/*
	* 到期日
	*/
	Date dueDate=new Date()

	/*
	* 多個訂單單身 
	*/

	static hasMany=[details:CustomerOrderDet]


    static constraints = {
    	dueDate nullable:true
    	customer nullable:true
    }
}
