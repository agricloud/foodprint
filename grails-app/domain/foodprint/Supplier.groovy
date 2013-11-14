package foodprint


public enum Country {
    TAIWAN,
    JAPAN,
    CHINA,
    HONGKONG
}


class Supplier {
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
    /*
    * 編號
    */
	String name


    /*
    * 名稱
    */
	String title


    /*
    * 供應商所屬國家
    */
	Country country=Country.TAIWAN

    String tel=""
    String email=""
    String address=""

    static constraints = {
        site nullable:true
    	name unique: true, blank: false
    }
}
