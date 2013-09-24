package foodprint

    /*
    * 領退料單頭
    */
class MaterialSheet {

	static hasMany=[details:MaterialSheetDet]

    /*
    * 單別
    */
    String nameType=""


    /*
    * 單號
    */
   	String name


    /*
    * 生產線別，加工廠商
    */
    Workstation workstation

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
    	name unique:'nameType'
    }
}
