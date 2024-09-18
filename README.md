# Health Context Monitoring Screens

Heart Rate & Respiratory Rate Screen          |  Symtoms Rating Screen
:-------------------------:|:-------------------------:
![HealthMonitoringAppScreen1](https://github.com/user-attachments/assets/814405d0-290b-4fc3-a9d1-490d3ed4eb9a)  |  ![HealthMonitoringAppScreen2](https://github.com/user-attachments/assets/0fec3740-4a32-4218-9dec-61526daee9fd)

# Health Context Monitoring App Video

[ContextMonitoringApp.webm](https://github.com/user-attachments/assets/0dce90aa-6279-421c-a8ef-0290bacaca75)

# YouTube Project Explanation link

[Project Explanation](https://www.youtube.com/watch?v=jlxLjx8e2wY&ab_channel=HartikSuhagiya)

# Question \& Answers
**Q1. 
Imagine you are new to the programming world and not proficient enough in coding. But, you have a brilliant idea where you want to develop a context-sensing application like Project 1.  You come across the Heath-Dev paper and want it to build your application. Specify what Specifications you should provide to the Health-Dev framework to develop the code ideally.**

**Answer:**
To develop a context-sensing application using the Health-Dev framework as outlined in the paper, I would begin by gathering detailed specifications. After defining these specifications, I would focus on the implementation—translating those specifications into code.

1. Define the Purpose and Context of the App: First, I would clearly define the focus of the health-related application, such as health monitoring, fitness tracking, or environmental sensing. Then, I would finalize the contextual information that the app will collect, such as heart rate, respiratory rate, user location, sleep patterns, and movement.

2. Decide on Sensors and Data Requirements: I would identify the necessary sensors to collect the health data (e.g., sensors for calculating heart rate or respiratory rate). For example, the application might use accelerometers, gyroscopes, heart rate sensors, GPS, or environmental sensors.

3. Choose Platforms and Device Compatibility: I would decide which operating system the app will support—iOS or Android. Additionally, I would consider whether the app can be integrated into other smart devices, such as smartwatches or fitness trackers.

4. User Interaction: I would determine high-level requirements for the UI/UX. Should the application display real-time health data, or provide feedback on the user’s activity? I would also decide how to notify users—whether through pop-ups, notifications, or vibrations.

5. Development: After learning the basics of Android development in Kotlin, I would begin implementing the features of the app step by step. This would involve coding the functionality I planned during the specification stage.

6. Data Privacy and Security: I would ensure the app complies with HIPAA policies by securely storing all health data in an encrypted format. Data privacy and secure handling would be a priority.

7. Health-Dev Framework Components: I would use the Health-Dev framework for sensor data aggregation and compatibility. Then, I would analyze the data and present meaningful results to users through the app’s user interface. I would also consider energy efficiency, especially since processing sensor data can affect battery life, and work to optimize power usage.

8. Performance and Scalability: I would measure the app’s performance by recording metrics such as data processing speed, accuracy of sensor data collection, and screen responsiveness. Additionally, I would test the app's scalability—how many users or devices it can support simultaneously.

9. Testing and Deployment: I would conduct unit tests for the Android app, followed by alpha and beta testing. Once everything is polished, I would make the application publicly available.

**Software**:
1. Android Studio and Android SDK: Android Studio is the primary Integrated Development Environment (IDE) used for developing the Health Context Monitoring App. It provides a rich environment for app development, debugging, and testing with built-in features such as layout editors, code analysis tools, and device emulators.
Android SDK (Software Development Kit) with a minimum SDK level 29 is used for the app and it includes essential libraries, tools, and APIs that enable interaction with mobile device hardware, such as accelerometer sensors, camera sensors. These tools have allowed the app to gather data from sensors, manage background services, and handle user interfaces.
2. Emulator: The Android Emulator is used to simulate real-world devices for testing purposes. It allows developers to test the app on various Android configurations, screen sizes, and hardware features without needing physical devices. The emulator also supports sensor emulation for testing sensor-driven functionalities like heart rate and respiratory rate calculations.
3. Kotlin Programming Language: The app is developed using Kotlin, a modern, concise, and statically typed programming language designed for Android development. Kotlin simplifies app development with its powerful features like null safety, coroutines for asynchronous programming, and better code readability compared to Java.

Kotlin is integrated with Android Studio, making it easier to implement UI components, access device sensors, and manage local databases such as RoomDB.

**Hardware**:

Android emulator(Medium Phone API 35): It is a virtual device configuration that simulates an Android phone running API Level 35. The "Medium Phone" designation means it is a standard-sized smartphone with typical hardware configurations like a mid-range screen size, resolution, and processing power. This emulator has allowed to test the Context Monitoring Application quickly on different mobile configurations having different android versions.

**Algorithms**

**Heart Rate Calculator Algorithm:**

  Step 1: Initialize retriever and extract frames
Initialize MediaMetadataRetriever
Load video from URI
Extract total number of frames

  Step 2: Extract every 50th frame
For each frame at regular intervals (e.g., 50):
    Extract the frame
    Store the frame if not null

  Step 3: Calculate pixel intensity
For each stored frame:
    Select a 100x100 pixel region (e.g., from (350, 350) to (450, 450))
    Sum the red, green, and blue values of the pixels in the region
    Store the average pixel intensity for each frame

  Step 4: Smooth the data
For each set of 5 consecutive pixel intensities:
    Compute the average and store the smoothed value

  Step 5: Count peaks
Set count = 0
For each smoothed intensity:
    If the difference between consecutive intensities > threshold (e.g., 3500):
        Increment count

  Step 6: Calculate heart rate
Heart rate = (count * 60) / 4

**Respiratory Rate Calculator Algorithm:**

  Step 1: Inputs
Three lists of acceleration values: accelValuesX, accelValuesY, and accelValuesZ (representing acceleration on the X, Y, and Z axes, respectively).

  Step 2: Initialize variables:
Set previousValue to 10.0 (initial reference value for the first comparison).
Initialize a counter k to 0 (this will count significant changes in acceleration).

  Step 3: Loop through the acceleration data starting from index 11:
  
For each index i from 11 to the end of the list:

Calculate the Euclidean distance for the acceleration at index i:
    
$$currentValue = \sqrt{accelValuesX[i]^2 + accelValuesY[i]^2 + accelValuesZ[i]^2}$$

Compare the absolute difference between previousValue and currentValue:
    
f the difference exceeds 0.15, increment the counter k.
    
if ∣previousValue−currentValue∣>0.15, then increment k
    
Update previousValue to be the currentValue for the next iteration.

  Step 4: Calculate the respiratory rate after the loop finishes:
  
$$Rate = \frac{k}{45.0}*30$$
  
  Step 5: Return the calculated rate as an integer value.


**Q2.
In Project 1 you have stored the user’s symptoms data in the local server. Using the bHealthy application suite how can you provide feedback to the user and develop a novel application to improve context sensing and use that to generate the model of the user?**

**Answer:**
To develop a novel context-sensing application using the bHealthy application suite, while enhancing user feedback and generating a user model, I would follow these steps:

1. **Leverage bHealthy’s Features for User Feedback:** The bHealthy suite provides tools for health monitoring and contextual awareness. I would use its API to calculate health metrics like heart rate and respiratory rate in real-time. If a user’s heart rate exceeds a certain threshold, the app would immediately notify them of potential health concerns. Additionally, based on the health data stored locally (e.g., symptoms, vitals), the app could recommend actions like taking a break, adjusting exercise intensity, or suggesting health improvements.

2. **Incorporate Machine Learning for Predictive Feedback:** I would implement a machine learning model based on the collected health data to provide predictive insights. The model could offer more accurate feedback by analyzing current data to predict future health risks. For example, the app could deliver context-aware notifications, such as advising the user to stay hydrated if the temperature is high.

3. **Improve Context Sensing:** I would enhance the app’s context sensing by integrating physical activity tracking using accelerometers and gyroscopes. This could help correlate health metrics with specific activities, such as walking, running, or sleeping. GPS and environmental sensors would further improve the app’s ability to detect user surroundings (e.g., air quality), providing relevant, real-time feedback such as recommending indoor exercise if outdoor air quality is poor.

4. **Data Aggregation and Analysis:** I would combine data from all relevant sensors and generate actionable feedback for users. This would allow the app to alert users to potential health risks and track their consistency in maintaining healthy habits. Data could be visualized in charts like pie, bar, and line graphs to make it easier for users to understand trends and improvements.

5. **Develop a Novel Application:** The app would track and log health metrics, physical activities, and environmental conditions in real-time. Based on the collected data, it would provide personalized suggestions, such as adjusting workout intensity based on heart rate trends or encouraging relaxation when stress levels are detected. Predictive analytics and machine learning models would help anticipate potential health issues and suggest proactive measures for prevention.

6. **Collect User Feedback:** I would incorporate user feedback to continually improve the app’s features, such as refining health suggestions, optimizing battery usage, and enhancing overall health monitoring accuracy.

**Q3.
A common assumption is mobile computing is mostly about app development. After completing Project 1 and reading both papers, have your views changed? If yes, what do you think mobile computing is about and why? If no, please explain why you still think mobile computing is mostly about app development, providing examples to support your viewpoint**

**Answer:**
Yes, my views on mobile computing have changed after completing Project 1 and reading both papers. Initially, I assumed mobile computing was primarily about app development, but now I see that it encompasses much more. Mobile computing is about integrating diverse systems—such as sensors, data processing, machine learning, and cloud services—into cohesive, real-time solutions. It involves not just developing user interfaces, but also handling sensor data collection, context awareness, energy efficiency, and optimizing performance. For instance, in Project 1, gathering health metrics like heart rate and respiratory rate, storing the data locally in RoomDB, and providing meaningful feedback required much more than just building an app. It involved managing complex data streams, ensuring privacy, and leveraging predictive analytics for personalized feedback. This shows that mobile computing is about creating intelligent, adaptive systems rather than just standalone applications.
