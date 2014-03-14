/*
 * File: app/controller/BackwardTraceTreeController.js
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

Ext.define('foodprint.controller.BackwardTraceTreeController', {
    extend: 'Ext.app.Controller',

    models: [
        'TraceTree'
    ],
    stores: [
        'BackwardTraceTreeStore'
    ],
    views: [
        'BackwardTraceTreeView'
    ],

    refs: [
        {
            ref: 'backwardTraceTreeCt',
            selector: 'backwardtracetreeview'
        }
    ],

    init: function(application) {
        this.control({
            'backwardtracetreeview commontracetoolbar combo[itemId=commonBatchCombo]':{
                select:this.doSetRoot
            },

            'backwardtracetreeview commontracetoolbar button[itemId=commonExpandallBtn]':{
                click:this.doExpandall
            },
            'backwardtracetreeview commontracetoolbar button[itemId=commonCollapseallBtn]':{
                click: this.doCollapseall
            },
            'backwardtracetreeview treepanel[itemId=backwardTraceTreePanel]':{
                beforeitemexpand: this.addExtraParamsToStore
            }
        });


    },

    doSetRoot: function() {
        console.log("TraceTree.doSearchBatchAndSetRoot");


        if(this.getBackwardTraceTreeCt().down('combo[itemId=commonBatchCombo]').getValue()!='') {

            var treeStore=this.getBackwardTraceTreeCt().down('treepanel[itemId=backwardTraceTreePanel]').getStore();
            var that = this;
            Ext.Ajax.request({
                method: 'GET',
                url:'/traceTree/backwardTraceRoot/',
                params:{
                    'id':this.getBackwardTraceTreeCt().down('combo[itemId=commonBatchCombo]').getValue()
                },
                success:function(response,options){
                    //console.log(response);
                    //console.log(options);
                    var record = Ext.decode(response.responseText);
                    //console.log(record);
                    var root={
                        'note':record.note,
                        'class':record.class,
                        'type':record.type,
                        'name':record.name,
                        'sheet':record.sheet,
                        'item.name':record.item.name,
                        'item.title':record.item.title,
                        'item.spec':record.item.spec,
                        'item.unit':record.item.unit,
                        'qty':record.qty

                    };
                    treeStore.setRootNode(root);
                    that.getBackwardTraceTreeCt().down('button[itemId=commonExpandallBtn]').setDisabled(false);
                    that.getBackwardTraceTreeCt().down('button[itemId=commonCollapseallBtn]').setDisabled(false);

                },
                callback: function(options,success,response) {

                }
            });
        }
    },

    doExpandall: function(button, e, eOpts) {


        var me = button.up().up().down('treepanel[itemId=backwardTraceTreePanel]');
        var toolbar = button.up('toolbar');

        me.getEl().mask('Expanding tree...');
        toolbar.disable();

        me.expandAll(function() {
            me.getEl().unmask();
            toolbar.enable();
        });

    },

    doCollapseall: function(button, e, eOpts) {

        var me = button.up().up().down('treepanel[itemId=backwardTraceTreePanel]');
        var toolbar = button.up('toolbar');

        toolbar.disable();
        me.collapseAll(function() {
            toolbar.enable();
        });

    },

    addExtraParamsToStore: function(node, eOpts) {
        console.log('addExtraParamsToStore');
        console.log(node.data);
        var params={};
        params.class = node.data.class;
        params.name = node.data.name;
        this.getBackwardTraceTreeCt().down('treepanel[itemId=backwardTraceTreePanel]').getStore().getProxy().extraParams = params;
    }

});
