Ext.define('GS.view.ForgotPasswordView', {
	extend : 'Ext.form.Panel',
	xtype : 'forgotpasswordview',

	config : {
		defaults : {
			required : true,
			labelAlign : 'left',
			labelWidth : '40%'
		},
		items : [ {
			id:'errorFieldset',
			xtype : 'fieldset',
			title : 'Forgot Password',
			instructions : 'Please provide your username. We will send your password to your email address'
		}, {
			xtype : 'textfield',
			name : 'username',
			label : 'Name',
			autoCapitalize : false,
			placeHolder: 'abc@gmail.com'
		}, {
			xtype : 'toolbar',
			docked : 'bottom',
			items : [
			// Lets add a load button which will load the formpanel with a User
			// model
			{
				id : 'sendForgotPasswordButton',
				text : 'Send',
				ui : 'confirm',
				

			}, 
			]
		} ]

	},

});
