
class FoodpaintJob {
    def foodpaintService
    static triggers = {
        simple name: 'mySimpleTrigger', startDelay: 1000, repeatInterval: 600000  
    }

    def group = "MyGroup"
    def execute(){
        log.info "execute foodpaintService.doDataImport()"
        foodpaintService.doDataImport()
    }
}