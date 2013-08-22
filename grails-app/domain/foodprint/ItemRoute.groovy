package foodprint

class ItemRoute {

	Item item
	int sequence
	Operation operation
	Workstation workstation

    static constraints = {
    	item(unique: ['sequence'])
    }
}
