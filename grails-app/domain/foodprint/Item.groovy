package foodprint

class Item {

    Site site
    String editor
    String creator
    Date dateCreated
    Date lastUpdated

    String name
	String title
	String description
    String spec
    String unit
	Long dueDays
	Date effectStartDate
	Date effectEndDate

	static hasMany=[itemRoutes:ItemRoute]

	static constraints = {
        site nullable:true
        editor nullable:true
		creator nullable:true
		name unique: true, blank: false
		description nullable:true
		spec nullable:true
		unit nullable:true
		dueDays nullable: true
		effectStartDate nullable: true
		effectEndDate nullable: true
	}

	public String toString(){
    	"品項編號：${name},品項名稱：${title}"
    }
}
