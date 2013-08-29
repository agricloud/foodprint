package foodprint

class CustomerOrder {

	/*
	* 訂單編號
	*/
	String name
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
    	name unique:true
    	dueDate nullable:true
    	customer nullable:true
    }
}
