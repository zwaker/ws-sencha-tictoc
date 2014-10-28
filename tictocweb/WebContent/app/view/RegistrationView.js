Ext.define('GS.view.RegistrationView', {
	extend : 'Ext.form.Panel',
	xtype : 'registrationview',
	
	config : {
		//locStore:null,
		defaults : {
			required : true,
			labelAlign : 'left',
			labelWidth : '40%'
		},
		items : [ {
			id:'errorFieldset',
			xtype : 'fieldset',
			title : 'Registration Information',
			instructions : 'Please enter registration information.'
		}, 
		  {
			xtype : 'textfield',
			name : 'username',
			label : 'User name',
			autoCapitalize : false,
			placeHolder: 'xyz@gmail.com'
		}, {
			xtype : 'passwordfield',
			name : 'password',
			label : 'Password',
			placeHolder: 'Password'
		}, 
		{
			xtype : 'textfield',
			name : 'firstName',
			label : 'First name',
			autoCapitalize : false,
			placeHolder: 'James'
		},
		{
			xtype : 'textfield',
			name : 'lastName',
			label : 'Last name',
			autoCapitalize : false,
			placeHolder: 'Bond'
		},
		{
            xtype: 'selectfield',
            name:'connectionMedium',
            label: 'Connection Medium',
            //placeHolder: 'e.g. Facebook',
            options: [
                {text: 'Facebook',  value: 'facebook'},
                {text: 'Twitter', value: 'twitter'},
                {text: 'Hotmail',  value: 'hotmail'},
                {text: 'Gmail',  value: 'gmail'},
                {text: 'Yahoo',  value: 'yahoo'}
            ]
        },
        {
            xtype: 'textfield',
            name:'mediumUsername',
            label: 'Medium username',
            placeHolder: 'abc@facebook.com'
        },
		
       // {
	//		id:'selectLocations',
      //  	xtype: 'searchfield',
        	//displayField: 'locationId',
        	// valueField: 'locationName',
        	  /*options: [
                        {text: 'First Option',  value: 'first'},
                        {text: 'Second Option', value: 'second'},
                        {text: 'Third Option',  value: 'third'}
                    ]*/
            //store:getLocStore(),
            //displayField: 'title',
            //typeAhead: false,
            //hideLabel: true,
            //hideTrigger:true,
            //anchor: '100%',

            /*listConfig: {
                loadingText: 'Searching...',
                emptyText: 'No matching posts found.',

                // Custom rendering template for each item
                getInnerTpl: function() {
                    return '<div class="search-item">' +
                        '<h3><span>{[Ext.Date.format(values.lastPost, "M j, Y")]}<br />by {author}</span>{title}</h3>' +
                        '{excerpt}' +
                    '</div>';
                }
            },*/
            //pageSize: 10,

            // override default onSelect to do redirect
            /*listeners: {
                select: function(combo, selection) {
                    var post = selection[0];
                    if (post) {
                        //window.location =
                            //Ext.String.format('http://www.sencha.com/forum/showthread.php?t={0}&p={1}', post.get('topicId'), post.get('id'));
                    }
                }
            }*/
		//},
		
		{
		    xtype: 'autocompletefield',
		    name:'locationId',
		    label: 'Location',
		    placeHolder: 'Sydney',
		    value: '',
		    config: {
		        proxy: {
		            type: 'ajax',
		            url: '/tictoc/faces/locationmanager.xhtml',
		            reader: {
		                type: 'json',
		                rootProperty: 'locations'
		            }
		        },
		        resultsHeight: 50,
		        needleKey: 'locationName',
		        labelKey: 'name'//,
		        //autoLoad: true
		        	
		    }
		},
		{
			xtype : 'textfield',
			name : 'birthyear',
			label : 'Year of Birth',
			autoCapitalize : false,
			placeHolder: 'e.g.1980'
		},
		{
		    xtype: 'autocompletefield',
		    name:'ethnicityId',
		    label: 'Ethnicity',
		    placeHolder: 'Vietnamese',
		    value: '',
		    config: {
		        proxy: {
		            type: 'ajax',
		            url: '/tictoc/faces/ethnicitymanager.xhtml',
		            reader: {
		                type: 'json',
		                rootProperty: 'ethnicities'
		            }
		        },
		        resultsHeight: 50,
		        needleKey: 'term',
		        labelKey: 'name'//,
		        //autoLoad: true
		        	
		    }
		},
		{
		    xtype: 'autocompletefield',
		    name:'languageId',
		    label: 'Language',
		    placeHolder: 'Bengali',
		    value: '',
		    config: {
		        proxy: {
		            type: 'ajax',
		            url: '/tictoc/faces/languagemanager.xhtml',
		            reader: {
		                type: 'json',
		                rootProperty: 'languages'
		            }
		        },
		        resultsHeight: 50,
		        needleKey: 'term',
		        labelKey: 'name'//,
		        //autoLoad: true
		        	
		    }
		},
		{
			xtype : 'toolbar',
			docked : 'bottom',
			items : [
			// Lets add a load button which will load the formpanel with a User model
			{
				id:'registrationButton',
				text : 'Register',
				ui : 'confirm',
				scope : this,
				handler : function() {
				}

			},
			{
				id:'signInButtonFromRegistrationView',
				text : 'Sign in',
				ui : 'confirm',
				scope : this,
				handler : function() {
					
				}

			}

			]
		} ]

	},

});
