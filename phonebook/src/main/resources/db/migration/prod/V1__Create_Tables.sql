CREATE TABLE IF NOT EXISTS `address`
(
	`id`            int         NOT NULL AUTO_INCREMENT,
	`address_type`  enum ('HOME','OTHER','WORK') DEFAULT NULL,
	`country_name`  varchar(40)                  DEFAULT NULL,
	`city_name`     varchar(40) NOT NULL,
	`country_state` varchar(40)                  DEFAULT NULL,
	`complement`    varchar(30)                  DEFAULT NULL,
	`district`      varchar(20)                  DEFAULT NULL,
	`number`        varchar(6)  NOT NULL,
	`postal_code`   varchar(15)                  DEFAULT NULL,
	`street`        varchar(30) NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `email`
(
	`id`             int         NOT NULL AUTO_INCREMENT,
	`email_domain`   varchar(30) NOT NULL,
	`email_type`     enum ('OTHER','PERSONAL','WORK') DEFAULT NULL,
	`email_username` varchar(30) NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `instant_messenger`
(
	`id`          int                                                                                                                            NOT NULL AUTO_INCREMENT,
	`im_type`     enum ('AIM','DISCORD','FACEBOOK','HANGOUTS','ICQ','INSTAGRAM','JABBER','OTHER','QQ','SKYPE','TELEGRAM','WINDOWS_LIVE','YAHOO') NOT NULL,
	`im_username` varchar(20)                                                                                                                    NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `telephone`
(
	`id`             int         NOT NULL AUTO_INCREMENT,
	`area_code`      varchar(4)                                                                                                                                                                          DEFAULT NULL,
	`country_code`   varchar(3)                                                                                                                                                                          DEFAULT NULL,
	`number`         varchar(20) NOT NULL,
	`telephone_type` enum ('CALLBACK','CAR','COMPANY_MAIN','HOME','HOME_FAX','ISDN','MAIN','MOBILE', 'OTHER','OTHER_FAX','PAGER','RADIO','TELEX','TTY_TDD','WORK','WORK_FAX','WORK_MOBILE','WORK_PAGER') DEFAULT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `contact`
(
	`id`                   char(36)    NOT NULL,
	`company`              varchar(20)                                                                                                                                                        DEFAULT NULL,
	`date`                 datetime(6)                                                                                                                                                        DEFAULT NULL,
	`date_type`            enum ('ANNIVERSARY','BIRTHDAY','OTHER')                                                                                                                            DEFAULT NULL,
	`first_name`           varchar(50) NOT NULL,
	`last_name`            varchar(50)                                                                                                                                                        DEFAULT NULL,
	`middle_name`          varchar(50)                                                                                                                                                        DEFAULT NULL,
	`nickname`             varchar(50)                                                                                                                                                        DEFAULT NULL,
	`phonetic_first_name`  varchar(50)                                                                                                                                                        DEFAULT NULL,
	`phonetic_last_name`   varchar(50)                                                                                                                                                        DEFAULT NULL,
	`phonetic_middle_name` varchar(50)                                                                                                                                                        DEFAULT NULL,
	`note`                 varchar(255)                                                                                                                                                       DEFAULT NULL,
	`relationship`         enum ('ASSISTANT','BROTHER','CHILD','DOMESTIC_PARTNER','FATHER','FRIEND','MANAGER','MOTHER','OTHER','PARENT','PARTNER','REFERRED_BY','RELATIVE','SISTER','SPOUSE') DEFAULT NULL,
	`title`                varchar(15)                                                                                                                                                        DEFAULT NULL,
	`website`              varchar(50)                                                                                                                                                        DEFAULT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `contact_address`
(
	`contact_id` char(36) NOT NULL,
	`address_id` int      NOT NULL,
	PRIMARY KEY (`contact_id`, `address_id`),
	KEY `FKa63wvjlxiwgo0098siqj9kjav` (`address_id`),
	CONSTRAINT `FKa63wvjlxiwgo0098siqj9kjav` FOREIGN KEY (`address_id`) REFERENCES `address` (`id`),
	CONSTRAINT `FKqqxykpjj1qrgxle7cpp0txicc` FOREIGN KEY (`contact_id`) REFERENCES `contact` (`id`)
);

CREATE TABLE IF NOT EXISTS `contact_email`
(
	`contact_id` char(36) NOT NULL,
	`email_id`   int      NOT NULL,
	PRIMARY KEY (`contact_id`, `email_id`),
	KEY `FK2wlgsyv59totqq1ghc75yvwmc` (`email_id`),
	CONSTRAINT `FK2wlgsyv59totqq1ghc75yvwmc` FOREIGN KEY (`email_id`) REFERENCES `email` (`id`),
	CONSTRAINT `FKjhb6oolv2p95xsci34vuoiq00` FOREIGN KEY (`contact_id`) REFERENCES `contact` (`id`)
);

CREATE TABLE IF NOT EXISTS `contact_im`
(
	`contact_id` char(36) NOT NULL,
	`im_id`      int      NOT NULL,
	PRIMARY KEY (`contact_id`, `im_id`),
	KEY `FKj46oq6ju6r14qk6kv0jxgo06d` (`im_id`),
	CONSTRAINT `FKauacpkvx9ie06frrcspfhmoyv` FOREIGN KEY (`contact_id`) REFERENCES `contact` (`id`),
	CONSTRAINT `FKj46oq6ju6r14qk6kv0jxgo06d` FOREIGN KEY (`im_id`) REFERENCES `instant_messenger` (`id`)
);

CREATE TABLE IF NOT EXISTS `contact_telephone`
(
	`contact_id`   char(36) NOT NULL,
	`telephone_id` int      NOT NULL,
	PRIMARY KEY (`contact_id`, `telephone_id`),
	KEY `FK921in21ry2gn6rqgpevd66lcj` (`telephone_id`),
	CONSTRAINT `FK921in21ry2gn6rqgpevd66lcj` FOREIGN KEY (`telephone_id`) REFERENCES `telephone` (`id`),
	CONSTRAINT `FKt9ftx0p2u2601fnmsmqdm849v` FOREIGN KEY (`contact_id`) REFERENCES `contact` (`id`)
);
