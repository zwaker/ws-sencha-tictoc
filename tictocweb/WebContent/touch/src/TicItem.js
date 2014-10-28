Ext.define('Ext.TicItem', {
	extend : 'Ext.dataview.component.DataItem',
	requires : [ 'Ext.Button', 'Ext.Label' ],
	xtype : 'ticitem',

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
		
		nameButton : {
			flex : 1
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

	applyNameButton : function(config) {

		return Ext.factory(config, Ext.Button, this.getNameButton());
	},

	updateNameButton : function(newNameButton, oldNameButton) {
		var record = this.getRecord();
		this.getNameButton().setMaxWidth(100);
		this.getNameButton().setText("View Details");
		if (oldNameButton) {
			this.remove(oldNameButton);
		}

		if (newNameButton) {
			newNameButton.on('tap', this.onNameButtonTap, this);

			this.add(newNameButton);
		}
		if((record.get('status')=='REQUEST_ACCEPTED')){
			this.getNameButton().show();
		}
		
	},
	onNameButtonTap : function(button, e) {
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