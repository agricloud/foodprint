package foodprint

class DefaultSheetDet extends DefaultTable{

	static belongsTo=[head:DefaultSheet]
    /*
    * 訂單項次，取訂單編號最大單身項次 +1
    */
	Integer sequence

    static constraints = {
    	sequence unique:'head'
    }
}
