package foodprint

class BatchRoute {

	Batch batch
	Workstation workstation
	Operation operation
	int sequence
	 

    static constraints = {
    	operation nullable:true
    }
}
