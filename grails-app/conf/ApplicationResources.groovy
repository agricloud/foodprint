
import org.codehaus.groovy.grails.web.context.ServletContextHolder as SCH
def __bundleName = 'finder'


modules = {

	extjs4 {
		//defaultBundle 'coding-tools'
		defaultBundle __bundleName
		//css
		resource url: 'extjs4/resources/ext-theme-neptune/ext-theme-neptune-all.css'		
		resource url: 'extjs4/ext-all.js'
		resource url: 'extjs4/ext-theme-neptune.js'		
		resource url: 'app.js'

	}	

	//載入所有的自定義 extjs class 
	extjs4App {
		defaultBundle __bundleName
		getFilesForPath('/app').each {
      resource url: it
    }
	}	

}

def getFilesForPath(path) {

    def webFileCachePaths = []

    def servletContext = SCH.getServletContext()

    //context isn't present when testing in integration mode. -jg
    if(!servletContext) return webFileCachePaths

    def realPath = servletContext.getRealPath('/')

    def appDir = new File("$realPath/$path")

    appDir.eachFileRecurse {File file ->
        if (file.isDirectory() || file.isHidden()) return
        webFileCachePaths << file.path.replace(realPath, '')
    }

    webFileCachePaths
}