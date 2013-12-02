package foodprint

import grails.util.Environment

class ApiController {
	def grailsApplication
    def readSysConfig = { 
    	def config = [:]

    	config.foodpaintUrl = grailsApplication.config.grails.foodpaint.service.server.url
    	config.environment = Environment.current.name

    	log.debug "\n extjs config: \n"+ config 
    	render(contentType: "application/json"){
    		config
    	}

    }
}
