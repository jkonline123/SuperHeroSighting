# SuperHero Sightings

A server-rendered Java application for tracking fictional superhero and villain sightings. The app connects characters, reported sightings, and locations; it also offers management screens for organizations and superpowers.

**Portfolio restoration:** This project originated as a Software Guild coursework/team project. This repository preserves its original source and authorship while documenting the work needed to make it easier to run and review.

## What it does

- Displays the ten most recent reported sightings on the home page
- Manages heroes and villains, their descriptions, and classifications
- Provides separate pages for locations, organizations, and superpowers
- Adds, edits, and deletes sighting records linking characters to locations
- Persists information in MySQL through Spring's `JdbcTemplate` and DAO interfaces

## Stack

| Layer | Technology |
| --- | --- |
| Language | Java 8 |
| Framework | Spring Boot 2.1.9, Spring MVC |
| Views | Thymeleaf, Bootstrap 4 |
| Data access | Spring JDBC, DAO pattern |
| Database | MySQL |
| Build | Maven 3.6.3 via Maven Wrapper |
| Testing | JUnit 4, Mockito |

Spring Boot 2.1.9 and Bootstrap 4 are **legacy dependencies**. This is a learning/portfolio project, **not a production-hardened application**.

## Project structure

```text
.
├── pom.xml
├── mvnw / mvnw.cmd
├── .mvn/wrapper/
├── sql/
│   └── schema.sql
└── src/
    ├── main/
    │   ├── java/com/heros/heroSightings/
    │   │   ├── Controllers/
    │   │   ├── Services/
    │   │   ├── daos/
    │   │   └── dtos/
    │   └── resources/
    │       ├── templates/
    │       └── application.properties
    └── test/java/
```

The old repository contained several archived application copies and generated `target/` outputs. Those files were removed **from the current branch**, while remaining recoverable from Git history.

## Run locally

**Prerequisites:** JDK 8 and a local MySQL server. Start in the project root.

1. Clone the repository:

   ```bash
   git clone https://github.com/jkonline123/SuperHeroSighting.git
   cd SuperHeroSighting
   ```

   Until these changes are merged, switch to the working branch:

   ```bash
   git switch improve/portfolio-ready
   ```

2. On macOS, select JDK 8 when necessary:

   ```bash
   export JAVA_HOME=$(/usr/libexec/java_home -v 1.8)
   export PATH="$JAVA_HOME/bin:$PATH"
   java -version
   ```

3. Create the database schema on a local development instance:

   ```bash
   mysql -u YOUR_MYSQL_USER -p < sql/schema.sql
   ```

   The schema uses `CREATE DATABASE IF NOT EXISTS` and does **not** drop the database. It is intended for a **fresh local database**. It does not migrate an already existing schema.

4. Supply **local** connection credentials as environment variables:

   ```bash
   export DB_USER="YOUR_MYSQL_USER"
   export DB_PASSWORD="YOUR_LOCAL_MYSQL_PASSWORD"
   # Optionally override DB_URL to point at a different MySQL instance.
   ```

5. Run the application:

   ```bash
   bash ./mvnw spring-boot:run
   ```

   Open [http://localhost:8080](http://localhost:8080).

**Note:** The Maven Wrapper will download its files on first use. Never commit database credentials. If the existing local MySQL schema predates the refactored `sql/schema.sql`, compare the columns before using create/edit forms; the original schema was inconsistent with some DAO queries.

## Testing

Run isolated controller unit tests (no MySQL needed):

```bash
bash ./mvnw -Dtest=CharacterControllerTest test
```

For the original Spring context-load test, configure a working local MySQL database and run:

```bash
bash ./mvnw test
```

Automated checks are configured to run the isolated controller tests. **End-to-end behavior and the full database-backed suite still need independent verification.**

## Architecture

```text
Browser
   ↓
Thymeleaf templates / Spring MVC controllers
   ↓
DAO interfaces
   ↓
JdbcTemplate implementations
   ↓
MySQL
```

The refactor introduces explicit constructor injection and changes delete actions from GET links to POST forms. The app still lacks user authentication, authorization, and a modern security review: do not deploy it publicly as-is.

## Screenshots

Screenshots will be added after verifying the application in a fresh local environment. No unverified or simulated screenshots are presented as genuine.

## Work in progress

- [x] Restore the intended application into a single Maven project
- [x] Remove committed build output and redundant project copies from the current tree
- [x] Move database configuration to environment variables
- [x] Standardize constructor injection in controllers
- [x] Convert destructive links into POST forms
- [x] Add focused JUnit/Mockito controller tests
- [x] Add a CI test workflow
- [ ] Verify CI and a full local build with a database
- [ ] Add real application screenshots
- [ ] Modernize dependencies and address authorization/CSRF protection before any public deployment

### Security note

Older revisions, ZIPs, and compiled assets committed in Git history may contain previously hardcoded MySQL credentials. The current source removes those values; **that does not erase historical secrets**. Rotate any password that is still active.

## Credits

Based on the original Software Guild-era project. Existing source-code author attributions and Git history are preserved.
