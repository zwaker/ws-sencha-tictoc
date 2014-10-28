Ext
		.define(
				'GS.controller.LoginController',
				{
					extend : 'Ext.app.Controller',
					xtype : 'logincontroller',
					config : {
						
						routes: {
				            'forgot-password': 'openForgotPasswordView',
				            
				        },

						refs : {
							//loginButton : '#',
							
						}
					},
					
					openForgotPasswordView : function() {
						alert('s');
						var form = Ext.getCmp('loginView1');
						alert('s');
						config = {
								id : 'forgotPasswordView1',
								xtype : 'forgotpasswordview',

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

			            console.log('anchor tapped');

					},
					
				});