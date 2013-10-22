
//import org.codehaus.groovy.grails.web.context.ServletContextHolder as SCH
def __bundleName = 'foodprint'


modules = {
    application {
        resource url:'js/application.js'
    }

    // overrides {

    //     bootstrap {
    //         defaultBundle __bundleName
    //     }
    //     jquery {
    //         defaultBundle __bundleName
    //     }

    // }   

    art {
        defaultBundle __bundleName
        resource url: 'css/bootstrap.css'  
        resource url: 'css/magnific-popup.css'        
        resource url: 'css/layout.css'
        resource url: 'css/style.css'
        resource url: 'css/flexslider.css'

        resource url: 'js/bootstrap.js'  
        resource url: 'js/magnific-popup.js'   
        resource url: 'js/jquery.flexslider.min.js'
        resource url: 'js/plugins.js'
    }


    // extjs4_dev {
    //  defaultBundle __bundleName

    //  resource url: 'extjs4/resources/ext-theme-neptune/ext-theme-neptune-all.css'        
    //  resource url: 'ext/ext-all.js'
    //  resource url: 'ext/ext-theme-neptune.js'        
    //  resource url: 'app.js'

    //  getFilesForPath('app').each {
 //      resource url: it
 //    }

    // }    
 //  extjs4 {
    //  defaultBundle __bundleName
    //  resource url: 'extjs4/resources/foodprint-all.css'
    //  resource url: 'extjs4/all-classes.js'
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