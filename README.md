# BookScrabble

A JavaFX Scrabble game with a View/ViewModel/Model structure and a book-backed
word dictionary. The repository includes the desktop UI, host/server code, and
sample dictionary books.

## Getting started

- Install a full **JDK 17** and make sure `JAVA_HOME` points to it. The application
  sources target Java 11; the compile command below was checked with JDK 17.
- Use a desktop session to run the JavaFX UI.
- An internet connection is needed on the first build to download Maven and
  dependencies. Maven is supplied by the checked-in wrapper; no separate Maven
  installation is required.

Run commands from the repository root so relative resource paths remain valid:

```sh
# Confirm the wrapper is using the intended JDK.
./mvnw --version

# Compile the application without compiling or running the legacy tests.
./mvnw -DskipTests compile

# Launch the desktop UI (configured entry point: com.Seals.scrabble.Main).
./mvnw javafx:run
```

On Windows, replace `./mvnw` with `.\mvnw.cmd` in PowerShell. If Java cannot be
found, check `JAVA_HOME` and `java -version` before retrying. JavaFX dependencies
are declared in `pom.xml`.

## Tests and current limitations

```sh
./mvnw test
```

The existing test suite currently fails during **test compilation**, before any
tests run: it imports TestNG without declaring that dependency and references
methods no longer present in the application (including `setGuestModel`,
`getGameServer`, and `placeWord`). A JDK 17 baseline run reported 16 compiler
errors. Successful application compilation is not a passing test suite.
The desktop launch command is provided for local use; GUI startup has not been
verified by the automated documentation check.

## Repository layout

- `src/main/java/com/Seals/scrabble/`: application, controllers, view models,
  game logic, and socket/dictionary services.
- `src/main/resources/com/Seals/scrabble/`: FXML views and application CSS.
- `src/test/java/`: legacy tests.
- `search_folder/` and `textFile/`: dictionary books and text fixtures.
- `pom.xml`: JavaFX dependencies, Java compilation settings, and launcher.

## Contributors
- this project is a game made with JavaFX and was made by:
  * Nir Tuttnauer
  * Aviv Yosef Nataf
  * Omar Yaakov
 
<br/>
 
 # Devision to groups 
 - View
   * Aviv
   * Omer
 - ViewModel
   * Aviv
   * Omer
 - Model
   * Nir
 - Server
   * Nir
 - Devops
   * Nir
   * Omer
 
<!-- # Devision to groups 
- Omer and Aviv- View, ViewModel
- Nir - Model
<br/> -->

# Projects Video
https://github.com/nirtuttnauer/Scrabble-PTM2/assets/21014429/9a187efb-f73a-4136-bfc9-27bfe0c2a663


# Project UML Diagram
<img src = "README resources/Current UML Diagram.png">


# Jira
<img width="852" alt="Screenshot 2023-05-01 at 16 59 22" src="https://user-images.githubusercontent.com/21014429/235462653-f4b79988-9d3f-470b-9f6e-b76310c41b5d.png">
<br/>

# GAANT
<img alt = "screen shot GAANT" src = "README resources/GANTT.png">
<br/>
<img alt ="updated GAANT" src = "https://github.com/nirtuttnauer/Scrabble-PTM2/assets/21014429/eac89ff6-ffe0-412c-9eb8-8ec34731dad5">

<!-- # Figma
- https://www.figma.com/community/file/1234863428671713218
<br/>
 -->
