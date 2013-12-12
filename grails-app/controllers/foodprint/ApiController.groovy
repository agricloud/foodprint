package foodprint

import grails.util.Environment

class ApiController {
	def grailsApplication
    def springSecurityService
    def readSysConfig = { 
    	def config = [:]

    	config.foodpaintUrl = grailsApplication.config.grails.foodpaint.service.server.url
    	config.environment = Environment.current.name
        config.username = springSecurityService?.currentUser?.username

    	log.debug "\n extjs config: \n"+ config 
    	render(contentType: "application/json"){
    		config
    	}

    }
}
