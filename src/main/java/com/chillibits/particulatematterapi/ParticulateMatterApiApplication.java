/*
 * Copyright © Marc Auberer 2019 - 2020. All rights reserved
 */

package com.chillibits.particulatematterapi;

import com.chillibits.particulatematterapi.model.db.main.Client;
import com.chillibits.particulatematterapi.model.db.main.Sensor;
import com.chillibits.particulatematterapi.model.db.main.User;
import com.chillibits.particulatematterapi.model.dbold.OldSensor;
import com.chillibits.particulatematterapi.repository.ClientRepository;
import com.chillibits.particulatematterapi.repository.OldSensorRepository;
import com.chillibits.particulatematterapi.repository.SensorRepository;
import com.chillibits.particulatematterapi.repository.UserRepository;
import com.chillibits.particulatematterapi.shared.ConstantUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SpringBootApplication
@EnableMongoRepositories(basePackages = "com.chillibits.particulatematterapi.repository.data")
public class ParticulateMatterApiApplication implements CommandLineRunner {

	@Autowired
	private SensorRepository sensorRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ClientRepository clientRepository;
	@Autowired
	private OldSensorRepository oldSensorRepository;

	public static void main(String[] args) {
		SpringApplication.run(ParticulateMatterApiApplication.class, args);
	}

	@Override
	public void run(String... args) {
		// Imports from the old api
		if(ConstantUtils.IMPORT_SENSORS_IF_TABLE_IS_EMPTY && sensorRepository.count() == 0) importFromOldSensors();

		// Create mandatory data records
		if(userRepository.count() == 0) userRepository.save(new User(ConstantUtils.UNKNOWN_USER_ID, "Unknown", "User", "info@chillibits.com", "not set", Collections.emptySet(), User.USER, User.LOCKED, System.currentTimeMillis(), System.currentTimeMillis()));
		if(clientRepository.count() == 0) clientRepository.save(new Client(ConstantUtils.UNKNOWN_CLIENT_ID, "unknownclient", "Unknown Client", "not set", Client.TYPE_NONE, "", Client.STATUS_SUPPORT_ENDED, false, 0, "0", 0, "0", "ChilliBits", ""));

		// Test space (will not be included in a stable build)

	}

	private void importFromOldSensors() {
		// Delete all contents
		sensorRepository.deleteAllInBatch();
		// Add all from the old table
		List<OldSensor> oldSensors = oldSensorRepository.findAll();
		List<Sensor> newSensors = new ArrayList<>();
		oldSensors.forEach(oldSensor -> newSensors.add(convertOldToNewSensor(oldSensor)));
		sensorRepository.saveAll(newSensors);
	}

	private Sensor convertOldToNewSensor(OldSensor oldSensor) {
		return new Sensor(
				oldSensor.getChipId(),
				Collections.emptySet(),
				oldSensor.getFirmwareVersion(),
				oldSensor.getCreationDate(),
				oldSensor.getNotes(),
				oldSensor.getLastUpdate(),
				oldSensor.getLastEdit(),
				Double.parseDouble(oldSensor.getLat()),
				Double.parseDouble(oldSensor.getLng()),
				Double.parseDouble(oldSensor.getAlt()) * 100,
				oldSensor.getCountry(),
				oldSensor.getCity(),
				false,
				true
		);
	}
}