package foodprint

class ItemRoute {

    Site site
    String editor
    String creator
    Date dateCreated
    Date lastUpdated
	
    static belongsTo = [item: Item]
	int sequence
	Operation operation
	Workstation workstation
    Supplier supplier

    static constraints = {
        site nullable:true
        editor nullable:true
        creator nullable:true
    	sequence(unique:['item','site'])
        workstation nullable:true
        supplier  nullable:true
    }

    public String toString(){
    	"品項：${item.title}，途程項次:${sequence}"
    }
}
