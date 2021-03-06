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
            'maincontainer button[itemId=erpManufactureOrderMaintain]':{
                click:this.erpManufactureOrderMaintain
            },
            'maincontainer':{
                afterrender:function(){
                    var link = Ext.get('btn-logout'); // will grab all DOM inputs
                    link.on('click', Utilities.logout);

                }
            }
        });


    },

    maintainBtn: function(xtypeStr, tabName) {
        //console.log(this.getMainContainer());
        var mainPanel = this.getMainContainer().down('panel[itemId=mainPanel]');

        //console.log(mainPanel);

        mainPanel.removeAll() 

        mainPanel.add({ 
            xtype: xtypeStr,
            title:tabName
        });

    },

    itemMaintain: function() {
        var xtypeStr = 'itemview';
        var tabName = Utilities.getMsg('mainContainer.itemMaintain.label');//'品項維護';
        this.maintainBtn(xtypeStr,tabName);
    },

    batchMaintain: function() {
        var xtypeStr = 'batchview';
        var tabName = Utilities.getMsg('mainContainer.batchMaintain.label');//'批號維護';
        this.maintainBtn(xtypeStr,tabName);
    },

    operationMaintain: function() {
        var xtypeStr = 'operationview';
        var tabName = Utilities.getMsg('mainContainer.operationMaintain.label');//'製程維護';
        this.maintainBtn(xtypeStr,tabName);
    },

    workstationMaintain: function() {
        var xtypeStr = 'workstationview';
        var tabName = Utilities.getMsg('mainContainer.workstationMaintain.label');//'工作站維護';
        this.maintainBtn(xtypeStr,tabName);
    },

    reportMaintain: function() {
        var xtypeStr = 'reportview';
        var tabName = Utilities.getMsg('mainContainer.reportMaintain.label');//'履歷維護';
        this.maintainBtn(xtypeStr,tabName);
    },

    paramMaintain: function() {
        var xtypeStr = 'paramview';
        var tabName = Utilities.getMsg('mainContainer.paramMaintain.label');//'參數設定';
        this.maintainBtn(xtypeStr,tabName);
    },

    batchParamsMaintain: function() {
        var xtypeStr = 'batchreportdetview';
        var tabName = Utilities.getMsg('mainContainer.batchParamsMaintain.label');//'批號履歷維護';
        this.maintainBtn(xtypeStr,tabName);
    },

    userMaintain: function() {
        var xtypeStr = 'userview';
        var tabName = Utilities.getMsg('mainContainer.userMaintain.label');//'使用者維護';
        this.maintainBtn(xtypeStr,tabName);
    },

    forwardTrace: function() {
        var xtypeStr = 'forwardtracetreeview';
        var tabName = Utilities.getMsg('mainContainer.forwardTrace.label');//'順向追溯';
        this.maintainBtn(xtypeStr,tabName);
    },

    backwardTrace: function() {
        var xtypeStr = 'backwardtracetreeview';
        var tabName = Utilities.getMsg('mainContainer.backwardTrace.label');//'逆向追溯';
        this.maintainBtn(xtypeStr,tabName);
    },

    customerMaintain: function() {
        var xtypeStr = 'customerview';
        var tabName = Utilities.getMsg('mainContainer.customerMaintain.label');//'客戶維護';
        this.maintainBtn(xtypeStr,tabName);
    },

    itemRouteMaintain: function() {
        var xtypeStr = 'itemrouteview';
        var tabName = Utilities.getMsg('mainContainer.itemRouteMaintain.label');//'品項途程維護';
        this.maintainBtn(xtypeStr,tabName);
    },

    batchRouteMaintain: function() {
        var xtypeStr = 'batchrouteview';
        var tabName = Utilities.getMsg('mainContainer.batchRouteMaintain.label');//'批號途程維護';
        this.maintainBtn(xtypeStr,tabName);
    },

    reportParamsMaintain: function() {
        var xtypeStr = 'reportparamsview';
        var tabName = Utilities.getMsg('mainContainer.reportParamsMaintain.label');//'履歷參數維護';
        this.maintainBtn(xtypeStr,tabName);
    },

    erpManufactureOrderMaintain: function() {
        var xtypeStr = 'erpmanufactureorderview';
        var tabName = Utilities.getMsg('mainContainer.manufactureOrderMaintain.label');//'製令維護';
        this.maintainBtn(xtypeStr,tabName);
    },

    supplierMaintain: function() {
        var xtypeStr = 'supplierview';
        var tabName = Utilities.getMsg('mainContainer.supplierMaintain.label');//'供應商維護';
        this.maintainBtn(xtypeStr,tabName);
    }

});
