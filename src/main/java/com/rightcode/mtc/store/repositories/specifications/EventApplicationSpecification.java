package com.rightcode.mtc.store.repositories.specifications;

import com.rightcode.mtc.dto.eventApplication.EventApplicationFilterProps;
import com.rightcode.mtc.store.entities.EventApplication;
import com.rightcode.mtc.store.entities.enums.ApplicationStatus;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class EventApplicationSpecification {
    public Specification<EventApplication> build(EventApplicationFilterProps filterProps) {
        return withEventId(filterProps.getEventId())
                .and(withMedicalWorkerId(filterProps.getMedicalWorkerId()))
                .and(withApplicationStatus(
                        filterProps.getApplicationStatus() != null
                                ? ApplicationStatus.valueOf(filterProps.getApplicationStatus())
                                : null
                        )
                )
                .and(withinDateRange(
                        filterProps.getStartDos() != null
                                ? LocalDate.parse(filterProps.getStartDos())
                                : null,
                        filterProps.getEndDos() != null
                                ? LocalDate.parse(filterProps.getEndDos())
                                : null
                        )
                );
    }

    private Specification<EventApplication> withEventId(Long eventId) {
        return ((root, query, cb) -> eventId == null ? cb.conjunction() :
                cb.equal(root.get("event").get("id"), eventId));
    }

    private Specification<EventApplication> withMedicalWorkerId(Long medicalWorkerId) {
        return ((root, query, cb) -> medicalWorkerId == null ? cb.conjunction() :
                cb.equal(root.get("medicalWorker").get("id"), medicalWorkerId));
    }

    private Specification<EventApplication> withApplicationStatus(ApplicationStatus applicationStatus) {
        return ((root, query, cb) -> applicationStatus == null ? cb.conjunction() :
                cb.equal(root.get("status"), applicationStatus));
    }

    private Specification<EventApplication> withinDateRange(LocalDate startDate, LocalDate endDate) {
        return (root, query, cb) -> {
            if (startDate == null && endDate == null) {
                return cb.conjunction();
            } else if (startDate != null && endDate != null) {
                if (startDate.isEqual(endDate)) {
                    return cb.equal(root.get("dos"), startDate);
                }
                return cb.between(root.get("dos"), startDate, endDate);
            } else if (startDate != null) {
                return cb.greaterThanOrEqualTo(root.get("dos"), startDate);
            } else {
                return cb.lessThanOrEqualTo(root.get("dos"), endDate);
            }
        };
    }
}
