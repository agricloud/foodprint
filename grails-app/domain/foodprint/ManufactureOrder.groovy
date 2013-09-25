package foodprint


    /*
    * 製造命令
    */
class ManufactureOrder extends DefaultSheet{

    /*
    * 訂單單身
    */
    CustomerOrderDet customerOrderDet

    static hasMany = [
        stockInSheetDet: StockInSheetDet,
        materialSheetDet: MaterialSheetDet
    ]

    /*
    * 品項編號
    */
    Item item

    /*
    * 批號
    */
    Batch batch

    /*
    * 生產量
    */
    Integer qty


    static constraints = {
        customerOrderDet nullable:true
    }
}
