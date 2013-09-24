package foodprint

class ItemRoute {

	static belongsTo = [item: Item]
	int sequence
	Operation operation
	Workstation workstation

    static constraints = {
    	sequence unique:'item'
    }

    public String toString(){
    	"品項：${item.title}，途程項次:${sequence}"
    }
}
