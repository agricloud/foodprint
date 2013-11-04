// Place your Spring DSL code here
import foodprint.*
import grails.util.Environment
beans = {


	localeResolver(org.springframework.web.servlet.i18n.SessionLocaleResolver) {
		defaultLocale = new Locale("zh","TW")
		java.util.Locale.setDefault(defaultLocale)
	}


	attachmentService(LocalAttachmentService){
		fileLocation = application.config.upload.files.path
		blankImg = application.parentContext.getResource('/images/blank.jpg').file
	}
}

