package foodprint

    /*
    * 進貨單身
    */
class IncomingOrderDet {
	
    /*
    * 進貨單頭
    */
	static belongsTo=[head:IncomingOrder]
	/*
    * 訂單項次，取訂單編號最大單身項次 +1
    */
	Integer sequence


    /*
    * 品項編號
    */
    Item item


    /*
    * 批號
    */
    Batch batch


    /*
    * 進貨數量
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
    	head(unique: ['sequence'])
        site nullable:true
    }
}
