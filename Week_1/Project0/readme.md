Project 0 for Revature Java-Test-Automation Batch 1804
Author:  Andrew Ahn
Email:  andrewsrahn@gmail.com

project 0 requirements
======================================================================
1. console application
2. user creation
3. user login
4. 2 roles:
	a.  user account must be approved before logging
	b.  admin can approve/reject new accounts and can lock/unlock existing accounts
5. user must have a transaction feature
6. all data must be persisted in files: serialization
7. must implement logging

due on monday, april 23, 2018 

greeting script
----------------------------------------------------------------------------
do you want to login user, login administrator, create user, or create administrator? 
(lu/la/cu/ca):

login administrator script
----------------------------------------------------------------------------
logging in as an administrator
what is your username?:
what is your password?:

administrator not found. logging in...
administrator found but password incorrect. logging in...

administrator update script
----------------------------------------------------------------------------
administratorname welcome.  

do you want to update users or end? (u/e):
updating...
ending...

users on the system include
pending: user0, user1, user2
approved: user0, user1, user2
rejected: user0, user1, user2
locked: user0
unlocked: user1, user2

which username do you want to update?:

user not found; updating...

updating user0
approve, reject, lock, or unlock? (a/r/l/u):
user0 approved
user0 rejected
user0 locked
user0 unlocked

login user script
---------------------------------------------------------------------------
logging in as a user
what is your username?:
what is your password?:

user username not found. logging in....
username found but password incorrect. logging in...

user transaction script
---------------------------------------------------------------------------
username welcome.

do you want to enter transaction or end? (t/e):
transacting...
ending...

balance
add or subtract $20? (a/s):

adding $20
new balance

subtracting $20
new balance

create user script
---------------------------------------------------------------------------
creating a user
username?:
password?:

username already exists. creating new user...
username created. entering transaction...

create administrator script
---------------------------------------------------------------------------
creating an administrator
username?:
password?:

administrator already exists. creating new user...
administrator created. entering 