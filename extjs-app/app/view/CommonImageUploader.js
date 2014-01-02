/*
 * File: app/view/CommonImageUploader.js
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

Ext.define('foodprint.view.CommonImageUploader', {
    extend: 'Ext.form.Panel',
    alias: 'widget.commonimageuploader',

    domainName: 'none',
    domainId: -1,
    maxHeight: 200,
    layout: {
        align: 'stretch',
        type: 'vbox'
    },
    bodyPadding: 10,

    initComponent: function() {
        var me = this;

        Ext.applyIf(me, {
            dockedItems: [
                {
                    xtype: 'toolbar',
                    flex: 1,
                    dock: 'top',
                    layout: {
                        pack: 'center',
                        type: 'hbox'
                    },
                    items: [
                        {
                            xtype: 'filefield',
                            name: 'file',
                            buttonOnly: true
                        },
                        {
                            xtype: 'button',
                            handler: function(button, event) {
                                button.up('commonimageuploader').saveImage();
                            },
                            text: '開始上傳'
                        },
                        {
                            xtype: 'button',
                            handler: function(button, event) {
                                button.up('commonimageuploader').deleteImage();
                            },
                            text: '刪除檔案'
                        }
                    ]
                }
            ],
            items: [
                {
                    xtype: 'image',
                    flex: 1,
                    itemId: 'mainImg',
                    src: ''
                }
            ]
        });

        me.processCommonImageUploader(me);
        me.callParent(arguments);
    },

    processCommonImageUploader: function(config) {
        console.log(config.dockedItems);

        config.dockedItems[0].hidden=true;

        config.dockedItems[0].items[0].buttonText=Utilities.getMsg('common.fileBrowse.label');
        config.dockedItems[0].items[1].text=Utilities.getMsg('common.uploadBtn.label');
        config.dockedItems[0].items[2].text=Utilities.getMsg('common.deleteBtn.label');
    },

    showImage: function() {

        console.log("do show img "+'/attachment/show/'+this.domainId+"?domainName="+this.domainName+'&fileType=jpg');
        this.getComponent("mainImg").setSrc('/attachment/show/'+this.domainId+'?domainName='+this.domainName+'&fileType=jpg');
    },

    saveImage: function() {

        var form = this.getForm();
        var domainName = this.domainName;
        var domainId = this.domainId;

        if(!domainId){
            Ext.Msg.alert('Error', "can't get id!");

        }else if(form.isValid()){
            var that=this;
            form.submit({
                url: '/attachment/save',
                waitMsg: 'Uploading your file...',
                params: {
                    domainName:domainName,
                    domainId: domainId,
                    fileType: 'jpg'
                },
                success: function(form, action) {
                    Ext.Msg.alert('Success', 'Your file has been uploaded.');
                    that.showImage();
                }
            });
        }
    },

    deleteImage: function() {
        var that=this;
        Ext.Ajax.request( {  
            url : '/attachment/delete/'+this.domainId,
            method : 'post',  
            params : {  
                domainName : this.domainName,
                fileType: 'jpg'
            },  
            success : function(response, options) {  
                that.emptyImage();
            },  
            failure : function() {  
                Ext.Msg.alert('Error', "can't delete image");
            }  
        });


    },

    emptyImage: function() {
        this.getComponent("mainImg").setSrc('');
    },

    showToolbar: function() {
        this.down("toolbar").setVisible(true); 
    },

    setDomainId: function(id) {
        this.domainId=id;
    }

});