package com.rightcode.mtc.endpoints;

import com.rightcode.mtc.dto.location.GetLocationListRequest;
import com.rightcode.mtc.dto.location.GetLocationListResponse;
import com.rightcode.mtc.dto.location.GetLocationTypeListRequest;
import com.rightcode.mtc.dto.location.GetLocationTypeListResponse;
import com.rightcode.mtc.store.entities.Location;
import com.rightcode.mtc.services.LocationService;
import com.rightcode.mtc.services.LocationTypeService;
import com.rightcode.mtc.store.entities.LocationType;
import lombok.RequiredArgsConstructor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.List;

@Endpoint
@RequiredArgsConstructor
public class LocationEndpoint {
    private final LocationService locationService;
    private final LocationTypeService locationTypeService;
    private final String TARGET_NAMESPACE = "http://www.rightcode.com/mtc/location";

    @PayloadRoot(namespace = TARGET_NAMESPACE, localPart = "getLocationListRequest")
    @ResponsePayload
    public GetLocationListResponse getLocationList(
            @RequestPayload GetLocationListRequest request
            ){
        List<Location> locations = locationService.getAll();
        return GetLocationListResponse.builder()
                .locations(locations.stream()
                        .map(this::locationMap)
                        .toList())
                .build();
    }

    @PayloadRoot(localPart = "getLocationTypeListRequest", namespace = TARGET_NAMESPACE)
    @ResponsePayload
    public GetLocationTypeListResponse getLocationTypeList(
            @RequestPayload GetLocationTypeListRequest request
    ){
        return GetLocationTypeListResponse.builder()
                .locationTypes(locationTypeService.getAll()
                        .stream()
                        .map(this::locationTypeMap)
                        .toList())
                .build();
    }


    private com.rightcode.mtc.dto.location.Location locationMap(Location location){
        return com.rightcode.mtc.dto.location.Location.builder()
                .id(location.getId())
                .number(location.getNumber())
                .type(locationTypeMap(location.getType()))
                .build();
    }

    private com.rightcode.mtc.dto.location.LocationType locationTypeMap(LocationType locationType){
        return com.rightcode.mtc.dto.location.LocationType.builder()
                .id(locationType.getId())
                .name(locationType.getName())
                .build();
    }
}
