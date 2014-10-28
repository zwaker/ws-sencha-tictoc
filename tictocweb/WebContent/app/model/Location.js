Ext.define('GS.model.Location', {
	extend : 'Ext.data.Model',

	config : {
		fields : [ {
			name : 'id',
			type : 'string',
			mapping: 'locationId'
		}, {
			name : 'name',
			type : 'string',
			mapping: 'name'
		} ],
		proxy: {
            type: 'jsonp',
            url : '/tictoc/faces/locationmanager.xhtml',
            reader: {
                type: 'json',
                rootProperty: 'locations',
                totalProperty: 'totalCount'
                	
            }
        }
	}
});
// Uses the User Model's Proxy

/*Ext.create('GS.store.LocationStore', {
   // model: 'GS.model.Location'
});*/