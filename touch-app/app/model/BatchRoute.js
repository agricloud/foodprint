/*
 * File: app/model/BatchRoute.js
 *
 * This file was generated by Sencha Architect version 2.2.3.
 * http://www.sencha.com/products/architect/
 *
 * This file requires use of the Sencha Touch 2.2.x library, under independent license.
 * License of Sencha Architect does not include license for Sencha Touch 2.2.x. For more
 * details see http://www.sencha.com/license or contact license@sencha.com.
 *
 * This file will be auto-generated each and everytime you save your project.
 *
 * Do NOT hand edit this file.
 */

Ext.define('foodprintTouch.model.BatchRoute', {
    extend: 'Ext.data.Model',

    config: {
        fields: [
            {
                name: 'id'
            },
            {
                name: 'sequence'
            },
            {
                mapping: 'batch.id',
                name: 'batch__id'
            },
            {
                mapping: 'operation.id',
                name: 'operation__id'
            },
            {
                mapping: 'workstation.id',
                name: 'workstation__id'
            },
            {
                mapping: 'supplier.id',
                name: 'supplier__id'
            },
            {
                mapping: 'operation.name',
                name: 'operation__name'
            },
            {
                mapping: 'workstation.name',
                name: 'workstation__name'
            },
            {
                mapping: 'supplier.name',
                name: 'supplier__name'
            },
            {
                mapping: 'operation.title',
                name: 'operation__title'
            },
            {
                mapping: 'workstation.title',
                name: 'workstation__title'
            },
            {
                mapping: 'supplier.title',
                name: 'supplier__title'
            },
            {
                name: 'startDate',
                type: 'date'
            },
            {
                name: 'endDate',
                type: 'date'
            }
        ]
    }
});