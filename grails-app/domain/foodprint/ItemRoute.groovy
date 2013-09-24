package foodprint

class ItemRoute {

	static belongsTo = [item: Item]
	int sequence
	Operation operation
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
    	sequence unique:true
        site nullable:true
    }

    public String toString(){
    	"品項：${item.title}，途程項次:${sequence}"
    }
}
