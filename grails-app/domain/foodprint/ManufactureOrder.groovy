package foodprint


    /*
    * 製造命令
    */
class ManufactureOrder {

    /*
    * 制令編號
    */
	String name


    /*
    * 制令單別
    */
	String nameType=""



    /*
    * 訂單單身
    */
    CustomerOrderDet customerOrderDet



    /*
    * 品項編號
    */
    Item item



    /*
    * 批號
    */
    Batch batch

    /*
    * 生產量
    */
    Integer qty

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
    	site nullable:true
        customerOrderDet nullable:true
    }
}
