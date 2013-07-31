package foodprint



import grails.test.mixin.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Resumes)
@Mock([ResumesCell, ResumesRow, ResumesCol, ResumesCollectData, Item, Batch, BatchResumes])
class ResumesTests {

    void "test create Resumes"() {
      def resumes = new Resumes(name:"Resumes 1").save(failOnError:true)

      def rowA = new ResumesRow(name: "rowA").save(failOnError:true)
      def rowB = new ResumesRow(name: "rowB").save(failOnError:true)
      
      def col1 = new ResumesCol(name: "col1").save(failOnError:true)
      def col2 = new ResumesCol(name: "col2").save(failOnError:true)
      def col3 = new ResumesCol(name: "col3").save(failOnError:true)

      def cellA1 = new ResumesCell(row:rowA, col:col1).save(failOnError:true)
      def cellA2 = new ResumesCell(row:rowA, col:col2).save(failOnError:true)
      def cellB2 = new ResumesCell(row:rowB, col:col2).save(failOnError:true)
      def cellB3 = new ResumesCell(row:rowB, col:col3).save(failOnError:true)

      def collectData1 = new ResumesCollectData(resumes:resumes, cell:cellA1).save(failOnError:true)
      def collectData2 = new ResumesCollectData(resumes:resumes, cell:cellA2).save(failOnError:true)
      def collectData3 = new ResumesCollectData(resumes:resumes, cell:cellB2).save(failOnError:true)
      def collectData4 = new ResumesCollectData(resumes:resumes, cell:cellB3).save(failOnError:true)

      assert resumes.collectDatas.size() == 4

      def item = new Item(name:"item").save(failOnError:true)
      def batch = new Batch(name:"batch", item:item, qty:1).save(failOnError:true)
      def batchResumes= new BatchResumes(batch:batch, resumes:resumes).save(failOnError:true)

      assert BatchResumes.list().size() == 1

      // 透過 BatchResumes 所擁有的 resumes 可以取得要收集的內容
      // 實際在使用時，可將 resumes 的內容作為前端收集的依據
      // 接著是要將履歷收集結果儲存的範例



    }
}
