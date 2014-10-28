Ext
		.define(
				'GS.controller.TaptocController',
				{
					extend : 'Ext.app.Controller',
					xtype : 'taptoccontroller',
					config : {
						control : {
							tapButton : {
								tap : 'performTapRequests'
							},
							tocButton : {
								tap : 'loadTocScreen'
							}

						},

						refs : {
							tapButton : '#tapRequestButton1',
							tocButton : '#tocButton'
						}
					},

					performTapRequests : function() {
						Ext.Ajax
						.request({
							type:'ajax',
							url : '/tictoc/faces/tapmanager.xhtml?type=performTapRequests',
							reader : {
								type : 'json',
								rootProperty : 'connectionRequestsSent'
							},
							// params : (values),
							method : 'GET',

							success : function(response) {
								alert('s');
								var tapDataView = Ext.getCmp('tapDataView');
								tapDataView.setData(response.responseText.trim());
								var tapRequestButton = Ext
								.getCmp('tapRequestButton1');
								tapRequestButton.hide();
							},
							failure : function(response) {
								alert('failed');
							}
						});
					},
					loadTocScreen : function() {

					},
					
				});