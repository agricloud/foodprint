package foodprint

    /*
    * 進貨單
    */
class IncomingOrder {

	static hasMany=[details:IncomingOrderDet]


    /*
    * 單別
    */
	String nameType
	

    /*
    * 單號
    */
	String name


    /*
    * 供應商
    */

    Supplier supplier


    /*
    * 進貨日期
    */
    Date incomingDate=new Date()
    

    /*
    * 單據日期
    */
    Date orderDate=new Date()


    /**
     * 廠別
     */
    Site site

    /**
     * 修改者
     */
    String editor = ""

    /**
     * 建立者
     */
    String creator = ""

    /**
     * 建立日期（自動欄位）
     */
    Date dateCreated

    /**
     * 修改日期（自動欄位）
     */
    Date lastUpdated
    
    static constraints = {
    	name unique:'nameType'
        site nullable:true

    }
}
