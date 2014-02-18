/*
 * File: app/view/Utilities.js
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

Ext.define('foodprint.view.Utilities', {
    extend: 'Ext.app.Controller',

    alternateClassName: [
        'Utilities'
    ],
    singleton: true,

    respondFailure: function(action, config) {

        Ext.MessageBox.alert('Failure',action.result.message);


        if(action.url.indexOf('create')!=-1){
            config.toolbar().down('button[itemId=commonSaveBtn]').setDisabled(false);	
        }else {
            config.toolbar().down('button[itemId=commonDeleteBtn]').setDisabled(false);
            config.toolbar().down('button[itemId=commonUpdateBtn]').setDisabled(false);


        }


    },

    respondSuccess: function(action, config) {
        Ext.MessageBox.alert('Success',action.result.message);

        console.log(config.grid());
        config.grid().getStore().reload();
        config.form().getForm().reset(true);
    },

    doUpdate: function(config) {

        console.log("util update");
        config.form().getForm().submit({
            url: '/'+config.domainName+'/update',
            submitEmptyText: false,
            waitMsg: 'Updating Data...',
            success: function(form,action) {
                Utilities.respondSuccess(action,config);
            },
            failure: function(form,action) {
                Utilities.respondFailure(action,config);
            }
        });
    },

    doDelete: function(config) {
        config.form().getForm().submit({
            url: '/'+config.domainName+'/delete',
            submitEmptyText: false,
            waitMsg: 'Deleting Data...',
            success: function(form,action) {
                Utilities.respondSuccess(action,config);
            },
            failure: function(form,action) {
                Utilities.respondFailure(action,config);
            }
        });

    },

    doCreate: function(config) {

        console.log("util create");
        config.form().getForm().submit({
            url: '/'+config.domainName+'/create',
            submitEmptyText: false,
            waitMsg: 'Saving Data...',
            success: function(form,action) {
                Utilities.respondSuccess(action,config);
            },
            failure: function(form,action) {
                Utilities.respondFailure(action,config);
            }
        });
    },

    validityChange: function(basic, valid,config) {
        config.form().getForm().updateRecord();

        if(basic.getRecord()!=null && basic.getRecord().getData().id!=null){
            if(valid){
                config.toolbar().down('button[itemId=commonUpdateBtn]').setDisabled(false);
            }
            else{
                config.toolbar().down('button[itemId=commonUpdateBtn]').setDisabled(true);
            }
        }
        else{
            if(valid){
                config.toolbar().down('button[itemId=commonSaveBtn]').setDisabled(false);
            }
            else{
                config.toolbar().down('button[itemId=commonSaveBtn]').setDisabled(true);
            }
        }
    },

    createFiltersFeature: function(config) {
        for (var i=0; i<config.columns.length; i++) {
            if(config.columns[i].xtype === 'numbercolumn'){
                config.columns[i].type = 'numeric'
            }
            else if(config.columns[i].xtype === 'datecolumn'){
                config.columns[i].type = 'date'
            }
            else if(config.columns[i].xtype === 'booleancolumn'){
                config.columns[i].type = 'boolean'
            }
        }

        config.features = [{
            ftype: 'filters',   
            encode: true,
            filters: config.columns
        }];

        return config;
    },

    logout: function() {

        Ext.MessageBox.confirm('logout', Utilities.getMsg('default.message.logout'), function(btn){
            if(btn === 'yes'){

                Ext.Ajax.request({
                    url: '/j_spring_security_logout',
                    success: function(response){
                        //var text = response.responseText;     
                        var mainVP = Ext.getCmp('mainVP');
                        mainVP.removeAll();
                        mainVP.add({
                            xtype: 'logincontainer'
                        },
                        {
                            xtype: 'panel',
                            tbar: {
                                xtype: 'commonshowtoolbar'
                            },
                            itemId: 'newAccount',
                            layout: {
                                align: 'stretch',
                                type: 'vbox'
                            },
                            items: [{
                                xtype: 'userform',
                                flex: 1
                            }]
                        });
                    }
                });

            }
        });



    },

    processConfigBundle: function(config, prefix) {
        if (!prefix) {
            console.warn("processConfigBundle require a prefix argument");
        }

        if (config.items) {
            for (var i=0; i<config.items.length; i++) {
                var target = config.items[i];

                if (target.fieldLabel) {
                    var key = prefix+'.'+target.name+'.label';

                    // Check lang def exists
                    if (this.getMsg(key) !== key+".undefined") {
                        target.fieldLabel = this.getMsg(key);
                    }else console.log(key);
                }
                if(target.items){
                    for(var j=0; j<target.items.length; j++){
                        var innerTarget=target.items[j];
                        if(innerTarget.fieldLabel){
                            key = prefix+'.'+innerTarget.name+'.label';

                            // Check lang def exists
                            if (this.getMsg(key) !== key+".undefined") {
                                innerTarget.fieldLabel = this.getMsg(key);
                            }else console.log(key);
                        }
                    }
                }
            }
        }

        if (config.columns) {
            if(config.title != null)
            config.title = this.getMsg(prefix+'.label');
            for (var i=0; i<config.columns.length; i++) {
                var target = config.columns[i];

                if (target.text) {
                    var key = prefix+'.'+target.dataIndex+'.label';

                    // Check lang def exists
                    if (this.getMsg(key) !== key+".undefined") {
                        target.text  = this.getMsg(key);
                    }else console.log(key)
                }
            }
        }


        return config;
    },

    getMsg: function(lang) {
        return foodprint.getApplication().bundle.getMsg(lang);
    },

    readSysConfig: function(callback) {
        var that = this;

        Ext.Ajax.request({
            url: '/api/readSysConfig',
            async: false,
            success: function(response, opts) {
                that.sysConfig = Ext.decode(response.responseText);
                if(callback)
                callback()

            },
            failure: function(response, opts) {
                Ext.MessageBox.alert('Failure', "無法讀取系統參數");
            }
        });
    },

    getSysConfig: function(name) {
        return this.sysConfig[name];
    },

    comboReload: function(combo, id, value) {
        combo.getStore().load({
            params: {'nameLike': value}
        });
        combo.setValue(id);

    }

});