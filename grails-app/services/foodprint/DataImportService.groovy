package foodprint
import org.codehaus.groovy.grails.commons.GrailsDomainClass
import javax.jws.*
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter
import org.grails.cxf.adapter.GrailsCxfMapAdapter
import groovy.xml.MarkupBuilder
    /*
    資料匯入 api 以 domain 的結構來設計
    <?xml version="1.0" encoding="UTF-8"?>
		<root>
			<importTable>item</importTable>
			<item>
				<name></name>
				<title></title>
				...
				<unit></unit>
			</item>
			...
			<item>
				<name></name>
				<title></title>
				...
				<unit></unit>
			</item>
		</root>

	如果欄位屬於 doamin 
    <?xml version="1.0" encoding="UTF-8"?>
		<root>
			<importTable>batch</importTable>
			<batch>
				<item>
					<name>itemA<name>
				</item>
			</batch>
		</root>
    */

class DataImportService {
	static expose = ['cxf']
	def grailsApplication

    @XmlJavaTypeAdapter(GrailsCxfMapAdapter.class)
	Map doDataImport(@WebParam(name="xmlString")String xmlString){

		log.info xmlString

		def result=[:]

		try {		
			def writer = new StringWriter()
		    def xml = new MarkupBuilder(writer)
			def records = new XmlParser().parseText(xmlString)
			def importTable = records.importTable.text()
			def fields= grailsApplication.getDomainClass('foodprint.'+importTable).persistentProperties.collect { it.name }

			// 動態實體化 domain class
			// GrailsDomainClass dc = grailsApplication.getDomainClass('foodprint.'+importTable)
			
			// 以 item 為例， dc.clazz.FindByName == Item.FindByName
			// 建立物件：dc.clazz.newInstance() == new Item()
			// def newDomainObject = dc.clazz.newInstance()



			records[importTable.toLowerCase()].eachWithIndex{ record, i ->

				//各 domain 需定義主鍵的索引
				def domain
				if(importTable=='Item')
					domain=getItemInstance(record)
				if(importTable=='Batch')
					domain=getBatchInstance(record)


				// 共用最後進行儲存
				if(domain){
					domain.properties=getDomainProperties(record, fields)
					domain.save(failOnError:true)
				}
			}
			result.success=true
		}catch(e){
			log.error e.message
			result.success=false
			result.message=e.message

		}

		






		return result





	}


    def private getItemInstance(record) {

		def item = Item.findByName(record.name.text())

		if(!item){
			item=new Item(name:record.name.text())
		}

    	item

    }

    def private getBatchInstance(record){



		def batch = Batch.findByName(record.name.text())

		if(!batch){
			batch=new Batch(name:record.name.text())
		}


		def itemName=record.item.name.text();
		def item=Item.findByName(itemName)
		
		// 處理品項關連
		if(item){
			batch.item=item
		}else {
			throw new Exception("batch.item.name:${itemName} is not exist")
		}


		batch

    }

    // def private batchSourceImport(xml){

    // }

    // def private customerOrderImport(xml){

    // }


    def private getDomainProperties(record, fields){
    	def props=[:]
    	fields.each{ field ->
			// log.info field+"====="+record
			if(record[field] && record[field].text())
				props[field]=record[field].text()
		}

		props

    }

}
