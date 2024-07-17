package com.rightcode.mtc.services;

import com.rightcode.mtc.faults.BusinessFault;
import com.rightcode.mtc.faults.FaultCode;
import com.rightcode.mtc.store.entities.Location;
import com.rightcode.mtc.store.repositories.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class LocationService extends GenericService<Location, LocationRepository>{

    public LocationService(LocationRepository repository) {
        super("location", repository);
    }

    /**
     * @param locationTypeId
     * ID типа аудитории
     * @param day
     * день периода
     * @param startTime
     * начало периода
     * @param endTime
     * конец периода
     * @return
     * Список аудиторий заданного типа,
     * которые свободны в указанный период указанного дня
     */
    public List<Location> getFreeByTypeAndPeriod(
            @NonNull Long locationTypeId,
            @NonNull LocalDate day,
            @NonNull LocalTime startTime,
            @NonNull LocalTime endTime
            ){
        return getRepository().findByTypeAndPeriod(locationTypeId, day, startTime, endTime);
    }

    /**

     * @param day
     * день периода
     * @param startTime
     * начало периода
     * @param endTime
     * конец периода
     * @return
     * Список аудиторий,
     * которые свободны в указанный период указанного дня
     */
    public List<Location> getFreeByPeriod(
            @NonNull LocalDate day,
            @NonNull LocalTime startTime,
            @NonNull LocalTime endTime
    ){
        return getRepository().findByPeriod(day, startTime, endTime);
    }

    public List<Location> getAllByType(@NonNull Long locationTypeId){
        return getRepository().findByType(locationTypeId);
    }
}
