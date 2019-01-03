# login-spring-boot-start

public static final String CREATE_TABLE_SQL =
"create table persistent_logins (
username varchar(64) not null, 
series varchar(64) primary key, 
token varchar(64) not null, 
last_used timestamp not null)";
   