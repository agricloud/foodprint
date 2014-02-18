/*
 * File: app/controller/ErpMaterialSheetDetController.js
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

Ext.define('foodprint.controller.ErpMaterialSheetDetController', {
    extend: 'Ext.app.Controller',

    mixins: {
        commonController: 'foodprint.controller.CommonController'
    },

    models: [
        'ErpMaterialSheetDet',
        'ErpMaterialSheet'
    ],
    stores: [
        'ErpMaterialSheetDetStore',
        'ErpMaterialSheetStore'
    ],
    views: [
        'ErpMaterialSheetDetView'
    ],

    refs: [
        {
            ref: 'mainGrid',
            selector: 'erpmaterialsheetdetview #grid'
        },
        {
            ref: 'mainForm',
            selector: 'erpmaterialsheetdetview #form'
        },
        {
            ref: 'masterGrid',
            selector: 'erpmaterialsheetdetview #masterGrid'
        }
    ],

    init: function(application) {

        this.control({
            'erpmaterialsheetdetview #index commonindextoolbar commoncreatebtn':{
                click:this.doCreate
            },
            'erpmaterialsheetdetview #index commonindextoolbar commonshowbtn':{
                click:this.doShow
            },
            'erpmaterialsheetdetview #show commonshowtoolbar commondeletebtn':{
                click:this.doDelete
            },
            'erpmaterialsheetdetview #show commonshowtoolbar commonsavebtn':{
                click:this.doSave
            },
            'erpmaterialsheetdetview #show commonshowtoolbar commoncancelbtn':{
                click:this.doCancel
            },
            'erpmaterialsheetdetview #index #masterGrid':{
                select:this.doIndexDetail
            },
            'erpmaterialsheetdetview #grid':{
                select: this.enableShowBtn,
                deselect: this.disableShowBtn,
                itemdblclick: this.doShow
            },
            'erpmaterialsheetdetview #show commonselectbtn':{
                click:this.activeManufactureOrderIndex
            },
            'erpmaterialsheetdetview #manufactureOrderIndex erpmanufactureordergrid':{
                itemdblclick: this.doSelectManufactureOrder
            }
        });

        this.domainName = 'foodpaint';
        this.foodpaintController = 'materialSheetDet';
        this.masterKey='materialSheet.id';

    },

    doSelectManufactureOrder: function(obj, record, index, eOpts) {
        this.getMainForm().getForm().setValues({

            'manufactureOrder.id':record.data['id'],
            'manufactureOrder.typeName':record.data['typeName'],
            'manufactureOrder.name':record.data['name']
        });
        this.activeEditor();
    }

});
