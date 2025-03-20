package com.medi.scedule;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.medi.scedule.bean.DaysEnum;
import com.medi.scedule.domain.DoctorsAvailableDays;
import com.medi.scedule.repository.DoctorsAvailableDaysRepo;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class DoctorsAvailableDaysLoader implements CommandLineRunner {
	
	@Autowired
	private DoctorsAvailableDaysRepo daysRepo;

	@Override
	public void run(String... args) throws Exception {
		
		List<DoctorsAvailableDays> daysList = new ArrayList<>();
		log.info("doctors available days data is loading start...");
		DoctorsAvailableDays sunday = new DoctorsAvailableDays(DaysEnum.SUNDAY.getId(), DaysEnum.SUNDAY.getDay());
		daysList.add(sunday);
		DoctorsAvailableDays monday = new DoctorsAvailableDays(DaysEnum.MONNDAY.getId(), DaysEnum.MONNDAY.getDay());
		daysList.add(monday);
		DoctorsAvailableDays tuesday = new DoctorsAvailableDays(DaysEnum.TUESDAY.getId(), DaysEnum.TUESDAY.getDay());
		daysList.add(tuesday);
		DoctorsAvailableDays wednesday = new DoctorsAvailableDays(DaysEnum.WEDNESDAY.getId(), DaysEnum.WEDNESDAY.getDay());
		daysList.add(wednesday);
		DoctorsAvailableDays thursday = new DoctorsAvailableDays(DaysEnum.THURSDAY.getId(), DaysEnum.THURSDAY.getDay());
		daysList.add(thursday);
		DoctorsAvailableDays friday = new DoctorsAvailableDays(DaysEnum.FRIDAY.getId(), DaysEnum.FRIDAY.getDay());
		daysList.add(friday);
		DoctorsAvailableDays saturday = new DoctorsAvailableDays(DaysEnum.SATURDAY.getId(), DaysEnum.SATURDAY.getDay());
		daysList.add(saturday);
		
		daysRepo.saveAll(daysList);
		log.info("doctors available days data is loading end...");
		
	}

}
