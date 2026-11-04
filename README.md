# SuperHero Sightings

A Java web application for recording fictional superhero and villain sightings, developed as a Software Guild-era project. Browse recently reported sightings and manage characters, locations, organizations, powers, and sighting records through server-rendered pages.

## Features

- Home page showing the ten most recent sightings
- Character management (hero/villain status, name, description)
- Locations, organizations, and superpowers sections
- Sighting records linked to a character and a location
- Edit and delete flows for several record types

This is an older learning project being refactored for a technical portfolio. Some flows may require further repair; they have not all been re-verified on a fresh machine.

## Technology

- Java 8 target
- Spring Boot 2.1.9.RELEASE (legacy / unsupported)
- Spring MVC, Thymeleaf, Spring JDBC (`JdbcTemplate`)
- MySQL
- Maven Wrapper, Bootstrap 4

> **Maintenance note:** The dependencies are historical and should not be exposed to the public internet without a security upgrade and review. Java 8 is the project's original target, not a recommendation for new projects.

## Which folder is the application?

**`heroSightings 2/` is the active application.** The other folders and ZIP files are historical project copies retained temporarily for comparison. Run all Maven commands inside `heroSightings 2/`. The repository is being cleaned up without discarding potentially unique legacy files.

## Local setup

Requirements: JDK 8, MySQL, and a terminal. Maven itself is not required if the bundled wrapper works.

1. Clone the repository and enter the active project:

   ```bash
   git clone https://github.com/jkonline123/SuperHeroSighting.git
   cd SuperHeroSighting/"heroSightings 2"
   ```

2. Create a **disposable local** MySQL database using `../SuperHeroSighting.sql`. **Warning: the script begins with `DROP DATABASE IF EXISTS SuperSightings` and will destroy an existing database of that name. Review it before importing.** From the active application directory:

   ```bash
   mysql -u YOUR_MYSQL_USER -p < ../SuperHeroSighting.sql
   ```

3. Supply credentials as environment variables (do not commit passwords):

   ```bash
   export DB_USER="YOUR_MYSQL_USER"
   export DB_PASSWORD="YOUR_LOCAL_MYSQL_PASSWORD"
   ```

   Optionally set `DB_URL` to override the default local `SuperSightings` JDBC URL. Use a dedicated local database account with only the permissions the application needs.

4. Run from the active application directory:

   ```bash
   bash ./mvnw spring-boot:run
   ```

5. Open `http://localhost:8080/`.

### Testing

From `heroSightings 2/`:

```bash
bash ./mvnw test
```

The original project contains a Spring context-load test that requires a working database configuration. Tests and end-to-end page flows have not yet been verified against the refreshed repository.

## Architecture

```text
Browser / Thymeleaf templates
          |
Spring MVC Controllers
          |
DAO interfaces and JDBC implementations
          |
       MySQL
```

Key code locations:

- Controllers: `heroSightings 2/src/main/java/com/heros/heroSightings/Controllers/`
- DAOs: `heroSightings 2/src/main/java/com/heros/heroSightings/daos/`
- Domain objects: `heroSightings 2/src/main/java/com/heros/heroSightings/dtos/`
- Templates: `heroSightings 2/src/main/resources/templates/`
- Database schema: `SuperHeroSighting.sql`

## Portfolio restoration status

- [x] Identify the active project
- [x] Move active database settings to environment variables
- [x] Begin incremental controller/DAO refactoring
- [ ] Verify the full build and all CRUD paths on a fresh environment
- [ ] Move the active Maven project to a clean repository root
- [ ] Remove archived duplicates and generated binaries after reviewing differences
- [x] Add focused character-controller unit tests (not yet executed in a local build)
- [ ] Add verified application screenshots and database-backed integration tests
- [ ] Evaluate a maintained Spring Boot upgrade separately

## Security and project history

An earlier revision of this repository contained hardcoded database credentials. Rotate any matching live password; removing a value from the current files does not erase it from Git history or archived ZIP files. Do not reuse those credentials.

This is a portfolio restoration of a collaborative/bootcamp-era project. Existing author credits and Git history are preserved.
