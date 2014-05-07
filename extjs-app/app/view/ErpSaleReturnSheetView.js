/*
 * File: app/view/ErpSaleReturnSheetView.js
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

Ext.define('foodprint.view.ErpSaleReturnSheetView', {
    extend: 'Ext.panel.Panel',
    alias: 'widget.erpsalereturnsheetview',

    requires: [
        'foodprint.view.CommonCustomerCombo',
        'foodprint.view.CommonSelectBtn',
        'foodprint.view.CommonWarehouseCombo',
        'foodprint.view.CommonWarehouseLocationCombo',
        'foodprint.view.ErpSaleSheetGrid',
        'foodprint.view.ErpSaleSheetDetGrid',
        'foodprint.view.CommonIndexToolbar',
        'foodprint.view.CommonShowToolbar'
    ],

    layout: {
        type: 'card'
    },

    initComponent: function() {
        var me = this;

        Ext.applyIf(me, {
            items: [
                {
                    xtype: 'panel',
                    tbar: {
                        xtype: 'commonindextoolbar'
                    },
                    itemId: 'index',
                    layout: {
                        type: 'fit'
                    },
                    items: [
                        me.processGrid({
                            xtype: 'gridpanel',
                            itemId: 'grid',
                            store: 'ErpSaleReturnSheetStore',
                            dockedItems: [
                                {
                                    xtype: 'pagingtoolbar',
                                    dock: 'bottom',
                                    width: 360,
                                    displayInfo: true,
                                    store: 'ErpSaleSheetStore'
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
                                    dataIndex: 'customer.id',
                                    text: 'Customer.Id',
                                    flex: 1
                                },
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'customer.name',
                                    text: 'Customer.Name',
                                    flex: 1
                                },
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'customer.title',
                                    text: 'Customer.Title',
                                    flex: 1
                                }
                            ],
                            listeners: {
                                afterrender: {
                                    fn: me.onGridAfterRender,
                                    scope: me
                                }
                            }
                        })
                    ]
                },
                {
                    xtype: 'panel',
                    tbar: {
                        xtype: 'commonshowtoolbar'
                    },
                    itemId: 'show',
                    layout: {
                        align: 'stretch',
                        type: 'vbox'
                    },
                    items: [
                        me.processForm({
                            xtype: 'form',
                            flex: 1,
                            itemId: 'form',
                            layout: {
                                align: 'stretch',
                                padding: 10,
                                type: 'vbox'
                            },
                            title: '',
                            items: [
                                {
                                    xtype: 'numberfield',
                                    hidden: true,
                                    fieldLabel: 'id',
                                    name: 'id',
                                    readOnly: true
                                },
                                {
                                    xtype: 'textfield',
                                    fieldLabel: 'typeName',
                                    name: 'typeName',
                                    readOnly: true,
                                    allowBlank: false
                                },
                                {
                                    xtype: 'textfield',
                                    fieldLabel: 'name',
                                    name: 'name',
                                    readOnly: true,
                                    allowBlank: false
                                },
                                {
                                    xtype: 'commoncustomercombo',
                                    allowBlank: false
                                },
                                {
                                    xtype: 'textfield',
                                    disabled: true,
                                    fieldLabel: 'Customer.title',
                                    name: 'customer.title'
                                }
                            ]
                        }),
                        {
                            xtype: 'panel',
                            tbar: {
                                xtype: 'commonindextoolbar'
                            },
                            flex: 1,
                            itemId: 'indexDetail',
                            layout: {
                                align: 'stretch',
                                type: 'vbox'
                            },
                            items: [
                                me.processDetailGrid({
                                    xtype: 'gridpanel',
                                    flex: 1,
                                    itemId: 'detailGrid',
                                    autoScroll: true,
                                    title: 'SaleReturnSheetDet',
                                    store: 'ErpSaleReturnSheetDetStore',
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
                                        },
                                        {
                                            xtype: 'numbercolumn',
                                            hidden: true,
                                            dataIndex: 'saleSheetDet.id',
                                            text: 'saleSheetDet.id',
                                            flex: 1
                                        },
                                        {
                                            xtype: 'gridcolumn',
                                            dataIndex: 'saleSheetDet.typeName',
                                            text: 'saleSheetDet.typeName',
                                            flex: 1
                                        },
                                        {
                                            xtype: 'gridcolumn',
                                            dataIndex: 'saleSheetDet.name',
                                            text: 'saleSheetDet.name',
                                            flex: 1
                                        },
                                        {
                                            xtype: 'gridcolumn',
                                            dataIndex: 'saleSheetDet.sequence',
                                            text: 'saleSheetDet.sequence',
                                            flex: 1
                                        }
                                    ],
                                    listeners: {
                                        beforerender: {
                                            fn: me.onGridBeforeRender,
                                            scope: me
                                        }
                                    }
                                })
                            ]
                        }
                    ],
                    listeners: {
                        afterrender: {
                            fn: me.onShowAfterRender,
                            scope: me
                        }
                    }
                },
                {
                    xtype: 'panel',
                    tbar: {
                        xtype: 'commonshowtoolbar'
                    },
                    itemId: 'showDetail',
                    layout: {
                        align: 'stretch',
                        type: 'vbox'
                    },
                    items: [
                        me.processDetailForm({
                            xtype: 'form',
                            flex: 1,
                            itemId: 'detailForm',
                            layout: {
                                align: 'stretch',
                                type: 'vbox'
                            },
                            bodyPadding: 10,
                            title: '',
                            items: [
                                {
                                    xtype: 'numberfield',
                                    flex: 1,
                                    hidden: true,
                                    fieldLabel: 'saleReturnSheet.id',
                                    name: 'saleReturnSheet.id',
                                    readOnly: true,
                                    allowBlank: false
                                },
                                {
                                    xtype: 'numberfield',
                                    hidden: true,
                                    fieldLabel: 'id',
                                    name: 'id',
                                    readOnly: true
                                },
                                {
                                    xtype: 'textfield',
                                    fieldLabel: 'typeName',
                                    name: 'typeName',
                                    readOnly: true,
                                    allowBlank: false
                                },
                                {
                                    xtype: 'textfield',
                                    fieldLabel: 'name',
                                    name: 'name',
                                    readOnly: true,
                                    allowBlank: false
                                },
                                {
                                    xtype: 'numberfield',
                                    fieldLabel: 'sequence',
                                    name: 'sequence',
                                    readOnly: true,
                                    allowBlank: false
                                },
                                me.processSaleSheetDetContainer({
                                    xtype: 'fieldcontainer',
                                    itemId: 'saleSheetDetContainer',
                                    layout: {
                                        align: 'stretch',
                                        type: 'hbox'
                                    },
                                    fieldLabel: 'saleSheetDet.SheetNum',
                                    items: [
                                        {
                                            xtype: 'numberfield',
                                            hidden: true,
                                            name: 'saleSheetDet.id',
                                            readOnly: true,
                                            allowBlank: false
                                        },
                                        {
                                            xtype: 'textfield',
                                            name: 'saleSheetDet.typeName',
                                            readOnly: true,
                                            allowBlank: false
                                        },
                                        {
                                            xtype: 'textfield',
                                            name: 'saleSheetDet.name',
                                            readOnly: true,
                                            allowBlank: false
                                        },
                                        {
                                            xtype: 'textfield',
                                            name: 'saleSheetDet.sequence',
                                            readOnly: true,
                                            allowBlank: false
                                        },
                                        {
                                            xtype: 'commonselectbtn',
                                            margins: '1'
                                        }
                                    ]
                                }),
                                me.processCustomerOrderDetContainer({
                                    xtype: 'fieldcontainer',
                                    itemId: 'customerOrderDetContainer',
                                    layout: {
                                        align: 'stretch',
                                        type: 'hbox'
                                    },
                                    fieldLabel: 'cusomerOrderDet.SheetNum',
                                    items: [
                                        {
                                            xtype: 'numberfield',
                                            hidden: true,
                                            name: 'customerOrderDet.id',
                                            readOnly: true
                                        },
                                        {
                                            xtype: 'textfield',
                                            name: 'customerOrderDet.typeName',
                                            readOnly: true
                                        },
                                        {
                                            xtype: 'textfield',
                                            name: 'customerOrderDet.name',
                                            readOnly: true
                                        },
                                        {
                                            xtype: 'textfield',
                                            name: 'customerOrderDet.sequence',
                                            readOnly: true
                                        }
                                    ]
                                }),
                                {
                                    xtype: 'commonwarehousecombo',
                                    allowBlank: false
                                },
                                {
                                    xtype: 'textfield',
                                    disabled: true,
                                    fieldLabel: 'Warehouse.title',
                                    name: 'warehouse.title'
                                },
                                {
                                    xtype: 'commonwarehouselocationcombo',
                                    allowBlank: false
                                },
                                {
                                    xtype: 'textfield',
                                    disabled: true,
                                    fieldLabel: 'WarehouseLocation.title',
                                    name: 'warehouseLocation.title'
                                },
                                {
                                    xtype: 'numberfield',
                                    flex: 1,
                                    hidden: true,
                                    fieldLabel: 'batch.id',
                                    name: 'batch.id',
                                    readOnly: true,
                                    allowBlank: false
                                },
                                {
                                    xtype: 'textfield',
                                    disabled: true,
                                    fieldLabel: 'Batch.name',
                                    name: 'batch.name',
                                    allowBlank: false
                                },
                                {
                                    xtype: 'numberfield',
                                    flex: 1,
                                    hidden: true,
                                    fieldLabel: 'item.id',
                                    name: 'item.id',
                                    readOnly: true,
                                    allowBlank: false
                                },
                                {
                                    xtype: 'textfield',
                                    disabled: true,
                                    fieldLabel: 'Item.name',
                                    name: 'item.name',
                                    allowBlank: false
                                },
                                {
                                    xtype: 'textfield',
                                    disabled: true,
                                    fieldLabel: 'Item.title',
                                    name: 'item.title'
                                },
                                {
                                    xtype: 'numberfield',
                                    fieldLabel: 'qty',
                                    name: 'qty',
                                    allowBlank: false
                                }
                            ]
                        })
                    ]
                },
                {
                    xtype: 'panel',
                    itemId: 'saleSheetDetIndex',
                    layout: {
                        align: 'stretch',
                        type: 'vbox'
                    },
                    items: [
                        {
                            xtype: 'erpsalesheetgrid',
                            flex: 1
                        },
                        {
                            xtype: 'erpsalesheetdetgrid',
                            flex: 1
                        }
                    ]
                }
            ]
        });

        me.callParent(arguments);
    },

    processGrid: function(config) {
        return Utilities.createFiltersFeature(Utilities.processConfigBundle(config, 'saleReturnSheet'));
    },

    processForm: function(config) {
        return Utilities.processConfigBundle(config, 'saleReturnSheet');
    },

    processDetailGrid: function(config) {

        return Utilities.processConfigBundle(config, 'saleReturnSheetDet');
    },

    processSaleSheetDetContainer: function(config) {

        return Utilities.processConfigBundle(config, 'saleSheetDet.sheetNum');
    },

    processCustomerOrderDetContainer: function(config) {

        return Utilities.processConfigBundle(config, 'customerOrderDet.sheetNum');
    },

    processDetailForm: function(config) {
        return Utilities.processConfigBundle(config, 'saleReturnSheetDet');
    },

    onGridAfterRender: function(component, eOpts) {
        component.getStore().load();
    },

    onGridBeforeRender: function(component, eOpts) {
        component.getStore().removeAll();
    },

    onShowAfterRender: function(component, eOpts) {
        component.down('commonshowtoolbar').down('commonprintbtn').show();
    }

});