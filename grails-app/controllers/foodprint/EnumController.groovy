package foodprint

class EnumController {

    def enumService

    def indexCountry(){

        render (contentType: 'application/json') {
            [Country:enumService.values(foodprint.Country)]
        }
    }

    def indexParamType(){

        render (contentType: 'application/json') {
            [ParamType:enumService.values(foodprint.ParamType)]
        }
    }

    def indexReportType(){
        render (contentType: 'application/json') {
            [ReportType:enumService.values(foodprint.ReportType)]
        }
    }
}
