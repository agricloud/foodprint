/*
 * File: app/controller/ErpOutSrcPurchaseSheetController.js
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

Ext.define('foodprint.controller.ErpOutSrcPurchaseSheetController', {
    extend: 'Ext.app.Controller',

    mixins: {
        commonController: 'foodprint.controller.CommonController'
    },

    models: [
        'ErpOutSrcPurchaseSheet',
        'ErpOutSrcPurchaseSheetDet',
        'Supplier',
        'Warehouse',
        'WarehouseLocation',
        'ErpManufactureOrder'
    ],
    stores: [
        'ErpOutSrcPurchaseSheetStore',
        'ErpOutSrcPurchaseSheetDetStore',
        'SupplierStore',
        'WarehouseStore',
        'WarehouseLocationStore',
        'ErpManufactureOrderStore'
    ],
    views: [
        'ErpOutSrcPurchaseSheetView'
    ],

    refs: [
        {
            ref: 'mainGrid',
            selector: 'erpoutsrcpurchasesheetview #grid'
        },
        {
            ref: 'mainForm',
            selector: 'erpoutsrcpurchasesheetview #form'
        },
        {
            ref: 'detailGrid',
            selector: 'erpoutsrcpurchasesheetview #detailGrid'
        },
        {
            ref: 'detailForm',
            selector: 'erpoutsrcpurchasesheetview #detailForm'
        }
    ],

    init: function(application) {
        this.control({
            'erpoutsrcpurchasesheetview #index commonindextoolbar commoncreatebtn':{
                click:this.doCreateAndIndexDetail
            },
            'erpoutsrcpurchasesheetview #index commonindextoolbar commonshowbtn':{
                click:this.doShowOutSrcPurchaseSheet
            },
            'erpoutsrcpurchasesheetview #show commonshowtoolbar commondeletebtn':{
                click:this.doDelete
            },
            'erpoutsrcpurchasesheetview #show commonshowtoolbar commonsavebtn':{
                click:this.doSave
            },
            'erpoutsrcpurchasesheetview #show commonshowtoolbar commoncancelbtn':{
                click:this.doCancel
            },
            'erpoutsrcpurchasesheetview #grid':{
                select: this.enableShowBtn,
                deselect: this.disableShowBtn,
                itemdblclick: this.doShowOutSrcPurchaseSheet
            },
            'erpoutsrcpurchasesheetview #show commonindextoolbar commoncreatebtn':{
                click:this.doCreateDetail
            },
            'erpoutsrcpurchasesheetview #show commonindextoolbar commonshowbtn':{
                click:this.doShowOutSrcPurchaseSheetDet
            },
            'erpoutsrcpurchasesheetview #showDetail commonshowtoolbar commondeletebtn':{
                click:this.doDeleteDetail
            },
            'erpoutsrcpurchasesheetview #showDetail commonshowtoolbar commonsavebtn':{
                click:this.doSaveDetail
            },
            'erpoutsrcpurchasesheetview #showDetail commonshowtoolbar commoncancelbtn':{
                click:this.doCancelDetail
            },
            'erpoutsrcpurchasesheetview #detailGrid':{
                select: this.enableDetailShowBtn,
                deselect: this.disableDetailShowBtn,
                itemdblclick: this.doShowOutSrcPurchaseSheetDet
            },
            'erpoutsrcpurchasesheetview #showDetail commonselectbtn':{
                click:this.activeManufactureOrderIndex
            },
            'erpoutsrcpurchasesheetview #manufactureOrderIndex erpmanufactureordergrid':{
                itemdblclick: this.doSelectManufactureOrder
            },
            'erpoutsrcpurchasesheetview #show commonshowtoolbar commonprintbtn':{
                click:this.doPrint
            }

        });


        this.domainName = 'foodpaint';
        this.foodpaintController = 'outSrcPurchaseSheet';
        this.foodpaintDetController = 'outSrcPurchaseSheetDet';
        this.masterKey='outSrcPurchaseSheet.id';
    },

    doSelectManufactureOrder: function(obj, record, index, eOpts) {
        this.getDetailForm().getForm().setValues({

            'manufactureOrder.id':record.data['id'],
            'manufactureOrder.typeName':record.data['typeName'],
            'manufactureOrder.name':record.data['name'],
            'item.id':record.data['item.id'],
            'item.name':record.data['item.name'],
            'item.title':record.data['item.title'],
            'batch.name':record.data['batch.name'],
            'qty':record.data['qty']
        });
        this.activeDetailEditor();
    },

    doShowOutSrcPurchaseSheet: function() {
        this.doShowAndIndexDetail(function(success,form,action){
            //由於store設定load第1-50筆
            //導致doShow時若資料屬於第50筆之後無法正常顯示
            //在此使combo重新load store
            var spcombo=form.findField('supplier.id');
            Utilities.comboReload(spcombo,action.result.data['supplier.id'],action.result.data['supplier.name']);

        });
    },

    doShowOutSrcPurchaseSheetDet: function() {

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
    }

});
