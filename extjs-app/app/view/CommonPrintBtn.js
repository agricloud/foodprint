/*
 * File: app/view/CommonPrintBtn.js
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

Ext.define('foodprint.view.CommonPrintBtn', {
    extend: 'Ext.button.Button',
    alias: 'widget.commonprintbtn',

    itemId: 'commonPrintBtn',
    style: 'font-family:Pictos;',
    glyph: 77,
    text: 'Print',

    initComponent: function() {
        var me = this;

        me.processCommonPrintBtn(me);
        me.callParent(arguments);
    },

    processCommonPrintBtn: function(config) {

        config.text=Utilities.getMsg('common.printBtn.label');

        return config;
    }

});