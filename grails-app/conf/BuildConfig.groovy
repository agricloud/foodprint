def grails23x = (grailsVersion.startsWith("2.3"))
def grails22x = (grailsVersion.startsWith("2.2"))

if (grails23x) {
    grails.servlet.version = "3.0" // Change depending on target container compliance (2.5 or 3.0)
}
else {
    // Grails version is 2.2.x
    grails.servlet.version = "2.5" // Change depending on target container compliance (2.5 or 3.0)
}

grails.project.class.dir = "target/classes"
grails.project.test.class.dir = "target/test-classes"
grails.project.test.reports.dir = "target/test-reports"
grails.project.work.dir = "target/work"
grails.project.target.level = 1.6
grails.project.source.level = 1.6
grails.project.war.file = "target/${appName}.war"
//grails.project.war.file = "target/${appName}-${appVersion}.war"

grails.war.resources = { stagingDir ->
    if(new File("${stagingDir}/development").exists())
        delete(includeEmptyDirs: true) { fileset dir: "${stagingDir}/development" }
        
    delete(includeEmptyDirs: true) { fileset dir: "${stagingDir}/art sample" }
    delete(includeEmptyDirs: true) { fileset dir: "${stagingDir}/xmlSample" }
}


if (grails23x) {
    grails.project.fork = [
        // configure settings for compilation JVM, note that if you alter the Groovy version forked compilation is required
        //  compile: [maxMemory: 256, minMemory: 64, debug: false, maxPerm: 256, daemon:true],

        // configure settings for the test-app JVM, uses the daemon by default
        test: [maxMemory: 512, minMemory: 64, debug: false, maxPerm: 256, daemon:true],
        // configure settings for the run-app JVM
        run: [maxMemory: 512, minMemory: 64, debug: false, maxPerm: 256, forkReserve:false],
        // configure settings for the run-war JVM
        war: [maxMemory: 512, minMemory: 64, debug: false, maxPerm: 256, forkReserve:false],
        // configure settings for the Console UI JVM
        console: [maxMemory: 512, minMemory: 64, debug: false, maxPerm: 256]
    ]
    grails.project.dependency.resolver = "maven" // or ivy
}
else {
    // Grails version is 2.2.x

    // grails.project.fork.run=true
    // grails.project.fork.run= [maxMemory:512, minMemory:64, debug:true, maxPerm:256 ]
}


grails.project.dependency.resolution = {
    // inherit Grails' default dependencies
    inherits("global") {
        // specify dependency exclusions here; for example, uncomment this to disable ehcache:
        // excludes 'ehcache'
    }
    log "error" // log level of Ivy resolver, either 'error', 'warn', 'info', 'debug' or 'verbose'
    checksums true // Whether to verify checksums on resolve
    legacyResolve false // whether to do a secondary resolve on plugin installation, not advised and here for backwards compatibility

    repositories {
        inherits true // Whether to inherit repository definitions from plugins

        grailsPlugins()
        grailsHome()
        mavenLocal()
        grailsCentral()
        mavenCentral()
        // uncomment these (or add new ones) to enable remote dependency resolution from public Maven repositories
        //mavenRepo "http://repository.codehaus.org"
        //mavenRepo "http://download.java.net/maven/2/"
        //mavenRepo "http://repository.jboss.com/maven2/"
    }

    dependencies {
        // specify dependencies here under either 'build', 'compile', 'runtime', 'test' or 'provided' scopes e.g.
        runtime 'mysql:mysql-connector-java:5.1.24'
        compile 'org.apache.ant:ant:1.7.1'    //you can also use runtime
        compile 'org.apache.ant:ant-launcher:1.7.1'

        runtime 'net.java.dev.jets3t:jets3t:0.9.0'
    }

    plugins {


        if (grails23x) {
            // plugins for the build system only
            build ":tomcat:7.0.42"

            // plugins for the compile step
            compile ":scaffolding:2.0.0"
            compile ':cache:1.1.1'

            // plugins needed at runtime but not for compilation
            runtime ":hibernate:3.6.10.2" // or ":hibernate4:4.1.11.1"
            runtime ":database-migration:1.3.5"
            runtime ":jquery:1.10.2"
            runtime ":resources:1.2"
        }
        else {
            // Grails version is 2.2.x

            runtime ":hibernate:$grailsVersion"
            runtime ":jquery:1.10.2"
            runtime ":resources:1.2"

            // Uncomment these (or add new ones) to enable additional resources capabilities
            //runtime ":zipped-resources:1.0"
            //runtime ":cached-resources:1.0"
            //runtime ":yui-minify-resources:0.1.4"

            build ":tomcat:$grailsVersion"

            runtime ":database-migration:1.2.1"

            compile ':cache:1.0.1'
        }

        // add plugin
        //compile ':spring-security-core:latest.release'
        compile ':spring-security-core:1.2.7.3'
        runtime ":cors:1.1.0"

        // Adds REST client capabilities to your Grails application.
        compile ":rest:0.8"

        // Grails REST Client Builder Plugin
        compile ':rest-client-builder:1.0.3'

        // compile ":twitter-bootstrap:3.0.0"

        compile ":crypto:2.0"  


        // Grails Ant Plugin
        // 執行 Ant 任務
        compile ":grails-ant:0.1.3"
        
        compile ":quartz:1.0-RC13"
    }
}
