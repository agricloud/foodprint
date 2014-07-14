/*
 * File: app/view/WarehouseLocationView.js
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

Ext.define('foodprint.view.WarehouseLocationView', {
    extend: 'Ext.panel.Panel',
    alias: 'widget.warehouselocationview',

    requires: [
        'foodprint.view.WarehouseGrid',
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
                        align: 'stretch',
                        type: 'vbox'
                    },
                    items: [
                        {
                            xtype: 'warehousegrid',
                            itemId: 'masterGrid',
                            flex: 1
                        },
                        me.processWarehouseLocationGrid({
                            xtype: 'gridpanel',
                            flex: 1,
                            itemId: 'grid',
                            title: 'WarehouseLocation',
                            store: 'WarehouseLocationStore',
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
                                    dataIndex: 'name',
                                    text: 'name',
                                    flex: 1
                                },
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'title',
                                    text: 'title',
                                    flex: 1
                                },
                                {
                                    xtype: 'gridcolumn',
                                    hidden: true,
                                    dataIndex: 'capacity',
                                    text: 'capacity',
                                    flex: 1
                                },
                                {
                                    xtype: 'gridcolumn',
                                    hidden: true,
                                    dataIndex: 'capacityUnit',
                                    text: 'capacityUnit',
                                    flex: 1
                                },
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'description',
                                    text: 'description',
                                    flex: 1
                                },
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'remark',
                                    text: 'remark',
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
                                type: 'vbox'
                            },
                            bodyPadding: 10,
                            title: '',
                            items: [
                                {
                                    xtype: 'textfield',
                                    hidden: true,
                                    fieldLabel: 'warehouse.id',
                                    name: 'warehouse.id',
                                    readOnly: true,
                                    allowBlank: false
                                },
                                {
                                    xtype: 'textfield',
                                    hidden: true,
                                    fieldLabel: 'id',
                                    name: 'id',
                                    readOnly: true
                                },
                                {
                                    xtype: 'textfield',
                                    fieldLabel: 'name',
                                    name: 'name',
                                    allowBlank: false
                                },
                                {
                                    xtype: 'textfield',
                                    fieldLabel: 'title',
                                    name: 'title'
                                },
                                {
                                    xtype: 'textfield',
                                    hidden: true,
                                    fieldLabel: 'capacity',
                                    name: 'capacity'
                                },
                                {
                                    xtype: 'textfield',
                                    hidden: true,
                                    fieldLabel: 'capacityUnit',
                                    name: 'capacityUnit'
                                },
                                {
                                    xtype: 'textfield',
                                    fieldLabel: 'description',
                                    name: 'description'
                                },
                                {
                                    xtype: 'textfield',
                                    fieldLabel: 'remark',
                                    name: 'remark'
                                }
                            ]
                        })
                    ]
                }
            ]
        });

        me.callParent(arguments);
    },

    processWarehouseLocationGrid: function(config) {
        return Utilities.createFiltersFeature(Utilities.processConfigBundle(config, 'warehouseLocation'));

    },

    processForm: function(config) {
        return Utilities.processConfigBundle(config, 'warehouseLocation');
    },

    onGridBeforeRender: function(component, eOpts) {
        component.getStore().removeAll();
    }

});