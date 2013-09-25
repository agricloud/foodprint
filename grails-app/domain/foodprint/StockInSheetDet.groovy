package foodprint

class StockInSheetDet extends DefaultSheetDet{


    /*
    * 品項編號
    */

    Item item


    /*
    * 批號
    */
    Batch batch


    /*
    * 庫別
    */

    String warehouse

    /*
    * 儲位
    */
    String stockLocation

    static hasOne = [manufactureOrder: ManufactureOrder]

    static constraints = {      
    }
}
