package com.rightcode.mtc.store.repositories.specifications;

import com.rightcode.mtc.dto.scheduleSlot.ScheduleSlotFilterProperties;
import com.rightcode.mtc.store.entities.ScheduleSlot;
import com.rightcode.mtc.store.entities.SlotLocation;
import com.rightcode.mtc.store.entities.User;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Subquery;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class ScheduleSlotSpecification {
    public Specification<ScheduleSlot> build(ScheduleSlotFilterProperties filter){
        return withEventTypeId(filter.getEventTypeId())
                .and(withEventStageId(filter.getEventStageId()))
                .and(withSpecialityId(filter.getSpecialityId()))
                .and(withEmployeeId(filter.getEmployeeId()))
                .and(withLocationId(filter.getLocationTypeId()))
                .and(withLocationNumber(filter.getLocationNumber()))
                .and(withStartDate(filter.getStartDate()))
                .and(withEndDate(filter.getEndDate()))
                .and(withDraft(filter.getDraft()));
    }

    private Specification<ScheduleSlot> withEventTypeId(Long eventTypeId){
        return ((root, query, cb) -> eventTypeId == null ? cb.conjunction() :
                cb.equal(root.get("event").get("type").get("id"), eventTypeId));
    }

    private Specification<ScheduleSlot> withEventStageId(Long eventStageId){
        return ((root, query, cb) -> eventStageId == null ? cb.conjunction() :
                cb.equal(root.get("stage").get("id"), eventStageId));
    }

    private Specification<ScheduleSlot> withSpecialityId(Long specialityId){
        return ((root, query, cb) -> specialityId == null ? cb.conjunction() :
                cb.equal(root.get("event").get("speciality").get("id"), specialityId));

    }

    private Specification<ScheduleSlot> withEmployeeId(Long employeeId){
        return ((root, query, cb) -> {
            if(employeeId == null){
                return cb.conjunction();
            }
            Subquery<Long> scheduleSlotSubquery = query.subquery(Long.class);
            Root<User> userForSSRoot = scheduleSlotSubquery.from(User.class);
            Join<User, ScheduleSlot> usersToSlotsJoin = userForSSRoot.join("slots");
            scheduleSlotSubquery.select(usersToSlotsJoin.get("id"))
                    .where(cb.equal(userForSSRoot.get("id"), employeeId));

            Subquery<Long> slotLocationSubquery = query.subquery(Long.class);
            Root<User> userForSLRoot = slotLocationSubquery.from(User.class);
            Join<User, SlotLocation> usersToLocationsJoin = userForSLRoot.join("locations");
            slotLocationSubquery.select(usersToLocationsJoin.get("slot").get("id"))
                    .where(cb.equal(userForSLRoot.get("id"), employeeId));

            return cb.or(
                    cb.in(root.get("id")).value(scheduleSlotSubquery),
                    cb.in(root.get("id")).value(slotLocationSubquery)
            );
        });
    }

    private Specification<ScheduleSlot> withLocationId(Long locationId){
        return (root, query, cb) -> {
            if(locationId == null){
                return cb.conjunction();
            }
            Subquery<Long> slotLocationSubquery = query.subquery(Long.class);
            Root<SlotLocation> slotLocationRoot = slotLocationSubquery.from(SlotLocation.class);
            slotLocationSubquery.select(slotLocationRoot.get("slot").get("id"))
                    .where(
                            cb.equal(slotLocationRoot.get("location").get("type").get("id"), locationId)
                    );

            return cb.in(root.get("id")).value(slotLocationSubquery);
        };
    }

    private Specification<ScheduleSlot> withLocationNumber(Integer locationNumber){
        return ((root, query, cb) -> {
            if(locationNumber == null){
                return cb.conjunction();
            }
            Subquery<Long> slotLocationSubquery = query.subquery(Long.class);
            Root<SlotLocation> slotLocationRoot = slotLocationSubquery.from(SlotLocation.class);
            slotLocationSubquery.select(slotLocationRoot.get("slot").get("id"))
                    .where(
                            cb.equal(slotLocationRoot.get("location").get("number"), locationNumber)
                    );

            return cb.in(root.get("id")).value(slotLocationSubquery);
        });
    }

    private Specification<ScheduleSlot> withStartDate(String startDate){
        return ((root, query, cb) -> {
            if(startDate == null){
                return cb.conjunction();
            }
            LocalDate date = LocalDate.parse(startDate);
            return cb.greaterThanOrEqualTo(root.get("dop"), date);
        });
    }
    private Specification<ScheduleSlot> withEndDate(String endDate){
        return ((root, query, cb) -> {
            if(endDate == null){
                return cb.conjunction();
            }
            LocalDate date = LocalDate.parse(endDate);
            return cb.lessThanOrEqualTo(root.get("dop"), date);
        });
    }

    private Specification<ScheduleSlot> withDraft(Boolean draft){
        return ((root, query, cb) -> {
            if(draft == null){
                return cb.conjunction();
            }
            return cb.equal(root.get("draft"), draft);
        });
    }
}
