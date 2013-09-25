package foodprint



    /*
    * 領料單身
    */
class MaterialSheetDet extends DefaultSheetDet{


    /*
    * 項次
    */
    Integer sequence
    
    /*
    * 制令
    */
    ManufactureOrder manufactureOrder


    /*
    * 品項編號，材料編號
    */
    Item item


    /*
    * 批號
    */
    Batch batch



    static constraints = {
    }
}
