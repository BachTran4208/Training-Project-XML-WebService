package com.example.demo.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.demo.dto.LOGServiceUpdateRequest;
import com.example.demo.dto.LOGServiceUpdateRequest.LogDetails;
import com.example.demo.entity.LogEntity;
import com.example.demo.repository.LogRepository;
import com.example.demo.util.XmlUtil;

public class LogServiceTest {

    @Mock
    private LogRepository repository;

    @Mock
    private XmlUtil xmlUtil;

    @InjectMocks
    private LogService logService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void testSave_WithLogDetails_ShouldSaveEntityWithXml() throws Exception {

        // Arrange
        LOGServiceUpdateRequest request = new LOGServiceUpdateRequest();

        request.setApplicationName("APP_TEST");
        request.setRequestGUID("GUID-001");
        request.setAccountName("account");
        request.setRealmName("realm");
        request.setClientIP("127.0.0.1");
        request.setGlobalReference("global-ref");
        request.setLocalReference("local-ref");

        request.setTotalRecords(10);

        request.setEnableGovernmentLogging(true);
        request.setEnableBlindLogging(false);
        request.setEnableEncryptedLogging(true);

        request.setEncryptionKeyGroup("KEY_GROUP");

        request.setEnableForwardLog(true);
        request.setEnableAutoMultiInsert(false);
        request.setEnableCompressBlobs(true);

        request.setRawLogPayload("<xml>payload</xml>");

        LogDetails logDetails = new LogDetails();
        request.setLogDetails(logDetails);

        when(xmlUtil.convert(any()))
                .thenReturn("<detail>xml</detail>");

        when(repository.save(any(LogEntity.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));


        // Act
        logService.save(request);


        // Assert
        verify(xmlUtil, times(1))
                .convert(request.getLogDetails());

        verify(repository, times(1))
                .save(any(LogEntity.class));
    }


    @Test
    void testSave_WithoutLogDetails_ShouldSaveEntityWithoutXml() throws Exception {

        // Arrange
        LOGServiceUpdateRequest request = new LOGServiceUpdateRequest();

        request.setApplicationName("APP_TEST");
        request.setRequestGUID("GUID-002");

        request.setRawLogPayload("payload");

        request.setLogDetails(null);


        // Act
        logService.save(request);


        // Assert
        verify(xmlUtil, never())
                .convert(any());

        verify(repository, times(1))
                .save(any(LogEntity.class));
    }


    @Test
    void testSave_WhenRepositoryThrowsException_ShouldThrowException()
            throws Exception {

        // Arrange
        LOGServiceUpdateRequest request = new LOGServiceUpdateRequest();

        request.setApplicationName("APP_TEST");

        when(repository.save(any(LogEntity.class)))
                .thenThrow(new RuntimeException("DB error"));


        // Act & Assert
        org.junit.jupiter.api.Assertions.assertThrows(
                RuntimeException.class,
                () -> logService.save(request)
        );


        verify(repository, times(1))
                .save(any(LogEntity.class));
    }
}
