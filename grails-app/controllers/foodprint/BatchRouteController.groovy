package foodprint

import grails.converters.JSON

class BatchRouteController {

    static allowedMethods = [create: "POST", update: "PUT", delete: "DELETE"]

    def messageSource
    def batchRouteService
    def domainService

    def index() {
        redirect(action: "list", params: params)
    }

    def show() {
        log.debug "${controllerName}-${actionName}"
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        def batchRoute=Batch.findById(params.batch.id).batchRoutes
        /** 
	*[batchRouteInstanceList:batchRoute.collect(), batchRouteInstanceTotal: batchRoute.size()]
	*or use
	*[batchRouteInstanceList: BatchRoute.list(params), batchRouteInstanceTotal: BatchRoute.count()]
        **/
        [batchRouteInstanceList:batchRoute.collect(), batchRouteInstanceTotal: batchRoute.size()]
    }

    def listJson(Integer max) {

        JSON.use('deep')
        def converter=list(max) as JSON
        converter.render(response)
    }


    def create() {
        def batchRouteInstance = new BatchRoute(params)
        render (contentType: 'text/json') {
            domainService.save(batchRouteInstance)
        }
    }


    def update() {

        def batchRouteInstance=BatchRoute.get(params.id)
        render (contentType: 'text/json') {
            domainService.save(batchRouteInstance, params)
        }

    }

    def delete() {

        def batchRouteInstance=BatchRoute.get(params.id)
        render (contentType: 'text/json') {
            domainService.delete(batchRouteInstance)
        }


    }
}
