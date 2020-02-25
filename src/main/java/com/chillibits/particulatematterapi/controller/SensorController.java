/*
 * Copyright © Marc Auberer 2019 - 2020. All rights reserved.
 */

package com.chillibits.particulatematterapi.controller;

import com.chillibits.particulatematterapi.model.MapsPlaceResult;
import com.chillibits.particulatematterapi.model.Sensor;
import com.chillibits.particulatematterapi.repository.SensorRepository;
import com.chillibits.particulatematterapi.shared.Constants;
import com.chillibits.particulatematterapi.shared.Credentials;
import com.chillibits.particulatematterapi.shared.Tools;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.net.URL;
import java.util.List;

@RestController
public class SensorController {

    @Autowired
    SensorRepository sensorRepository;

    @RequestMapping(method = RequestMethod.GET, path = "/sensor", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Sensor> getAllSensors(@RequestParam(required = false) boolean all, @RequestParam(required = false) boolean compressed) {
        return sensorRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.POST, path = "/sensor", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Sensor addSensor(@RequestBody Sensor sensor) {
        // Retrieve country and city from latitude and longitude
        try {
            String url = "https://maps.googleapis.com/maps/api/geocode/json?key=" + Credentials.GOOGLE_API_KEY + "&latlng=" + sensor.getLatitude() + "," + sensor.getLongitude() + "&sensor=false&language=en";
            MapsPlaceResult place = new ObjectMapper().readValue(new URL(url), MapsPlaceResult.class);
            sensor.setCountry(place.getCountry());
            sensor.setCity(place.getCity());
        } catch (Exception e) {
            sensor.setCountry(Constants.BLANK_COLUMN);
            sensor.setCity(Constants.BLANK_COLUMN);
        }

        // Set remaining attributes
        long creationDate = System.currentTimeMillis();
        sensor.setFirmwareVersion(Constants.EMPTY_COLUMN);
        sensor.setNotes(Constants.BLANK_COLUMN);
        //sensor.setCreationDate(creationDate);
        sensor.setLatitude(Tools.round(sensor.getLatitude(), 4));
        sensor.setLongitude(Tools.round(sensor.getLongitude(), 4));
        //sensor.setLastEdit(creationDate);
        sensor.setLastMeasurement(creationDate);
        sensor.setMapsUrl("https://www.google.com/maps/place/" + sensor.getLatitude() + "," + sensor.getLongitude());
        sensor.setLastValueP1(0);
        sensor.setLastValueP2(0);

        // Save sensor to database
        return sensorRepository.save(sensor);
    }

    @Transactional
    @RequestMapping(method = RequestMethod.PUT, path = "/sensor", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Integer updateSensor(@RequestBody Sensor sensor) {
        return sensorRepository.updateSensor(sensor.getId(), sensor.getLatitude(), sensor.getLongitude(), sensor.getLastValueP1(), sensor.getLastValueP2());
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/sensor/{id}")
    public void deleteSensor(@PathVariable("id") Integer id) {
        sensorRepository.deleteById(id);
    }
}