package com.example.schedulemanagementappdev.schedule.repository;

import com.example.schedulemanagementappdev.schedule.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}
