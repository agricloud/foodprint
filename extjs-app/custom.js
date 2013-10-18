Ext.onReady(function() {
    var hideMask = function () {
        Ext.get('loading').remove();
        Ext.get('loading-mask').remove();
        Ext.fly('loading-mask').animate({
            opacity: 0,
            remove: true
        });
    };
    
    Ext.defer(hideMask, 250);
});