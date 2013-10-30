// locations to search for config files that get merged into the main config;
// config files can be ConfigSlurper scripts, Java properties files, or classes
// in the classpath in ConfigSlurper format

// grails.config.locations = [ "classpath:${appName}-config.properties",
//                             "classpath:${appName}-config.groovy",
//                             "file:${userHome}/.grails/${appName}-config.properties",
//                             "file:${userHome}/.grails/${appName}-config.groovy"]

// if (System.properties["${appName}.config.location"]) {
//    grails.config.locations << "file:" + System.properties["${appName}.config.location"]
// }

grails.app.context = '/'
grails.project.groupId = appName // change this to alter the default package name and Maven publishing destination

// The ACCEPT header will not be used for content negotiation for user agents containing the following strings (defaults to the 4 major rendering engines)
grails.mime.disable.accept.header.userAgents = ['Gecko', 'WebKit', 'Presto', 'Trident']
grails.mime.types = [
    all:           '*/*',
    atom:          'application/atom+xml',
    css:           'text/css',
    csv:           'text/csv',
    form:          'application/x-www-form-urlencoded',
    html:          ['text/html','application/xhtml+xml'],
    js:            'text/javascript',
    json:          ['application/json', 'text/json'],
    multipartForm: 'multipart/form-data',
    rss:           'application/rss+xml',
    text:          'text/plain',
    hal:           ['application/hal+json','application/hal+xml'],
    xml:           ['text/xml', 'application/xml']
]

// URL Mapping Cache Max Size, defaults to 5000
//grails.urlmapping.cache.maxsize = 1000

// What URL patterns should be processed by the resources plugin
grails.resources.adhoc.patterns = ['/images/*', '/css/*', '/js/*', '/plugins/*']

// Legacy setting for codec used to encode data with ${}
grails.views.default.codec = "html"

// The default scope for controllers. May be prototype, session or singleton.
// If unspecified, controllers are prototype scoped.
grails.controllers.defaultScope = 'singleton'

// GSP settings
grails {
    views {
        gsp {
            encoding = 'UTF-8'
            htmlcodec = 'xml' // use xml escaping instead of HTML4 escaping
            codecs {
                expression = 'html' // escapes values inside ${}
                scriptlet = 'html' // escapes output from scriptlets in GSPs
                taglib = 'none' // escapes output from taglibs
                staticparts = 'none' // escapes output from static template parts
            }
        }
        // escapes all not-encoded output at final stage of outputting
        filteringCodecForContentType {
            //'text/html' = 'html'
        }
    }
}
 
grails.converters.encoding = "UTF-8"
// scaffolding templates configuration
grails.scaffolding.templates.domainSuffix = 'Instance'

// Set to false to use the new Grails 1.2 JSONBuilder in the render method
grails.json.legacy.builder = false
// enabled native2ascii conversion of i18n properties files
grails.enable.native2ascii = true
// packages to include in Spring bean scanning
grails.spring.bean.packages = []
// whether to disable processing of multi part requests
grails.web.disable.multipart=false

// request parameters to mask when logging exceptions
grails.exceptionresolver.params.exclude = ['password']

// configure auto-caching of queries by default (if false you can cache individual queries with 'cache: true')
grails.hibernate.cache.queries = false

environments {
    development {
        grails.serverURL = "http://localhost:8080"
        grails.indexPath = "/development/app.html"
        grails.logging.jul.usebridge = true
        grails.resources.debug=true
        grails.converters.default.pretty.print = true

        foodpaint.service.server.url = "http://localhost:8180"
        foodpaint.service.api.url = "http://localhost:8180/api"
    }
    production {
        grails.indexPath = "/production/index.html"
        grails.logging.jul.usebridge = false
        
        foodpaint.service.server.url = "http://192.168.1.18:8080/foodpaint/"
        foodpaint.service.api.url = "http://192.168.1.18:8080/foodpaint/api"
        // TODO: grails.serverURL = "http://www.changeme.com"
    }
}

// log4j configuration
environments {
 
    development {
        log4j = {
            appenders {
                file name: 'grailsfile', file: 'target/grails.log'
                file name: 'rootlog', file: 'target/root.log'
                file name: 'devfile', file: 'target/development.log'
                console name:'stdout',

                layout: pattern(conversionPattern: "[%d{HH:mm:ss:SSS}] %-5p %c{2}:%L %m%n")
            }
            root { error 'stdout', 'rootlog' }
            info additivity: false, grailsfile: 'org.codehaus.groovy.grails.commons'
            all additivity: false, devfile: [
                'grails.app.controllers',
                'grails.app.domain',
                'grails.app.services',
                'grails.app.taglib',
                'grails.app.conf',
                'grails.app.filters'
            ]
        }
    }
 
    test {
        log4j = {
            appenders {
                file name: 'grailsfile', file: 'target/grails.log'
                file name: 'rootlog', file: 'target/root.log'
                file name: 'testfile', file: 'target/test.log'
                console name:'stdout',
                
                layout: pattern(conversionPattern: "[%d{HH:mm:ss:SSS}] %-5p %c{2}:%L %m%n")
            }
            root { error 'stdout', 'rootlog' }
            info additivity: false, grailsfile: 'org.codehaus.groovy.grails.commons'
            all additivity: false, testfile: [
                'grails.app.controllers',
                'grails.app.domain',
                'grails.app.services',
                'grails.app.taglib',
                'grails.app.conf',
                'grails.app.filters'
            ]
     
        }
    }
    production {
        grails.logging.jul.usebridge = false
        log4j = {
            appenders {
                layout: pattern(conversionPattern: "[%d{HH:mm:ss:SSS}] %-5p %c{2}:%L %m%n")
            }
            root { 
                error()
            }
        }

    }
}

extjs.version = '4.2.1'
touch.version = '2.3.0'

grails.plugins.springsecurity.userLookup.userDomainClassName = 'foodprint.User'
grails.plugins.springsecurity.userLookup.authorityJoinClassName = 'foodprint.UserRole'
grails.plugins.springsecurity.authority.className = 'foodprint.Role'

grails.plugins.springsecurity.useSwitchUserFilter = true
grails.plugins.springsecurity.controllerAnnotations.staticRules = [
   '/j_spring_security_switch_user': ['ROLE_ADMIN']
]


grails.plugins.springsecurity.successHandler.defaultTargetUrl = '/login/authSucccessExtJs'
grails.plugins.springsecurity.successHandler.alwaysUseDefault = true
grails.plugins.springsecurity.failureHandler.defaultFailureUrl = '/login/authFailExtJs?login_error=1'


upload.files.path="${userHome}/.grails/image"

// grails.converters.json.date= 'javascript'


