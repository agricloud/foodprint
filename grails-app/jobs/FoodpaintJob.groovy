
class FoodpaintJob {
    def foodpaintService
    static triggers = {
        simple name: 'mySimpleTrigger', startDelay: 5000, repeatInterval: 600000  
    }

    def group = "MyGroup"
    //由於paint/print連同一資料庫 暫不匯入
    // def execute(){
    //     log.info "execute foodpaintService.doDataImport()"
    //     foodpaintService.doDataImport()
    // }
}