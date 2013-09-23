package foodprint
import org.codehaus.groovy.grails.commons.GrailsDomainClass
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
	def grailsApplication

	def public doDataImport(xml){

		def records = new XmlParser().parseText(xml)
		def importTable = records.importTable.text()
		def fields= grailsApplication.getDomainClass('foodprint.'+importTable).persistentProperties.collect { it.name }
		
		// 動態實體化 domain class
		// GrailsDomainClass dc = grailsApplication.getDomainClass('foodprint.'+importTable)
		
		// 以 item 為例， dc.clazz.FindByName == Item.FindByName
		// dc.clazz.newInstance() == new Item()
		
		// def newDomainObject = dc.clazz.newInstance()


		def domains

		if(importTable=='Item')
			domains=itemImport(records)
		if(importTable=='Batch')
			domains=batchImport(records)


		// 共用最後進行儲存
		if(domains){
			domains.eachWithIndex(){ domain, i ->
				def domainRecord=records[importTable.toLowerCase()][i];
				domain.properties=getDomainProperties(domainRecord, fields)
				domain.save(failOnError:true)
			}
		}



	}


    def private itemImport(records) {
  		
  		def domains=[]

    	records.item.each{
    		def item = Item.findByName(it.name.text())

    		if(!item){
    			item=new Item(name:it.name.text())
    		}

    		domains << item
    	}

    	domains

    }

    def private batchImport(records){

  		def domains=[]

    	records.batch.each{ recordBatch ->

    		def batch = Batch.findByName(recordBatch.name.text())

    		if(!batch){
    			batch=new Batch(name:recordBatch.name.text())
    		}


			def itemName=recordBatch.item.name.text();
			log.info itemName
			def item=Item.findByName(itemName)
    		
    		try{
    			// 處理品項關連
    			if(item){
    				batch.item=item
	    			domains << batch
    			}else {
    				throw new Exception("batch.item.name:${itemName} is not exist")
				}

			}catch(e){

				log.info e.message

			}

    	}

    	domains
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
