package foodprint
import grails.util.Environment

class HomeController {

    def index() { 
        redirect(uri: grailsApplication.config.grails.indexPath)
    }
}
