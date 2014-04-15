package foodprint


import grails.converters.JSON

class FoodpaintController {

    def grailsApplication

    def index = {

        def queryParams = processParams()

        def url = "${grailsApplication.config.grails.foodpaint.service.server.url}/${params.foodpaintController}/index"
        
        render (contentType: 'application/json') {
            doCallFoodpaint(url, queryParams)
        }
    }

    def show = {

        def queryParams = processParams()

        def url = "${grailsApplication.config.grails.foodpaint.service.server.url}/${params.foodpaintController}/show"
        
        render (contentType: 'application/json') {
            doCallFoodpaint(url, queryParams)
        }
    }


    def create = {

        def queryParams = processParams()

        def url = "${grailsApplication.config.grails.foodpaint.service.server.url}/${params.foodpaintController}/create"
        
        render (contentType: 'application/json') {
            doCallFoodpaint(url, queryParams)
        }
    }

    def save = {

        def queryParams = processParams()

        def url = "${grailsApplication.config.grails.foodpaint.service.server.url}/${params.foodpaintController}/save"
        
        render (contentType: 'application/json') {
            doCallFoodpaint(url, queryParams)
        }
    }

    def update = {

        def queryParams = processParams()

        def url = "${grailsApplication.config.grails.foodpaint.service.server.url}/${params.foodpaintController}/update"
        
        render (contentType: 'application/json') {
            doCallFoodpaint(url, queryParams)
        }
    }

    def delete = {

        def queryParams = processParams()

        def url = "${grailsApplication.config.grails.foodpaint.service.server.url}/${params.foodpaintController}/delete"
        
        render (contentType: 'application/json') {
            doCallFoodpaint(url, queryParams)
        }
    }
    
    def action = {

        def queryParams = processParams()

        def url = "${grailsApplication.config.grails.foodpaint.service.server.url}/${params.foodpaintController}/${params.foodpaintAction}"
        
        render (contentType: 'application/json') {
            doCallFoodpaint(url, queryParams)
        }
    }

    def private processParams(){

        def queryParams = [:]
        params.each {
            key, value ->

            if(key!='action' && key!='controller'&& key!='criteria'){

                if(value instanceof Date)
                    queryParams.put(key,value.format("yyyy-MM-dd'T'HH:mm:ss"))

                if(value instanceof String)
                    queryParams.put(key,value)
            }

        }
        return queryParams
    }

    def private doCallFoodpaint(url, queryParams){
        println url
        println 'queryParams == '+queryParams

        withHttp(uri: url) {
            // def html = get(path : '/search', query : [q:'Groovy'])
            def response = get( query : queryParams)
        }

    }
}
