/*
 * File: app/view/ErpManufactureOrderGrid.js
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

Ext.define('foodprint.view.ErpManufactureOrderGrid', {
    extend: 'Ext.grid.Panel',
    alias: 'widget.erpmanufactureordergrid',

    itemId: 'erpManufactureOrderGrid',
    store: 'ErpManufactureOrderStore',

    initComponent: function() {
        var me = this;

        Ext.applyIf(me, {
            dockedItems: [
                {
                    xtype: 'pagingtoolbar',
                    dock: 'bottom',
                    width: 360,
                    displayInfo: true,
                    store: 'ErpManufactureOrderStore'
                }
            ],
            columns: [
                {
                    xtype: 'numbercolumn',
                    hidden: true,
                    dataIndex: 'id',
                    text: 'Id',
                    flex: 1
                },
                {
                    xtype: 'gridcolumn',
                    dataIndex: 'typeName',
                    text: 'TypeName',
                    flex: 1
                },
                {
                    xtype: 'gridcolumn',
                    dataIndex: 'name',
                    text: 'Name',
                    flex: 1
                },
                {
                    xtype: 'numbercolumn',
                    hidden: true,
                    dataIndex: 'item.id',
                    text: 'Item.id',
                    flex: 1,
                    format: '0,000'
                },
                {
                    xtype: 'gridcolumn',
                    dataIndex: 'item.name',
                    text: 'Item.name',
                    flex: 1
                },
                {
                    xtype: 'gridcolumn',
                    dataIndex: 'item.title',
                    text: 'Item.title',
                    flex: 1
                },
                {
                    xtype: 'numbercolumn',
                    hidden: true,
                    dataIndex: 'batch.id',
                    text: 'Batch.id',
                    flex: 1
                },
                {
                    xtype: 'gridcolumn',
                    dataIndex: 'batch.name',
                    text: 'Batch.name',
                    flex: 1
                },
                {
                    xtype: 'numbercolumn',
                    dataIndex: 'qty',
                    text: 'qty',
                    flex: 1
                }
            ],
            listeners: {
                afterrender: {
                    fn: me.onGridAfterRender,
                    scope: me
                }
            }
        });

        me.processErpManufactureOrderGrid(me);
        me.callParent(arguments);
    },

    processErpManufactureOrderGrid: function(config) {
        return Utilities.createFiltersFeature(Utilities.processConfigBundle(config, 'manufactureOrder'));
    },

    onGridAfterRender: function(component, eOpts) {
        component.getStore().load();
    }

});