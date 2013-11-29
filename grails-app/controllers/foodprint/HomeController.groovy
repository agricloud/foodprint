package foodprint

class HomeController {

    def index = { 
        redirect(uri: grailsApplication.config.grails.indexPath)
    }
}
