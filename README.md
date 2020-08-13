# A Simple Mask Distributor

## A program designed to simplify the task of distributing masks to a population, accounting for each individual's needs that vary with their age, medical conditions, and home address.

This program will receive a user's input of their name, current address, age, and any pre-existing medical conditions, and compute the number of masks the user will receive according to their age and medical conditions (if any) and by what date they can receive their masks by according to their address.

The distributor will follow these guidelines to determine the number of masks each individual will receive and by when:
- The average adult of **age 14 - 64** will receive the standard amount of masks distributed per person, which starts at 15. This number may vary depending on the available supplies of masks.
- Children **(under the age of 14)** and seniors **(over the age of 64)** will receive 10 more than the standard masks per person.
- **Pregnant women**, or **people with pre-existing medical conditions** that make them more susceptible to COVID-19 (specified under *medical conditions*) will receive 5 more than the standard masks per person.
- **Local residents** and **foreign residents** will be placed in separate queues, as local residents will be able to receive their masks in a weeks time, and foreign residents will receive them in a month. Again, this time period may vary according to the number of people in the queue.

Once completed, the distributor can be used in any hospitals, pharmacies or small town clinics that require a systematic method to distribute masks during the COVID-19 pandemic. By accounting for the information inputed by the user, the distributor introduces a both equitable and preservative strategy in dealing with the current mask shortages.

A personal factor that gave me the idea for this project was a call from my Grandpa in Korea, asking me if he could use my Korean citizenship to hold a place in the queue for purchasing masks. Although he lives in a fairly small city, he told me that it became a huge struggle to find masks in stores once the pandemic started, and hence many of them started a "booking" system where locals can sign up for a pack of masks and pick it up when it becomes available. I saw some flaws in this system, with one of them being the fact that it does not account for specific populations such as the old or the sick who are more in need of masks than the healthy, young population, and thought it would be a good idea to implement a program that can distributes masks more effectively by recognizing these needs.

Additionally, I thought it would only be fitting for me to create a project in response to the current COVID-19 pandemic, in attempts to encourage and motivate the fight against these difficult times for everyone in the world today.

## User Stories
* As a user, I want to be able to add a customer into the mask distribution list.
* As a user, I want to be able to view the number of masks a given customer will receive.
* As a user, I want to be able to view the the date at which a given customer will receive their masks.
* As a user, I want to be able to view a given customer's position in the queue.
* As a user, I want to be able to remove customers who have already received their masks from the distribution list.
* As a user, I want to be able to save the distribution list whenever I add or remove a customer.
* As a user, I want to be able to print previously edited versions of the distribution lists when I start the application.

## Instructions for Grader
* You can generate the first required event (adding customers onto the distribution list) by clicking which distribution list you would like to edit from the home tab, entering the customer's details, then clicking the "Add customer" button.
* You can generate the second required event (removing customers from the distribution list) by clicking which distribution list you would like to edit from the home tab, selecting the customer that you wish to remove, then clicking the "Remove customer" button.
* You can trigger my audio component by clicking on any of the following buttons: "Add customer", "Remove customer", "Local Distribution List", "Foreign Distribution List".
* You can save the state of my application by simply quitting the program.
* You can reload the state of my application automatically, every time you run the application again.

## Phase 4: Task 2
1) Robust classes
* <code>CustomerNotInListException</code> thrown in the <code>DistributionList</code>, <code>LocalList</code>, <code>ForeignList</code> classes in <code>model</code>, and the <code>Handler</code> class in <code>ui</code> whenever there is a call to a method to retrieve customer details of a customer who is not already in the list.
* <code>IOException</code> thrown in both <code>FileReader</code> and <code>FileWriter</code> classes in <code>persistence</code>, for when an error occurs while trying to access a file.

2) Type Hierarchies
* Both <code>LocalList</code> and <code>ForeignList</code> classes in <code>model</code> extend the abstract class <code>DistributionList</code>, overriding the <code>getDate</code> method. This method returns 7 days for customers in <code>LocalList</code>, and 30 days for customers in <code>ForeignList</code>.
* Both <code>LocalListTab</code> and <code>ForeignListTab</code> classes in <code>ui</code> extend the abstract class <code>ListTab</code>, overriding the methods <code>addCustomersToListModel</code>, <code>listSetUp</code>, <code>getListModel</code>, and <code>getDistributionList</code>. These methods all require access to a specific list, and hence need to be overrided in both subclasses.

3) Bidirectional relationships
* The <code>ListTab</code> class in <code>ui</code> needs fields of <code>AddListener</code> and <code>RemoveListener</code>, and vice versa. <code>ListTab</code> needs to pass the listeners as parameters to methods that set up the add and remove buttons, and the listeners need access to the contents of the <code>ListTab</code> to iterate over each line and save it to a file.
* The <code>Application</code> class needs a field of the <code>Handler</code> class, and vice versa. <code>Application</code> needs to call the corresponding method in <code>Handler</code> whenever the user inputs a command, and <code>Handler</code> needs access to <code>Application</code>'s saving methods.
* The class <code>HomeTab</code> requires a field of the <code>DistributionListUI</code> class, and vice versa. <code>HomeTab</code> needs to be able to pass the ui field to the buttons that switch the screen to the list tabs, and the <code>DistributionListUI</code> needs to load the home tab on its side bar.


## Phase 4: Task 3
* The <code>Handler</code> class in <code>ui</code> has poor cohesion, as it has 2 major responsibilities of handling the action triggered by the user's command in <code>Application</code>, and performing these actions on lists based on the customer's address (checking if the customer's address contains "BC", and performing the action on the local list if true, foreign list otherwise)
> To improve this, I created a class called <code>Classifier</code> in <code>ui</code>, which contains all the methods that <code>Handler</code> calls first--after the user inputs a customer--to set the list on which the action is being performed according to the inputted customer's address. Then, I referenced the methods in this class in <code>Handler</code>, by creating a field of its type.

* After creating the <code>Classifier</code> class, I realized that there was too much coupling between <code>Handler</code>, <code>Classifier</code> and <code>Application</code>. <code>Handler</code> depended on <code>Classifier</code> to separate the inputted customer into the local or foreign list, <code>Classifier</code> depended on <code>Application</code> for its saving functionalities, and <code>Application</code> depended on <code>Handler</code> to handle the user's commands. This circle of dependencies are not resilient to change, resulting in me having to change all three classes when there is a changed introduced to one.
> To improve this, I created another class called <code>Saver</code> in <code>ui</code>, which contains all the methods that relate to saving the changes in the distribution lists to the files. This got rid of <code>Classifier</code>'s need to depend= on <code>Application</code>, and broke the circle of dependencies to a straight line. Now there is a bidirectional relationship between <code>Handler</code> and <code>Application</code>, and between <code>Handler</code> and <code>Classifier</code>, and a unidirectional relationship from <code>Classifier</code> to <code>Saver</code>.
