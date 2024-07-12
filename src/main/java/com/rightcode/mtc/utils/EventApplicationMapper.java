package com.rightcode.mtc.utils;

import com.rightcode.mtc.store.entities.EventApplication;
import com.rightcode.mtc.dto.eventApplication.EventApplicationResponse;
import org.springframework.stereotype.Component;

@Component
public class EventApplicationMapper implements Mapper<EventApplication, EventApplicationResponse> {
    @Override
    public EventApplicationResponse toDto(EventApplication dao) {
        EventApplicationResponse dto = new EventApplicationResponse();

        //TODO: Возможна большая нагрузка на БД. Лучше сделать один запрос через JOIN

        dto.setId(dao.getId());
        dto.setDos(dao.getDos().toString());
        dto.setStatus(dao.getStatus().name());
        dto.setEvent(dao.getEvent().getType().getAcronym());
        dto.setFullName(String.format("%s %s%s",
                dao.getMedicalWorker().getSurname(),
                dao.getMedicalWorker().getName(),
                dao.getMedicalWorker().getPatronymic() != null ? " " + dao.getMedicalWorker().getPatronymic() : ""));
        dto.setPhoneNumber(dao.getMedicalWorker().getPhoneNumber());
        dto.setEmail(dao.getMedicalWorker().getEmail());
        dto.setMedicalOrganization(dao.getMedicalWorker().getOrganization().getName());
        dto.setMedicalSpeciality(dao.getMedicalWorker().getSpeciality().getName());
        dto.setMedicalPosition(dao.getMedicalWorker().getPosition().getName());

        return dto;
    }


}