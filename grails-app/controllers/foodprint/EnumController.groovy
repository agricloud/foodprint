package foodprint

class EnumController {

    def enumService

    def indexCountry = {

        render (contentType: 'application/json') {
            [data:enumService.values(foodprint.Country)]
        }
    }

    def indexParamType = {

        render (contentType: 'application/json') {
            [data:enumService.values(foodprint.ParamType)]
        }
    }

    def indexReportType = {
        render (contentType: 'application/json') {
            [data:enumService.values(foodprint.ReportType)]
        }
    }
}
