# Weekly Status Report
### Team Report
**Copy of Last Weeks Plans/Goals:**
- Begin creating and adding functionality:
    - Database: Create enums for Diet, Health, Cuisine and alter the size of those arrays. Make sure users can be easily removed from database and it can be properly cleaned.
    - Backend: Filtering by Diet, Health, and Cuisine (User’s Taste Profile), add filtering and sorting by number of ingredients missing
    - Frontend: adding authentication using Google sign in, filtering Buttons for Diet, Health, Cuisine
- Begin collecting user feedback on the MVP. Based on the feedback, our additional functionality might be changed. 
- Brainstorm if any key features are missing that should be necessary in a full release
- Begin creating documentation (Frontend, Backend, Database). Continue implementing each component, adding features we finalized after user feedback (Frontend, Backend, Database).
- Find out if we can improve the time of the Website loading

**Progress/Issues:**
- Functionality
    - Database: Implemented new size of arrays, users can easily be removed from database, and sandbox can change their address/password very easily now.
    - Backend: Null expiration dates, for when users do not specify dates for their ingredients
    - Frontend: Added Filtering menu (still needs to be connected to backend for recipe search). Additional style changes.
- User feedback requires a lot of setup, so little was gathered. As soon as it’s properly placed on a website, this step should be easier.
- No new features as of yet
- Documentation has been added
- Testing has become more comprehensive
- Building and testing has become more streamlined using scripts and Maven
- Website loading time hasn’t improved yet
- Started doing more pull requests, helps with keeping the master branch clean


**Plans/Goals:**
- Implement newly added selectable filters
- Set up web server, not just localhost
- CircleCI-Maven integration
- Add taste profile information to database
- Finish adding Google sign in


### Individual Contributions
**Michael Figgs:**
- Goals from last week: Add a less strict filtering algorithm and combine it with the strict one so that we can have strictly doable recipes first, then next best recipes based on least missing ingredients.
- Progress: Fixed some bugs with the expiration date sorting, worked on developer documentation, added a combined filter system that I’m not very happy with so it is commented out until I can do it better.
- Goals for coming week: Implement combined sorting better and make it easier for front end to call. Collect new meal type information that the front end wants.

**Kyle Johnson:**
- Goals from last week: Add some additional functionality to the frontend, including a ‘reset pantry’ button, a message to let the user know that their pantry has been updated successfully. Make sure that the virtual pantry properly displays the items that a persistent user has in the database. Finish the logo and make the frontend more stylish. Beyond next week: Get the User Taste Profile operational.
- Progress: Worked with the group on the User Documentation and Developer Documentation. Secured a domain name for our website (www.projectquarantinechef.com). Continued implementation of Frontend including style changes and the addition of a Recipe Filtering menu.
- Goals for coming week: Incorporate Filters into the “Find Recipes” search and calls to the backend. Figure out logistics of serving our web server as a static website, and connect it to our acquired custom domain name.

**Julia Kalmykov:**
- Goals from last week: Set up Frontend authentication using Google sign in, help Kyle to create Taste Profile. Implement new methods in Spark Server to enable new frontend experiences to communicate with the backend layer.
- Progress: Added command line scripts to quick start application using Maven build, fixed missed dependencies; Google sign in is in progress
- Goals for coming week:  Finish Google sign in implementation. Implement new methods in Spark Server to enable new frontend experiences to communicate with the backend layer.

**Aleksa Milovanovic:**
- Goals from last week: Learn how to run 1-click builds and 1-click tests with Maven. A stretch goal is to figure out how to make Integration Tests with Maven. Another goal is to figure out how to implement a bug tracker in CircleCI and run all the maven commands from CircleCI.
- Progress: 1-click tests have been implemented, but integration testing doesn’t seem super easy to handle. Some Maven test drivers exist, but further research is necessary. We’ve implemented the bug tracker in a top-level .txt file, which seems fine for now - CircleCI doesn’t seem like it does bug tracking, and installing a new software just to hold bug reports is a little excessive in my opinion. Maven commands are fairly simple to run from CircleCI, just going to take a little more improvement on the current model to have it run those commands without a hitch. Also changed the database code to handle another feature in the UserProfile, which took a major code rehaul. 
- Goals for coming week: Implement the CircleCI-Maven integration, create the enums for the Taste Profile and store them somewhere for easy access, and have the pipelines run properly during building.

**Mitchell Wong:**
- Goals from last week: My goal this week is to work with Michael in developing code to handle multiple users. Another feature we would like to get done is handling a signed in and a non-signed in user. Challenges and longer term goals include confirmation that a user can actually cook the recipe with their ingredients, as well as sorting recipes by number of missing ingredients.
- Progress: Backend code should be able to handle multiple users using a user ID represented with an integer. Sorting algorithms have been improved as well as priority for expiring ingredients. 
- Goals for coming week: Backend goals for this week are to sort recipes by the number of missing ingredients, as well as finding out if we are able to confirm whether or not the user has the necessary ingredients to cook the recipe.
