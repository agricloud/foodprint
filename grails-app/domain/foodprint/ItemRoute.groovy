package foodprint

class ItemRoute {

    Site site
    String editor = ""
    String creator = ""
    Date dateCreated
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
