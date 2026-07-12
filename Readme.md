# Welcome to the Password Manager Program
## purpose of this program
this program is meant to help me understand the design how when creating a system from scratch, im currently reading about encrypption and hasing and decided it would be a good learning example to create a program that uses hashing to store passwords into a hashmap

# Planning questions:
**The single Responsibility Principle(SRP)**
* Q --> what is the one job of this class/ component?
* if you try and put the database, the password hashing logic and the user input all inside your Main.java class, it becomes a messy GOD class.

**To figure out what goes where, split your system based on roles:**
* The blueprint(Model): "what entity am i holding data for ?" --> a user holds user data. It shouldnt care how it gets hashed or where it gets stored.
* The worker(utility / service): "what utility calculation do I need to perform over and over ?" --> hasing data, validating the passowrd. Thats a toool, so its gets its own PasswordHasher class + PasswordValidator class.
* The boss(manager/ controller): "Who coordinates the whole operation?" --> userManager. It talks to the database, calls the hasher and makes decisions, e.g main.java.

**Trace the data life cycle?**
* Q --> "what data enters the system, how does it transform, and where does it end up?"
* grab a piece of paper and draw a map --> input - to - output pipeline before typing a single line of code.
* * input: plain text string("secret123")
* * transformation: plain text + SALT + SHA-256 algorithm --> scrambled HEX string
* * storage: Map/database string
  * * when you map the data transformation visually, your class and method signitres naturall write themselves.
  * * if input == (String password, String SALT) -- output == String Hash then your method signiture in java should be:
    * public String hashPassword(String password, String SALT).

**Think in contracts(interfaces and public methods)**
* Q --> "if i were another developer using my code, what simple methods would i expect this system to give me ?"
* * don't worry about how the code inside the system works yet. just define the menu of what your system can do.
* * for a authentication system your class would only contain:
    * boolean register(String username, String password)
    * boolean login(String username, String password)
* once you define method names and return types, youve built the base blueprint. all thats left is the body.

**The edge cases and failure questions**
* Q --> "how can the user break this? What happens when things go wrong?"
* professional engineering isnt just making the happy path work, its handeling breakages gracefully. 
*  * what if the user regitsers a existing user? 
*  * what if someone submits a empty string?
*  * what if the username is not in the map when they try and login again


