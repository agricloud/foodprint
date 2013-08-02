package foodprint

class ItemRoute {

	Item item 
	int sequence
	Workstation workstation

    static constraints = {
    	item(unique: ['sequence'])
    }
}
