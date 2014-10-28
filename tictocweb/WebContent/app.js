//<debug>
Ext.Loader.setPath({
	'Ext' : 'touch/src',
	'GS' : 'app'
});
// </debug>

Ext
		.application({
			name : 'GS',
			// requires : [ 'Ext.MessageBox' ],
			models : [ 'GS.model.Users', 'GS.model.Location' ],
			views : [ 'Main', 'GS.view.LoginView', 'GS.view.RegistrationView',
					'GS.view.TictocTabView', 'GS.view.TicView',
					'GS.view.TocView',
					'GS.view.ForgotPasswordView'],
			controllers : [ 'GS.controller.RegistrationController' ,'GS.controller.TaptocController','GS.controller.LoginController'],
			icon : {
				'57' : 'resources/icons/Icon.png',
				'72' : 'resources/icons/Icon~ipad.png',
				'114' : 'resources/icons/Icon@2x.png',
				'144' : 'resources/icons/Icon~ipad@2x.png'
			},

			isIconPrecomposed : true,

			startupImage : {
				'320x460' : 'resources/startup/320x460.jpg',
				'640x920' : 'resources/startup/640x920.png',
				'768x1004' : 'resources/startup/768x1004.png',
				'748x1024' : 'resources/startup/748x1024.png',
				'1536x2008' : 'resources/startup/1536x2008.png',
				'1496x2048' : 'resources/startup/1496x2048.png'
			},
			requires : [ 'Ext.MessageBox', 'Ext.form.*', 'Ext.field.*',
					'Ext.Button', 'Ext.data.*', 'Ext.Toolbar',
					'GS.controller.RegistrationController', 'Ext.data.Store',
					'GS.model.Users', 'GS.model.Location',
					'GS.view.RegistrationView', 'GS.view.TicView',
					'GS.store.LocationStore', 'Ext.AutocompleteField',
					'Ext.TicItem','GS.model.TtUserConnection',
					'GS.controller.TaptocController','Ext.TocItem',
					'GS.controller.LoginController',
					'GS.view.ForgotPasswordView'
					],

			launch : function() {
				var form;
				// Destroy the #appLoadingIndicator element
				Ext.fly('appLoadingIndicator').destroy();
				/*
				 * var locationStore= Ext.create('Ext.data.Store', { model:
				 * 'GS.model.Location', proxy: { type: 'ajax', url :
				 * 'http://localhost:8080/tictoc/faces/locationmanager.xhtml',
				 * reader:{type:'json'} },
				 * 
				 * autoLoad: true });
				 */
				// alert("S");
				
				  /*config = { //locStore:locationStore, 
						  xtype :'registrationview', id : 'registrationView1', };*/
				 
				/*
				 * config = { //locStore:locationStore, xtype : 'tictoctabview',
				 * id : 'tictoctabView1', };
				 */

				config = {
					id:'loginView1',
					xtype : 'loginview'
						
				};

				
				 //config={ id:'registrationView1',xtype:'registrationview' };
				 

				// alert('0');
				// If we are on a phone, we just want to add the form into the
				// viewport as is.
				// This will make it stretch to the size of the Viewport.
				if (Ext.os.deviceType == 'Phone') {
					form = Ext.Viewport.add(config);
				} else {
					// If we are not on a phone, we want to make the formpanel
					// model and give it a fixed with and height.
					Ext.apply(config, {
						modal : true,
						height : '90%',
						width : '40%',
						centered : true,

						// Disable hideOnMaskTap so it cannot be hidden
						hideOnMaskTap : false
					});

					// Add it to the Viewport and show it.
					form = Ext.Viewport.add(config);

				}
				 //alert('1');
				form.user = Ext.create('GS.model.Users', {
					name : '',
					password : ''
				});
				
				// form.location=Ext.create('GS.model.Location',{locationId:'',password:'m'});
				form.setRecord(form.user);
				// alert('2');
				// var selectLoc = Ext.getCmp('selectLocations');
				// selectLoc.setStore(locationStore);
				form.show();
				this.form = form;
				


			},
			onUpdated : function() {
				Ext.Msg
						.confirm(
								"Application Update",
								"This application has just successfully been updated to the latest version. Reload now?",
								function(buttonId) {
									if (buttonId === 'yes') {
										window.location.reload();
									}
								});
			}
		});
