# A Simple Mask Distributor

## A program designed to simplify the task of distributing masks to a population, accounting for each individual's needs that vary with their age, medical conditions, and home address.

This program will receive a user's input of their name, current address, age, gender, and any pre-existing medical conditions, and output the number of masks they will receive and the time at which they will receive them according to their place in the queue.

The distributor will follow these guidelines to determine the number of masks each individual will receive:
- The average adult of **age 14 - 64** will receive 10 masks per person.
- Children **(under the age of 14)** and seniors **(over the age of 64)** will recieve 20 masks per person.
- **Pregnant women**, or **people with pre-existing disabilities** that make them more susceptible to COVID-19 (specified under *medical conditions*) will recieve 15 masks per person.
- **Local residents** will be placed before all other foreign residents in the queue.

Once completed, the distributor can be used in any hospitals, pharmacies or small town clinics that require a systematic method to distribute masks during the COVID-19 pandemic. By accounting for the information inputed by the user, the distributor introduces a both equitable and preservative strategy in dealing with the current mask shortages.

A personal factor that gave me the idea for this project was a call from my Grandpa in Korea, asking me if he could use my Korean citizenship to hold a place in the queue for purchasing masks. Although he lives in a fairly small city, he told me that it became a huge struggle to find masks in stores once the pandemic started, and hence many of them started a "booking" system where locals can sign up for a pack of masks and pick it up when it becomes available. I saw some flaws in this system, with one of them being the fact that it does not account for specific populations such as the old or the disabled who are more in need of masks than the healthy, young population, and thought it would be a good idea to implement a program that recognizes these needs and distributes masks in a more effective manner.

Additionally, I thought it would only be fitting for me to create a project in response to the current COVID-19 pandemic, in attempts to encourage and motivate the fight against these difficult times for everyone in the world today.

## User Stories
* As a user, I want to be able to input customer data into the mask distribution list.
* As a user, I want to be able to view the distribution list of customers waiting to receive masks and their information.
* As a user, I want to be able to view how many masks a given customer will receive.
* As a user, I want to be able to check off customers that have received their masks.
* As a user, I want to be able to deleted customers from the distribution list.
