# Weekly Status Report
### Team Report
**Copy of Last Weeks Plans/Goals:**
- Talk to Kaushal to see if our project has been properly rescoped, and if not, figure our how to properly rescope it. (Thursday)
- Design the architecture for the project.
- Create a development plan for implementing the architecture we design.
    - Individual subgroups (backend, databases, frontend) will devise their own strategies to tackle their component(s).
    - Discuss as a group how these components will work together to produce our desired end product given the use cases we have established.
    - Beyond next week: begin development for a minimum viable product.

**Progress/Issues:**
- Talked to Kaushal and determined that we have properly rescoped the project.
    - Determined the important features and are now focusing on them for our minimum viable product.
- Completed Software Architecture
    - Worked together to make sure that everyone understood the system at a high level. Splitting into parts then refining the architecture was successful as it allowed us to understand our components better, then as a result we understood the system too.
- Blueprinted the Design of the Systems
    - Separated into groups to take care of design. This was relatively successful, and we were able to address questions that we had about who was responsible for each part.
    - Made a lot of progess toward understanding how these components fit together by splitting into teams.
    - Some parts were confusing, but by understanding our team's responsibilities individually, it was relatively simple to assign additional responsibility or determine how a new feature would work, such as using the websit while not logged in.
- Refined Schedule
    - Made very slight changes in order to make it more explicit.
- Created testing plan and coding guidelines
    - Left to each team to work out, better for workflow if people that aren't concerned with that component don't have to work about the implementation at all.

**Plans/Goals:**
- Create a more comprehensive plan for testing and continuous integration for the project.
- Each component's team should create an initial draft of their implementation. It doesn’t need to have all of the functionality but should at least have a sketch of the classes we decided on in the software architecture/design of the components.
- Beyond next week: Produce minimum viable product by May 12.

### Individual Contributions
**Michael Figgs:**
- Goals from last week: Get workspace all set up. Discuss architecture with group and write specifications for the backend.
- Progress: Coordinated with Mitchell to write basic expectations of all our classes and methods. Coordinated with frontend and backend teams to figure out what they need from us and what we need from them. Ran into issues with my IDE being set up for previous courses preferences. Struggled with how we plan on using the API but have some ideas.
- Goals for coming week: Transfer design plan into a more permanent spec, make skeleton/stubs for all classes/methods, and start writing tests for the json parser.

**Kyle Johnson:**
- Goals from last week: Solidify framework for frontend. Figure out how frontend and backend/database components will interact. Create a rough design sketch for frontend and a timeline for implementation.
- Progress: Solidified my choice of React as the front end framework of the website. This seemed like a natural choice, as I have used it before, and it is totally suitable for this project’s requirements. Helped brainstorm software architecture, working with backend and database teams to determine how our modules will interact, and developed the software design section for frontend.  Proofread doc & fixed formatting issues.
- Goals for coming week: Flesh out a concrete testing plan for the frontend of the website. Begin initial implementation of frontend UI, at minimum get HTML Layout and CSS Style going, and re-familiarize myself with React framework.

**Julia Kalmykov:**
- Goals from last week: Understand how the components work together and what my responsibilities are in the system.
- Progress: Figured out how the components work together. Worked more on use cases, worked on the testing strategy section, helped the group brainstorm other sections from the requirements document.
- Goals for coming week: Familiarize myself  with neo4j as well as refresh React skills. Start setting up the database and front end.

**Aleksa Milovanovic:**
- Goals from last week: Devoting more time to the team, figuring out how we'll be structuring meetings (have an agenda for each), and focus on learning the system architecture and how it all works together.
- Progress: Figuring out architecture as a team, designing components as smaller groups and making important design decisions. There were some points that we thought of something we hadn’t considered before, and that was very illuminating to get figured out. I learned a lot about designing a system from scratch, and it actually seems a lot less intimidating now.
- Goals for coming week: I hope to make some progress on setting up a database on neo4j (hopefully get it all set up), as well as create the classes/function stubs that need to exist for interfacing with the database. By May 3rd, I hope to look over our design and see if there’s anything that needs to be reevaluated before we really start implementing the design of the component.

**Mitchell Wong:**
- Goals from last week: Figure out what needs to happen in the backend (how tasks will be divided and handled). Longer term goals: figure out how my code will interact with the database and the front end.
- Progress: The backend team chose Java for development. Michael and I came up with a design plan for classes and objects to be included in the backend. We decided to split up the work, where I will take filtering, and he will take parsing, however there will still be a lot of working together. We figured out that we needed ingredient and user information from the database. We are stuck on handling concurrent users and may have to research information on setting up a server.
- Goals for coming week: Finalize Java classes by creating method stubs. Create initial testing methods for the backend to be included in both testing classes. Longer term goals include figuring out to get data from the API and implementing the parsing/filtering.
