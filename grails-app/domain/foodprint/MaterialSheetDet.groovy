package foodprint



    /*
    * 領料單身
    */
class MaterialSheetDet {

	/*
    * 領退料單頭
    */
	static belongsTo=[head:MaterialSheet]


    /*
    * 制令
    */
    ManufactureOrder manufactureOrder


    /*
    * 品項編號，材料編號
    */
    Item item


    /*
    * 批號
    */
    Batch batch


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
    }
}
