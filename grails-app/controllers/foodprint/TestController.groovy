package foodprint

import org.springframework.dao.DataIntegrityViolationException
import org.springframework.http.converter.StringHttpMessageConverter
import grails.converters.JSON
import grails.converters.XML
import grails.plugins.rest.client.RestBuilder
import java.nio.charset.Charset



class TestController {

	static allowedMethods = [create: "POST",update: "PUT",  delete: "DELETE"]

	def tt() {
		// def rest = new RestBuilder()
		// rest.restTemplate.setMessageConverters([new StringHttpMessageConverter(Charset.forName("UTF-8"))])
		// def resp = rest.get("http://192.168.2.104:8100/SFT/ws/demo/records/GinPin/410002/981009-410002")
		

		// def records = new XmlSlurper().parseText(resp.text)
		def webRootDir = servletContext.getRealPath ("/")

		// Create a new file instance
		def f = new File (webRootDir + "/xmlSample/report.xml")

		// Parxing XML file here
		def reportXml = new XmlParser().parseText(f.text)
		log.info reportXml.form.field[0].'@label'



		
		// resp.setCharacterEncoding("UTF-8")
		// def resp = rest.get("http://localhost:8080/batch/listXml")
  // 		resp.xml.name == 'acegi'

  // 		println "resp=======\n"+resp.xml
  // 		println XML.parse(resp.text)
		// def result = resp.text.encodeAsJSON();
		// println result

		// resp.json instanceof JSONObject
  // 		resp.json.name == 'acegi'
  		// def converter=resp.text as XML
//         converter.render(response)
// return [reportData: XML.parse(resp.text)]
return [reportData: reportXml]

// render( contentType: 'text/xml'){
// 	XML.parse(resp.text)
// }
// def converter=resp.text as XML
//         converter.render(response)
    }
	

}