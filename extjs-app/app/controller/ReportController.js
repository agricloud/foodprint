/*
 * File: app/controller/ReportController.js
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

Ext.define('foodprint.controller.ReportController', {
    extend: 'Ext.app.Controller',

    mixins: {
        commonController: 'foodprint.controller.CommonController'
    },

    models: [
        'Report',
        'ReportType'
    ],
    stores: [
        'ReportStore',
        'ReportTypeStore'
    ],
    views: [
        'ReportView'
    ],

    refs: [
        {
            ref: 'mainGrid',
            selector: 'reportview #grid'
        },
        {
            ref: 'mainForm',
            selector: 'reportview #form'
        }
    ],

    init: function(application) {

        this.control({
            'reportview #index commonindextoolbar commoncreatebtn':{
                click:this.doCreate
            },
            'reportview #index commonindextoolbar commonshowbtn':{
                click:this.doShow
            },
            'reportview #show commonshowtoolbar commondeletebtn':{
                click:this.doDelete
            },
            'reportview #show commonshowtoolbar commonsavebtn':{
                click:this.doSave
            },
            'reportview #show commonshowtoolbar commoncancelbtn':{
                click:this.doCancel
            },
            'reportview #grid':{
                select: this.enableShowBtn,
                deselect: this.disableShowBtn,
                itemdblclick: this.doShow
            }

        });


        this.domainName = 'report';
    }

});
