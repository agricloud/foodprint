package foodprint


    /*
    * 銷貨單單身
    */
class SaleSheetDet extends DefaultSheetDet{


    /*
    * 品項
    */
    Item item


    /*
    * 訂單單身
    */
    CustomerOrderDet customerOrderDet


    /*
    * 批號
    */
    Batch batch


    /*
    * 數量
    */
    Integer qty

    static constraints = {
    }
}
