package common

class ExtJSFilters {

    def springSecurityService
    def dateService

    def filters = {
        all(controller:'*', action:'*') {
            before = {
                params.each {
                    key, value ->
                    // Transform value from Ext JS to Grails date style 
                    // 時區 (\+|\-)\d\d:\d\d
                    if (value ==~ /^\d\d\d\d\-\d\d\-\d\dT\d\d:\d\d:\d\d$/) {

                        params[key] = dateService.parseToUTC("yyyy-MM-dd'T'HH:mm:ss",value)
                        log.info "Found ${value} is a Ext JS date format, transform into Grails style"

                    }else if (value ==~ /^\d\d\d\d\-\d\d\-\d\d$/) {
                        params[key] = dateService.parseToUTC('yyyy-MM-dd',value)
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
                if(params.sort){
                    def sortJson = grails.converters.JSON.parse(params.sort)
                    params.sort = sortJson[0].property
                    params.order = sortJson[0].direction
                }
                
                def user =springSecurityService.currentUser
                if(user){
                    params["site.id"] = user.site?.id.toString()
                }

                params.criteria = {

                    if(user.username != 'admin'){
                        if(params.controller != 'site'){
                            eq('site',user.site)
                        }
                        else
                            eq('id',user.site.id)
                    }

                    if(params.filter){                           
                        def filterJson = grails.converters.JSON.parse(params.filter)
                        filterJson.each{

                            if(it.type == 'string'){
                                it.value = it.value+"%"
                            }else if(it.type == 'numeric'){
                                 it.value = it.value.toLong()
                            }else if(it.type == 'date'){
                                it.value = dateService.parseToUTC('MM/dd/yyyy',it.value)
                            }

                            if(it.comparison){

                                if(it.comparison == 'lt') lt(it.field,it.value)
                                else if(it.comparison == 'gt')gt(it.field,it.value)
                                else eq(it.field,it.value)

                            }else like(it.field,it.value)

                        }
                    }
                    if(params.nameLike){
                        like('name',params.nameLike+"%")
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
