package com.rightcode.mtc.store.repositories.specifications;

import com.rightcode.mtc.dto.event.FilterEventParticipantsRequest;
import com.rightcode.mtc.store.entities.Event;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class EventSpecification {

    public Specification<Event> build(FilterEventParticipantsRequest filterProps) {
        return withEventTypeId(filterProps.getEventTypeId())
                .and(withMedicalSpecialityId(filterProps.getMedicalSpecialityId()))
                .and(withinDateRange(
                        filterProps.getDateFrom() != null ? LocalDate.parse(filterProps.getDateFrom()) : null,
                        filterProps.getDateTo() != null ? LocalDate.parse(filterProps.getDateTo()) : null
                ));
    }

    private Specification<Event> withEventTypeId(Long eventTypeId) {
        return (root, query, cb) -> eventTypeId == null ? cb.conjunction() :
                cb.equal(root.get("eventType").get("id"), eventTypeId);
    }

    private Specification<Event> withMedicalSpecialityId(Long medicalSpecialityId) {
        return (root, query, cb) -> medicalSpecialityId == null ? cb.conjunction() :
                cb.equal(root.get("medicalSpeciality").get("id"), medicalSpecialityId);
    }

    private Specification<Event> withinDateRange(LocalDate startDate, LocalDate endDate) {
        return (root, query, cb) -> {
            if (startDate == null && endDate == null) {
                return cb.conjunction();
            } else if (startDate != null && endDate != null) {
                if (startDate.isEqual(endDate)) {
                    return cb.equal(root.get("date"), startDate);
                }
                return cb.between(root.get("date"), startDate, endDate);
            } else if (startDate != null) {
                return cb.greaterThanOrEqualTo(root.get("date"), startDate);
            } else {
                return cb.lessThanOrEqualTo(root.get("date"), endDate);
            }
        };
    }
}
