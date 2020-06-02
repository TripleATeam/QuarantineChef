# Weekly Status Report
### Team Report
**Copy of Last Weeks Plans/Goals:**
- Finish our planned features for the web app and decide if we want to implement any stretch features.
- Get the web app live on the web as a static website.
- Figure out how to pass the taste profile data between the front end and the back end, or if the taste profile needs to be received from the database.
- Refactor some hard coded values and restructure.

**Progress/Issues:**
- Got the web app deployed on Google App Engine.
- Linked the google app to our custom domain name.
- Improved performance by making the pantry upload from DB only when a particular ingredient category is opened by the user.
- Took a final look at the features that we were able to complete for the final release and finished the implementation of those features, removing partial implementations of features that we weren’t able to complete.
- Had issues with filtering with the Edamam API that were not anticipating. Some of the filtering options were not available on the free version of the API, which we hadn’t realized until we got to implementing them, so we had to limit our filtering options as a result.


### Individual Contributions
**Michael Figgs:**
- Goals from last week: Work on making the filter look through more than just the default 10 recipes so that there is a higher likelihood of finding a recipe that the user has everything for.
- Progress: Made the find recipes sort 50 recipes instead of just 10, helped debug issues in with getting the website running.

**Kyle Johnson:**
- Goals from last week: Get the web app functional as a static website.
- Progress: Got the web app deployed on Google App Engine and linked it to our custom domain name, https://www.projectquarantinechef.com. Did a lot of trouble shooting with the backend and database to debug the deployment, as there were some errors that weren’t caught running locally that we encountered in the deployment.

**Julia Kalmykov:**
- Goals from last week: Add a window that pops up when the user tries to get the recipe without any ingredients in the virtual pantry. Change how a temporary pantry works (no need to store the data to the database).
- Progress: We got rid of the temporary user so there was no need to implement a temporary pantry. Worked on frontend: resolved ingredient checkboxes issue, disabled the checkboxes for filters that are not supported by the free Edamam version, hid the pantry and find recipe components for not-signed in user (those components appear when user signs in), improved performance by making the pantry upload only when particular ingredient category is opened by user.

**Aleksa Milovanovic:**
- Goals from last week: Work on googleID-userID matching. See how the googleID would be best used in regards to userID and how best to get userIDs for future users (incrementing [probably not], or random [probably]).
- Progress: Finished googleID-userID matching, with random generation of userIDs. If any googleUserID already is matched to a userID, returns that - else generates a new **random** id that isn’t associated with any other googleUserID. Lot of debugging for the final release.

**Mitchell Wong:**
- Goals from last week: The goal for this week is to integrate this taste profile feature with the front end, and also do a large amount of testing to make sure that backend won’t crash and is able to handle edge cases.
- Progress: Finished implementing taste profile in the backend. Is able to handle the input from the frontend and directly gets translated into the backend’s filtering system. Fixed a few bugs in the backend with some initial checks.
