# TwitterTracker
Test project for exploring Twitter Api, with twitter4j

To start the project, enter your developer credentials in application.properties file and start the application via Application class.

To select how to filter, configure application.properties "twitter.locations" and "twitter.track"
track selects words to follow
locations specifies coordinates of area to follow (supply name, if present will add coordinates to filter)

Note that Twitter API requires either track, locations, or users to be set, otherwise it won't even allow connection.

The project will output statistics (total tweets, % of odd worded tweets) every period of time to logger. There is no saving, so we ignore deletion notices, that require to delete saved data.

To show more info, enable debug mode.