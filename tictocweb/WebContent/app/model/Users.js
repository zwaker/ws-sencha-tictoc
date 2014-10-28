//http://docs.sencha.com/touch/2-0/source/Validations.html
Ext.define('GS.model.Users', {
	extend : 'Ext.data.Model',

	config : {
		fields : [ {
			name : 'username',
			type : 'string'
		}, {
			name : 'password',
			type : 'string'
		}, {
			name : 'firstName',
			type : 'string'
		}, {
			name : 'lastName',
			type : 'string'
		}, {
			name : 'birthyear',
			type : 'string'
		}, {
			name : 'locationId',
			type : 'string'
		}, {
			name : 'ethnicityId',
			type : 'string'
		} , {
			name : 'languageId',
			type : 'string'
		} ],
		validations : [ {
			type : 'presence',
			field : 'username',
			message : 'Email address cannot be empty'
		}, {
			type : 'presence',
			field : 'password',
			message : 'Password cannot be empty'
		}, {
			type : 'presence',
			field : 'firstName',
			message : 'First Name cannot be empty'
		},{
			type : 'presence',
			field : 'lastName',
			message : 'Last Name cannot be empty '
		},{
			type : 'presence',
			field : 'birthyear',
			message : 'Year of birth cannot be empty'
		}, {
			type : 'presence',
			field : 'locationId',
			message : 'Location cannot be empty'
		}, {
			type : 'presence',
			field : 'ethnicityId',
			message : 'Ethnicity field cannot be empty'
		}
		, {
			type : 'presence',
			field : 'languageId',
			message : 'Language field cannot be empty'
		}
		, {
			type : 'length',
			field : 'password',
			min : 6,
			message : 'The password you entered cannot be less than 6 characters'
		}, {
			type : 'format',
			field : 'username',
			matcher : /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/,
			message : 'Email address you entered is not valid'
		}

		],

		proxy : {
			type : 'rest',
			url : 'http://localhost:8080/userservice/faces/usermanager.xhtml',
			reader : {
				type : 'json',
				rootProperty : 'users'
			}
		}
	}
});
// Uses the User Model's Proxy
/*
 * Ext.create('Ext.data.Store', { model: 'GS.model.Users' });
 */