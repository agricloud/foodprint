package foodprint

class ExtJSFilters {

    def filters = {
        all(controller:'*', action:'*') {
            before = {
                log.info "Start ${controllerName}-${actionName}-Filter"
                log.info params

                def pa

                params.each {
                    key, value ->
                    // Transform value from Ext JS to Grails date style 
                    // 時區 (\+|\-)\d\d:\d\d
                    if (value ==~ /^\d\d\d\d\-\d\d\-\d\dT\d\d:\d\d:\d\d$/) {

                        // Convert into Date object
                        params[key] = Date.parse('yyyy-MM-dd HH:mm:ss', value.replaceFirst('T', ' '))

                        // Convert into Grails style date string
                        //params[key] = "${value}Z"
                        
                        // 下面的 date 轉換方式如果有兩個 date 就會有問題
                        // Convert into GORM properties recognization
                        // params[key] = 'date.struct'
                        // params["${key}_year"] = value.substring(0,4)
                        // params["${key}_month"] = value.substring(5,7)
                        // params["${key}_day"] = value.substring(8,10)

                        // params["${key}_hour"] = value.substring(11,13)
                        // params["${key}_minute"] = value.substring(14,16)
                        // params["${key}_second"] = value.substring(17,19)

                        log.info "Found ${value} is a Ext JS date format, transform into Grails style"
                        // log.info "params[${key}] = ${params[key]}"
                    }else if (value ==~ /^\d\d\d\d\-\d\d\-\d\d$/) {

                        // Convert into Date object
                        params[key] = Date.parse('yyyy-MM-dd', value)

                        log.info "Found ${value} is a Ext JS date format, transform into Grails style"
                    }



                    // 參考連結 http://grails.org/doc/latest/guide/single.html#dataBinding
                    // 其中：An association property can be set to null by passing the literal String "null".
                    // 可能風險，null 值是真的要作為 null 值，而不是文字的 'null' 值 
                    if(!value && key.endsWith(".id")){
                        value="null"
                    }


                }


                // 如果從前端 extjs 傳進來需要進行分頁處理，轉換為 grails 處理分頁之 params
                if(params.start && params.limit){
                    params.offset = params.int('start')?:0
                    params.max = params.int('limit')?:50
                }


                params.criteria = {
                    if(params.filter){
                        def filterJson = grails.converters.JSON.parse(params.filter)
                        filterJson.each{


                            if(it.type == 'string'){
                                it.value = it.value+"%"
                            }else if(it.type == 'numeric'){
                                 it.value = it.value.toLong()
                            }else if(it.type == 'date'){
                                 it.value = Date.parse('MM/dd/yyyy', it.value)
                            }

                            if(it.comparison){

                                if(it.comparison == 'lt') lt(it.field,it.value)
                                else if(it.comparison == 'gt')gt(it.field,it.value)
                                else eq(it.field,it.value)

                            }else like(it.field,it.value)

                        }
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
