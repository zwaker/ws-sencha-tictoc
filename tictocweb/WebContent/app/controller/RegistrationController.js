Ext.define('GS.controller.RegistrationController', {
	extend : 'Ext.app.Controller',
	xtype : 'registrationController',
	config : {
		routes: {
            'register': 'openRegistrationView',
            
        },
		control : {
			
			registrationButton : {
				tap : 'doRegistration'
			},
			loginButton : {
				tap : 'doLogin'
			},
			sendForgottenPasswordButton : {
				tap : 'doSendForgottenPasswordProcess'
			},
			signInButtonFromRegistrationView : {
				tap : 'openLoginView'
			},
			

		},

		refs : {
			registrationButton : '#registrationButton',
			loginButton : '#loginButton',
			sendForgottenPasswordButton : '#sendForgotPasswordButton',
			signInButtonFromRegistrationView:'#signInButtonFromRegistrationView'

		}
	},
	openLoginView : function() {
		//alert('s');
		var form = Ext.getCmp('registrationView1');
		//alert('s');
		config = {
				id : 'loginView1',
				xtype : 'loginview',

			};
		Ext.Viewport.remove(form);
		if (Ext.os.deviceType == 'Phone') {
			form = Ext.Viewport.add(config);
		} else {
			// If we are not on a phone, we want
			// to make the formpanel model and
			// give it a fixed with and height.
			Ext.apply(config, {
				modal : true,
				height : 505,
				width : 480,
				centered : true,

				// Disable hideOnMaskTap so it
				// cannot be hidden
				hideOnMaskTap : false
			});

			// Add it to the Viewport and show
			// it.

		}

		form = Ext.Viewport.add(config);
		form.show();

       // console.log('anchor tapped');

	},
	openRegistrationView : function() {
		//alert('s');
		var form = Ext.getCmp('loginView1');
		//alert('s');
		config = {
				id : 'registrationView1',
				xtype : 'registrationview',

			};
		Ext.Viewport.remove(form);
		if (Ext.os.deviceType == 'Phone') {
			form = Ext.Viewport.add(config);
		} else {
			// If we are not on a phone, we want
			// to make the formpanel model and
			// give it a fixed with and height.
			Ext.apply(config, {
				modal : true,
				height : 505,
				width : 480,
				centered : true,

				// Disable hideOnMaskTap so it
				// cannot be hidden
				hideOnMaskTap : false
			});

			// Add it to the Viewport and show
			// it.

		}

		form = Ext.Viewport.add(config);
		form.show();

        //console.log('anchor tapped');

	},
	doSendForgottenPasswordProcess:function(){
		var form = Ext.getCmp('forgotPasswordView1');
		var formValues=form.getValues();
		//alert('s');
		Ext.Ajax
		.request({
			type:'ajax',
			params : (formValues),
			url : '/tictoc/faces/usermanager.xhtml?type=sendForgottenPassword',
			reader : {
				//type : 'json',
				//rootProperty : 'sentPassword'
			},
			// params : (values),
			method : 'POST',

			success : function(response) {
				//alert(response.responseText);
			},
			failure : function(response) {
				//alert('failed');
			}
		});
	},
	
	doRegistration : function() {
		// alert('s');
		var form = Ext.getCmp('registrationView1');

		var model = Ext.ModelMgr.create(form.getValues(), 'GS.model.Users');

		var errors = model.validate();// ,
		var message = "";
		if (errors.isValid()) {

			// Mask the form
			form.setMasked({
				xtype : 'loadmask',
				message : 'Saving...'
			});

			form.updateRecord(form.user, true);

			// form.updateRecord(form.user, true);
			// alert('s');
			var usern = form.user;
			// alert(usern.get('name'));

			var values = form.getValues();
			var jsonString = Ext.encode(values);

			Ext.Ajax.request({

				url : '/tictoc/faces/usermanager.xhtml?type=registration',

				params : (values),
				method : 'POST',

				success : function(response) {
					setTimeout(function() {
						form.setMasked(false);
					}, 1000);
					//alert(response.responseText);
					var message = "Please enter registration information";
					responseString = response.responseText.trim();
					//alert(responseString);
					if (responseString == "userexists") {
						//alert('userexist');
						var errorFieldset = Ext.getCmp('errorFieldset');
						message = "This username you entered already exists";
						errorFieldset.setInstructions("<br>" + message);
						return false;
					}
					if (responseString == "locationdoesnotexists") {
						//alert('userexist');
						var errorFieldset = Ext.getCmp('errorFieldset');
						message = "This location you entered cannot be found";
						errorFieldset.setInstructions("<br>" + message);
						return false;
					}
					if (responseString == "ethnicitydoesnotexists") {
						//alert('userexist');
						var errorFieldset = Ext.getCmp('errorFieldset');
						message = "This ethnicity you entered cannot be found";
						errorFieldset.setInstructions("<br>" + message);
						return false;
					}
					if (responseString == 'registrationsuccessful') {
						this.form = form;

						config = {
							id : 'loginView1',
							xtype : 'loginview',

						};
						Ext.Viewport.remove(form);
						if (Ext.os.deviceType == 'Phone') {
							form = Ext.Viewport.add(config);
						} else {
							// If we are not on a phone,
							// we want
							// to make the formpanel
							// model and
							// give it a fixed with and
							// height.
							Ext.apply(config, {
								modal : true,
								height : 505,
								width : 480,
								centered : true,

								// Disable hideOnMaskTap
								// so it
								// cannot be hidden
								hideOnMaskTap : false
							});

							// Add it to the Viewport
							// and show
							// it.

						}

						
						
						form = Ext.Viewport.add(config);
						var errorFieldset = Ext.getCmp('errorFieldsetLogin');
						message = "Your registration was successful, Please login now";
						errorFieldset.setInstructions("<br>" + message);
						form.show();

					}

				},
				failure : function() {
					setTimeout(function() {
						form.setMasked(false);
					}, 1000);
					alert('form submission failed!');
				}
			});

		} else {

			Ext.each(errors.items, function(rec, i) {

				message += rec.getMessage() + "<br>";

			});
			var errorFieldset = Ext.getCmp('errorFieldset');
			errorFieldset
					.setInstructions("Please enter registration information"
							+ "<br>" + message);
			// Ext.Msg.alert("Validate", message, function(){});
			return false;
		}
	},
	doLogin : function() {

		alert('s');
		var form = Ext.getCmp('loginView1');
		var values = form.getValues();
		var jsonString = Ext.encode(values);

		Ext.Ajax.request({

			url : '/tictoc/faces/usermanager.xhtml?type=login',

			params : (values),
			method : 'POST',

			success : function(response) {
				var message = "Please enter login information";
				responseString = response.responseText.trim();
				//alert(responseString);
				if (responseString == "userdoesnotexist") {
					//alert('userexist');
					var errorFieldset = Ext.getCmp('errorFieldset');
					message = "The email you entered does not belong to any account.";
					errorFieldset.setInstructions("<br>" + message);
					return false;
				}
				if (responseString == "wrongpassword") {
					//alert('userexist');
					var errorFieldset = Ext.getCmp('errorFieldset');
					message = "The password you entered is incorrect. Please try again (make sure your caps lock is off).";
					errorFieldset.setInstructions("<br>" + message);
					return false;
				}
				var form = Ext.getCmp('loginView1');
				alert(response.responseText);
				config = {
					id : 'tictoctabView1',
					xtype : 'tictoctabview',

				};
				Ext.Viewport.remove(form);
				if (Ext.os.deviceType == 'Phone') {
					form = Ext.Viewport.add(config);
				} else {
					// If we are not on a phone, we want
					// to make the formpanel model and
					// give it a fixed with and height.
					Ext.apply(config, {
						modal : true,
						height : 505,
						width : 480,
						centered : true,

						// Disable hideOnMaskTap so it
						// cannot be hidden
						hideOnMaskTap : false
					});

					// Add it to the Viewport and show
					// it.

				}

				form = Ext.Viewport.add(config);
				form.show();

				//alert('form submitted successfully!');
			},
			failure : function() {
				alert(response.responseText);
				//alert('form submission failed!');
			}
		});
		// called whenever the Login button is tapped
	},

	doLogout : function() {
		// called whenever any Button with action=logout is
		// tapped
	}
});