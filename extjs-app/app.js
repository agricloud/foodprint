/*
 * File: app.js
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

//@require @packageOverrides
Ext.Loader.setConfig({
    enabled: true,
    paths: {
        'Ext.ux': 'extjs/examples/ux',
        'Ext.i18n': 'ux/i18n'
    }
});

Ext.application({

    requires: [
        'foodprint.view.Utilities',
        'Ext.ux.grid.FiltersFeature',
        'foodprint.controller.CommonController',
        'Ext.i18n.Bundle'
    ],
    bundle: {
        bundle: 'Application',
        lang: 'zh-TW',
        path: '../i18n',
        noCache: true//,format: 'json'
    },
    views: [
        'ErpReceivableSheetGrid',
        'ErpReceivableSheetDetGrid',
        'ErpAccountsPayableVoucherSheetView',
        'ErpAccountsPayableSheetView'
    ],
    autoCreateViewport: true,
    controllers: [
        'MainPageController',
        'LoginController',
        'ItemController',
        'ParamController',
        'WorkstationController',
        'CustomerController',
        'BatchController',
        'UserController',
        'OperationController',
        'BackwardTraceTreeController',
        'ForwardTraceTreeController',
        'ItemRouteController',
        'BatchRouteController',
        'BatchReportDetController',
        'ReportController',
        'ReportParamsController',
        'ErpManufactureOrderController',
        'SupplierController',
        'BatchSourceController',
        'ErpCustomerOrderController',
        'ErpMaterialSheetController',
        'ErpPurchaseSheetController',
        'ErpStockInSheetController',
        'ErpOutSrcPurchaseSheetController',
        'ErpSaleSheetController',
        'WarehouseController',
        'InventoryController',
        'InventoryDetailController',
        'SiteController',
        'WarehouseLocationController',
        'ErpPurchaseReturnSheetController',
        'ErpOutSrcPurchaseReturnSheetController',
        'ErpSaleReturnSheetController',
        'ErpMaterialReturnSheetController',
        'ErpAccountSheetController'
    ],
    name: 'foodprint',

    launch: function() {
        Ext.form.DateField.prototype.altFormats = 'm/d/Y|n/j/Y|n/j/y|m/j/y|n/d/y|m/j/Y|n/d/Y|m-d-y|m-d-Y|m/d|m-d|md|mdy|mdY|d|Y-m-d|n-j|n/j|Y-m-d\\TH:i:s\\Z';
        Ext.form.DateField.prototype.submitFormat = 'Y-m-d\\TH:i:s';

        //Ext.form.DateField.prototype.submitFormat = 'Y-m-d H:i:s \\z';


        var afterConfigRead = function(){

            //未登出自動轉入系統畫面
            if(Utilities.getSysConfig("username")){
                //LoginController loginSuccess
                var mainVP = Ext.getCmp('mainVP');
                mainVP.removeAll();
                mainVP.add({xtype: 'maincontainer'});

                Ext.get('username').update(Utilities.getSysConfig("username"), false);
            }
            else{
                if(Utilities.getSysConfig("environment")== "development"){
                    var login = Ext.getCmp("login");
                    login.down("textfield[name=j_username]").setValue("admin");
                    login.down("textfield[name=j_password]").setValue("admin");
                }   
            }

        }

        Utilities.readSysConfig(afterConfigRead);
    }

});
