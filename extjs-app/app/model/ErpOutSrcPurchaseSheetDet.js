/*
 * File: app/model/ErpOutSrcPurchaseSheetDet.js
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

Ext.define('foodprint.model.ErpOutSrcPurchaseSheetDet', {
    extend: 'Ext.data.Model',

    fields: [
        {
            name: 'outSrcPurchaseSheet.id'
        },
        {
            name: 'id'
        },
        {
            name: 'typeName'
        },
        {
            name: 'name'
        },
        {
            name: 'sequence'
        },
        {
            name: 'item.id'
        },
        {
            name: 'item.name'
        },
        {
            name: 'item.title'
        },
        {
            name: 'batch.id'
        },
        {
            name: 'batch.name'
        },
        {
            name: 'manufactureOrder.id'
        },
        {
            name: 'manufactureOrder.typeName'
        },
        {
            name: 'manufactureOrder.name'
        },
        {
            name: 'warehouse.id'
        },
        {
            name: 'warehouse.name'
        },
        {
            name: 'warehouse.title'
        },
        {
            name: 'warehouseLocation.id'
        },
        {
            name: 'warehouseLocation.name'
        },
        {
            name: 'warehouseLocation.title'
        },
        {
            name: 'qty'
        }
    ],

    proxy: {
        type: 'rest',
        url: '/foodpaint?foodpaintController=outSrcPurchaseSheetDet',
        reader: {
            type: 'json',
            root: 'data'
        }
    }
});