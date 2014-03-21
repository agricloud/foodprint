/*
 * File: app/controller/StorageLocationController.js
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

Ext.define('foodprint.controller.StorageLocationController', {
    extend: 'Ext.app.Controller',

    mixins: {
        commonController: 'foodprint.controller.CommonController'
    },

    models: [
        'StorageLocation',
        'Warehouse'
    ],
    stores: [
        'StorageLocationStore',
        'WarehouseStore'
    ],
    views: [
        'StorageLocationView'
    ],

    refs: [
        {
            ref: 'mainGrid',
            selector: 'storagelocationview #grid'
        },
        {
            ref: 'mainForm',
            selector: 'storagelocationview #form'
        },
        {
            ref: 'masterGrid',
            selector: 'storagelocationview #masterGrid'
        }
    ],

    init: function(application) {
        this.control({
            'storagelocationview #index commonindextoolbar commoncreatebtn':{
                click:this.doCreate
            },
            'storagelocationview #index commonindextoolbar commonshowbtn':{
                click:this.doShow
            },
            'storagelocationview #show commonshowtoolbar commondeletebtn':{
                click:this.doDelete
            },
            'storagelocationview #show commonshowtoolbar commonsavebtn':{
                click:this.doSave
            },
            'storagelocationview #show commonshowtoolbar commoncancelbtn':{
                click:this.doCancel
            },

            'storagelocationview #index #masterGrid':{
                select:this.doIndexDetail
            },
            'storagelocationview #grid':{
                select: this.enableShowBtn,
                deselect: this.disableShowBtn,
                itemdblclick: this.doShow
            }
        });


        this.domainName = 'foodpaint';
        this.foodpaintController = 'storageLocation';
        this.masterKey='warehouse.id';
    }

});
