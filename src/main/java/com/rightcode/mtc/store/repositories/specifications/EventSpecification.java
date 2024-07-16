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
                        parsedate(filterProps.getDateFrom()),
                        parsedate(filterProps.getDateTo())
                ));
    }

    private LocalDate parsedate(String dateStr) {
        if (dateStr == null || dateStr.isEmpty()) {
            return null;
        }
        return LocalDate.parse(dateStr);
    }

    private Specification<Event> withEventTypeId(Long eventTypeId) {
        return (root, query, cb) -> eventTypeId == null ? cb.conjunction() :
                cb.equal(root.get("type").get("id"), eventTypeId);
    }

    private Specification<Event> withMedicalSpecialityId(Long medicalSpecialityId) {
        return (root, query, cb) -> medicalSpecialityId == null ? cb.conjunction() :
                cb.equal(root.get("speciality").get("id"), medicalSpecialityId);
    }

    private Specification<Event> withinDateRange(LocalDate startDate, LocalDate endDate) {
        return (root, query, cb) -> {
            if (startDate == null && endDate == null) {
                return cb.conjunction();
            } else if (startDate != null && endDate != null) {
                return cb.and(
                        cb.greaterThanOrEqualTo(root.get("startDate"), startDate),
                        cb.lessThanOrEqualTo(root.get("endDate"), endDate)
                );
            } else if (startDate != null) {
                return cb.greaterThanOrEqualTo(root.get("startDate"), startDate);
            } else {
                return cb.lessThanOrEqualTo(root.get("endDate"), endDate);
            }
        };
    }
}
