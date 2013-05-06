/*
 * File: app/view/StdEditorToolbar.js
 *
 * This file was generated by Sencha Architect version 2.2.2.
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

Ext.define('finder_extjs.view.StdEditorToolbar', {
    extend: 'Ext.toolbar.Toolbar',
    alias: 'widget.stdeditortoolbar',

    requires: [
        'finder_extjs.view.CreateBtn',
        'finder_extjs.view.DeleteBtn',
        'finder_extjs.view.SaveBtn'
    ],

    itemId: 'stdEditorToolbar',

    initComponent: function() {
        var me = this;

        Ext.applyIf(me, {
            items: [
                {
                    xtype: 'createbtn'
                },
                {
                    xtype: 'deletebtn'
                },
                {
                    xtype: 'savebtn'
                }
            ]
        });

        me.callParent(arguments);
    }

});