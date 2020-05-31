# Weekly Status Report
### Team Report
**Copy of Last Weeks Plans/Goals:**
- Implement newly added selectable filters
- Set up web server, not just localhost
- CircleCI-Maven integration
- Add taste profile information to database
- Finish adding Google sign in

**Progress/Issues:**
- Got sign in functionality working and pulled the working version into our main branch.
- Encountered a few issues from an earlier merge that we hadn’t noticed, and were brought to our attention by the group reviewing our project, and got those squared away.
- Continued working on integrating Filtering/Taste Profile with a signed in user.
- Still working on issues with the pantries for non-signed-in users.
- Taste profile support for the backend has been finished.

**Plans/Goals:**
- Finish our planned features for the web server and decide if we want to implement any stretch features.
- Get the web server live on the web as a static website.
- Figure out how to pass the taste profile data between the front end and the back end, or if the taste profile needs to be received from the database.
- Refactor some hard coded values and restructure.

### Individual Contributions
**Michael Figgs:**
- Goals from last week: Implement combined sorting better and make it easier for front end to call. Collect new meal type information that the front end wants.
- Progress: Added a sortByMissing to the filter and made the current method that frontend calls call it so they didn't have to change anything.
- Goals for coming week: Work on making the filter look through more than just the default 10 recipes so that there is a higher likelihood of finding a recipe that the user has everything for.

**Kyle Johnson:**
- Goals from last week: Incorporate Filters into the “Find Recipes” search and calls to the backend. Figure out logistics of serving our web server as a static website, and connect it to our acquired custom domain name.
- Progress: Did some research on serving our web server as a static website, but haven’t done the implementation yet. I’m still unclear on a few things needed to do this. I tested how to serve a simple website with no special dependencies on Github pages and how to forward our custom domain name to the Github pages site.
- Goals for coming week: Get the web server functional as a static website.

**Julia Kalmykov:**
- Goals from last week: Finish Google sign in implementation. Implement new methods in Spark Server to enable new frontend experiences to communicate with the backend layer.
- Progress: Finished with Google sign in implementation. Implemented methods in Spark Server to enable new frontend features to communicate with the backend layer.
- Goals for coming week: Add a window that pops up when the user tries to get the recipe without any ingredients in the virtual pantry. Change how a temporary pantry works (no need to store the data to the database).

**Aleksa Milovanovic:**
- Goals from last week: Implement the CircleCI-Maven integration, create the enums for the Taste Profile and store them somewhere for easy access, and have the pipelines run properly during building.
- Progress: Pipelines working, but didn’t make too much progress otherwise. Made sandbox changing easier, replaced some hard-coded values with a class constant. 
- Goals for coming week: Work on googleID-userID matching. See how the googleID would be best used in regards to userID and how best to get userIDs for future users (incrementing [probably not], or random [probably]).

**Mitchell Wong:**
- Goals from last week: Backend goals for this week are to sort recipes by the number of missing ingredients, as well as finding out if we are able to confirm whether or not the user has the necessary ingredients to cook the recipe.
- Progress: This week I wrote code for backend support of the taste profile. Now, the RecipeFilter class can handle input for diet, health, cuisineType, and mealType. The challenge ahead is making sure the data for these user preferences comes in a form that can be used to filter recipes.
- Goals for coming week: The goal for this week is to integrate this taste profile feature with the front end, and also do a large amount of testing to make sure that backend won’t crash and is able to handle edge cases.
