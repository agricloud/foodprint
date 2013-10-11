package foodprint
import grails.converters.JSON
import org.codehaus.groovy.grails.web.json.JSONObject
import org.codehaus.groovy.grails.commons.DefaultGrailsDomainClass

class TraceTreeController {
	def batchAnalyzeService

    def forwardQuery() { 

	    /* sample json
		{"text":".","children": [
		    {
		        task:'Project: Shopping',
		        duration:13.25,
		        user:'Tommy Maintz',
		        iconCls:'task-folder',
		        expanded: true,
		        children:[{
		            task:'Housewares',
		            duration:1.25,
		            user:'Tommy Maintz',
		            iconCls:'task-folder',
		            children:[{
		                task:'Kitchen supplies',
		                duration:0.25,
		                user:'Tommy Maintz',
		                leaf:true,
		                iconCls:'task'
		            }]
		        }]
		    }
		]}
	    */


	    // node 為前端 extjs async(非同步)時會傳入後端的參數
	    // sort 可以作為後端查詢排序對象用，設定於 extjs 中的 store sorters，也可以用於一般 store 使用
	    /*
	    * 前端傳入參數範例
	    * sort:[{"property":"leaf","direction":"ASC"},{"property":"task","direction":"ASC"}]
		* node:src/folder/folder2
	    */

	    def jsonTreeArray = []
	    

	    // 定義 src 為 tree root 的 id
	    // 樹狀最一開始的節點 root 需要在 extjs store 中的 root 屬性定義，範例定義方式如下
	    /*
	    * {
        *      	task: 'Ext JS',
        *       id: 'src',
        *       user: 'test'
        * }
	    */
	    // 其中 id: 'src' 會做 node 傳往後後方進行查詢
	    // 透過 node 的傳入 可以在分析 node 的資料進行後續節點的查詢
	    if(params.node == '4'){
	        
	    	def jsonTree = [:]

		    jsonTree.itemtitle = 'Project: Shopping'
		    jsonTree.expectQty = '123'
		    jsonTree.country = 'Tommy Maintz'
		    jsonTree.supplier = 'Tommy Maintz'
	        jsonTree.id= "5"
	        jsonTree.cls= "folder"
	    	jsonTree.leaf = false
	        jsonTreeArray << jsonTree


	   	}else if(params.node == 'src/folder'){	    	

	    	def jsonTree = [:]

		    jsonTree.task = 'Project: Shopping'
		    jsonTree.duration = '456'
		    jsonTree.user = 'Tommy Maintz'
	        jsonTree.id= "src/folder/folder2"
	        jsonTree.cls= "folder"
	        jsonTree.leaf = false
	        jsonTreeArray << jsonTree


		}else if(params.node == 'src/folder/folder2'){

	    	def jsonTree = [:]

		    jsonTree.task = 'folder'
		    jsonTree.duration = '789'
		    jsonTree.user = 'Tommy Maintz'
		    jsonTree.leaf = false
		    jsonTree.iconCls = 'task'
		    jsonTree.children=[]

		    def left = [:]
		    left.task = 'Kitchen supplies'
		    left.duration = '789'
		    left.user = 'Tommy Maintz'
		    left.leaf = true
		    left.iconCls = 'task'
		    jsonTree.children << left

	        jsonTreeArray << jsonTree

	       	def jsonTree2 = [:]

		    jsonTree2.task = 'folder2'
		    jsonTree2.duration = '789'
		    jsonTree2.user = 'Tommy Maintz'
		    jsonTree2.leaf = false
		    jsonTree2.id= "src/folxder/folder2/test"
		    jsonTree2.iconCls = 'task'
		    jsonTree2.children=[]
		    // 有無給定 children 將決定該節點可否觸動再次下展的查詢 request
		    // 若給定空的 children 前端 extjs 樹狀將呈現無法下展
		    // 若無給定則顯示可以繼續下展

		    jsonTreeArray << jsonTree2


		}

		println jsonTreeArray
		render (contentType: 'application/json') {
            jsonTreeArray
        }



    }

    def backwardTrace(){
    	log.debug "${controllerName}--backwardTrace"
		def batch = Batch.findById(params.node)
println batch.name
println batch.batchSources
		def jsonTreeArray = []
		batchAnalyzeService.backwardTrace(batch).batchChild.each{ b ->
			def jsonTree = new JSONObject()
println b.item.title		
			jsonTree.itemtitle = b.item.title
		    	jsonTree.expectQty = b.expectQty
		    	jsonTree.country = b.country
		    	jsonTree.supplier = b.supplier
	        		jsonTree.id= b.id

			if(batchAnalyzeService.isBackwardEndBatch(b).isEndBatch){
				jsonTree.leaf = true
		    		jsonTree.iconCls = 'task'
			}
println jsonTree
			jsonTree.batchSources = null
			jsonTreeArray << jsonTree

			//直接放入batch 樹便可展開 但無法加入children判斷是否為葉節點
			// jsonTreeArray << b
		}
		render (contentType: 'application/json') {
            			jsonTreeArray
       		 }
    }

    def forwardTrace(){
    	log.debug "${controllerName}--forwardTrace"

		def batch = Batch.findById(params.node)

		def jsonTreeArray = []



		batchAnalyzeService.forwardTrace(batch).batchHead.each{ b ->

            def tempJson = b as JSON
			def jsonTree = JSON.parse(tempJson.toString())
			
			
			if(batchAnalyzeService.isForwardEndBatch(b).isEndBatch){
				jsonTree.children=[]
			}

            // jsonTree.item.title = b.item.title

            log.info b as JSON

			jsonTreeArray << jsonTree

			//直接放入batch 樹便可展開 但無法加入children判斷是否為葉節點
			// jsonTreeArray << b
		}

		render (contentType: 'application/json') {
            jsonTreeArray
        }


    }
}
