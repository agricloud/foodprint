package foodprint


    /*
    * 記錄生產的 batch 是由哪些元物料的 batch 所組成
    */
class BatchSource extends DefaultTable {


    /*
    * 最接近的直接上層批號
    */
	static belongsTo = [parent: Batch]


    /*
    * 目前子批號
    */
	Batch batch


    /*
    * 最上層成品批號
    */
	Batch topParent

    /*
    * 是否是最底層批號
    */
    Boolean isEndBatch = false

    static constraints = {
    	batch unique: 'parent' 
    	topParent nullable: true
    }
}
