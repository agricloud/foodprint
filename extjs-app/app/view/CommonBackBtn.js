/*
 * File: app/view/CommonBackBtn.js
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

Ext.define('foodprint.view.CommonBackBtn', {
    extend: 'Ext.button.Button',
    alias: 'widget.commonbackbtn',

    itemId: 'commonBackBtn',
    style: 'font-family:Pictos;',
    glyph: 115,
    text: 'Back',

    initComponent: function() {
        var me = this;

        me.processCommonBackBtn(me);
        me.callParent(arguments);
    },

    processCommonBackBtn: function(config) {
        config.text=Utilities.getMsg('common.backBtn.label');

        return config;
    }

});