# Weekly Status Report
### Team Report
**Copy of Last Weeks Plans/Goals:**
- Create a more comprehensive plan for testing and continuous integration for the project.
- Each component's team should create an initial draft of their implementation. It doesn’t need to have all of the functionality but should at least have a sketch of the classes we decided on in the software architecture/design of the components.
- Beyond next week: Produce minimum viable product by May 12.

**Progress/Issues:**
- Implemented a CI service into the Github repository, laid out a plan for who does what in the project, began coding, added build tools.
- Had trouble determining what a CI service is used for and how to implement one. Was very difficult and would’ve liked to see more talk in lecture on this topic. Were stuck for a bit on this. Maven is another thing that was difficult to figure out, but we’re slowly understanding and working past this. We learned what a CI is useful for, and how good teams use them in their projects.

**Plans/Goals:**
- Finish the bulk of the coding
    - Each group (Frontend, Backend, Database) implement their separate components, prioritizing features for MVP
    - As a team, work on integration of these components.
- Get a workable product ready to field-test
- Make sure the website is running properly
- Re-evaluate if some features are impossible to implement
    - Discuss what features we will be implementing after the initial beta release
- Produce minimum viable product by May 12
- Begin documenting features/methods


### Individual Contributions
**Michael Figgs:**
- Goals from last week: Transfer design plan into a more permanent spec, make skeleton/stubs for all classes/methods, and start writing tests for the json parser.
- Progress: Worked on updating requirements document. Researched and helped decide what CI we will use. Started to get familiar with the git repository and started to add class files and method stubs. Continued to work with Mitchell to figure out how our methods and tests should work. Problems encountered: trying to get circleCI to work with our project structure.
- Goals for coming week: make progress implementing tests and functionality of backend. Keep documenting how to use what I make. I want to be able to parse a json response by May 8th

**Kyle Johnson:**
- Goals from last week: Flesh out a concrete testing plan for the frontend of the website. Begin initial implementation of frontend UI, at minimum get HTML Layout and CSS Style going, and re-familiarize myself with React framework.
- Progress: Solidified my choice in testing infrastructure for Frontend. Researched CI Services and worked with the team to solidify our choice in CI Service for this project. Made adjustments to the project schedule with Kaushal’s feedback in mind. Fixed formatting issues and added Table of Contents to our working document.
- Goals for coming week: Implement Frontend MVP for next week’s Beta release. This should include a bare-bones UI with a way to add ingredients to your pantry (with optional expiration dates), and a ‘generate recipes’ button.

**Julia Kalmykov:**
- Goals from last week: Familiarize myself  with neo4j as well as refresh React skills. Start setting up the database and front end.
- Progress: Started familiarizing myself with neo4j as well as refreshing React skills.  Worked on exploring different CI services pros and cons, worked on the testing strategy section, helped the group brainstorm other sections from the requirements document. 
- Goals for coming week: Set up the Neo4j, work on database getIngredientsByGroup, placeInDatabase methods. FrontEnd: work on Virtual Pantry component.

**Aleksa Milovanovic:**
- Goals from last week: I hope to make some progress on setting up a database on neo4j (hopefully get it all set up), as well as create the classes/function stubs that need to exist for interfacing with the database. By May 3rd, I hope to look over our design and see if there’s anything that needs to be reevaluated before we really start implementing the design of the component.
- Progress: Spun up the database, got started on writing code for the project (set up the stubs), set up the build tools (Maven) and worked on getting CI service (CircleCI) set up in Github. Also discovered a driver for Java/Neo4j integration. Reevaluated design and changed a few small details, but overall it looks good. Worked on learning CI.
- Goals for coming week: Set up the Neo4j driver on java, along with learning how to properly use Maven. Write my assigned code and get it interfacing with the backend by next week. 

**Mitchell Wong:**
- Goals from last week: Finalize Java classes by creating method stubs. Create initial testing methods for the backend to be included in both testing classes. Longer term goals include figuring out to get data from the API and implementing the parsing/filtering.
- Progress: This week the backend team finalized the testing plan for filtering and parsing the recipes and data. There will be two classes, RecipeFilterTest and RecipeParserTest classes to perform unit testing on the methods included in RecipeFilter and RecipeParser. Problems encountered this week include making sure circleCI properly works to automatically test the backend code.
- Goals for coming week: Create Java classes, fill out the entirety of every class with a “rough draft” approach. We will make sure the backend works like it is supposed to, as well as the API. Longer term goals include making sure the backend can handle multiple users.
