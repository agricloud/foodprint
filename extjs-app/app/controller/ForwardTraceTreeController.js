/*
 * File: app/controller/ForwardTraceTreeController.js
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

Ext.define('foodprint.controller.ForwardTraceTreeController', {
    extend: 'Ext.app.Controller',

    models: [
        'TraceTree'
    ],
    stores: [
        'ForwardTraceTreeStore'
    ],
    views: [
        'ForwardTraceTreeView'
    ],

    refs: [
        {
            ref: 'forwardTraceTreeCt',
            selector: 'forwardtracetreeview'
        },
        {
            ref: 'commonTraceToolbar',
            selector: 'forwardtracetreeview commontracetoolbar'
        }
    ],

    init: function(application) {
        this.control({
            'forwardtracetreeview commontracetoolbar combo[itemId=commonBatchCombo]':{
                select: this.doSearchBatch
            },
            'forwardtracetreeview commontracetoolbar button[itemId=commonExpandallBtn]':{
                click: this.doExpandall
            },
            'forwardtracetreeview commontracetoolbar button[itemId=commonCollapseallBtn]':{
                click: this.doCollapseall
            },
            'forwardtracetreeview treepanel[itemId=forwardTraceTreePanel]':{
                beforeitemappend: this.beforeitemappend
            }
        });
    },

    doSearchBatch: function() {

        console.log("forwardTraceTree.doSearchBatch");

        if(this.getForwardTraceTreeCt().down('combo[itemId=commonBatchCombo]').getValue()!='') {

            var treeStore=this.getForwardTraceTreeCt().down('treepanel[itemId=forwardTraceTreePanel]').getStore();

            Ext.Ajax.request({
                method: 'GET',
                url:'/traceTree/getBatchRoot/',
                params:{
                    'id':this.getCommonTraceToolbar().down('combo[itemId=commonBatchCombo]').getValue()
                },
                success:function(response,options){
                    //console.log(response);
                    //console.log(options);
                    var record = Ext.decode(response.responseText);
                    //console.log(record);
                    var root={
                        id:record.id,
                        name:record.name,
                        'item.title':record.item.title,
                        'sheet.name':record.sheet.name,
                        'sheet.typeName':record.sheet.typeName,
                        expectQty:record.expectQty,
                        'countryTitle':record.countryTitle,
                        dueDate:record.dueDate,
                        'supplier.id':record.supplier.id

                    };
                    treeStore.setRootNode(root);
                },
                callback: function(options,success,response) {

                }
            });


            /*未加入批號單據之處理方式
            var batchStore=Ext.getStore('BatchShowDeepStore');
            batchStore.load({
            params:{
            'id':this.getCommonTraceToolbar().down('triggerfield[itemId=commonBatchTrigger]').getValue()
            },
            callback: function(records, operation, success) {
            //console.log(records);
            //console.log(operation);
            //console.log(success);
            var root={
                id:records[0].data.id,
                name:records[0].data.name,
                'item.title':records[0].data['item.title'],
                expectQty:records[0].data.expectQty,
                country:records[0].data.country

            };
            treeStore.setRootNode(root);

        }
    });
    */
            }
    },

    doExpandall: function(button, e, eOpts) {
        console.log(button);
        var me = button.up().up().down('treepanel[itemId=forwardTraceTreePanel]');
        var toolbar = button.up('toolbar');

        me.getEl().mask('Expanding tree...');
        toolbar.disable();

        me.expandAll(function() {
            me.getEl().unmask();
            toolbar.enable();
        });

    },

    doCollapseall: function(button, e, eOpts) {
        var me = button.up().up().down('treepanel[itemId=forwardTraceTreePanel]');
        var toolbar = button.up('toolbar');

        toolbar.disable();
        me.collapseAll(function() {
            toolbar.enable();
        });

    },

    beforeitemappend: function() {
        this.getForwardTraceTreeCt().down('button[itemId=commonExpandallBtn]').setDisabled(false);
        this.getForwardTraceTreeCt().down('button[itemId=commonCollapseallBtn]').setDisabled(false);
    }

});
