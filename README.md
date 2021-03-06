# Particulate Matter API
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/a14af7aaa8414e62a4a62a7c9bf0e4db)](https://app.codacy.com/manual/marcauberer/particulate-matter-api?utm_source=github.com&utm_medium=referral&utm_content=marcauberer/particulate-matter-api&utm_campaign=Badge_Grade_Dashboard)
[![Build Status](https://travis-ci.com/marcauberer/particulate-matter-api.svg?branch=master)](https://travis-ci.com/marcauberer/particulate-matter-api)

This is the official ChilliBits Particulate Matter REST API.

*Attention: This repository is still in development. Up to now, there's no stable API version.*

## Features
-   Chart endpoint
    -   Get chart from single sensor in a specific time span
    -   Get chart from country average in a specific time span with a certain period
    -   Get chart from city average in a specific time span with a certain period

-   Client endpoint
    -   Get all clients, which are registered in the system
    -   Get information about a single client by its name

-   Data endpoint
    -   Get data records from single sensor in a specific time span
    -   Get data records from single sensor in a specific time span in a compressed form to reduce transmission capacity
    -   Get latest data record from single sensor
    -   Get average of the latest records of specific sensors
    -   Get data records from country in a specific time span
    -   Get average out of the latest data records of sensors within a specific country
    -   Get data records from city in a specific time span
    -   Get average out of the latest data records of sensors within a specific city

-   Push endpoint
    -   Endpoint for data transmissions (more details in section 'push endpoint')

-   Ranking endpoint
    -   Get ranking by country
    -   Get ranking by country in a compressed form
    -   Get ranking by city
    -   Get ranking by city in a compressed form

-   Sensor endpoint
    -   Add sensor to the database
    -   Update a sensor that already exists in the database
    -   Get all sensors
    -   Delete a sensor from the database
    -   Get sensor sync package (Used to reduce transmission capacity)

-   Stats endpoint
    -   Get stats about the api

-   User endpoint
    -   Add user to the database
    -   Update an existing user
    -   Get user by its email
    -   Delete user from the database

## Usage
You can find the API documentation at [api.pm.chillibits.com](https://api.pm.chillibits.com/swagger-ui.html)

## Push endpoint
If you have a sensor from luftdaten.info / sensor.community, you simply have to enable the option 'Feinstaub-App' in the APIs section. The data is transmitted to our server automatically.

If you have built your own IoT device, you can also send data to our system. Setup the connection as follows:

-   Request type: **Post request**
-   Host: **api.pm.chillibits.com**
-   Schema: **https**
-   Port: **443**

The required data format can be found in our API documentation at [api.pm.chillibits.com](https://api.pm.chillibits.com/swagger-ui.html#/push/pushDataUsingPOST)

## Register an application
If your the developer of a application and you want to consume data from our api, you have to contact us via [email](mailto:contact@chillibits.com?subject=Register%20application%20pmapi).
The registration process is as follows:

-   We'll check the code of your application to ensure, that your not using our data in a malicious way. Therefore, only open source applications are accepted.
-   After this verification, we send you a mail with all required information and ask you for required information about you / your company or your application.
-   You answer the mail with all the required information
-   We'll create a client record, which will have the appropriate access rights for our endpoints
-   Let's go and start building your awesome application.

© ChilliBits 2019 - 2020 (Designed and developed by Marc Auberer)