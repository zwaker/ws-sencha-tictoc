Ext.define('GS.view.TocView', {
	extend : 'Ext.form.Panel',
	xtype : 'tocview',

	config : {
		defaults : {
			required : true,
			labelAlign : 'left',
			labelWidth : '40%'
		},
		items : [ {
			xtype : 'fieldset',
			title : 'Toc',
			instructions : 'Toc'
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
