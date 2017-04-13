/* truncate the application table */
SET FOREIGN_KEY_CHECKS = 0; 
TRUNCATE `oauth`.`application`; 
SET FOREIGN_KEY_CHECKS = 1;

INSERT INTO `oauth`.`application` (`name`, `client_id`, `client_secret`, `date_created`, `redirect_url`) VALUES ('JasonApplication', '6wCRM7NQGumjSMZ1AlnpEQBJxfUa', '1NsceIHllowQ0ZqBOBna5ZAQqNka', now(), 'http://127.0.0.1:8080/oauth2callback');


/* truncate the scope table */
SET FOREIGN_KEY_CHECKS = 0; 
TRUNCATE `oauth`.`scope`; 
SET FOREIGN_KEY_CHECKS = 1;

INSERT INTO `oauth`.`scope` (`name`, `value`) VALUES ('DEFAULT_SCOPE', 'default');
INSERT INTO `oauth`.`scope` (`name`, `value`) VALUES ('USER_READ_SCOPE', 'user_read');
INSERT INTO `oauth`.`scope` (`name`, `value`) VALUES ('USER_WRITE_SCOPE', 'user_write');

/* truncate the api table */
SET FOREIGN_KEY_CHECKS = 0; 
TRUNCATE `oauth`.`api`; 
SET FOREIGN_KEY_CHECKS = 1;

INSERT INTO `oauth`.`api` (`context`, `name`, `version`, `endpoint`) VALUES ('/user/info/v1', 'UserAPI', 'v1', 'http://127.0.0.1:8989/user/info/v1');

/* truncate the api resource table */
SET FOREIGN_KEY_CHECKS = 0; 
TRUNCATE `oauth`.`api_resource`; 
SET FOREIGN_KEY_CHECKS = 1;

INSERT INTO `oauth`.`api_resource` (`url_pattern`, `method`, `api_id`) VALUES ('/*', 'GET', 1);
INSERT INTO `oauth`.`api_resource` (`url_pattern`, `method`, `api_id`) VALUES ('/*', 'POST', 1);

/* truncate the api resource scope table */
SET FOREIGN_KEY_CHECKS = 0; 
TRUNCATE `oauth`.`api_resource_scope`; 
SET FOREIGN_KEY_CHECKS = 1;

INSERT INTO `oauth`.`api_resource_scope` (`api_resource_id`, `scope_id`) VALUES (1, 2);

/* truncate the token table */
SET FOREIGN_KEY_CHECKS = 0; 
TRUNCATE `oauth`.`token`; 
SET FOREIGN_KEY_CHECKS = 1;

/* truncate the token scope table */
SET FOREIGN_KEY_CHECKS = 0; 
TRUNCATE `oauth`.`token_scope`; 
SET FOREIGN_KEY_CHECKS = 1;