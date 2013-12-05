package foodprint

import common.*
import org.junit.*
import grails.test.mixin.*
import spock.lang.Specification

@TestFor(ItemRouteController)
@Mock([ItemRoute, 
    DomainService, TestService])
class ItemRouteControllerSpec extends Specification {

    void setup(){
        def testService = new TestService()
        testService.createTestMessage(messageSource)
    }

    void "測試 index action，並且 response 為 json 格式"() {
        setup: "建立 ItemRoute 測試資料"
            def item = new Item(name:"item1",title:"華珍玉米",spec:"華珍甜玉米，高糖分、皮薄",unit:"kg",description:"非基因轉殖品種").save(failOnError: true)

            def workstation = new Workstation(name:"workstation1",title:"檢驗站01").save(failOnError: true)
            def operation = new Operation(name:"operation1",title:"施肥").save(failOnError: true)

            def itemRoute = new ItemRoute(item:item,sequence:1,operation:operation,workstation:workstation)
            item.addToItemRoutes(itemRoute).save(failOnError: true)

        when: "執行 ItemRouteController 提供的 index action"
            controller.index()

        then: "response 要能取得 ItemRoute json 格式初始資料"
            assert response.json

        then: "json 裡有 itemRouteInstanceList 屬性，且有一筆資料 name 屬性為 itemRoute"
            assert response.json.itemRouteInstanceList[0].name == "itemRoute"

        then: "json 裡有 itemRouteInstanceTotal 屬性為 1"
            assert response.json.itemRouteInstanceTotal == 1   

    }

    void "測試 show action，並且 response 為 json 格式"() {

        setup: "建立測試資料"
            def item = new Item(name:"item1",title:"華珍玉米",spec:"華珍甜玉米，高糖分、皮薄",unit:"kg",description:"非基因轉殖品種").save(failOnError: true)

            def workstation = new Workstation(name:"workstation1",title:"檢驗站01").save(failOnError: true)
            def operation = new Operation(name:"operation1",title:"施肥").save(failOnError: true)

            def itemRoute = new ItemRoute(item:item,sequence:1,operation:operation,workstation:workstation)
            item.addToItemRoutes(itemRoute).save(failOnError: true)

        and: "前端傳入資料，定義 id 為測試資料的 id"
            params.id = itemRoute.id

        when: "執行 ItemRouteController 提供的 show action"
            controller.show()

        then: "response 要能取得 ItemRoute json 格式初始資料"
            assert response.json

        then: "json 裡有 success 屬性，且為 true"
            assert response.json.success

        then: "json 裡有 data.class 屬性為 foodprint.ItemRoute"
            assert response.json.data.class == "foodprint.ItemRoute"

    }

    void "測試 create action，並且回傳為 json 格式(尚未儲存)"() {

        setup:"建立測試資料"
            def item = new Item(name:"item1",title:"華珍玉米",spec:"華珍甜玉米，高糖分、皮薄",unit:"kg",description:"非基因轉殖品種").save(failOnError: true)

        and: "前端傳入資料，定義 item.id 為測試資料的 item.id"
            params["item.id"]=item.id

        when: "執行 create action"
            controller.create()

        then: "response 要能取得 json 格式初始資料"
            assert response.json

        then: "json 裡有 success 屬性，且為 true"
            assert response.json.success

        then: "json 裡有 data.class 屬性"
            assert response.json.data.class
    }

    void "測試 save action，並且回傳為 json 格式(儲存完成)"() {

        setup:"建立測試資料"
            def item = new Item(name:"item1",title:"華珍玉米",spec:"華珍甜玉米，高糖分、皮薄",unit:"kg",description:"非基因轉殖品種").save(failOnError: true)

        and: "前端傳入資料"
            params["sequence"] = 1            
            params["item.id"] = item.id

        when: "執行 save action"
            controller.save()

        then: "response 要能取得 json 格式初始資料"
            assert response.json

        then: "json 裡有 success 屬性為 true"
            assert response.json.success

        then: "資料庫將有筆新增資料"
            assert ItemRoute.list().size() == 1
            assert ItemRoute.get(1)   
    }

    void "測試 update action，並且回傳為 json 格式"() {

        setup: "建立測試資料"
            def item1 = new Item(name:"item1",title:"華珍玉米",spec:"華珍甜玉米，高糖分、皮薄",unit:"kg",description:"非基因轉殖品種").save(failOnError: true)
            def item2 = new Item(name:"item2",title:"華珍玉米",spec:"華珍甜玉米，高糖分、皮薄",unit:"kg",description:"非基因轉殖品種").save(failOnError: true)

            def workstation = new Workstation(name:"workstation1",title:"檢驗站01").save(failOnError: true)
            def operation = new Operation(name:"operation1",title:"施肥").save(failOnError: true)

            def itemRoute = new ItemRoute(item:item1,sequence:1,operation:operation,workstation:workstation)
            item.addToItemRoutes(itemRoute).save(failOnError: true)

        and: "前端傳入資料，定義 id 為測試資料的 id，並且修改屬性"
            params.id = itemRoute.id
            params.item = item2

        when: "執行 update action"
            controller.update()
        
        then: "response 要能取得 json 格式初始資料"
            assert response.json

        then: "json 裡有 success 屬性，且為 true"
            assert response.json.success

        then: "將有筆新增資料"
            assert ItemRoute.list().size() == 1

        then: "修改後的屬性有正確寫入"
            assert ItemRoute.get(1).item == item2
    }

    void "測試 delete action，並且回傳為 json 格式"() {

        setup: "建立測試資料"
            def item = new Item(name:"item1",title:"華珍玉米",spec:"華珍甜玉米，高糖分、皮薄",unit:"kg",description:"非基因轉殖品種").save(failOnError: true)

            def workstation = new Workstation(name:"workstation1",title:"檢驗站01").save(failOnError: true)
            def operation = new Operation(name:"operation1",title:"施肥").save(failOnError: true)

            def itemRoute = new ItemRoute(item:item,sequence:1,operation:operation,workstation:workstation)
            item.addToItemRoutes(itemRoute).save(failOnError: true)
        
        and: "前端傳入資料，定義 id 為測試資料的 id"
            params.id = itemRoute.id

        when: "執行 delete action"
            controller.delete()

        then: "response 要能取得 json 格式初始資料"
            assert response.json

        then: "json 裡有 success 屬性，且為 true"
            assert response.json.success        
 
        then: "該筆資料已移除"
            assert ItemRoute.list().size() == 0
    }

}
