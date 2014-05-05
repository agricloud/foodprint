/*
 * File: app/view/ErpCustomerOrderView.js
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

Ext.define('foodprint.view.ErpCustomerOrderView', {
    extend: 'Ext.panel.Panel',
    alias: 'widget.erpcustomerorderview',

    requires: [
        'foodprint.view.ErpCustomerOrderGrid',
        'foodprint.view.CommonCustomerCombo',
        'foodprint.view.ErpCustomerOrderDetGrid',
        'foodprint.view.CommonItemCombo',
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
                        {
                            xtype: 'erpcustomerordergrid',
                            itemId: 'grid'
                        }
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
                                    itemId: 'customer.title',
                                    fieldLabel: 'customer.title',
                                    name: 'customer.title'
                                },
                                {
                                    xtype: 'datefield',
                                    fieldLabel: 'dueDate',
                                    name: 'dueDate'
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
                                {
                                    xtype: 'erpcustomerorderdetgrid',
                                    itemId: 'detailGrid',
                                    flex: 1
                                }
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
                                    fieldLabel: 'id',
                                    name: 'id',
                                    readOnly: true
                                },
                                {
                                    xtype: 'numberfield',
                                    hidden: true,
                                    fieldLabel: 'customerOrder.id',
                                    name: 'customerOrder.id',
                                    readOnly: true,
                                    allowBlank: false
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
                                {
                                    xtype: 'commonitemcombo',
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
                }
            ]
        });

        me.callParent(arguments);
    },

    processForm: function(config) {
        return Utilities.processConfigBundle(config, 'customerOrder');
    },

    processDetailForm: function(config) {
        return Utilities.processConfigBundle(config, 'customerOrderDet');
    },

    onShowAfterRender: function(component, eOpts) {
        component.down('commonshowtoolbar').down('commonprintbtn').show();
    }

});