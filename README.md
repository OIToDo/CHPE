# Camera Human Pose Estimation (CHPE) mobile app
### Introduction:
The Archimedes Institute ([Utrecht Science Park](https://www.utrechtsciencepark.nl/en/home/about-the-park?gclid=Cj0KCQiApaXxBRDNARIsAGFdaB_tQooIpPlGFwhje32Y_yqTvd-EZJwY-UG-r5NI4e3RL78rgCUtVYIaApj8EALw_wcB "Utrecht Science Park - About the park")) had a request for an application that can analyse people their presentations. To be exact, they want to be able to get statistics on the usage of gestures and your stance during a presentation. Based on this data they want to provide an analyses for the user. 

The idea we came up with was an app on your mobile device that uses your camera / gallery to get your presentation input.
Upon selecting your input a machine learning model ([TensorFlow.js version of PoseNet](https://medium.com/tensorflow/real-time-human-pose-estimation-in-the-browser-with-tensorflow-js-7dd0bc881cd5 "Real-time Human Pose Estimation in the Browser with TensorFlow.js")) will be used to obtain a person's coordinates for each frame from your input (video) file.

After obtaining the person's coordinates from the video file, the input is normalized and thrown into a database.
That bring us to the database.

### Explanation ERD:
A video consists of frames. These amount of frames shown every second ([FPS](https://en.wikipedia.org/wiki/Frame_rate "Frame rate - Wiki")) 
are variable. Each frame has 15 or 18 coordinates (depending on the model used).

How is this put into a database?

![ERD Diagram](https://i.gyazo.com/c0f051018e065b8fe9e8e5a9418dfb35.png)

1. For each video a record is added in the video table
2. For each frame of a video, a record is added in the frame table
3. For every coordinate a record will be added in the coordinate table
4. At last the relations between video, frame and frame coordinates are created

Within the scope of Android and how the data is retrieved it is possible that the program is destructed while executing. This is not desired when the process is not finished yet and there has been no reason for the user to close the application. 

This is taken into account by the database in the following way:
* If the application is stopped before the Query's are executed, the entire program will restart
* If a video is added, but there are no frames added, the already added video will be used to proceed
* If a video is added and a frame is added, but there no coordinates known yet, the coordinates can be added and the process can continue
* If a video is added and a frame is added, but not all coordinates have been added to the database yet, the frame will be done all over again

The though behind all these steps, is that it can take a really long time to process a certain video and you do not want to have to restart that progress if it gets interrupted. 

### Persistance package:
Class diagram:

![enter image description here](https://i.gyazo.com/2ac7816b370d100b44b9098771c2a172.png)

The persistance package is the implementation of our database, the database functions as [ORM](https://blog.bitsrc.io/what-is-an-orm-and-why-you-should-use-it-b2b6f75f5e2a "What is an ORM and Why You Should Use it"). 

The classes are as following:
* AppDatabase
Within this class the entities (classes / tables) are registered for Android room. This class furthers serves the use of access to the DAO's of each entity.
* PersistenceClient
Within this class the access to the AppDatabase instance is settled. It is not desired have to initialize a new AppDatabase when someone tries to add something to the database. This would be a relative heavy operation.

Furthermore we have the following Database Access Objects (DOA) as shown in the image and we have some notes to come with this package: 
* The NN-naming is chosen as a prefix to indicate that that files have something to do with the neural network. In addition to this, it is also an indication to the programmers that what happens with the NN-classes (not all of these are within the Persistance package) interacts with the database and should therefore not be interrupted.
*  

