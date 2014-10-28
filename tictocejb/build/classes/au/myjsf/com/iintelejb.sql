SELECT * FROM MYKILLERAPP.VoteItem x where x.id=2;
DELETE FROM MYKILLERAPP.VoteItem  where VoteItem.id=1;
drop TABLE mykillerapp.VoteItem;
INSERT into mykillerapp.VoteItem(name,yCount,nCount) values('Are you confident about the Australian economy?','43','34')

CREATE TABLE mykillerapp.VoteItem (
		id int NOT NULL AUTO_INCREMENT,
		name VARCHAR(200) NOT NULL,
		yCount VARCHAR(2000) NOT NULL,
		nCount  VARCHAR(20) NOT NULL,
		PRIMARY KEY (id)
	);


