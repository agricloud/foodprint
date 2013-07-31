package foodprint
import grails.util.Environment

class HomeController {

    def index() { 
      if (Environment.current == Environment.DEVELOPMENT) {
         redirect(uri: "/development/app.html")
      }
      else {
         redirect(uri: "/production/index.html")
      }

    }
}
