/*
 * File: app/view/ErpCustomerOrderGrid.js
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

Ext.define('foodprint.view.ErpCustomerOrderGrid', {
    extend: 'Ext.grid.Panel',
    alias: 'widget.erpcustomerordergrid',

    itemId: 'erpCustomerOrderGrid',
    store: 'ErpCustomerOrderStore',

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
                    dataIndex: 'customer.id',
                    text: 'Customer.id',
                    flex: 1
                },
                {
                    xtype: 'gridcolumn',
                    dataIndex: 'customer.name',
                    text: 'Customer.name',
                    flex: 1
                },
                {
                    xtype: 'gridcolumn',
                    dataIndex: 'customer.title',
                    text: 'Customer.title',
                    flex: 1
                },
                {
                    xtype: 'datecolumn',
                    dataIndex: 'dueDate',
                    text: 'dueDate',
                    flex: 1
                }
            ],
            listeners: {
                afterrender: {
                    fn: me.onErpCustomerOrderGridAfterRender,
                    scope: me
                }
            }
        });

        me.processErpCustomerOrderGrid(me);
        me.callParent(arguments);
    },

    processErpCustomerOrderGrid: function(config) {
        return Utilities.createFiltersFeature(Utilities.processConfigBundle(config, 'customerOrder'));
    },

    onErpCustomerOrderGridAfterRender: function(component, eOpts) {
        component.getStore().load();
    }

});