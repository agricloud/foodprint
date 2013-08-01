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
                        params[key] = "${value}Z"

                        log.info "Found ${value} is a Ext JS date format, transform into Grails style"
                        log.info "params[${key}] = ${value}"
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
