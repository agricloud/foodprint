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
  		rest.restTemplate.setMessageConverters([new StringHttpMessageConverter(Charset.forName("UTF-8"))])
  		def resp = rest.get("http://192.168.2.104:8100/SFT/ws/demo/records/GinPin/410002/981009-410002")
  		def records = new XmlParser().parseText(resp.text)

      // 勿刪， 無 http://192.168.2.104:8100/SFT 測試用
      // def webRootDir = servletContext.getRealPath ("/")
      // def f = new File (webRootDir + "/xmlSample/report.xml")
      // def records = new XmlParser().parseText(f.text)



  		return [reportData: records]

    }
}
