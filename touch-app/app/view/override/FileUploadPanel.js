Ext.define('foodprintTouch.view.override.FileUploadPanel', {
    override: 'foodprintTouch.view.FileUploadPanel',
	alias: 'widget.fileuploadpanel',
    requires: [
       'Ext.ux.Fileup'
    ],
    domainId:-1,
    domainName: 'none',
    
    config: {
        items: [
			{
                xtype: 'segmentedbutton',
                items: [
                    {
                        xtype: 'fileupload',
                        name:'file',
                        itemId:'fileuploadBtn',
                        autoUpload: true,
                        states: {
                            browse: {
                                text: '圖片上傳'
                            }
                        },
                        
                        listeners:{
                            success: function(){
                                this.up().up().showImage();
                            },
                            failure: function(){
                                this.up().up().fileUploadFailure();
                            },
                            tap: function( btn, e, eOpts ){
                                var uploadUrl=this.up().up().getUploadUrl();
                                console.log(uploadUrl);
                                btn.setUrl(uploadUrl);
                            
                            }
                        }
                    },
                    {
                        xtype: 'button',
                        itemId:'deleteBtn',
                        text:'delete',
                        handler: function(button, event) {
                            button.up().up().deleteImage();
                        }
                    }
                ]
            },
            {
                xtype: 'image',
                src:'',
                itemId:'mainImg',
                hidden:true,
                height: 201
            }
        ]

    },
    showImage: function() {
        this.getComponent("mainImg").show();
        this.getComponent("mainImg").setSrc('/attachment/show/'+this.domainId+"?domainName="+this.domainName+'&date='+(new Date()));
		
    },
    getUploadUrl:function(){
    	
    	return '/attachment/save?domainId='+this.domainId+'&domainName='+this.domainName;
    },

    deleteImage: function() {
        var that=this;
        Ext.Ajax.request( {  
            url : '/attachment/delete/'+this.domainId,
            method : 'post',  
            params : {  
                domainName : this.domainName 
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
    	this.getComponent("mainImg").hide();
    },

    hiddenBtn: function() {
        this.getComponent("deleteBtn").hide();
    	this.getComponent("fileuploadBtn").hide();
    },
    showBtn: function() {
        this.getComponent("deleteBtn").show();
    	this.getComponent("fileuploadBtn").show();
    },
    setDomainId: function(id){
        this.domainId=id;
    },
    setDomainName: function(name){
        this.domainName=name;
    }
});