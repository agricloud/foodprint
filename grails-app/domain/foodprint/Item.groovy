package foodprint

/**
 * 履歷明細項目（這邊打類別說明，暫定請蹤影做修改）
 * 
 * @author smlsun@gmail.com, ...
 * @version 1.0
 */
class Item {
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
    /**
     * 項目名稱，必填欄位
     */
    String name

    /**
     * 項目標題
     */
	String title=""

	/**
	 * 項目描述說明文字
	 */
	String description = ""


    /*
    * 規格敘述
    */
    String spec=""


    /*
    * 單位
    */
    String unit=""

    /**
     * 記錄期限多少天數
     */
	Long dueDays

	/**
	 * 有效起始日期
	 */
	Date effectStartDate

	/**
	 * 有效結束日期
	 */
	Date effectEndDate



    /**
     * 一對多關係（項目圖片）
     */
	static hasMany=[itemRoutes:ItemRoute]

    /**
     * 廠別可以不設定<br/>
     * 有效起始與結束日期可以不設定
     */
	static constraints = {
        site nullable:true
		name unique: true, blank: false
		dueDays nullable: true
		effectStartDate nullable: true
		effectEndDate nullable: true
	}
	public String toString(){
    	"品項編號：${name},品項名稱：${title}"
    }
}
