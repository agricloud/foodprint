/*
 * File: app/view/ErpSaleSheetDetGrid.js
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

Ext.define('foodprint.view.ErpSaleSheetDetGrid', {
    extend: 'Ext.grid.Panel',
    alias: 'widget.erpsalesheetdetgrid',

    itemId: 'erpSaleSheetDetGrid',
    autoScroll: true,
    title: 'ErpSaleSheetDet',
    store: 'ErpSaleSheetDetStore',

    initComponent: function() {
        var me = this;

        Ext.applyIf(me, {
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
                    hidden: true,
                    dataIndex: 'typeName',
                    text: 'TypeName',
                    flex: 1
                },
                {
                    xtype: 'gridcolumn',
                    hidden: true,
                    dataIndex: 'name',
                    text: 'Name',
                    flex: 1
                },
                {
                    xtype: 'numbercolumn',
                    dataIndex: 'sequence',
                    text: 'Sequence',
                    flex: 1,
                    format: '0,000'
                },
                {
                    xtype: 'numbercolumn',
                    hidden: true,
                    dataIndex: 'warehouse.id',
                    text: 'Warehouse.id',
                    flex: 1
                },
                {
                    xtype: 'gridcolumn',
                    dataIndex: 'warehouse.name',
                    text: 'Warehouse.name',
                    flex: 1
                },
                {
                    xtype: 'gridcolumn',
                    dataIndex: 'warehouse.title',
                    text: 'Warehouse.title',
                    flex: 1
                },
                {
                    xtype: 'numbercolumn',
                    hidden: true,
                    dataIndex: 'warehouseLocation.id',
                    text: 'WarehouseLocation.id',
                    flex: 1
                },
                {
                    xtype: 'gridcolumn',
                    dataIndex: 'warehouseLocation.name',
                    text: 'WarehouseLocation.name',
                    flex: 1
                },
                {
                    xtype: 'gridcolumn',
                    dataIndex: 'warehouseLocation.title',
                    text: 'WarehouseLocation.title',
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
                    hidden: true,
                    dataIndex: 'item.id',
                    text: 'Item.id',
                    flex: 1
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
                    xtype: 'gridcolumn',
                    dataIndex: 'qty',
                    text: 'qty',
                    flex: 1
                },
                {
                    xtype: 'numbercolumn',
                    hidden: true,
                    dataIndex: 'customerOrderDet.id',
                    text: 'CustomerOrderDet.id',
                    flex: 1
                },
                {
                    xtype: 'gridcolumn',
                    dataIndex: 'customerOrderDet.typeName',
                    text: 'CustomerOrderDet.typeName',
                    flex: 1
                },
                {
                    xtype: 'gridcolumn',
                    dataIndex: 'customerOrderDet.name',
                    text: 'CustomerOrderDet.name',
                    flex: 1
                },
                {
                    xtype: 'numbercolumn',
                    dataIndex: 'customerOrderDet.sequence',
                    text: 'CustomerOrderDet.sequence',
                    flex: 1,
                    format: '0,000'
                }
            ],
            listeners: {
                beforerender: {
                    fn: me.onGridBeforeRender,
                    scope: me
                }
            }
        });

        me.processErpSaleSheetDetGrid(me);
        me.callParent(arguments);
    },

    processErpSaleSheetDetGrid: function(config) {

        return Utilities.processConfigBundle(config, 'saleSheetDet');
    },

    onGridBeforeRender: function(component, eOpts) {
        component.getStore().removeAll();
    }

});