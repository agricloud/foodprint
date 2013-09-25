package foodprint



    /*
    * 退貨單身
    */
class ReturnSheetDet extends DefaultSheetDet{

    /*
    * 品項編號
    */
    Item item


    /*
    * 批號
    */
    Batch batch


    /*
    * 進貨數量
    */
    Integer qty

    static constraints = {
    }
}
