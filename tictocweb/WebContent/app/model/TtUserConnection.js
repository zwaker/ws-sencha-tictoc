Ext.define('GS.model.TtUserConnection', {
	extend : 'Ext.data.Model',

	config : {
		fields : [ {
			name : 'id',
			type : 'string',
			mapping: 'id'
		}, {
			name : 'ttuserId',
			type : 'string',
			mapping: 'ttuserId'
		} ,
		{
			name : 'connectionTtuserId',
			type : 'string',
			mapping: 'connectionTtuserId'
		} ,
		{
			name : 'status',
			type : 'string',
			mapping: 'status'
		} ,
		{
			name : 'connectionFirstName',
			type : 'string',
			mapping: 'connectionFirstName'
		} ,
		{
			name : 'connectionLastName',
			type : 'string',
			mapping: 'connectionLastName'
		} ,
		{
			name : 'connectionMediumName',
			type : 'string',
			mapping: 'connectionMediumName'
		} ,
		{
			name : 'connectionPrefferedMediumUserName',
			type : 'string',
			mapping: 'connectionPrefferedMediumUserName'
		} ]
	}
});
// Uses the User Model's Proxy

/*Ext.create('GS.store.LocationStore', {
   // model: 'GS.model.Location'
});*/