package foodprint

class ItemRoute {

	Item item
	int sequence
	Workstation workstation
	Operation operation

    static constraints = {
    	item(unique: ['sequence'])
    }
}
