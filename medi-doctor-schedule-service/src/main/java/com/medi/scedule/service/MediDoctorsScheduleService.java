package com.medi.scedule.service;

import java.util.List;

import com.medi.scedule.bean.MediDoctorScheduleBean;

public interface MediDoctorsScheduleService {
	
	public MediDoctorScheduleBean createDoctorSchedule(MediDoctorScheduleBean scheduleBean);
	public MediDoctorScheduleBean updateDoctorSchedule(MediDoctorScheduleBean scheduleBean);
	public MediDoctorScheduleBean getDoctorSchedule(String scheduleId);
	public List<MediDoctorScheduleBean> getDoctorScheduleByName(Integer doctorId);
	public List<MediDoctorScheduleBean> findAllDoctorSchedules();
	public MediDoctorScheduleBean deleteDoctorSchedules(Long scheduleId);

}
