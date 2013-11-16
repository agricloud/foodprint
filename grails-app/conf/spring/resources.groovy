// Place your Spring DSL code here
import foodprint.*
import common.*
import grails.util.Environment
beans = {


	localeResolver(org.springframework.web.servlet.i18n.SessionLocaleResolver) {
		defaultLocale = new Locale("zh","TW")
		java.util.Locale.setDefault(defaultLocale)
	}

	if(application.config.grails.upload.location.s3){
		attachmentService(S3AttachmentService){
			s3Service = ref("s3Service")
			imageModiService = ref("imageModiService")
			fileLocation = application.config.grails.aws.root
			blankImg = application.parentContext.getResource('/images/blank.gif').file
		}
	}else {
		attachmentService(LocalAttachmentService){
			fileLocation = application.config.grails.upload.location.local.path
			blankImg = application.parentContext.getResource('/images/blank.gif').file
		}
	}

	
}

