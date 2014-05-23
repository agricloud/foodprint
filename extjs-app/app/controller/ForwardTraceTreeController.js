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
        'TraceTree',
        'TraceSheetDetail'
    ],
    stores: [
        'ForwardTraceTreeStore',
        'TraceSheetDetailStore'
    ],
    views: [
        'ForwardTraceTreeView'
    ],

    refs: [
        {
            ref: 'mainTree',
            selector: 'forwardtracetreeview #tree'
        },
        {
            ref: 'sheetGrid',
            selector: 'forwardtracetreeview #sheetGrid'
        }
    ],

    init: function(application) {
        this.control({
            'forwardtracetreeview commontracetoolbar combo[itemId=commonBatchCombo]':{
                select: this.doSetRoot
            },
            'forwardtracetreeview commontracetoolbar button[itemId=commonExpandallBtn]':{
                click: this.doExpandall
            },
            'forwardtracetreeview commontracetoolbar button[itemId=commonCollapseallBtn]':{
                click: this.doCollapseall
            },
            'forwardtracetreeview commontracetoolbar button[itemId=commonPrintBtn]':{
                click: this.doPrint
            },
            'forwardtracetreeview treepanel[itemId=tree]':{
                beforeitemexpand: this.addExtraParamsToStore,
                cellclick: this.doIndexSheetDetail
            },
            'forwardtracetreeview toolbar commonbackbtn':{
                click: this.activeTreeDiagram
            }
        });
    },

    doSetRoot: function() {

        console.log("forwardTraceTree.doSetRoot");

        if(this.getMainTree().up().down('combo[itemId=commonBatchCombo]').getValue()!='') {

            var treeStore=this.getMainTree().getStore();
            var that = this;
            Ext.Ajax.request({
                method: 'GET',
                url:'/traceTree/forwardTraceRoot/',
                params:{
                    'id':this.getMainTree().up().down('combo[itemId=commonBatchCombo]').getValue()
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
                        'qty':record.qty,
                        'sheetDetail':record.sheetDetail,
                        'children':record.children

                    };
                    treeStore.setRootNode(root);
                    that.getMainTree().up().down('button[itemId=commonExpandallBtn]').setDisabled(false);
                    that.getMainTree().up().down('button[itemId=commonCollapseallBtn]').setDisabled(false);
                    that.getMainTree().up().down('button[itemId=commonPrintBtn]').setDisabled(false);
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

        var me = this.getMainTree();
        var toolbar = button.up('toolbar');

        me.getEl().mask('Expanding tree...');
        toolbar.disable();

        me.expandAll(function() {
            me.getEl().unmask();
            toolbar.enable();
        });

    },

    doCollapseall: function(button, e, eOpts) {
        var me = this.getMainTree();
        var toolbar = button.up('toolbar');

        toolbar.disable();
        me.collapseAll(function() {
            toolbar.enable();
        });

    },

    addExtraParamsToStore: function(node, eOpts) {
        var params={};
        params.class = node.data.class;
        params.name = node.data.name;
        this.getMainTree().getStore().getProxy().extraParams = params;
    },

    doIndexSheetDetail: function(view, td, cellIndex, record, tr, rowIndex, e, eOpts) {
        //cellIndex:第幾欄
        //record:該列資料
        //rowIndex:第幾列

        if(this.getMainTree().headerCt.getHeaderAtIndex(cellIndex).dataIndex=="sheet"){
            if(record.raw.sheetDetail){
                this.getSheetGrid().getStore().loadRawData(record.raw.sheetDetail);
                this.getMainTree().up('panel[itemId=treeDiagram]').up().getLayout().setActiveItem(this.getSheetGrid().up('panel[itemId=sheetDetail]'));
            }
        }
    },

    activeTreeDiagram: function() {
        this.getSheetGrid().getStore().removeAll();
        this.getSheetGrid().up('panel[itemId=sheetDetail]').up().getLayout().setActiveItem(this.getMainTree().up('panel[itemId=treeDiagram]'));
    },

    doPrint: function() {
        Ext.Ajax.request({
            method: 'GET',
            url:'/traceTree/forwardTracePrint/',
            params:{
                'id':this.getMainTree().up().down('combo[itemId=commonBatchCombo]').getValue()
            },
            success:function(response,options){
                //console.log(response);
                //console.log(options);
                var record = Ext.decode(response.responseText);
                //console.log(record);
                window.open("/reportFiles/"+record.fileName);
            }
        });
    }

});
