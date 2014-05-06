/*
 * File: app/controller/ErpOutSrcPurchaseReturnSheetController.js
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

Ext.define('foodprint.controller.ErpOutSrcPurchaseReturnSheetController', {
    extend: 'Ext.app.Controller',

    mixins: {
        commonController: 'foodprint.controller.CommonController'
    },

    models: [
        'ErpOutSrcPurchaseReturnSheet',
        'ErpOutSrcPurchaseReturnSheetDet',
        'ErpOutSrcPurchaseSheet',
        'ErpOutSrcPurchaseSheetDet',
        'Supplier',
        'Warehouse',
        'WarehouseLocation'
    ],
    stores: [
        'ErpOutSrcPurchaseReturnSheetStore',
        'ErpOutSrcPurchaseReturnSheetDetStore',
        'ErpOutSrcPurchaseSheetStore',
        'ErpOutSrcPurchaseSheetDetStore',
        'SupplierStore',
        'WarehouseStore',
        'WarehouseLocationStore'
    ],
    views: [
        'ErpOutSrcPurchaseReturnSheetView'
    ],

    refs: [
        {
            ref: 'mainGrid',
            selector: 'erpoutsrcpurchasereturnsheetview #grid'
        },
        {
            ref: 'mainForm',
            selector: 'erpoutsrcpurchasereturnsheetview #form'
        },
        {
            ref: 'detailGrid',
            selector: 'erpoutsrcpurchasereturnsheetview #detailGrid'
        },
        {
            ref: 'detailForm',
            selector: 'erpoutsrcpurchasereturnsheetview #detailForm'
        }
    ],

    init: function(application) {
        this.control({
            'erpoutsrcpurchasereturnsheetview #index commonindextoolbar commoncreatebtn':{
                click:this.doCreateAndIndexDetail
            },
            'erpoutsrcpurchasereturnsheetview #index commonindextoolbar commonshowbtn':{
                click:this.doShowOutSrcPurchaseReturnSheet
            },
            'erpoutsrcpurchasereturnsheetview #show commonshowtoolbar commondeletebtn':{
                click:this.doDelete
            },
            'erpoutsrcpurchasereturnsheetview #show commonshowtoolbar commonsavebtn':{
                click:this.doSave
            },
            'erpoutsrcpurchasereturnsheetview #show commonshowtoolbar commoncancelbtn':{
                click:this.doCancel
            },
            'erpoutsrcpurchasereturnsheetview #show commonshowtoolbar commonprintbtn':{
                click:this.doPrint
            },
            'erpoutsrcpurchasereturnsheetview #grid':{
                select: this.enableShowBtn,
                deselect: this.disableShowBtn,
                itemdblclick: this.doShowOutSrcPurchaseReturnSheet
            },
            'erpoutsrcpurchasereturnsheetview #show commonindextoolbar commoncreatebtn':{
                click:this.doCreateDetail
            },
            'erpoutsrcpurchasereturnsheetview #show commonindextoolbar commonshowbtn':{
                click:this.doShowOutSrcPurchaseReturnSheetDet
            },
            'erpoutsrcpurchasereturnsheetview #showDetail commonshowtoolbar commondeletebtn':{
                click:this.doDeleteDetail
            },
            'erpoutsrcpurchasereturnsheetview #showDetail commonshowtoolbar commonsavebtn':{
                click:this.doSaveDetail
            },
            'erpoutsrcpurchasereturnsheetview #showDetail commonshowtoolbar commoncancelbtn':{
                click:this.doCancelDetail
            },
            'erpoutsrcpurchasereturnsheetview #detailGrid':{
                select: this.enableDetailShowBtn,
                deselect: this.disableDetailShowBtn,
                itemdblclick: this.doShowOutSrcPurchaseReturnSheetDet
            },
            'erpoutsrcpurchasereturnsheetview #showDetail commonselectbtn':{
                click:this.activeOutSrcPurchaseSheetDetIndex
            },
            'erpoutsrcpurchasereturnsheetview #outSrcPurchaseSheetDetIndex erpoutsrcpurchasesheetgrid':{
                select: this.doIndexDetailOutSrcPurchaseSheet
            },
            'erpoutsrcpurchasereturnsheetview #outSrcPurchaseSheetDetIndex erpoutsrcpurchasesheetdetgrid':{
                itemdblclick: this.doSelectOutSrcPurchaseSheetDet
            }
        });
        this.domainName = 'foodpaint';
        this.foodpaintController = 'outSrcPurchaseReturnSheet';
        this.foodpaintDetController = 'outSrcPurchaseReturnSheetDet';
        this.masterKey='outSrcPurchaseReturnSheet.id';
    },

    doShowOutSrcPurchaseReturnSheet: function() {
        this.doShowAndIndexDetail(function(success,form,action){
            //由於store設定load第1-50筆
            //導致doShow時若資料屬於第50筆之後無法正常顯示
            //在此使combo重新load store
            var spcombo=form.findField('supplier.id');
            Utilities.comboReload(spcombo,action.result.data['supplier.id'],action.result.data['supplier.name']);

        });
    },

    doShowOutSrcPurchaseReturnSheetDet: function() {
        this.doShowDetail(function(success,form,action){
            //由於store設定load第1-50筆
            //導致doShow時若資料屬於第50筆之後無法正常顯示
            //在此使combo重新load store
            var whcombo=form.findField('warehouse.id');
            Utilities.comboReload(whcombo,action.result.data['warehouse.id'],action.result.data['warehouse.name']);

            //warehouseLocation combo需指定warehouse id才可load
            var wlcombo=form.findField('warehouseLocation.id');
            Utilities.compositionComboReload(wlcombo, 'warehouse.id', action.result.data['warehouse.id'],action.result.data['warehouseLocation.id']);
        });
    },

    activeOutSrcPurchaseSheetDetIndex: function(obj, record, index, eOpts) {
        this.getMainForm().up('panel[itemId=show]').up().getLayout().setActiveItem(this.getMainGrid().up().up().down("panel[itemId=outSrcPurchaseSheetDetIndex]"));
    },

    doIndexDetailOutSrcPurchaseSheet: function(obj, record, index, eOpts) {
        var grid = this.getMainGrid().up().up().down("panel[itemId=outSrcPurchaseSheetDetIndex]").down("grid[itemId=erpOutSrcPurchaseSheetDetGrid]");

        grid.getStore().data.clear();

        var params = {};
        params["outSrcPurchaseSheet.id"]=record.data.id;

        grid.getStore().getProxy().extraParams = params;
        grid.getStore().load();

    },

    doSelectOutSrcPurchaseSheetDet: function(obj, record, index, eOpts) {
        this.getDetailForm().getForm().setValues({
            'outSrcPurchaseSheetDet.id':record.data['id'],
            'outSrcPurchaseSheetDet.typeName':record.data['typeName'],
            'outSrcPurchaseSheetDet.name':record.data['name'],
            'outSrcPurchaseSheetDet.sequence':record.data['sequence'],
            'manufactureOrder.id':record.data['manufactureOrder.id'],
            'manufactureOrder.typeName':record.data['manufactureOrder.typeName'],
            'manufactureOrder.name':record.data['manufactureOrder.name'],
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

        //在此使combo重新load store
        var whcombo=this.getDetailForm().getForm().findField('warehouse.id');
        Utilities.comboReload(whcombo,record.data['warehouse.id'],record.data['warehouse.name']);

        //warehouseLocation combo需指定warehouse id才可load
        var wlcombo=this.getDetailForm().getForm().findField('warehouseLocation.id');
        Utilities.compositionComboReload(wlcombo, 'warehouse.id',record.data['warehouse.id'],record.data['warehouseLocation.id']);

        this.activeDetailEditor();
    }

});
