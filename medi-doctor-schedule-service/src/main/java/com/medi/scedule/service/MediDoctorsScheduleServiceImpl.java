package com.medi.scedule.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.medi.scedule.bean.MediDoctorScheduleBean;
import com.medi.scedule.domain.DoctorsAvailableDays;
import com.medi.scedule.domain.MediDoctorSchedule;
import com.medi.scedule.exception.AvailableDaysException;
import com.medi.scedule.exception.DoctorScheduleException;
import com.medi.scedule.repository.DoctorsAvailableDaysRepo;
import com.medi.scedule.repository.DoctorsScheduleRepo;


@Service
public class MediDoctorsScheduleServiceImpl implements MediDoctorsScheduleService {
	
	@Autowired
	private DoctorsScheduleRepo doctorsScheduleRepo;
	
	@Autowired
	private DoctorsAvailableDaysRepo doctorsAvailableRepo;

	@Override
	public MediDoctorScheduleBean createDoctorSchedule(MediDoctorScheduleBean scheduleBean) {
		MediDoctorSchedule scheduleDomain = new MediDoctorSchedule();
		BeanUtils.copyProperties(scheduleBean, scheduleDomain);
		if(scheduleBean.getAvailableDays() != null && scheduleBean.getAvailableDays().size() > 0) {
			List<DoctorsAvailableDays> availableDaysList = new ArrayList<>();
			scheduleBean.getAvailableDays().stream().forEach(availableDayBean->{
				//DoctorsAvailableDays availableDaysDomain = new DoctorsAvailableDays();
				//DoctorsAvailableDays availableDaysDomain = doctorsAvailableRepo.findById(availableDayBean.getId()).get();
				Optional<DoctorsAvailableDays> optionalDays = doctorsAvailableRepo.findById(availableDayBean.getId());
				if(!optionalDays.isPresent()) {
					throw new AvailableDaysException(HttpStatus.NOT_FOUND.value(), "no day found with the given id", new Date());
				}
				DoctorsAvailableDays availableDaysDomain = optionalDays.get();
				BeanUtils.copyProperties(availableDayBean, availableDaysDomain);
				availableDaysList.add(availableDaysDomain);
			});
			scheduleDomain.setAvailableDays(availableDaysList);
		}
		try {
			doctorsScheduleRepo.save(scheduleDomain);
			BeanUtils.copyProperties(scheduleDomain, scheduleBean);
		}catch (Exception e) {
			if(e instanceof DataIntegrityViolationException) {
				String message ="Data is duplicated or not in database acceptible format";
				System.out.println(message);
				/*
				 * for(StackTraceElement ste: e.getStackTrace()) {
				 * System.out.println(ste.toString()); System.out.println(ste.getFileName()); }
				 */
				throw new DoctorScheduleException(HttpStatus.NOT_ACCEPTABLE.value(),message,new Date());
			}
			throw new DoctorScheduleException(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getLocalizedMessage(), new Date());
		}
		
		return scheduleBean;
	}

	@Override
	public MediDoctorScheduleBean updateDoctorSchedule(MediDoctorScheduleBean scheduleBean) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MediDoctorScheduleBean getDoctorSchedule(String scheduleId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MediDoctorScheduleBean> getDoctorScheduleByName(Integer doctorId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MediDoctorScheduleBean> findAllDoctorSchedules() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MediDoctorScheduleBean deleteDoctorSchedules(Long scheduleId) {
		// TODO Auto-generated method stub
		return null;
	}

}
