package foodprint


    /*
    * 客戶訂單單身
    */
class CustomerOrderDet {

	/*
    * 訂單單頭
    */
	static hasOne=[head:CustomerOrder]
	
    /*
    * 訂單項次，取訂單編號最大單身項次 +1
    */
	Integer sequence


    /*
    * 關連品項編號
    */
	Item item

    /*
    * 訂單數量
    */
	Integer qty


    static constraints = {
    	head(unique: ['sequence'])
    }
}
