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


## 🧪 Proof of Concept: Cloud API Integration

As a step toward remote data storage, I built a standalone experimental client (`CloudVaultStorage`) to test sending data over the network using Java 11+ `java.net.http.HttpClient`.

### How It Works
1. **Payload Construction:** A JSON-formatted string representing vault data is constructed manually in Java.
    *  String dummyVaultJson = "{\"user\": \"david\", \"vault\": [{\"service\": \"github\", \"pass\": \"encrypted_hash_123\"}]}"; --> this is the string i sent over to JsonBin.io
       CloudStorage cloudStorage = new CloudStorage();
       cloudStorage.uploadVaultToCloud(dummyVaultJson);
2. **HTTP Communication:** Using `HttpClient`, the application constructs an `HttpRequest` with `POST` headers (`Content-Type: application/json` and `X-Master-Key`).
3. **Cloud Storage:** The payload is transmitted over HTTPS to the JSONBin.io REST API.
4. **Verification:** The API returns an HTTP `200 OK` status and a unique `binId` confirming the data was received and stored in the cloud.

*Note: This feature is currently a working proof-of-concept and has not yet been integrated into the main password manager workflow.*

## 🛠️ Developer Workflow & Automation (`Makefile`)

This project includes a `Makefile` to automate common development tasks, OS-level operations, and testing workflows.

> **Note:** A Makefile is purely a local developer tool—it runs on the OS level to chain terminal commands and will not affect the production build.

---

### 🚀 Available Commands

Run these shortcuts from the root of the project:

| Command | Action |
| :--- | :--- |
| `make help` | Lists all available targets and descriptions. |
| `make compile` | Compiles Java code cleanly using Maven. |
| `make test` | Executes the full JUnit test suite via Maven. |
| `make push m="your commit message"` | Runs tests first; if they pass, automatically stages, commits, and pushes code to GitLab. |
| `make clean` | Cleans up local build outputs and the target directory. |

---

### 🧠 Core Concepts & Cheatsheet

* **Why Make + Maven?** Maven handles Java compilation, dependencies, and unit tests. Make handles OS-level tasks (managing background processes, checking network ports, chaining multi-step workflows).
* **What is `.PHONY`?** Tells Make that targets (like `compile` or `clean`) are command names, preventing conflicts if a folder or file with the same name exists in the project root.
* **Tabs vs Spaces:** Makefiles strictly require true **Tab** indentations for execution blocks.
* **No File Extension:** The file must be named strictly `Makefile` (no `.txt` or `.md` extension).

---

### 📝 Sample `Makefile` Reference

```makefile
MVN = mvn

.PHONY: help compile test push clean

help:
	@echo "Available shortcuts:"
	@echo "  make compile            - Build Java source files"
	@echo "  make test               - Run unit tests"
	@echo "  make push m=\"msg\"       - Run tests, stage, commit, and push"
	@echo "  make clean              - Clean build directory"

compile:
	$(MVN) clean compile

test:
	$(MVN) test

# Safety Gate: Runs tests BEFORE allowing git commit/push
push:
	@if [ -z "$(m)" ]; then \
		echo "❌ Error: Missing commit message. Use: make push m=\"your message\""; \
		exit 1; \
	fi
	@echo "🧪 Running test suite..."
	$(MVN) test
	@echo "✅ Tests passed! Staging and pushing to remote..."
	git add .
	git commit -m "$(m)"
	git push

clean:
	$(MVN) clean

## Cloud Storage Backup Module (`CloudStorage`)

The `CloudStorage` class handles backing up local encrypted vault data to a remote cloud repository (JSONBin API) using Java’s native HTTP Client.

### Architectural Highlights & OOP Patterns

1. **Dependency Injection & Constructor Chaining**
   - Implements constructor overloading using `this(...)` chaining. 
   - **Production Constructor:** Uses environment variables (`System.getenv("JSONBIN_KEY")`) and defaults to standard `HttpClient.newHttpClient()`.
   - **Test Constructor:** Accepts custom or mocked `HttpClient` and `apiKey` dependencies to allow isolated testing without hitting real network endpoints.

2. **Abstraction & Single Responsibility Principle (SRP)**
   - Exposes a clean, single high-level interface method: `uploadVaultToCloud(String binId, String jsonVaultData)`.
   - Encapsulates complex request construction, header mapping, network transmission, and response parsing behind `private` helper methods (`buildPutRequest`, `sendRequest`, `logResponse`).

3. **Guard Clause Validation**
   - Validates API credentials early (`isApiKeyValid()`) before attempting network overhead, failing gracefully if keys are missing.

4. **Unit Testing Strategy**
   - Fully unit-tested using **JUnit 5** and **Mockito 5**.
   - Mocks network socket execution (`HttpClient.send(...)`) and HTTP responses (`HttpResponse<String>`) to verify 200 OK success paths, 401 Unauthorized handling, missing API keys, and network exception recovery.