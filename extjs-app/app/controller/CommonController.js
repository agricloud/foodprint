/*
 * File: app/controller/CommonController.js
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

Ext.define('foodprint.controller.CommonController', {
    extend: 'Ext.app.Controller',

    isErpDomain: false,

    doShow: function(callback) {
        console.log('commonController--'+this.domainName+'--doShow');

        var that = this;
        var record= this.getMainGrid().getSelectionModel().getSelection()[0];
        var id = -1;

        if(this.getMainGrid().getSelectionModel().getSelection()[0])
        id = record.data.id;


        this.getMainForm().getForm().load({
            url:this.getRoot()+'/'+this.domainName+'/show/'+id,
            params: this.getParams(),
            waitMsg:Utilities.getMsg('default.message.load'),
            success: function(form, action) {
                that.activeEditor();
                that.actionName = 'update';

                if (callback && callback instanceof Function) {
                    callback(true,form,action)
                }
            },

            failure: function(form, action) {
                Ext.MessageBox.alert('Failure',action.result.message);
                if (callback && callback instanceof Function){
                    callback(false,form,action)
                }
            }
        });




    },

    doCreate: function() {
        console.log('commonController--'+this.domainName+'--doCreate');
        var that = this

        var params = this.getParams();
        params[this.masterKey]=this.masterId;
        this.getMainForm().getForm().reset(true);

        this.getMainForm().getForm().load({
            url: this.getRoot()+'/'+this.domainName+'/create',
            params:params,
            success: function(form, action) {
                that.actionName = 'save';
                that.activeEditor();
                that.getMainForm().up('panel[itemId=show]').down('commondeletebtn').setDisabled(true);

            },

            failure: function(form, action) {
                Ext.MessageBox.alert('Failure',action.result.message);
            }

        });





    },

    doDelete: function() {
        var that = this ;

        var msg = Ext.Msg;
        msg.buttonText={
            no: Utilities.getMsg('default.message.no'),
            yes: Utilities.getMsg('default.message.yes')
        };

        msg.confirm('Confirm delete', Utilities.getMsg('default.message.deleteConfirm'), function(e)
        {
            if(e == 'yes')
            {
                that.actionName='delete';
                that.submitForm(function(success){
                    if(success){
                        that.activeGrid();
                        that.actionName = '' ;
                        that.getMainGrid().getStore().reload();
                        that.disableShowBtn();
                    }
                    that.actionName='update';

                });
            }
        });
    },

    doSave: function() {
        console.log('commonController--'+this.domainName+'--doSave');
        var that = this ;

        this.submitForm(function(success){
            if(success){

                that.getMainGrid().getStore().reload();

                if(that.actionName === 'save'){
                    that.activeGrid();
                    that.actionName = '' ;
                }
            }

        });

    },

    doIndexDetail: function(obj, record, index, eOpts) {
        this.masterId = record.data.id;

        console.log('commonController--'+this.domainName+'/'+this.masterId+'--doIndexDetail');

        var grid = this.getMainGrid();

        grid.getStore().data.clear();

        var params = {}
        params[this.masterKey]=this.masterId


        grid.getStore().getProxy().extraParams = params;
        grid.getStore().load();
        console.log(grid.getStore());

    },

    submitForm: function(callback) {
        console.log('/'+this.domainName+'/'+this.actionName);

        this.getMainForm().getForm().submit({
            url: this.getRoot()+'/'+this.domainName+'/'+this.actionName,
            params: this.getParams(),
            submitEmptyText: false,
            waitMsg: 'Updating Data...',
            success: function(form,action) {
                Ext.MessageBox.alert('Success',action.result.message);

                if (callback) {
                    callback(true,form,action)
                }

            },
            failure: function(form,action) {
                var msg ="";
                for(var key in action.result.errors){
                    msg+=action.result.errors[key];
                }
                msg = action.result.message+'<br>'+msg;
                Ext.MessageBox.alert('Failure',msg);
                if (callback) {
                    callback(false,form,action)
                }
            }
        });
    },

    activeGrid: function() {

        this.getMainForm().up('panel[itemId=show]').up().getLayout().setActiveItem(this.getMainGrid().down().up("panel[itemId=index]"));
    },

    activeEditor: function() {
        if(this.getMainForm().up('panel[itemId=show]').down('commondeletebtn'))
        this.getMainForm().up('panel[itemId=show]').down('commondeletebtn').setDisabled(false);
        this.getMainForm().up('panel[itemId=show]').up().getLayout().setActiveItem(this.getMainForm().up('panel[itemId=show]'));
    },

    doCancel: function() {

        this.getMainForm().getForm().reset(true);
        this.activeGrid();


    },

    setImageUploader: function() {
        console.log(this.getImageUploader());
        var record= this.getMainGrid().getSelectionModel().getSelection()[0]
        this.getImageUploader().setDomainId(record.data['id']);
        this.getImageUploader().showImage();
    },

    disableShowBtn: function() {
        this.getMainGrid().up('panel[itemId=index]').down('commonshowbtn').setDisabled(true);
    },

    enableShowBtn: function() {
        this.getMainGrid().up('panel[itemId=index]').down('commonshowbtn').setDisabled(false);
    },

    getRoot: function() {
        /* 0111 erpDomain統一呼叫foodpaintController 刪除isErpDomain參數
        if(this.isErpDomain)
        return Utilities.getSysConfig("foodpaintUrl");
        else return '';
        */

        return '';
    },

    doIndexFoodpaint: function() {
        var grid = this.getMainGrid();
        grid.getStore().getProxy().url = this.getRoot()+"/"+this.domainName
        grid.getStore().load();
    },

    getParams: function() {
        if(this.domainName == 'foodpaint')
        return {foodpaintController:this.foodpaintController};
        else return '';
    }

});