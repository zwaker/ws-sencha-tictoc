Ext.define('Ext.TocItem', {
	extend : 'Ext.dataview.component.DataItem',
	requires : [ 'Ext.Button', 'Ext.Label' ],
	xtype : 'tocitem',

	config : {

		firstName : {
			flex : 1
		},
		lastName : {
			flex : 1
		},
		connectionTtuserId : {
			flex : 1
		},
		connectionMediumName : {
			flex : 1
		},
		
		confirmButton : {
			flex : 1
		},
		rejectButton : {
			flex : 1
		},
		viewDetailsButton : {
			flex : 1,
			hidden:true
		},
		dataMap : {
			/*getNameButton: {
			    setText: 'name'
			    	
			}
			,*/
			getFirstName : {
				setHtml : 'connectionFirstName'
			},
			getLastName : {
				setHtml : 'connectionLastName'
			},
			getConnectionTtuserId : {
				setHtml : 'connectionTtuserId'
			},
			getConnectionTtuserId : {
				value:'connectionMediumName'
			}
		},
		layout : {
			type : 'hbox',
			align : 'center'
		}
	},

	applyLabelItem : function(config) {
		return Ext.factory(config, Ext.field.Hidden, this.getConnectionTtuserId());
	},
	applyFirstName : function(config) {
		return Ext.factory(config, Ext.Label, this.getFirstName());
	},
	applyLastName : function(config) {
		return Ext.factory(config, Ext.Label, this.getLastName());
	},

	applyConfirmButton : function(config) {

		return Ext.factory(config, Ext.Button, this.getConfirmButton());
	},
	applyRejectButton : function(config) {

		return Ext.factory(config, Ext.Button, this.getRejectButton());
	},
	applyViewDetailsButton : function(config) {

		return Ext.factory(config, Ext.Button, this.getViewDetailsButton());
	},

	
	updateConfirmButton : function(newConfirmButton, oldConfirmButton) {
		var record = this.getRecord();
		this.getConfirmButton().setMaxWidth(100);
		this.getConfirmButton().setText("Confirm");
		if (oldConfirmButton) {
			this.remove(oldConfirmButton);
		}

		if (newConfirmButton) {
			newConfirmButton.on('tap', this.onConfirmButtonTap, this);

			this.add(newConfirmButton);
		}
		
		if((record.get('status')!='REQUEST_RECEIVED')){
			this.getConfirmButton().hide();
		}
		
	},
	onConfirmButtonTap : function(button, e) {
		var record = this.getRecord();
		var values={"id":record.get('id')};
		Ext.Ajax
		.request({

			url : '/tictoc/faces/tocmanager.xhtml?type=confirm',

			params : (values),
			method : 'GET',
			scope:this,
			success : function(response) {
				alert('successful');
				this.getConfirmButton().hide();
				this.getRejectButton().hide();
				this.getViewDetailsButton().show();
			},
			failure : function(response) {
			}
		});
		
	},
	updateRejectButton : function(newRejectButton, oldRejectButton) {
		var record = this.getRecord();
		this.getRejectButton().setMaxWidth(100);
		this.getRejectButton().setText("Reject");
		if (oldRejectButton) {
			this.remove(oldRejectButton);
		}

		if (newRejectButton) {
			newRejectButton.on('tap', this.onRejectButtonTap, this);

			this.add(newRejectButton);
		}
		if((record.get('status')!='REQUEST_RECEIVED')){
			this.getRejectButton().hide();
		}
	},
	onRejectButtonTap : function(button, e) {
		var record = this.getRecord();
		var values={"id":record.get('id')};
		Ext.Ajax
		.request({

			url : '/tictoc/faces/tocmanager.xhtml?type=reject',

			params : (values),
			method : 'GET',
			scope:this,
			success : function(response) {
				alert('successful');
				this.getConfirmButton().hide();
				this.getRejectButton().hide();
				
			},
			failure : function(response) {
			}
		});
		
	},
	
	updateViewDetailsButton : function(newViewDetailsButton, oldViewDetailsButton) {
		var record = this.getRecord();
		this.getViewDetailsButton().setMaxWidth(100);
		this.getViewDetailsButton().setText("View Details");
		if (oldViewDetailsButton) {
			this.remove(oldViewDetailsButton);
		}

		if (newViewDetailsButton) {
			newViewDetailsButton.on('tap', this.onViewDetailsButtonTap, this);

			this.add(newViewDetailsButton);
		}
		if((record.get('status')=='REQUEST_ACCEPTED')){
			this.getViewDetailsButton().show();
		}
	},
	onViewDetailsButtonTap : function(button, e) {
		var record = this.getRecord();
		//alert(this.getTestThis().getHtml());
		//alert(this.getLabelItem().getHtml());
		alert(record.get('connectionMediumName'));
		//alert(this.getData());
		Ext.Msg.alert("Details", // the title of the alert
				record.get('connectionFirstName')+" "+record.get('connectionFirstName')+
				" is using " + record.get('connectionMediumName') +'<br>'
				+"the id is " + record.get('connectionPrefferedMediumUserName'));// the message of the alert
		
		
	},
	updateConnectionTtuserId : function(newConnectionTtuserId, oldConnectionTtuserId) {
		if (oldConnectionTtuserId) {
			this.remove(oldConnectionTtuserId);
		}

		if (newConnectionTtuserId) {
			this.add(newConnectionTtuserId);
		}
	},
	updateFirstName : function(newFirstName, oldFirstName) {

		if (oldFirstName) {
			this.remove(oldFirstName);
		}

		if (newFirstName) {
			this.add(newFirstName);
		}
	},
	updateLastName : function(newLastName, oldLastName) {

		if (oldLastName) {
			this.remove(oldLastName);
		}

		if (newLastName) {
			this.add(newLastName);
		}
	}
});