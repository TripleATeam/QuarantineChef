# Weekly Status Report
### Team Report
**Copy of Last Weeks Plans/Goals:**
- Finish the bulk of the coding
    - Each group (Frontend, Backend, Database) implement their separate components, prioritizing features for MVP
    - As a team, work on integration of these components.
- Get a workable product ready to field-test
- Make sure the website is running properly
- Re-evaluate if some features are impossible to implement
    - Discuss what features we will be implementing after the initial beta release
- Produce minimum viable product by May 12
- Begin documenting features/methods

**Progress/Issues:**
- Each group finished the coding on their separate component
- Worked on integration and debugging
- Re-evaluated some features that we were not able to implement for Beta release
    - Pushed these features to the full release, though we may need to reexamine these and determine if they will even be included at all
- Got minimum viable product ready (run and tested for userId = 0). 
__Core Functionality of MVP:__
    - Database: ability to store/retrieve Ingredient Pantry. 
    - Backend: recipe filtering by Key Ingredient and Expiration Date. 
    - Frontend: Basic UI with Pantry and ‘Generate Recipes’ Button
- Postponed some features to be done after Beta Release 
    - Google Sign-in
    - Only return recipes that can be made with ingredients in pantry (as of right now, returns recipes that require more ingredients than provided)
- Bugs/Issues
    - Maven dependencies were inconsistent, had to manually check them. After doing so, Maven stopped reacting poorly. Still some warnings, though.
    - User interaction with an empty Pantry in the database was wrong, quickly fixed.
    - Other minor bugsplats found by the test suites.

**Plans/Goals:**
- Begin creating and adding functionality:
    - Database: Create enums for Diet, Health, Cuisine and alter the size of those arrays. Make sure users can be easily removed from database and it can be properly cleaned.
    - Backend: Filtering by Diet, Health, and Cuisine (User’s Taste Profile), add filtering and sorting by number of ingredients missing
    - Frontend: adding authentication using Google sign in, filtering Buttons for Diet, Health, Cuisine
- Begin collecting user feedback on the MVP. Based on the feedback, our additional functionality might be changed. 
- Brainstorm if any key features are missing that should be necessary in a full release
- Begin creating documentation (Frontend, Backend, Database). Continue implementing each component, adding features we finalized after user feedback (Frontend, Backend, Database).
- Find out if we can improve the time of the Website loading




### Individual Contributions
**Michael Figgs:**
- Goals from last week: make progress implementing tests and functionality of backend. Keep documenting how to use what I make. I want to be able to parse a json response by May 8th
- Progress: Implemented a way to successfully interact with the Edamam api, implemented a parser to parse the json returned by Edamam and create simpler recipe objects for use in the backend, created tests for the parser/edamam interaction, created a strict filter for a list of recipes and a pantry, created tests for the strict filter, added javadoc comments to all my code. This weeks challenges: many issues with project dependencies, our original plan for passing data between components did not workout so we had to rethink that system
- Goals for coming week: Add a less strict filtering algorithm and combine it with the strict one so that we can have strictly doable recipes first, then next best recipes based on least missing ingredients.

**Kyle Johnson:**
- Goals from last week: Implement Frontend MVP for next week’s Beta release. This should include a bare-bones UI with a way to add ingredients to your pantry (with optional expiration dates), and a ‘generate recipes’ button.
- Progress: Got the frontend of our web app up and running and worked with Julia to integrate the frontend with the backend and database. 
- Goals for coming week: Add some additional functionality to the frontend, including a ‘reset pantry’ button, a message to let the user know that their pantry has been updated successfully. Make sure that the virtual pantry properly displays the items that a persistent user has in the database. Finish the logo and make the frontend more stylish. Beyond next week: Get the User Taste Profile operational.

**Julia Kalmykov:**
- Goals from last week: Set up the Neo4j, work on database getIngredientsByGroup, placeInDatabase methods. FrontEnd: work on Virtual Pantry component.
- Progress: Implemented GenerateRecipes frontend component to render recipes from backend. Implemented backend API layer by using SparkServer. Debugged and fixed small bugs in other layers and made sure everything works well together.
- Goals for coming week: Set up Frontend authentication using Google sign in, help Kyle to create Taste Profile. Implement new methods in Spark Server to enable new frontend experiences to communicate with the backend layer.

**Aleksa Milovanovic:**
- Goals from last week: Set up the Neo4j driver on java, along with learning how to properly use Maven. Write my assigned code and get it interfacing with the backend by next week. 
- Progress: Neo4j driver properly implemented, interfacing great with the rest of the code. Added correctly to pom.xml (Learned how to use Maven, could still use some work). Assigned code (and more) properly written and talked with backend on what they needed as we continued development. Very good progress.
- Goals for coming week: Learn how to run 1-click builds and 1-click tests with Maven. A stretch goal is to figure out how to make Integration Tests with Maven. Another goal is to figure out how to implement a bug tracker in CircleCI and run all the maven commands from CircleCI.

**Mitchell Wong:**
- Goals from last week: Create Java classes, fill out the entirety of every class with a “rough draft” approach. We will make sure the backend works like it is supposed to, as well as the API. Longer term goals include making sure the backend can handle multiple users.
- Progress: Backend did a large amount of work this week. I created the RecipeFilter class, and worked on the SparkServer class to provide recipes to the frontend via the server. Michael and I were able to confirm the API works, with some limitations that we did not expect. The backend can potentially handle multiple users, but the SparkServer.java currently only is handling one user.
- Goals for coming week: My goal this week is to work with Michael in developing code to handle multiple users. Another feature we would like to get done is handling a signed in and a non-signed in user. Challenges and longer term goals include confirmation that a user can actually cook the recipe with their ingredients, as well as sorting recipes by number of missing ingredients.
