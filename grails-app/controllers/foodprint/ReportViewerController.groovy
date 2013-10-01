package foodprint

// import org.springframework.dao.DataIntegrityViolationException
import org.springframework.http.converter.StringHttpMessageConverter
// import grails.converters.JSON
import grails.converters.XML
import grails.plugins.rest.client.RestBuilder
import java.nio.charset.Charset
import grails.transaction.Transactional

@Transactional(readOnly = true)
class ReportViewerController {

    def demo() { 

      // 勿刪， 無 http://192.168.2.104:8100/SFT 測試用
      // def webRootDir = servletContext.getRealPath ("/")
      // def f = new File (webRootDir + "/xmlSample/report.xml")
      // def records = new XmlParser().parseText(f.text)

      	def rest = new RestBuilder()
		rest.restTemplate.setMessageConverters([new StringHttpMessageConverter(Charset.forName("UTF-8"))])
		def url = "http://192.168.2.104:8100/SFT/"
		def imgUrl = url+"PDA/"
		def resp = rest.get(url+"ws/demo/records/GinPin/410002/981009-410002")
		def records = new XmlParser().parseText(resp.text)



		def formImg=records.form.field.findAll{ field->
				field.'@label'=="圖片"
  			}
  		formImg.each{
  			it.value=imgUrl+it.text()
  		}

  		records.tabs.tab.detail.row.cell.each{ cell->
  			if(cell.img.size()>0){
  				cell.img[0].'@src'	= [imgUrl+ cell.img.'@src'[0]]
			}
  		}

  		return [reportData: records]

    }
}
