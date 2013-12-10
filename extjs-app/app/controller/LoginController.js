/*
 * File: app/controller/LoginController.js
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

Ext.define('foodprint.controller.LoginController', {
    extend: 'Ext.app.Controller',

    mixins: {
        commonController: 'foodprint.controller.CommonController'
    },

    models: [
        'User'
    ],
    stores: [
        'UserStore'
    ],
    views: [
        'LoginContainer'
    ],

    refs: [
        {
            ref: 'mainForm',
            selector: 'mainviewport #form'
        }
    ],

    init: function(application) {
        this.control({
            'form[itemId=loginForm] button[itemId=loginBtn]': {
                click: this.doLogin
            },
            'form[itemId=loginForm] button[itemId=QueryBtn]': {
                click: this.showQueryWin
            },
            'form[itemId=loginForm] button[itemId=cancelBtn]': {
                click: this.doReset
            },
            'form[itemId=loginForm] button[itemId=addAccountBtn]': {
                click: this.doCreateAccount
            },
            '#mainVP #show commonshowtoolbar commoncancelbtn': {
                click: this.doCancel
            },
            '#mainVP #show commonshowtoolbar commondeletebtn': {
                click: this.doDelete
            },
            '#mainVP #show commonshowtoolbar commonsavebtn': {
                click: this.doSave
            }

        });

        this.domainName = 'user';
    },

    doLogin: function(btn, e, eOpts) {
        var form = btn.up('form').getForm();
        if (form.isValid()) {
            form.submit({
                success: this.loginSuccess,
                failure: this.loginFailed
            });
        }
        else {
            Ext.Msg.alert('無法登入', '請檢查表單資料！');
        }
    },

    loginSuccess: function(form, action) {
        var mainVP = Ext.getCmp('mainVP');
        mainVP.removeAll();
        mainVP.add({xtype: 'maincontainer'});

        Ext.get('username').update(action.result.username, false);
    },

    loginFailed: function() {
        Ext.Msg.alert('無法登入', '無法通過驗證！');
    },

    showQueryWin: function() {

    },

    doReset: function(btn, e, eOpts) {
        var form = btn.up('form').getForm();
        form.reset();
    },

    doCreateAccount: function(btn, e, eOpts) {
        var mainVP = Ext.getCmp('mainVP');
        mainVP.getLayout().setActiveItem(mainVP.down('panel[itemId=show]'));

        this.doCreate();

        this.getMainForm().up('panel[itemId=show]').down('commondeletebtn').setVisible(false);
    },

    doCancel: function(btn, e, eOpts) {
        var mainVP = Ext.getCmp('mainVP');
        mainVP.getLayout().setActiveItem(mainVP.down('logincontainer'));

    }

});
