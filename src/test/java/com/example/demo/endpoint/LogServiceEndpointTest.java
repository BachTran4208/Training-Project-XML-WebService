package com.example.demo.endpoint;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.demo.dto.LOGServiceUpdateRequest;
import com.example.demo.dto.LOGServiceUpdateResponse;
import com.example.demo.service.LogService;

public class LogServiceEndpointTest {

    @Mock
    private LogService service;

    @InjectMocks
    private LogServiceEndpoint endpoint;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testUpdate_ShouldReturnSuccessResponse() throws Exception {
        // Arrange
        LOGServiceUpdateRequest request = new LOGServiceUpdateRequest();

        doNothing().when(service).save(request);

        // Act
        LOGServiceUpdateResponse response = endpoint.update(request);

        // Assert
        assertNotNull(response);
        assertEquals(0, response.getUpdateResultCode());
        assertNotNull(response.getResponseGUID());

        assertDoesNotThrow(() -> UUID.fromString(response.getResponseGUID()));

        verify(service, times(1)).save(request);
    }

    @Test
    void testUpdate_WhenServiceThrowsException_ShouldPropagateException() throws Exception {
        // Arrange
        LOGServiceUpdateRequest request = new LOGServiceUpdateRequest();

        doThrow(new RuntimeException("Save failed"))
                .when(service)
                .save(request);

        // Act + Assert
        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> endpoint.update(request)
        );

        assertEquals("Save failed", exception.getMessage());

        verify(service, times(1)).save(request);
    }
}