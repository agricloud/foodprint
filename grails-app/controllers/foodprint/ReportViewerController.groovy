package foodprint

// import org.springframework.dao.DataIntegrityViolationException
import org.springframework.http.converter.StringHttpMessageConverter
// import grails.converters.JSON
import grails.converters.XML
import grails.plugins.rest.client.RestBuilder
import java.nio.charset.Charset

class ReportViewerController {

    def index() { 
    	def rest = new RestBuilder()
		println "reportViewer======tt"

		rest.restTemplate.setMessageConverters([new StringHttpMessageConverter(Charset.forName("UTF-8"))])
		def resp = rest.get("http://192.168.2.104:8100/SFT/ws/demo/records/GinPin/410002/981009-410002")
		// println XML.parse(resp.text)
		// println resp.text
  //       return [reportData: resp]
  		println resp.text
  		def records = new XmlParser().parseText(resp.text)
  		println records.form[0]
  		println record.form[0].value[0]
  		// println records[0]
  		return [reportData: records]
        // [itemInstanceList: Item.list(params), itemInstanceTotal: Item.count()]


    }
}
