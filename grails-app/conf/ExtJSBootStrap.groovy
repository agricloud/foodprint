import foodprint.*

class ExtJSBootStrap {
    
    def grailsApplication

    def init = { servletContext ->

        //自動檢查 Ext JS 是否存在
        log.info "Checking Ext JS library ..."
        
        def extjs_ver = grailsApplication.config.extjs.version
        log.info "Configuration specified Ext JS version: ${extjs_ver}"

        def extjs_url = "http://cdn.sencha.com/ext/gpl/ext-${extjs_ver}-gpl.zip"

        log.info "Starting download progress from ${extjs_url} ..."

        ant.echo message: 'Testing: Grails Ant Plugin [OK]'

        //ant.get src: 'http://google.com/', dest: '/tmp/test.html'

        environments {
            development {
            }
        }
    }
    def destroy = {
    }
}
