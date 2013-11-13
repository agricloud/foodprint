package foodprint

class ItemRoute {
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
	static belongsTo = [item: Item]
	int sequence
	Operation operation
	Workstation workstation


    static constraints = {
        site nullable:true
    	sequence unique:'item'
    }

    public String toString(){
    	"品項：${item.title}，途程項次:${sequence}"
    }
}
