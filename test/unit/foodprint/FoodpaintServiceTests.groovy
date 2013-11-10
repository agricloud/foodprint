package foodprint

import grails.test.mixin.*
import org.junit.*
import grails.converters.*

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(FoodpaintService)
@Mock([
	Site,
    Item,
    Customer,
    Workstation,
    Operation,
    Supplier,
    Batch,
    BatchSource])
class FoodpaintServiceTests {

    void testOperationImport() {
        def operationJson = '''
            {
                "operation": [
                    {
                        "site": {
                            "id": "null",
                            "name": "null"
                        },
                        "id": 116,
                        "title": "園地管理-整地",
                        "importFlag": 3,
                        "editor": "",
                        "description": "整地",
                        "name": "A101",
                        "lastUpdated": "2013-11-10T07:24:42Z",
                        "dateCreated": "2013-11-10T07:24:42Z",
                        "class": "foodpaint.Operation",
                        "creator": ""
                    }
                ]
            }
        '''

    	service.importData(operationJson);

        assert Operation.count() == 1
    }
    void testBatchImport() {
        new Item(name: "K640F01").save(failOnError: true, flush:true)


        def batchJson = '''
           {
                "batch": [
                    {
                        "site": {
                            "id": "null",
                            "name": "null"
                        },
                        "importFlag": -1,
                        "expirationDate": null,
                        "batchType": {
                            "name": "PRODUCT",
                            "enumType": "foodpaint.BatchType"
                        },
                        "class": "foodpaint.Batch",
                        "lastUpdated": "2013-11-10T07:24:52Z",
                        "expectQty": 0,
                        "country": "TAIWAN",
                        "creator": "",
                        "id": 286,
                        "editor": "",
                        "name": "20131017-K640F01",
                        "item": {
                            "site": null,
                            "id": 69,
                            "unit": "公斤",
                            "title": "重光六號",
                            "importFlag": 4,
                            "editor": "",
                            "description": null,
                            "name": "K640F01",
                            "lastUpdated": "2013-11-10T07:24:39Z",
                            "dateCreated": "2013-11-10T07:24:39Z",
                            "class": "foodpaint.Item",
                            "creator": ""
                        },
                        "dateCreated": "2013-11-10T07:24:52Z",
                        "manufactureDate": null,
                        "dueDate": null,
                        "supplier": {
                            "site": null,
                            "id": 268,
                            "title": "益興飼料肥料加工廠",
                            "importFlag": 5,
                            "editor": "",
                            "name": "S0001",
                            "lastUpdated": "2013-11-10T07:24:49Z",
                            "dateCreated": "2013-11-10T07:24:49Z",
                            "class": "foodpaint.Supplier",
                            "creator": "",
                            "country": {
                                "name": "TAIWAN",
                                "enumType": "foodpaint.Country"
                            }
                        }
                    }
                ]
            }
        '''

        service.importData(batchJson);

        assert Item.count() == 1
    }
    void testCustomerImport() {

        def customerJson = '''
            {
                "customer": [
                    {
                        "site": {
                            "id": "null",
                            "name": "null"
                        },
                        "id": 279,
                        "title": "四工管三B",
                        "importFlag": 3,
                        "editor": "",
                        "email": "",
                        "address": "",
                        "name": "001",
                        "lastUpdated": "2013-11-10T07:24:50Z",
                        "dateCreated": "2013-11-10T07:24:50Z",
                        "class": "foodpaint.Customer",
                        "creator": ""
                    }
                ]
            }
        '''

        service.importData(customerJson);

        assert Customer.count() == 1
    }

    void testSupplierImport() {

        def supplierJson = '''
            {
                "supplier": [
                    {
                        "site": {
                            "id": "null",
                            "name": "null"
                        },
                        "id": 268,
                        "title": "益興飼料肥料加工廠",
                        "importFlag": 5,
                        "editor": "",
                        "name": "S0001",
                        "lastUpdated": "2013-11-10T07:24:49Z",
                        "dateCreated": "2013-11-10T07:24:49Z",
                        "class": "foodpaint.Supplier",
                        "creator": "",
                        "country": {
                            "name": "TAIWAN",
                            "enumType": "foodpaint.Country"
                        }
                    }
                ]
            }
        '''

        service.importData(supplierJson);

        assert Supplier.count() == 1
    }

    void testWorkstationImport() {

        def workstationJson = '''
            {
                "workstation": [
                    {
                        "site": {
                            "id": "null",
                            "name": "null"
                        },
                        "id": 111,
                        "title": "A農地(1980)-柳丁栽種區(1980)",
                        "importFlag": 4,
                        "editor": "",
                        "name": "PLA001",
                        "lastUpdated": "2013-11-10T07:24:41Z",
                        "dateCreated": "2013-11-10T07:24:41Z",
                        "class": "foodpaint.Workstation",
                        "creator": ""
                    },
                ]
            }
        '''

        service.importData(workstationJson);

        assert Workstation.count() == 1
    }
}
