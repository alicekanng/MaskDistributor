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
* As a user, I want to be able to save the distribution list whenever I add a customer.
* As a user, I want to be able to print previously edited versions of the distribution lists when I start the application.

## Instructions for Grader
* You can generate the first required event (adding customers onto the distribution list) by clicking which distribution list you would like to add on to from the home tab, entering the customer's details, then clicking the "Add customer" button.
* You can generate the second required event (removing customers from the distribution list) by clicking which distribution list you would like to add on to from the home tab, selecting the customer that you wish to remove, then clicking the "Remove customer" button.
* You can trigger my audio component by clicking on any of the following buttons: "Add customer", "Remove customer", "Local Distribution List", "Foreign Distribution List".
* You can save the state of my application by simple adding another customer, then quitting the program.
* You can reload the state of my application automatically, every time the application runs again.
