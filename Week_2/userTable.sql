CREATE TABLE userTable(
userName VARCHAR(60),
balance INT,
isAdmin INT,
isLocked INT,
isApproved INT,
CONSTRAINT PK_USERNAME PRIMARY KEY (userName)
)