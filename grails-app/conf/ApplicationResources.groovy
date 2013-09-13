
//import org.codehaus.groovy.grails.web.context.ServletContextHolder as SCH
def __bundleName = 'foodprint'


modules = {
    application {
        resource url:'js/application.js'
    }

 	overrides {

		bootstrap {
		    defaultBundle __bundleName
		}

	}   
	// extjs4_dev {
	// 	defaultBundle __bundleName

	// 	resource url: 'extjs4/resources/ext-theme-neptune/ext-theme-neptune-all.css'		
	// 	resource url: 'ext/ext-all.js'
	// 	resource url: 'ext/ext-theme-neptune.js'		
	// 	resource url: 'app.js'

	// 	getFilesForPath('app').each {
 //      resource url: it
 //    }

	// }	
 //  extjs4 {
	// 	defaultBundle __bundleName
	// 	resource url: 'extjs4/resources/foodprint-all.css'
	// 	resource url: 'extjs4/all-classes.js'
	// }	
}



// def getFilesForPath(path) {

//     def webFileCachePaths = []

//     def servletContext = SCH.getServletContext()

//     //context isn't present when testing in integration mode. -jg
//     if(!servletContext) return webFileCachePaths

//     def realPath = servletContext.getRealPath('/')

//     def appDir = new File("$realPath/$path")

//     appDir.eachFileRecurse {File file ->
//         if (file.isDirectory() || file.isHidden()) return
//         webFileCachePaths << file.path.replace(realPath, '')
//     }

//     webFileCachePaths
// }