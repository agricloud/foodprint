package foodprint

class BatchRoute {

	static belongsTo=[batch:Batch]
	Workstation workstation
	Operation operation
	int sequence
	 

    static constraints = {
    	sequence unique:'batch'
    }
}
