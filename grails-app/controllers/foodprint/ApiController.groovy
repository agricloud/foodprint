package foodprint

class ApiController {
	def grailsApplication
    def readSysConfig() { 
    	def config = [:]

    	config.foodpaintUrl = grailsApplication.config.grails.foodpaint.service.server.url

    	render(contentType: "application/json"){
    		config
    	}

    }
}
