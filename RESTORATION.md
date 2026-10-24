# SuperHeroSighting — 2019 restoration notes

This branch preserves the original `master` branch. The most complete application appears to be in `heroSightings 2/`. This is an archival Java 8 / Spring Boot 2.1.9 application, not a production-ready deployment.

## Before running

1. Make a backup of any existing MySQL database. The historical root `SuperHeroSighting.sql` begins with `DROP DATABASE IF EXISTS SuperSightings`, so do not run it against valuable data.
2. The historic `application.properties` contains hardcoded database credentials. **Do not reuse these credentials.** Rotate any password that may still be active. Old Git commits retain secrets even after file edits.
3. Use a dedicated local MySQL user, not the `root` database account. Keep credentials in an untracked local configuration or environment variables. Do not publish secrets.
4. Use Java 8 (or a compatible toolchain confirmed by a build) and Maven. Run Maven commands from `heroSightings 2/`, not the repository root.

## Known problems identified during source review

- `SightingDAOImpl.getSightingById` used an `id` column that does not exist in the provided SQL schema. This restoration branch changes it to `SightingsID`.
- `LocationDaoDB.addLocation` inserts only a name and address, but the root SQL schema requires non-null `Latitude` and `Longitude`. The model and form have no coordinate fields. A considered schema/model change is needed before location creation can work reliably.
- The repository contains multiple historical copies of the application and committed build artifacts. The most complete source appears in `heroSightings 2/`.
- The old dependency versions need compatibility and security assessment before any internet-facing deployment.

## Suggested local verification

After preparing a fresh, disposable database and secure local application configuration:

```sh
cd "heroSightings 2"
java -version
./mvnw -version
./mvnw test
./mvnw spring-boot:run
```

The historical Maven report records one successful Spring Boot test, but this restoration branch has **not yet been compiled or run**. Do not interpret that old report as current verification.

## Next steps

- Prepare a safe, non-destructive schema fixture for testing.
- Resolve the Location coordinate mismatch.
- Verify app startup and CRUD routes against a local MySQL database.
- Add fresh automated tests, screenshots, and a portfolio-focused README.
