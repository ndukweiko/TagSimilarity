# TagSimilarity
A Backend Database Service for Generating Content Recommendations based on User Data. Uses TF_IDF to compute Cosine Similarity between vectors
3 Command Line args: user password uniqueidentifier(found in user_content db table)
-When entered a list of Content related to that user's activity will be printed to the console.
-The Similarity of two pieces is computed using the tf_idf equations to create vectors of numbers which we can then plug into the cosine
similarity formula in order to find out how similar to documents(in this case, strings of text) are from 0 to 1.
A lot of the data sanitation was done in java and mostly involved managing whitespace and stripping special characters.
I also had to discern which fields were intended to be unique and make sure that the java code accounted for that when parsing the csv file
and that the database would provide constraints not allowing a user to insert multiple rows with the same id.
The final database consists of the Content table(the csv data) and two additional tables that I added to serve the purposes
of my project: The users table (a table of users for the system) and the user_content table (an intermediary table relating users and
the content they've enjoyed).
