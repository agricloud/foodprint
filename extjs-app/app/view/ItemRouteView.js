/*
 * File: app/view/ItemRouteView.js
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

Ext.define('foodprint.view.ItemRouteView', {
    extend: 'Ext.panel.Panel',
    alias: 'widget.itemrouteview',

    requires: [
        'foodprint.view.ItemGrid',
        'foodprint.view.CommonOperationCombo',
        'foodprint.view.CommonWorkstationCombo',
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
                            xtype: 'itemgrid',
                            itemId: 'masterGrid',
                            flex: 1
                        },
                        me.processGrid({
                            xtype: 'gridpanel',
                            flex: 1,
                            itemId: 'grid',
                            autoScroll: true,
                            title: '品項途程',
                            store: 'ItemRouteStore',
                            columns: [
                                {
                                    xtype: 'gridcolumn',
                                    hidden: true,
                                    dataIndex: 'id',
                                    text: 'id',
                                    flex: 1
                                },
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'sequence',
                                    text: 'sequence',
                                    flex: 1
                                },
                                {
                                    xtype: 'gridcolumn',
                                    hidden: true,
                                    dataIndex: 'operation.id',
                                    text: 'Operation_id',
                                    flex: 1
                                },
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'operation.name',
                                    text: 'Operation_name',
                                    flex: 1
                                },
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'operation.title',
                                    text: 'Operation_title',
                                    flex: 1
                                },
                                {
                                    xtype: 'gridcolumn',
                                    hidden: true,
                                    dataIndex: 'workstation.id',
                                    text: 'Workstation_id',
                                    flex: 1
                                },
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'workstation.name',
                                    text: 'Workstation_name',
                                    flex: 1
                                },
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'workstation.title',
                                    text: 'Workstation_title',
                                    flex: 1
                                }
                            ],
                            selModel: Ext.create('Ext.selection.RowModel', {

                            }),
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
                    items: [
                        me.processForm({
                            xtype: 'form',
                            itemId: 'form',
                            bodyPadding: 10,
                            items: [
                                {
                                    xtype: 'textfield',
                                    anchor: '100%',
                                    hidden: true,
                                    fieldLabel: 'id',
                                    name: 'id'
                                },
                                {
                                    xtype: 'textfield',
                                    anchor: '100%',
                                    hidden: true,
                                    fieldLabel: 'item.id',
                                    name: 'item.id'
                                },
                                {
                                    xtype: 'numberfield',
                                    anchor: '100%',
                                    fieldLabel: 'sequence',
                                    name: 'sequence'
                                },
                                {
                                    xtype: 'commonoperationcombo',
                                    anchor: '100%'
                                },
                                {
                                    xtype: 'textfield',
                                    anchor: '100%',
                                    fieldLabel: 'operation_title',
                                    name: 'operation.title'
                                },
                                {
                                    xtype: 'commonworkstationcombo',
                                    anchor: '100%'
                                },
                                {
                                    xtype: 'textfield',
                                    anchor: '100%',
                                    fieldLabel: 'workstation_title',
                                    name: 'workstation.title'
                                }
                            ]
                        })
                    ]
                }
            ]
        });

        me.callParent(arguments);
    },

    processGrid: function(config) {
        return Utilities.processConfigBundle(config, 'itemRoute');
    },

    processForm: function(config) {
        return Utilities.processConfigBundle(config, 'itemRoute');
    },

    onGridBeforeRender: function(component, eOpts) {
        component.getStore().removeAll();
    }

});