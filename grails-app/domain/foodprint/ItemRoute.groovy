package foodprint

class ItemRoute extends DefaultTable{

	static belongsTo = [item: Item]
	int sequence
	Operation operation
	Workstation workstation


    static constraints = {
    	sequence unique:true
    }

    public String toString(){
    	"品項：${item.title}，途程項次:${sequence}"
    }
}
