/*
 * File: app/controller/ErpMaterialReturnSheetController.js
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

Ext.define('foodprint.controller.ErpMaterialReturnSheetController', {
    extend: 'Ext.app.Controller',

    mixins: {
        commonController: 'foodprint.controller.CommonController'
    },

    models: [
        'ErpMaterialReturnSheet',
        'ErpMaterialReturnSheetDet',
        'ErpMaterialSheet',
        'ErpMaterialSheetDet',
        'Workstation',
        'Supplier',
        'Warehouse',
        'WarehouseLocation'
    ],
    stores: [
        'ErpMaterialReturnSheetStore',
        'ErpMaterialReturnSheetDetStore',
        'ErpMaterialSheetStore',
        'ErpMaterialSheetDetStore',
        'WorkstationStore',
        'SupplierStore',
        'WarehouseStore',
        'WarehouseLocationStore'
    ],
    views: [
        'ErpMaterialReturnSheetView'
    ],

    refs: [
        {
            ref: 'mainGrid',
            selector: 'erpmaterialreturnsheetview #grid'
        },
        {
            ref: 'mainForm',
            selector: 'erpmaterialreturnsheetview #form'
        },
        {
            ref: 'detailGrid',
            selector: 'erpmaterialreturnsheetview #detailGrid'
        },
        {
            ref: 'detailForm',
            selector: 'erpmaterialreturnsheetview #detailForm'
        }
    ],

    init: function(application) {
        this.control({
            'erpmaterialreturnsheetview #index commonindextoolbar commoncreatebtn':{
                click:this.doCreateAndIndexDetail
            },
            'erpmaterialreturnsheetview #index commonindextoolbar commonshowbtn':{
                click:this.doShowMaterialReturnSheet
            },
            'erpmaterialreturnsheetview #show commonshowtoolbar commondeletebtn':{
                click:this.doDelete
            },
            'erpmaterialreturnsheetview #show commonshowtoolbar commonsavebtn':{
                click:this.doSave
            },
            'erpmaterialreturnsheetview #show commonshowtoolbar commoncancelbtn':{
                click:this.doCancel
            },
            'erpmaterialreturnsheetview #grid':{
                select: this.enableShowBtn,
                deselect: this.disableShowBtn,
                itemdblclick: this.doShowMaterialReturnSheet
            },
            'erpmaterialreturnsheetview #show commonindextoolbar commoncreatebtn':{
                click:this.doCreateDetail
            },
            'erpmaterialreturnsheetview #show commonindextoolbar commonshowbtn':{
                click:this.doShowMaterialReturnSheetDet
            },
            'erpmaterialreturnsheetview #showDetail commonshowtoolbar commondeletebtn':{
                click:this.doDeleteDetail
            },
            'erpmaterialreturnsheetview #showDetail commonshowtoolbar commonsavebtn':{
                click:this.doSaveDetail
            },
            'erpmaterialreturnsheetview #showDetail commonshowtoolbar commoncancelbtn':{
                click:this.doCancelDetail
            },
            'erpmaterialreturnsheetview #detailGrid':{
                select: this.enableDetailShowBtn,
                deselect: this.disableDetailShowBtn,
                itemdblclick: this.doShowMaterialReturnSheetDet
            },
            'erpmaterialreturnsheetview #showDetail commonselectbtn':{
                click:this.activeMaterialSheetDetIndex
            },
            'erpmaterialreturnsheetview #materialSheetDetIndex erpmaterialsheetgrid':{
                select: this.doIndexDetailMaterialSheet
            },
            'erpmaterialreturnsheetview #materialSheetDetIndex erpmaterialsheetdetgrid':{
                itemdblclick: this.doSelectMaterialSheetDet
            },
            'erpmaterialreturnsheetview #show commonshowtoolbar commonprintbtn':{
                click:this.doPrint
            }
        });

        this.domainName = 'foodpaint';
        this.foodpaintController = 'materialReturnSheet';
        this.foodpaintDetController = 'materialReturnSheetDet';
        this.masterKey='materialReturnSheet.id';
    },

    doShowMaterialReturnSheet: function() {
        this.doShowAndIndexDetail(function(success,form,action){
            //由於store設定load第1-50筆
            //導致doShow時若資料屬於第50筆之後無法正常顯示
            //在此使combo重新load store
            var wscombo=form.findField('workstation.id');
            Utilities.comboReload(wscombo,action.result.data['workstation.id'],action.result.data['workstation.name']);
            var spcombo=form.findField('supplier.id');
            Utilities.comboReload(spcombo,action.result.data['supplier.id'],action.result.data['supplier.name']);
        });
    },

    doShowMaterialReturnSheetDet: function() {
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

    activeMaterialSheetDetIndex: function() {

        this.getMainForm().up('panel[itemId=show]').up().getLayout().setActiveItem(this.getMainGrid().up().up().down("panel[itemId=materialSheetDetIndex]"));

    },

    doIndexDetailMaterialSheet: function(obj, record, index, eOpt) {
        var grid = this.getMainGrid().up().up().down("panel[itemId=materialSheetDetIndex]").down("grid[itemId=erpMaterialSheetDetGrid]");

        grid.getStore().data.clear();

        var params = {}
        params["materialSheet.id"]=record.data.id;

        grid.getStore().getProxy().extraParams = params;
        grid.getStore().load();
    },

    doSelectMaterialSheetDet: function(obj, record, index, eOpt) {
        this.getDetailForm().getForm().setValues({

            'materialSheetDet.id':record.data['id'],
            'materialSheetDet.typeName':record.data['typeName'],
            'materialSheetDet.name':record.data['name'],
            'materialSheetDet.sequence':record.data['sequence'],
            'warehouse.id':record.data['warehouse.id'],
            'warehouse.title':record.data['warehouse.title'],
            'warehouseLocation.id':record.data['warehouseLocation.id'], 
            'warehouseLocation.title':record.data['warehouseLocation.title'],   
            'item.id':record.data['item.id'],
            'item.name':record.data['item.name'],
            'item.title':record.data['item.title'],
            'batch.id':record.data['batch.id'],
            'batch.name':record.data['batch.name'],
            'manufactureOrder.id':record.data['manufactureOrder.id'],
            'manufactureOrder.typeName':record.data['manufactureOrder.typeName'],
            'manufactureOrder.name':record.data['manufactureOrder.name'],
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
