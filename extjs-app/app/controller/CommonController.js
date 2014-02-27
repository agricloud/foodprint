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

                if(form.findField('typeName')&&form.findField('name')){
                    form.findField('typeName').setReadOnly( true );
                    form.findField('name').setReadOnly( true );
                }

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

                if(form.findField('typeName')&&form.findField('name')){
                    form.findField('typeName').setReadOnly( false );
                    form.findField('name').setReadOnly( false );
                }

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
                    if(that.getMainForm().up().down('[itemId=detailGrid]')){
                        that.enableDetailCreateBtn();
                    }
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
        if(this.getMainForm().up().down('[itemId=detailGrid]')){
            this.enableDetailCreateBtn();
        }

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

    getParams: function() {
        if(this.domainName == 'foodpaint')
        return {foodpaintController:this.foodpaintController};
        else return {};
    },

    activeManufactureOrderIndex: function() {

        this.getMainForm().up('panel[itemId=show]').up().getLayout().setActiveItem(this.getMainGrid().up().up().down("panel[itemId=manufactureOrderIndex]"));
    },

    activeCustomerOrderDetIndex: function() {

        this.getMainForm().up('panel[itemId=show]').up().getLayout().setActiveItem(this.getMainGrid().up().up().down("panel[itemId=customerOrderDetIndex]"));

        var customerOrderDetGrid=this.getMainGrid().up().up().down("panel[itemId=customerOrderDetIndex]").down("grid[itemId=customerOrderDetGrid]");

    },

    doCreateAndIndexDetail: function() {
        this.doCreate();
        //單頭單身合併時 需將單身store移除
        this.getDetailGrid().getStore().removeAll();
        this.disableDetailCreateBtn();
        this.disableDetailShowBtn();
    },

    doShowAndIndexDetail: function(callback) {
        var that=this;
        this.doShow(function(success,form,action){

            if (callback && callback instanceof Function) {
                callback(true,form,action)
            }

            var record= that.getMainGrid().getSelectionModel().getSelection()[0];
            that.masterId = record.data.id;

            console.log('commonController--'+that.domainName+'/'+that.masterId+'--doShowAndIndexDetail');

            var grid = that.getDetailGrid();

            grid.getStore().data.clear();

            var params = {}
            params[that.masterKey]=that.masterId


            grid.getStore().getProxy().extraParams = params;
            grid.getStore().load();
        });


    },

    doShowDetail: function(callback) {
        console.log('commonController--'+this.domainName+'--doShowDetail');

        var that = this;
        var record= this.getDetailGrid().getSelectionModel().getSelection()[0];
        var id = -1;

        if(this.getDetailGrid().getSelectionModel().getSelection()[0])
        id = record.data.id;


        this.getDetailForm().getForm().load({
            url:this.getRoot()+'/'+this.domainName+'/show/'+id,
            params: this.getDetailParams(),
            waitMsg:Utilities.getMsg('default.message.load'),
            success: function(form, action) {

                //由於store設定load第1-50筆
                //導致doShow時若資料屬於第50筆之後無法正常顯示
                //在此使combo在doShow重新load store
                var formField=form.getFields();

                formField.each(function(item,index,len){
                    if(item instanceof Ext.form.field.ComboBox && item.xtype!='commoncountrycombo'){
                        var displayField;
                        switch(item.xtype){
                            case 'commonitemcombo':
                            displayField='item.name';
                            break;
                            case 'commonbatchcombo':
                            displayField='batch.name';
                            break;
                            case 'commonsuppliercombo':
                            displayField='supplier.name';
                            break;
                            case 'commonworkstationcombo':
                            displayField='workstation.name';
                            break;
                            case 'commonoperationcombo':
                            displayField='operation.name';
                            break;
                            case 'commonreportcombo':
                            displayField='report.name';
                            break;
                            case 'commonparamcombo':
                            displayField='param.name';
                            break;
                            case 'commoncustomercombo':
                            displayField='customer.name';
                            break;
                            //case 'commoncountrycombo':
                            //displayField='countryTitle';
                            //break;
                        }

                        item.getStore().load({
                            params: {'nameLike': action.result.data[displayField]}
                        });
                        console.log(action.result.data[item.getName()]);
                        item.setValue(action.result.data[item.getName()]);
                    }
                });

                that.activeDetailEditor();
                that.detailActionName = 'update';

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

    doCreateDetail: function() {
        console.log('commonController--'+this.domainName+'--doCreateDetail');
        var that = this

        var params = this.getDetailParams();
        params[this.masterKey]=this.masterId;
        this.getDetailForm().getForm().reset(true);

        this.getDetailForm().getForm().load({
            url: this.getRoot()+'/'+this.domainName+'/create',
            params:params,
            success: function(form, action) {
                that.detailActionName = 'save';
                that.activeDetailEditor();
                that.getDetailForm().up('panel[itemId=showDetail]').down('commondeletebtn').setDisabled(true);

            },

            failure: function(form, action) {
                Ext.MessageBox.alert('Failure',action.result.message);
            }

        });
    },

    doDeleteDetail: function() {
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
                that.detailActionName='delete';
                that.submitDetailForm(function(success){
                    if(success){
                        that.activeDetailGrid();
                        that.detailActionName = '' ;
                        that.getDetailGrid().getStore().reload();
                        that.disableDetailShowBtn();
                    }
                    that.detailActionName='update';

                });
            }
        });
    },

    doSaveDetail: function() {
        console.log('commonController--'+this.domainName+'--doSave');
        var that = this ;

        this.submitDetailForm(function(success){
            if(success){

                that.getDetailGrid().getStore().reload();

                if(that.detailActionName === 'save'){
                    that.activeDetailGrid();
                    that.detailActionName = '' ;
                }
            }

        });

    },

    activeDetailEditor: function() {
        if(this.getDetailForm().up('panel[itemId=showDetail]').down('commondeletebtn'))
        this.getDetailForm().up('panel[itemId=showDetail]').down('commondeletebtn').setDisabled(false);
        this.getDetailForm().up('panel[itemId=showDetail]').up().getLayout().setActiveItem(this.getDetailForm().up('panel[itemId=showDetail]'));
    },

    activeDetailGrid: function() {

        this.getDetailForm().up('panel[itemId=showDetail]').up().getLayout().setActiveItem(this.getDetailGrid().down().up("panel[itemId=show]"));
    },

    doCancelDetail: function() {
        this.getDetailForm().getForm().reset(true);
        this.activeDetailGrid();
    },

    enableDetailShowBtn: function() {
        this.getDetailGrid().up('panel[itemId=indexDetail]').down('commonshowbtn').setDisabled(false);
    },

    disableDetailShowBtn: function() {
        this.getDetailGrid().up('panel[itemId=indexDetail]').down('commonshowbtn').setDisabled(true);
    },

    getDetailParams: function() {
        if(this.domainName == 'foodpaint')
        return {foodpaintController:this.foodpaintDetController};
        else return {};
    },

    submitDetailForm: function(callback) {
        console.log('/'+this.domainName+'/'+this.detailActionName);

        this.getDetailForm().getForm().submit({
            url: this.getRoot()+'/'+this.domainName+'/'+this.detailActionName,
            params: this.getDetailParams(),
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

    enableDetailCreateBtn: function() {
        this.getDetailGrid().up('panel[itemId=indexDetail]').down('commoncreatebtn').setDisabled(false);
    },

    disableDetailCreateBtn: function() {
        this.getDetailGrid().up('panel[itemId=indexDetail]').down('commoncreatebtn').setDisabled(true);
    }

});