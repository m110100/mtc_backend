package com.rightcode.mtc.services;

import com.rightcode.mtc.dto.eventParticipant.EventParticipantExcelRequest;
import com.rightcode.mtc.dto.eventParticipant.EventParticipantExcelResponse;
import com.rightcode.mtc.dto.eventParticipant.EventParticipantResponse;
import com.rightcode.mtc.dto.eventParticipant.EventParticipantsRequest;
import com.rightcode.mtc.faults.BusinessFault;
import com.rightcode.mtc.faults.FaultCode;
import com.rightcode.mtc.store.entities.User;
import com.rightcode.mtc.store.entities.enums.ApplicationStatus;
import com.rightcode.mtc.store.repositories.UserRepository;
import com.rightcode.mtc.utils.EventParticipantMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EventParticipantService {
    private final EventParticipantMapper eventParticipantMapper;
    private final UserRepository repository;

    public EventParticipantResponse getEventParticipants(EventParticipantsRequest request) {
        long nextCursor;
        Pageable pageable;
        if (request.getCursor().getAfter() != null) {
            pageable = PageRequest.of(
                    Math.toIntExact(request.getCursor().getAfter()) / request.getCursor().getLimit(),
                    request.getCursor().getLimit()
            );
            nextCursor = request.getCursor().getAfter() + request.getCursor().getLimit();
        } else {
            pageable = PageRequest.of(0, request.getCursor().getLimit());
            nextCursor = request.getCursor().getLimit();
        }

        ApplicationStatus status = null;
        if (request.getFilter().getEventStatus() != null && !request.getFilter().getEventStatus().isEmpty()) {
            try {
                status = ApplicationStatus.valueOf(request.getFilter().getEventStatus());
            } catch (IllegalArgumentException e) {
                throw new BusinessFault("Invalid event status: " + request.getFilter().getEventStatus(), FaultCode.E003.name());
            }
        }

        Page<User> page = repository.findUsersByEventIdAndStatus(
                request.getFilter().getEventId(),
                status,
                pageable
        );

        return eventParticipantMapper.toResponse(page, nextCursor);
    }

    public EventParticipantExcelResponse getEventParticipantExcel(EventParticipantExcelRequest request) {
        ApplicationStatus status = null;
        if (request.getEventStatus() != null && !request.getEventStatus().isEmpty()) {
            try {
                status = ApplicationStatus.valueOf(request.getEventStatus());
            } catch (IllegalArgumentException e) {
                throw new BusinessFault("Invalid event status: " + request.getEventStatus(), FaultCode.E003.name());
            }
        }

        List<User> users = repository.findUsersByEventIdAndStatus(
                request.getEventId(),
                status
        );

        if (users.isEmpty()) {
            throw new BusinessFault("No participants found for the event", FaultCode.E001.name());
        }

        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            Sheet sheet = workbook.createSheet("Event Participants");

            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("ID");
            headerRow.createCell(1).setCellValue("Фамилия");
            headerRow.createCell(2).setCellValue("Имя");
            headerRow.createCell(3).setCellValue("Отчество");
            headerRow.createCell(4).setCellValue("Username");
            headerRow.createCell(5).setCellValue("Телефон");
            headerRow.createCell(6).setCellValue("Email");
            headerRow.createCell(7).setCellValue("Дата рождения");

            int rowNum = 1;
            for (User user : users) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(user.getId());
                row.createCell(1).setCellValue(user.getSurname());
                row.createCell(2).setCellValue(user.getName());
                row.createCell(3).setCellValue(user.getPatronymic());
                row.createCell(4).setCellValue(user.getUsername());
                row.createCell(5).setCellValue(user.getPhoneNumber());
                row.createCell(6).setCellValue(user.getEmail());
                row.createCell(7).setCellValue(user.getDob().toString());
            }

            workbook.write(outputStream);
            byte[] excelBytes = outputStream.toByteArray();
            String base64Excel = Base64.getEncoder().encodeToString(excelBytes);

            return new EventParticipantExcelResponse("participants.xlsx", base64Excel);

        } catch (IOException e) {
            throw new BusinessFault("Error creating Excel file: " + e, FaultCode.E003.name());
        }
    }
}
