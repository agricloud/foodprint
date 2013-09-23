package foodprint



import grails.test.mixin.*
import org.junit.*
import groovy.xml.MarkupBuilder

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(DataImportService)
@Mock([Item,Batch])
class DataImportServiceTests {



    void testItemImport() {
    	def writer = new StringWriter()
    	def xml = new MarkupBuilder(writer)

    	xml.root(){
    		importTable('Item')
    		item{
    			name("item1")
    		}
    		item{
    			name("item2")
    		}
    	}


    	service.doDataImport(writer.toString())
    	assert Item.list().size() == 2 

    }
    void testBatchImport() {
        def writer = new StringWriter()
        def xml = new MarkupBuilder(writer)

        new Item(name:'item1').save()

        xml.root(){
            importTable('Batch')
            batch{
                name("batch1")
                item{
                    name("item1")
                }
            }
            batch{
                name("batch2")
                item{
                    name("item1")
                }
            }
            batch{
                name("batch3")
                item{
                    name("item2")
                }
            }
        }


        service.doDataImport(writer.toString())
        assert Batch.list().size() == 2 

    }
}
