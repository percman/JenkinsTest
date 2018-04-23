What this program does
  -New users are free to pick any user name and password combination as long as the user name that they choose is not taken by another user or if user name doesn't match one of the user's that is pending approval of the admin
  -Existing users can deposit, withdraw, or view funds that they have in their account
  -The administrator can view and approve or reject the accounts of new users as well as lock or unlock existing users
  -If an existing user's account is locked then that user can not log in to their account. They will be prompted to contact the admin in the event that the account is locked.
  
How this program works
  -To execute the program, run the Application.java file
  -Both the admin and users are different object that inherit from the abstract account class. This allows both types of objects to be deserialized using the same deserialization method.
  -New and existing users are kept in a serialized UserList object that keeps two different lists. A list of User objects for new accounts waiting for admin approval, called waiting, and a list of existing users called list. Note: The list of existing users is not a list of User objects, it is instead a list of user name Strings.
  -When the admin chooses to approve an account, that user object is serialized into it's own text file and is deleted from the waiting list. If the user is rejected then the user object is simply deleted from the list. When the admin is done iterating through the list of user objects, the UserList object is serialized to override it's previous state.
  -Whenever any change is made to an account, whether it be depositing, withdrawing, locking or unlocking the object is serialized to save it's state onto the it's txt file.
  
  IMPORTANT NOTE - You NEED the andy.txt file and the pendingUser.txt file in the resource folder. They hold the admin object and the UserList object. This program does not let you create new Admins or UserList objects. User name and password to the admin account is in the resource folder's README
