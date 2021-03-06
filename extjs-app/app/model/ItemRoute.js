/*
 * File: app/model/ItemRoute.js
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

Ext.define('foodprint.model.ItemRoute', {
    extend: 'Ext.data.Model',

    fields: [
        {
            mapping: 'id',
            name: 'id'
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
            name: 'sequence'
        },
        {
            mapping: 'operation.id',
            name: 'operation.id'
        },
        {
            name: 'operation.name'
        },
        {
            name: 'operation.title'
        },
        {
            mapping: 'workstation.id',
            name: 'workstation.id'
        },
        {
            name: 'workstation.name'
        },
        {
            name: 'workstation.title'
        }
    ],

    proxy: {
        type: 'rest',
        url: '/itemRoute',
        reader: {
            type: 'json',
            root: 'itemRouteInstanceList',
            totalProperty: 'itemRouteInstanceTotal'
        }
    }
});