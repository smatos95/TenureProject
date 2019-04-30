
DROP TABLE IF EXISTS users;
CREATE TABLE IF NOT EXISTS users (
   userNumber INT NOT NULL UNIQUE AUTO_INCREMENT,
   loginName VARCHAR(50) NOT NULL UNIQUE,
   userPassword VARCHAR(64) NOT NULL,
   firstName VARCHAR (25)DEFAULT '',
   lastName VARCHAR (35)DEFAULT '',
   emailAddress VARCHAR(50)DEFAULT '',
   userRole VARCHAR (30) NOT NULL,
   lastLoginTime VARCHAR (25),
   loginCount INT DEFAULT 0, 
   salt VARCHAR(50),
   LastAttemptedLoginTime VARCHAR(25),
   locked BOOLEAN DEFAULT FALSE,
   AttemptedLoginCount INT DEFAULT 0,
   PRIMARY KEY (userNumber)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


CREATE INDEX user_email_index on users (emailAddress);



INSERT INTO users (loginName, userPassword,firstName,lastName,emailAddress,
userRole,lastLoginTime, loginCount, salt)
VALUES 
    # admin1234
   ('admin','1b22b95d03da1e7811dd0be2f8801e12a119a2c105f26a5bef18651f298e1121','System','Administrator','cjones@bloomu.edu','SystemAdmin','2015-02-13T20:25:25.596',0, 'me9ehohb79iobanooq0etdnc7q');

DROP TABLE IF EXISTS bu_users;
CREATE TABLE IF NOT EXISTS bu_users (
   userNumber INT UNIQUE DEFAULT 0,
   loginName VARCHAR(20) NOT NULL UNIQUE,
   firstName VARCHAR (35)DEFAULT '',
   lastName VARCHAR (35)DEFAULT '',
   emailAddress VARCHAR(60)DEFAULT '',
   displayName VARCHAR(60)DEFAULT '',
   userRole VARCHAR (30) NOT NULL,
   lastLoginTime VARCHAR (25),
   loginCount INT DEFAULT 0, 
   PRIMARY KEY (userNumber)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE INDEX bu_user_email_index on bu_users (emailAddress);

INSERT INTO bu_users ( loginName, firstName,lastName,emailAddress,displayName,
userRole,lastLoginTime, loginCount)
VALUES 
('cjones','Curt', 'Jones','cjones@bloomu.edu','Curt Jones','Chair','2018-02-25T20:25:25.596',0);

DROP TABLE IF EXISTS error_logs;
CREATE TABLE IF NOT EXISTS error_logs (
  `EVENT_ID` int(11) NOT NULL AUTO_INCREMENT,
  `EVENT_DATE` datetime DEFAULT NULL,
  `LEVEL` varchar(15) DEFAULT NULL,
  `LOGGER` varchar(45) DEFAULT NULL,
  `MSG` varchar(260) DEFAULT NULL,
  `THROWABLE` varchar(260) DEFAULT NULL,
  PRIMARY KEY (`EVENT_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS properties;
CREATE TABLE IF NOT EXISTS properties (
   propertyNumber INT NOT NULL UNIQUE AUTO_INCREMENT,
   propertyName VARCHAR(64) NOT NULL UNIQUE,
   propertyValue VARCHAR(128) NOT NULL,
   description VARCHAR (1024)DEFAULT '',
   previousValue VARCHAR(128),
   defaultValue VARCHAR(128),
   PRIMARY KEY (propertyNumber)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
