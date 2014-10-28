Ext.define('GS.store.LocationStore', {
	extend  : 'Ext.data.Store',
	requires: 'GS.model.Location',
	config: {
		model: 'GS.model.Location'
	}
});