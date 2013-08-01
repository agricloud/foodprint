package foodprint

class ExtJSFilters {

    def filters = {
        all(controller:'*', action:'*') {
            before = {
                log.info "Start ${controllerName}-${actionName}-Filter"

                params.each {
                    key, value ->

                    // Transform value from Ext JS to Grails date style
                    if (value ==~ /^\d\d\d\d\-\d\d\-\d\dT\d\d:\d\d:\d\d$/) {

                        // Convert into Date object
                        //params[key] = Date.parse('yyyy-MM-dd HH:mm:ss', value.replaceFirst('T', ' '))

                        // Convert into Grails style date string
                        //params[key] = "${value}Z"
                        
                        // Convert into GORM properties recognization
                        params[key] = 'date.struct'
                        params["${key}_year"] = value.substring(0,4)
                        params["${key}_month"] = value.substring(5,7)
                        params["${key}_day"] = value.substring(8,10)

                        params["${key}_hour"] = value.substring(11,13)
                        params["${key}_minute"] = value.substring(14,16)
                        params["${key}_second"] = value.substring(17,19)

                        log.info "Found ${value} is a Ext JS date format, transform into Grails style"
                        log.info "params[${key}] = ${params[key]}"
                    }
                }
            }
            after = { Map model ->

            }
            afterView = { Exception e ->

            }
        }
    }
}
