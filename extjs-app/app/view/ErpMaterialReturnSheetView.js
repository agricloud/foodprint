/*
 * File: app/view/ErpMaterialReturnSheetView.js
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

Ext.define('foodprint.view.ErpMaterialReturnSheetView', {
    extend: 'Ext.panel.Panel',
    alias: 'widget.erpmaterialreturnsheetview',

    requires: [
        'foodprint.view.CommonWorkstationCombo',
        'foodprint.view.CommonSupplierCombo',
        'foodprint.view.CommonSelectBtn',
        'foodprint.view.CommonWarehouseCombo',
        'foodprint.view.CommonWarehouseLocationCombo',
        'foodprint.view.ErpMaterialSheetGrid',
        'foodprint.view.ErpMaterialSheetDetGrid',
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
                            store: 'ErpMaterialReturnSheetStore',
                            dockedItems: [
                                {
                                    xtype: 'pagingtoolbar',
                                    dock: 'bottom',
                                    width: 360,
                                    displayInfo: true,
                                    store: 'ErpMaterialSheetStore'
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
                                    dataIndex: 'workstation.id',
                                    text: 'Workstation.Id',
                                    flex: 1
                                },
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'workstation.name',
                                    text: 'Workstation.Name',
                                    flex: 1
                                },
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'workstation.title',
                                    text: 'Workstation.Title',
                                    flex: 1
                                },
                                {
                                    xtype: 'numbercolumn',
                                    hidden: true,
                                    dataIndex: 'supplier.id',
                                    text: 'Supplier.Id',
                                    flex: 1
                                },
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'supplier.name',
                                    text: 'Supplier.Name',
                                    flex: 1
                                },
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'supplier.title',
                                    text: 'Supplier.Title',
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
                                    xtype: 'commonworkstationcombo'
                                },
                                {
                                    xtype: 'textfield',
                                    disabled: true,
                                    fieldLabel: 'Workstation_title',
                                    name: 'workstation.title'
                                },
                                {
                                    xtype: 'commonsuppliercombo'
                                },
                                {
                                    xtype: 'textfield',
                                    disabled: true,
                                    fieldLabel: 'Supplier_title',
                                    name: 'supplier.title'
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
                                    title: 'ErpMaterialReturnSheetDet',
                                    store: 'ErpMaterialReturnSheetDetStore',
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
                                            dataIndex: 'manufactureOrder.id',
                                            text: 'ManufactureOrder.id',
                                            flex: 1
                                        },
                                        {
                                            xtype: 'gridcolumn',
                                            dataIndex: 'manufactureOrder.typeName',
                                            text: 'ManufactureOrder.typeName',
                                            flex: 1
                                        },
                                        {
                                            xtype: 'gridcolumn',
                                            dataIndex: 'manufactureOrder.name',
                                            text: 'ManufactureOrder.name',
                                            flex: 1
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
                                            dataIndex: 'materialSheetDet.id',
                                            text: 'materialSheetDet.id',
                                            flex: 1
                                        },
                                        {
                                            xtype: 'gridcolumn',
                                            dataIndex: 'materialSheetDet.typeName',
                                            text: 'materialSheetDet.typeName',
                                            flex: 1
                                        },
                                        {
                                            xtype: 'gridcolumn',
                                            dataIndex: 'materialSheetDet.name',
                                            text: 'materialSheetDet.name',
                                            flex: 1
                                        },
                                        {
                                            xtype: 'gridcolumn',
                                            dataIndex: 'materialSheetDet.sequence',
                                            text: 'materialSheetDet.sequence',
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
                                    hidden: true,
                                    fieldLabel: 'materialReturnSheet.id',
                                    name: 'materialReturnSheet.id',
                                    readOnly: true,
                                    allowBlank: false
                                },
                                {
                                    xtype: 'numberfield',
                                    flex: 1,
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
                                me.processMaterialSheetDetContainer({
                                    xtype: 'fieldcontainer',
                                    itemId: 'materialSheetDetContainer',
                                    layout: {
                                        align: 'stretch',
                                        type: 'hbox'
                                    },
                                    fieldLabel: 'materialSheetDet.sheetNum',
                                    items: [
                                        {
                                            xtype: 'numberfield',
                                            hidden: true,
                                            name: 'materialSheetDet.id',
                                            readOnly: true,
                                            allowBlank: false
                                        },
                                        {
                                            xtype: 'textfield',
                                            name: 'materialSheetDet.typeName',
                                            readOnly: true,
                                            allowBlank: false
                                        },
                                        {
                                            xtype: 'textfield',
                                            name: 'materialSheetDet.name',
                                            readOnly: true,
                                            allowBlank: false
                                        },
                                        {
                                            xtype: 'textfield',
                                            name: 'materialSheetDet.sequence',
                                            readOnly: true,
                                            allowBlank: false
                                        },
                                        {
                                            xtype: 'commonselectbtn'
                                        }
                                    ]
                                }),
                                me.processManufactureOrderContainer({
                                    xtype: 'fieldcontainer',
                                    itemId: 'manufactureOrderContainer',
                                    layout: {
                                        align: 'stretch',
                                        type: 'hbox'
                                    },
                                    fieldLabel: 'manufactureOrder.sheetNum',
                                    items: [
                                        {
                                            xtype: 'numberfield',
                                            hidden: true,
                                            name: 'manufactureOrder.id',
                                            readOnly: true,
                                            allowBlank: false
                                        },
                                        {
                                            xtype: 'textfield',
                                            name: 'manufactureOrder.typeName',
                                            readOnly: true,
                                            allowBlank: false
                                        },
                                        {
                                            xtype: 'textfield',
                                            name: 'manufactureOrder.name',
                                            readOnly: true,
                                            allowBlank: false
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
                                    hidden: true,
                                    fieldLabel: 'batch.id',
                                    name: 'batch.id',
                                    readOnly: true,
                                    allowBlank: false
                                },
                                {
                                    xtype: 'textfield',
                                    disabled: true,
                                    fieldLabel: 'batch.name',
                                    name: 'batch.name',
                                    allowBlank: false
                                },
                                {
                                    xtype: 'numberfield',
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
                    itemId: 'materialSheetDetIndex',
                    layout: {
                        align: 'stretch',
                        type: 'vbox'
                    },
                    items: [
                        {
                            xtype: 'erpmaterialsheetgrid',
                            flex: 1
                        },
                        {
                            xtype: 'erpmaterialsheetdetgrid',
                            flex: 1
                        }
                    ]
                }
            ]
        });

        me.callParent(arguments);
    },

    processGrid: function(config) {
        return Utilities.createFiltersFeature(Utilities.processConfigBundle(config, 'materialReturnSheet'));
    },

    processForm: function(config) {
        return Utilities.processConfigBundle(config, 'materialReturnSheet');
    },

    processDetailGrid: function(config) {
        return Utilities.processConfigBundle(config, 'materialReturnSheetDet');
    },

    processMaterialSheetDetContainer: function(config) {
        return Utilities.processConfigBundle(config, 'materialSheetDet.sheetNum');
    },

    processManufactureOrderContainer: function(config) {
        return Utilities.processConfigBundle(config, 'manufactureOrder.sheetNum');
    },

    processDetailForm: function(config) {
        return Utilities.processConfigBundle(config, 'materialReturnSheetDet');
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