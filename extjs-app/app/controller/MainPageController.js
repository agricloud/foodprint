/*
 * File: app/controller/MainPageController.js
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

Ext.define('foodprint.controller.MainPageController', {
    extend: 'Ext.app.Controller',

    views: [
        'MainContainer',
        'OperationView',
        'MainViewport',
        'ItemView',
        'UserView',
        'ItemRouteView',
        'BatchView',
        'BatchRouteView',
        'CustomerView',
        'BatchReportDetView',
        'ReportView',
        'ParamView',
        'WorkstationView'
    ],

    refs: [
        {
            ref: 'mainContainer',
            selector: 'maincontainer'
        }
    ],

    init: function(application) {
        this.control({
            'maincontainer button[itemId=itemMaintain]':{
                click:this.itemMaintain
            },
            'maincontainer button[itemId=batchMaintain]':{
                click:this.batchMaintain
            },
            'maincontainer button[itemId=workstationMaintain]':{
                click:this.workstationMaintain
            },
            'maincontainer button[itemId=operationMaintain]':{
                click:this.operationMaintain
            },
            'maincontainer button[itemId=batchParamsMaintain]':{
                click:this.batchParamsMaintain
            },
            'maincontainer button[itemId=paramMaintain]':{
                click:this.paramMaintain
            },
            'maincontainer button[itemId=reportMaintain]':{
                click:this.reportMaintain
            },
            'maincontainer button[itemId=reportParamsMaintain]':{
                click:this.reportParamsMaintain
            },
            'maincontainer button[itemId=userMaintain]':{
                click:this.userMaintain
            },
            'maincontainer button[itemId=forwardTrace]':{
                click:this.forwardTrace
            },
            'maincontainer button[itemId=backwardTrace]':{
                click:this.backwardTrace
            },
            'maincontainer button[itemId=customerMaintain]':{
                click:this.customerMaintain
            },
            'maincontainer button[itemId=supplierMaintain]':{
                click:this.supplierMaintain
            },
            'maincontainer button[itemId=itemRouteMaintain]':{
                click:this.itemRouteMaintain
            },
            'maincontainer button[itemId=batchRouteMaintain]':{
                click:this.batchRouteMaintain
            },
            'maincontainer button[itemId=batchSourceMaintain]':{
                click:this.batchSourceMaintain
            },
            'maincontainer button[itemId=warehouseMaintain]':{
                click:this.warehouseMaintain
            },
            'maincontainer button[itemId=erpCustomerOrderMaintain]':{
                click:this.erpCustomerOrderMaintain
            },
            'maincontainer button[itemId=erpCustomerOrderDetMaintain]':{
                click:this.erpCustomerOrderDetMaintain
            },
            'maincontainer button[itemId=erpPurchaseSheetMaintain]':{
                click:this.erpPurchaseSheetMaintain
            },
            'maincontainer button[itemId=erpPurchaseSheetDetMaintain]':{
                click:this.erpPurchaseSheetDetMaintain
            },
            'maincontainer button[itemId=erpManufactureOrderMaintain]':{
                click:this.erpManufactureOrderMaintain
            },
            'maincontainer button[itemId=erpMaterialSheetMaintain]':{
                click:this.erpMaterialSheetMaintain
            },
            'maincontainer button[itemId=erpMaterialSheetDetMaintain]':{
                click:this.erpMaterialSheetDetMaintain
            },
            'maincontainer button[itemId=erpStockInSheetMaintain]':{
                click:this.erpStockInSheetMaintain
            },
            'maincontainer button[itemId=erpStockInSheetDetMaintain]':{
                click:this.erpStockInSheetDetMaintain
            },
            'maincontainer button[itemId=erpOutSrcPurchaseSheetMaintain]':{
                click:this.erpOutSrcPurchaseSheetMaintain
            },
            'maincontainer button[itemId=erpOutSrcPurchaseSheetDetMaintain]':{
                click:this.erpOutSrcPurchaseSheetDetMaintain
            },
            'maincontainer button[itemId=erpSaleSheetMaintain]':{
                click:this.erpSaleSheetMaintain
            },
            'maincontainer button[itemId=erpSaleSheetDetMaintain]':{
                click:this.erpSaleSheetDetMaintain
            },
            'maincontainer':{
                afterrender:function(){
                    var link = Ext.get('btn-logout'); // will grab all DOM inputs
                    link.on('click', Utilities.logout);

                }
            }
        });


    },

    maintainBtn: function(xtypeStr, tabName, docLink) {
        //console.log(this.getMainContainer());
        var mainPanel = this.getMainContainer().down('panel[itemId=mainPanel]');

        //console.log(mainPanel);

        mainPanel.removeAll() 

        mainPanel.add({ 
            xtype: xtypeStr,
            title:tabName +'　'+'<a href="'+docLink+'" target="_blank" style="color:grey;text-decoration:underline;">說明</a>'
        });

    },

    itemMaintain: function() {
        var xtypeStr = 'itemview';
        var tabName = Utilities.getMsg('mainContainer.itemMaintain.label');//'品項維護';
        var docLink = 'https://drive.google.com/file/d/0B_Z9et2ajnisY1BuTHZEOWswZE0/edit?usp=sharing';
        this.maintainBtn(xtypeStr,tabName,docLink);
    },

    batchMaintain: function() {
        var xtypeStr = 'batchview';
        var tabName = Utilities.getMsg('mainContainer.batchMaintain.label');//'批號維護';
        var docLink = 'https://drive.google.com/file/d/0B_Z9et2ajnisQS16ekwwTWFBVXc/edit?usp=sharing';
        this.maintainBtn(xtypeStr,tabName,docLink);
    },

    operationMaintain: function() {
        var xtypeStr = 'operationview';
        var tabName = Utilities.getMsg('mainContainer.operationMaintain.label');//'製程維護';
        var docLink = 'https://drive.google.com/file/d/0B_Z9et2ajnisNDVROVpIcDMzNTQ/edit?usp=sharing';
        this.maintainBtn(xtypeStr,tabName,docLink);
    },

    workstationMaintain: function() {
        var xtypeStr = 'workstationview';
        var tabName = Utilities.getMsg('mainContainer.workstationMaintain.label');//'工作站維護';
        var docLink = 'https://drive.google.com/file/d/0B_Z9et2ajnisS0JyNjByV0ZfblU/edit?usp=sharing';
        this.maintainBtn(xtypeStr,tabName,docLink);
    },

    reportMaintain: function() {
        var xtypeStr = 'reportview';
        var tabName = Utilities.getMsg('mainContainer.reportMaintain.label');//'履歷維護';
        var docLink = 'https://drive.google.com/file/d/0B_Z9et2ajnisQkotRTdfRFNPVk0/edit?usp=sharing';
        this.maintainBtn(xtypeStr,tabName,docLink);
    },

    paramMaintain: function() {
        var xtypeStr = 'paramview';
        var tabName = Utilities.getMsg('mainContainer.paramMaintain.label');//'參數設定';
        var docLink = 'https://drive.google.com/file/d/0B_Z9et2ajnisc3lCM0g0Y0hRWFE/edit?usp=sharing';
        this.maintainBtn(xtypeStr,tabName,docLink);
    },

    batchParamsMaintain: function() {
        var xtypeStr = 'batchreportdetview';
        var tabName = Utilities.getMsg('mainContainer.batchParamsMaintain.label');//'批號履歷維護';
        var docLink = 'https://drive.google.com/file/d/0B_Z9et2ajnisZDlmbmpyR0NaYmM/edit?usp=sharing';
        this.maintainBtn(xtypeStr,tabName,docLink);
    },

    userMaintain: function() {
        var xtypeStr = 'userview';
        var tabName = Utilities.getMsg('mainContainer.userMaintain.label');//'使用者維護';
        var docLink = 'https://drive.google.com/file/d/0B_Z9et2ajnisQ2F1RkV6aTZQSTA/edit?usp=sharing';
        this.maintainBtn(xtypeStr,tabName,docLink);
    },

    forwardTrace: function() {
        var xtypeStr = 'forwardtracetreeview';
        var tabName = Utilities.getMsg('mainContainer.forwardTrace.label');//'順向追溯';
        var docLink = 'https://drive.google.com/file/d/0B_Z9et2ajnisU1podnpJdzctY0E/edit?usp=sharing';
        this.maintainBtn(xtypeStr,tabName,docLink);
    },

    backwardTrace: function() {
        var xtypeStr = 'backwardtracetreeview';
        var tabName = Utilities.getMsg('mainContainer.backwardTrace.label');//'逆向追溯';
        var docLink = 'https://drive.google.com/file/d/0B_Z9et2ajnisMWZrN0xKY1pmSGc/edit?usp=sharing';
        this.maintainBtn(xtypeStr,tabName,docLink);
    },

    customerMaintain: function() {
        var xtypeStr = 'customerview';
        var tabName = Utilities.getMsg('mainContainer.customerMaintain.label');//'客戶維護';
        var docLink = 'https://drive.google.com/file/d/0B_Z9et2ajnisbG83RFV0eVcyc0U/edit?usp=sharing';
        this.maintainBtn(xtypeStr,tabName,docLink);
    },

    itemRouteMaintain: function() {
        var xtypeStr = 'itemrouteview';
        var tabName = Utilities.getMsg('mainContainer.itemRouteMaintain.label');//'品項途程維護';
        var docLink = 'https://drive.google.com/file/d/0B_Z9et2ajnisWklaSEJheExCSVU/edit?usp=sharing';
        this.maintainBtn(xtypeStr,tabName,docLink);
    },

    batchRouteMaintain: function() {
        var xtypeStr = 'batchrouteview';
        var tabName = Utilities.getMsg('mainContainer.batchRouteMaintain.label');//'批號途程維護';
        var docLink = 'https://drive.google.com/file/d/0B_Z9et2ajnisMVcteWhWYVo5bU0/edit?usp=sharing';
        this.maintainBtn(xtypeStr,tabName,docLink);
    },

    reportParamsMaintain: function() {
        var xtypeStr = 'reportparamsview';
        var tabName = Utilities.getMsg('mainContainer.reportParamsMaintain.label');//'履歷參數維護';
        var docLink = 'https://drive.google.com/file/d/0B_Z9et2ajnisSS12Ym5TT2VPSlk/edit?usp=sharing';
        this.maintainBtn(xtypeStr,tabName,docLink);
    },

    supplierMaintain: function() {
        var xtypeStr = 'supplierview';
        var tabName = Utilities.getMsg('mainContainer.supplierMaintain.label');//'供應商維護';
        var docLink = 'https://drive.google.com/file/d/0B_Z9et2ajniseF9mX0RYN19mR1U/edit?usp=sharing';
        this.maintainBtn(xtypeStr,tabName,docLink);
    },

    batchSourceMaintain: function() {
        var xtypeStr = 'batchsourceview';
        var tabName = Utilities.getMsg('mainContainer.batchSourceMaintain.label');//'批號途程維護';
        var docLink = 'https://drive.google.com/file/d/0B_Z9et2ajnisMlZpQ1F1U3ZzZUU/edit?usp=sharing';
        this.maintainBtn(xtypeStr,tabName,docLink);
    },

    erpManufactureOrderMaintain: function() {
        var xtypeStr = 'erpmanufactureorderview';
        var tabName = Utilities.getMsg('mainContainer.manufactureOrderMaintain.label');//'製令維護';
        var docLink = '';// 'https://drive.google.com/file/d/0B_Z9et2ajnisQS16ekwwTWFBVXc/edit?usp=sharing';
        this.maintainBtn(xtypeStr,tabName,docLink);
    },

    erpCustomerOrderMaintain: function() {

        var xtypeStr = 'erpcustomerorderview';
        var tabName = Utilities.getMsg('mainContainer.customerOrderMaintain.label');//'訂單維護';
        var docLink = '';// 'https://drive.google.com/file/d/0B_Z9et2ajnisQS16ekwwTWFBVXc/edit?usp=sharing';
        this.maintainBtn(xtypeStr,tabName,docLink);
    },

    erpCustomerOrderDetMaintain: function() {

        var xtypeStr = 'erpcustomerorderdetview';
        var tabName = Utilities.getMsg('mainContainer.customerOrderDetMaintain.label');//'訂單單身維護';
        var docLink = '';// 'https://drive.google.com/file/d/0B_Z9et2ajnisQS16ekwwTWFBVXc/edit?usp=sharing';
        this.maintainBtn(xtypeStr,tabName,docLink);
    },

    erpMaterialSheetMaintain: function() {

        var xtypeStr = 'erpmaterialsheetview';
        var tabName = Utilities.getMsg('mainContainer.materialSheetMaintain.label');
        var docLink = '';// 'https://drive.google.com/file/d/0B_Z9et2ajnisQS16ekwwTWFBVXc/edit?usp=sharing';
        this.maintainBtn(xtypeStr,tabName,docLink);
    },

    erpMaterialSheetDetMaintain: function() {

        var xtypeStr = 'erpmaterialsheetdetview';
        var tabName = Utilities.getMsg('mainContainer.materialSheetDetMaintain.label');
        var docLink = '';// 'https://drive.google.com/file/d/0B_Z9et2ajnisQS16ekwwTWFBVXc/edit?usp=sharing';
        this.maintainBtn(xtypeStr,tabName,docLink);
    },

    erpPurchaseSheetMaintain: function() {

        var xtypeStr = 'erppurchasesheetview';
        var tabName = Utilities.getMsg('mainContainer.purchaseSheetMaintain.label');
        var docLink = '';// 'https://drive.google.com/file/d/0B_Z9et2ajnisQS16ekwwTWFBVXc/edit?usp=sharing';
        this.maintainBtn(xtypeStr,tabName,docLink);
    },

    erpPurchaseSheetDetMaintain: function() {

        var xtypeStr = 'erppurchasesheetdetview';
        var tabName = Utilities.getMsg('mainContainer.purchaseSheetDetMaintain.label');
        var docLink = '';// 'https://drive.google.com/file/d/0B_Z9et2ajnisQS16ekwwTWFBVXc/edit?usp=sharing';
        this.maintainBtn(xtypeStr,tabName,docLink);
    },

    erpStockInSheetMaintain: function() {

        var xtypeStr = 'erpstockinsheetview';
        var tabName = Utilities.getMsg('mainContainer.stockInSheetMaintain.label');
        var docLink = '';// 'https://drive.google.com/file/d/0B_Z9et2ajnisQS16ekwwTWFBVXc/edit?usp=sharing';
        this.maintainBtn(xtypeStr,tabName,docLink);
    },

    erpStockInSheetDetMaintain: function() {

        var xtypeStr = 'erpstockinsheetdetview';
        var tabName = Utilities.getMsg('mainContainer.stockInSheetDetMaintain.label');
        var docLink = '';// 'https://drive.google.com/file/d/0B_Z9et2ajnisQS16ekwwTWFBVXc/edit?usp=sharing';
        this.maintainBtn(xtypeStr,tabName,docLink);
    },

    erpOutSrcPurchaseSheetMaintain: function() {

        var xtypeStr = 'erpoutsrcpurchasesheetview';
        var tabName = Utilities.getMsg('mainContainer.outSrcPurchaseSheetMaintain.label');
        var docLink = '';// 'https://drive.google.com/file/d/0B_Z9et2ajnisQS16ekwwTWFBVXc/edit?usp=sharing';
        this.maintainBtn(xtypeStr,tabName,docLink);
    },

    erpOutSrcPurchaseSheetDetMaintain: function() {

        var xtypeStr = 'erpoutsrcpurchasesheetdetview';
        var tabName = Utilities.getMsg('mainContainer.outSrcPurchaseSheetDetMaintain.label');
        var docLink = '';// 'https://drive.google.com/file/d/0B_Z9et2ajnisQS16ekwwTWFBVXc/edit?usp=sharing';
        this.maintainBtn(xtypeStr,tabName,docLink);
    },

    erpSaleSheetMaintain: function() {

        var xtypeStr = 'erpsalesheetview';
        var tabName = Utilities.getMsg('mainContainer.saleSheetMaintain.label');
        var docLink = '';// 'https://drive.google.com/file/d/0B_Z9et2ajnisQS16ekwwTWFBVXc/edit?usp=sharing';
        this.maintainBtn(xtypeStr,tabName,docLink);
    },

    erpSaleSheetDetMaintain: function() {

        var xtypeStr = 'erpsalesheetdetview';
        var tabName = Utilities.getMsg('mainContainer.saleSheetDetMaintain.label');
        var docLink = '';// 'https://drive.google.com/file/d/0B_Z9et2ajnisQS16ekwwTWFBVXc/edit?usp=sharing';
        this.maintainBtn(xtypeStr,tabName,docLink);
    },

    warehouseMaintain: function() {

        var xtypeStr = 'warehouseview';
        var tabName = Utilities.getMsg('mainContainer.warehouseMaintain.label');
        var docLink = '';// 'https://drive.google.com/file/d/0B_Z9et2ajnisQS16ekwwTWFBVXc/edit?usp=sharing';
        this.maintainBtn(xtypeStr,tabName,docLink);
    }

});
