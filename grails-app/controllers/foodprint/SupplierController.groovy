package foodprint

import org.springframework.dao.DataIntegrityViolationException
import org.apache.commons.lang.exception.ExceptionUtils
import grails.converters.JSON

class SupplierController {

    def domainService

    /*
    * 將定義在 supplier domain 中的 enum CountryType 轉換為 json
    */
    def countryJson(){

        render (contentType: 'application/json') {
            [CountryJson:foodprint.Country.values()]
        }
    }


}