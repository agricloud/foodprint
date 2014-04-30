/*
 * File: app/controller/ErpSaleReturnSheetController.js
 *
 * This file was generated by Sencha Architect version 2.2.3.
 * http://www.sencha.com/products/architect/
 *
 * This file requires use of the Ext JS 4.2.x library, under independent license.
 * License of Sencha Architect does not include license for Ext JS 4.2.x. For more
 * details see http://www.sencha.com/license or contact license@sencha.com.
 *
 * This file will be auto-generated each and everytime you save your project.
 *
 * Do NOT hand edit this file.
 */

Ext.define('foodprint.controller.ErpSaleReturnSheetController', {
    extend: 'Ext.app.Controller',

    mixins: {
        commonController: 'foodprint.controller.CommonController'
    },

    models: [
        'ErpSaleReturnSheet',
        'ErpSaleReturnSheetDet',
        'ErpSaleSheet',
        'ErpSaleSheetDet',
        'ErpCustomerOrder',
        'ErpCustomerOrderDet',
        'Customer'
    ],
    stores: [
        'ErpSaleReturnSheetStore',
        'ErpSaleReturnSheetDetStore',
        'ErpSaleSheetStore',
        'ErpCustomerOrderStore',
        'CustomerStore',
        'ErpSaleSheetDetStore',
        'ErpCustomerOrderDetStore'
    ],
    views: [
        'ErpSaleReturnSheetView'
    ],

    refs: [
        {
            ref: 'mainGrid',
            selector: 'erpsalereturnsheetview #grid'
        },
        {
            ref: 'mainForm',
            selector: 'erpsalereturnsheetview #form'
        },
        {
            ref: 'detailGrid',
            selector: 'erpsalereturnsheetview #detailGrid'
        },
        {
            ref: 'detailForm',
            selector: 'erpsalereturnsheetview #detailForm'
        }
    ],

    init: function(application) {
        this.control({
            'erpsalereturnsheetview #index commonindextoolbar commoncreatebtn':{
                click:this.doCreateAndIndexDetail
            },
            'erpsalereturnsheetview #index commonindextoolbar commonshowbtn':{
                click:this.doShowSaleReturnSheet
            },
            'erpsalereturnsheetview #show commonshowtoolbar commondeletebtn':{
                click:this.doDelete
            },
            'erpsalereturnsheetview #show commonshowtoolbar commonsavebtn':{
                click:this.doSave
            },
            'erpsalereturnsheetview #show commonshowtoolbar commoncancelbtn':{
                click:this.doCancel
            },
            'erpsalereturnsheetview #grid':{
                select: this.enableShowBtn,
                deselect: this.disableShowBtn,
                itemdblclick: this.doShowSaleReturnSheet
            },
            'erpsalereturnsheetview #show commonindextoolbar commoncreatebtn':{
                click:this.doCreateDetail
            },
            'erpsalereturnsheetview #show commonindextoolbar commonshowbtn':{
                click:this.doShowSaleReturnSheetDet
            },
            'erpsalereturnsheetview #showDetail commonshowtoolbar commondeletebtn':{
                click:this.doDeleteDetail
            },
            'erpsalereturnsheetview #showDetail commonshowtoolbar commonsavebtn':{
                click:this.doSaveDetail
            },
            'erpsalereturnsheetview #showDetail commonshowtoolbar commoncancelbtn':{
                click:this.doCancelDetail
            },
            'erpsalereturnsheetview #detailGrid':{
                select: this.enableDetailShowBtn,
                deselect: this.disableDetailShowBtn,
                itemdblclick: this.doShowSaleReturnSheetDet
            },
            'erpsalereturnsheetview #showDetail #saleSheetDetContainer commonselectbtn':{
                click:this.activeSaleSheetDetIndex
            },
            'erpsalereturnsheetview #showDetail commonitemcombo':{
                select:this.doCancelSaleSheetDet
            },
            'erpsalereturnsheetview #saleSheetDetIndex erpsalesheetgrid':{
                select: this.doIndexDetailSaleSheet
            },
            'erpsalereturnsheetview #saleSheetDetIndex erpsalesheetdetgrid':{
                itemdblclick: this.doSelectSaleSheetDet
            }

        });

        this.domainName = 'foodpaint';
        this.foodpaintController = 'saleReturnSheet';
        this.foodpaintDetController = 'saleReturnSheetDet';
        this.masterKey='saleReturnSheet.id';
    },

    doIndexDetailSaleSheet: function(obj, record, index, eOpts) {
        var grid = this.getMainGrid().up().up().down("panel[itemId=saleSheetDetIndex]").down("grid[itemId=erpSaleSheetDetGrid]");

        grid.getStore().data.clear();

        var params = {}
        params["saleSheet.id"]=record.data.id;

        grid.getStore().getProxy().extraParams = params;
        grid.getStore().load();
    },

    doSelectSaleSheetDet: function(obj, record, index, eOpts) {

        this.getDetailForm().getForm().setValues({

            'saleSheetDet.id':record.data['id'],
            'saleSheetDet.typeName':record.data['typeName'],
            'saleSheetDet.name':record.data['name'],
            'saleSheetDet.sequence':record.data['sequence'],
            'warehouse.id':record.data['warehouse.id'],
            'warehouse.title':record.data['warehouse.title'],
            'warehouseLocation.id':record.data['warehouseLocation.id'], 
            'warehouseLocation.title':record.data['warehouseLocation.title'],   
            'item.id':record.data['item.id'], 
            'item.name':record.data['item.name'],
            'item.title':record.data['item.title'],
            'batch.id':record.data['batch.id'],
            'batch.name':record.data['batch.name'],
            'qty':record.data['qty']


        });

        //warehouseLocation combo需指定warehouse id才可load
        var wlcombo=this.getDetailForm().up().up().down("form[itemId=detailForm]").down("combo[itemId=commonWarehouseLocationCombo]");
        Utilities.compositionComboReload(wlcombo, 'warehouse.id',record.data['warehouse.id'],record.data['warehouseLocation.id']);


        this.activeDetailEditor();
    },

    doCancelSaleSheetDet: function() {

        this.getDetailForm().getForm().setValues({

            'saleSheetDet.id':null,
            'saleSheetDet.typeName':null,
            'saleSheetDet.name':null,
            'saleSheetDet.sequence':null,
            'warehouse.id':null,
            'warehouse.name':null,
            'warehouse.title':null,
            'warehouseLocation.id':null,    
            'warehouseLocation.name':null,
            'warehouseLocation.title':null, 
            'item.id':null,
            'item.name':null,  
            'item.title':null,
            'qty':null,
            'customerOrderDet.id':null,
            'customerOrderDet.typeName':null,
            'customerOrderDet.name':null,
            'customerOrderDet.sequence':null,
            'batch.id':null,
            'batch.name':null,
            'qty':0,


        });
    },

    doShowSaleReturnSheet: function() {
        this.doShowAndIndexDetail(function(success,form,action){
            //由於store設定load第1-50筆
            //導致doShow時若資料屬於第50筆之後無法正常顯示
            //在此使combo重新load store
            var cucombo=form.findField('customer.id');
            Utilities.comboReload(cucombo,action.result.data['customer.id'],action.result.data['customer.name']);

        });
    },

    doShowSaleReturnSheetDet: function() {

        this.doShowDetail(function(success,form,action){
            //由於store設定load第1-50筆
            //導致doShow時若資料屬於第50筆之後無法正常顯示
            //在此使combo重新load store
            var whcombo=form.findField('warehouse.id');
            Utilities.comboReload(whcombo,action.result.data['warehouse.id'],action.result.data['warehouse.name']);

            var wlcombo=form.findField('warehouseLocation.id');
            Utilities.compositionComboReload(wlcombo, 'warehouse.id', action.result.data['warehouse.id'],action.result.data['warehouseLocation.id']);

        });
    }

});
