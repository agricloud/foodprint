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
        'ErpMaterialSheet',
        'ErpMaterialSheetDet',
        'Workstation',
        'Supplier',
        'Warehouse',
        'WarehouseLocation',
        'Batch',
        'ErpManufactureOrder',
        'ErpMaterialReturnSheetDet'
    ],
    stores: [
        'ErpMaterialReturnSheetStore',
        'ErpMaterialSheetStore',
        'ErpMaterialSheetDetStore',
        'WorkstationStore',
        'SupplierStore',
        'WarehouseStore',
        'WarehouseLocationStore',
        'BatchStore',
        'ErpManufactureOrderStore',
        'ErpMaterialReturnSheetDetStore'
    ],
    views: [
        'ErpMaterialReturnSheetView',
        'ErpMaterialSheetDetGrid',
        'ErpMaterialSheetGrid',
        'ErpMaterialReturnSheetGrid'
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
            selector: 'erpmaterialreturnsheetview #show #indexDetail #detailGrid'
        },
        {
            ref: 'detailForm',
            selector: 'erpmaterialreturnsheetview #detailForm'
        },
        {
            ref: 'materialSheetDetGrid',
            selector: 'erpmaterialreturnsheetview #materialSheetDetIndex #detailGrid'
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
            'erpmaterialreturnsheetview #show #indexDetail #detailGrid':{
                select: this.enableDetailShowBtn,
                deselect: this.disableDetailShowBtn,
                itemdblclick: this.doShowMaterialReturnSheetDet
            },
            'erpmaterialreturnsheetview #materialSheetDetIndex #detailGrid':{
                // itemdblclick: this.doSelectMaterialSheetDet //暫用
                itemdblclick: this.doShowMaterialSheetDet //尚未調整好
            },
            'erpmaterialreturnsheetview #showDetail #detailForm commonselectbtn':{
                click:this.activeMaterialSheetDetIndex
            },
            'erpmaterialreturnsheetview #showDetail #detailForm commoncancelbtn':{
                click:this.doCancelSelectMaterialSheetDet
            },
            'erpmaterialreturnsheetview #materialSheetDetIndex erpmaterialsheetgrid':{
                select: this.doIndexDetailMaterialSheet
            }
        });


        this.domainName = 'foodpaint';
        this.foodpaintController = 'materialReturnSheet';
        this.foodpaintDetController = 'materialReturnSheetDet';
        this.masterKey='materialReturnSheet.id';
    },

    doIndexDetailMaterialSheet: function(obj, record, index, eOpts) {
        var grid = this.getMainGrid().up().up().down("panel[itemId=materialSheetDetIndex]").down("grid[itemId=detailGrid]");
        console.log();
        var store=grid.getStore(grid);
        console.log(store);

        store.data.clear();

        var params = {};
        params["materialSheet.id"]=record.data.id;

        store.getProxy().extraParams = params;
        store.load();
    },

    doCancelMaterialSheetDet: function() {

        this.getDetailForm().getForm().setValues({
            'materialSheetDet.id':null,
            'materialSheetDet.typeName':null,
            'materialSheetDet.name':null,
            'materialSheetDet.sequence':null
        });
    },

    doShowMaterialReturnSheet: function() {
        this.doShowAndIndexDetail(function(success,form,action){

        });
    },

    doShowMaterialReturnSheetDet: function() {

        this.doShowDetail(function(success,form,action){

        });
    },

    doSelectMaterialSheetDet: function(obj, record, index, eOpts) {
        this.getDetailForm().getForm().setValues({

            'materialSheetDet.id':record.data['id'],
            'materialSheetDet.typeName':record.data['typeName'],
            'materialSheetDet.name':record.data['name'],
            'materialSheetDet.sequence':record.data['sequence'],
            'item.id':record.data['item.id'],
            'item.name':record.data['item.name'],
            'item.title':record.data['item.title'],
            'warehouse.id':record.data['warehouse.id'],
            'warehouse.title':record.data['warehouse.title'],
            'warehouseLocation.id':record.data['warehouseLocation.id'],
            'warehouseLocation.title':record.data['warehouseLocation.title'],
            'batch.id':record.data['batch.id'],
            'batch.name':record.data['batch.name'],
            'manufactureOrder.id':record.data['manufactureOrder.id'],
            'manufactureOrder.name':record.data['manufactureOrder.name']
        });

        this.activeDetailEditor();


    },

    doShowMaterialSheetDet: function() {
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

    doCancelSelectMaterialSheetDet: function() {
        var form=this.getDetailForm();
        var id=form.down('field[name=id]').getValue();
        var typeName=form.down('field[name=typeName]').getValue();
        var name=form.down('field[name=name]').getValue();
        var sequence=form.down('field[name=sequence]').getValue();
        form.getForm().reset(true);

        form.getForm().setValues({
            'id':id,
            'typeName':typeName,
            'name':name,
            'sequence':sequence
        });

    }

});
