Ext.define('GS.view.LoginView', {
	extend : 'Ext.form.Panel',
	xtype : 'loginview',

	config : {
		defaults : {
			required : true,
			labelAlign : 'left',
			labelWidth : '40%'
		},
		items : [ {
			id:'errorFieldsetLogin',
			xtype : 'fieldset',
			title : 'Login Information',
			instructions : 'Please enter login information.'
		}, {
			xtype : 'panel',
			style:'font: normal 12px helvetica ',
			html:'Not a member yet? <a id="registerLink" href="#register"/>Register</a>'
				
		}, {
			xtype : 'textfield',
			name : 'username',
			label : 'Name',
			autoCapitalize : false,
			placeHolder: 'abc@gmail.com'
		}, {
			xtype : 'passwordfield',
			name : 'password',
			label : 'Password',
			placeHolder: 'Password'
				
		},{
			xtype : 'panel',
			style:'font: normal 12px helvetica ',
			html:'Forgot password? <a id="forgotPasswordLink" href="#forgot-password"/>Click here</a>'

		}, {
			xtype : 'toolbar',
			docked : 'bottom',
			items : [
			// Lets add a load button which will load the formpanel with a User
			// model
			{
				id : 'loginButton',
				text : 'Login',
				ui : 'confirm',
				//scope : this
				
			}

			]
		} ]
		/*listeners  : {
	        element  : 'element',
	        delegate : 'a',
	        tap      : function(e) {
	            e.stopEvent();
	            alert('s');

	        }
	    }*/

	},

});
