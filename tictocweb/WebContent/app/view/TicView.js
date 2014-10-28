Ext.define('GS.view.TicView', {
	extend : 'Ext.form.Panel',
	xtype : 'ticview',

	config : {
		defaults : {
			required : true,
			labelAlign : 'left',
			labelWidth : '40%'
		},
		items : [ {
			xtype : 'fieldset',
			title : 'Tap',
			instructions : 'Tap'
		}, {
			xtype : 'textfield',
			name : 'ss',
			label : 'Name',
			autoCapitalize : false
		}, {
			xtype : 'toolbar',
			docked : 'bottom',
			items : [
			// Lets add a load button which will load the formpanel with a User model
			{
				id : 'tapButton',
				text : 'Tap',
				ui : 'confirm',
				scope : this,
				handler : function() {
				}

			}, {
				text : 'Toc',
				ui : 'confirm',
				scope : this,
				handler : function() {

				}

			}

			]
		} ]

	},

});
