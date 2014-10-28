Ext
		.define(
				'GS.view.TictocTabView',
				{
					extend : 'Ext.TabPanel',
					xtype : 'tictoctabview',

					config : {
						fullscreen : true,
						tabBarPosition : 'bottom',

						defaults : {
							styleHtmlContent : true
						},

						items : [
								{
									id:'tapDataView',
									xtype : 'dataview',
									title : 'Tap',
									iconCls : 'user',
									items : [ {

										id : 'errorFieldset',
										xtype : 'fieldset',
										title : 'Connection Requests',
										instructions : 'Perform Request',

									}, {
										id : 'tapRequestButton1',
										xtype : 'button',
										text : 'Tap Request',
										hidden : true

									}, {

										xtype : 'fieldset',
										title : 'Request List',
										instructions : '',

									} ],

									store : {
										model : 'GS.model.TtUserConnection',
										sorters : 'id',

										grouper : {
											groupFn : function(record) {
												return record.get('id')[0];
											}
										},
										proxy : {
											type : 'ajax',
											url : '/tictoc/faces/tapmanager.xhtml?type=retrieveTapRequests',
											reader : {
												type : 'json',
												rootProperty : 'connectionRequestsSent'
											}
										},
										autoLoad : true
									},

									// itemTpl : '<div class="contact">{name}
									// <strong>{name}</strong><input
									// class=\'genericButton\' id=\'bbb\'
									// type=\'button\'
									// value=\'zul\'></input></div>',
									useComponents : true,
									defaultType : 'ticitem',
									baseCls : 'my-dataview',
									itemCls : 'my-dataview-item',
									
									// constructor: function(config) {alert('s');},
									
								},
								{
									xtype : 'dataview',
									title : 'Toc',
									iconCls : 'user',
									items : [ {

										//id : 'errorFieldset',
										xtype : 'fieldset',
										title : 'Connection Requests Received',
										instructions : '',
									} ],

									store : {
										model : 'GS.model.TtUserConnection',
										sorters : 'id',

										grouper : {
											groupFn : function(record) {
												return record.get('id')[0];
											}
										},
										proxy : {
											type : 'ajax',
											url : '/tictoc/faces/tocmanager.xhtml?type=retrieveTocRequests',
											reader : {
												type : 'json',
												rootProperty : 'connectionRequestsReceived'
											}
										},
										autoLoad : true
									},

									// itemTpl : '<div class="contact">{name}
									// <strong>{name}</strong><input
									// class=\'genericButton\' id=\'bbb\'
									// type=\'button\'
									// value=\'zul\'></input></div>',
									useComponents : true,
									defaultType : 'tocitem',
									baseCls : 'my-dataview',
									itemCls : 'my-dataview-item',
								}

						]
					},
					initialize : function() {
					//this.callSuper.caller.apply(this,null);
					// alert('s');
					Ext.Ajax
							.request({

								url : '/tictoc/faces/tapmanager.xhtml?type=userCanMakeTapRequest',

								// params : (values),
								method : 'GET',

								success : function(response) {
									var res = Ext.JSON
											.decode(response.responseText
													.trim());
									// alert(res.userCanMakeTapRequest);
									if (res.userCanMakeTapRequest) {
										var tapRequestButton = Ext
												.getCmp('tapRequestButton1');
										tapRequestButton.show();
									}else{
										var tapRequestButton = Ext
										.getCmp('tapRequestButton1');
										tapRequestButton.hide();
									}
								},
								failure : function(response) {
								}
							});
					var tapRequestButton = Ext.getCmp('tapRequestButton1');
					// tapRequestButton.hide();
					// alert('sss');
					this.superclass.initialize.call(this);

				}
					


				});