package com.example.demo.endpoint;

import java.util.UUID;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.example.demo.dto.LOGServiceUpdateRequest;
import com.example.demo.dto.LOGServiceUpdateResponse;
import com.example.demo.service.LogService;

import lombok.RequiredArgsConstructor;

@Endpoint
@RequiredArgsConstructor
public class LogServiceEndpoint {

    private static final String NAMESPACE = "http://webservices.ins.com/WsLogServiceExample";
    private final LogService service;

    @PayloadRoot(namespace = NAMESPACE, localPart = "LOGServiceUpdateRequest")
    @ResponsePayload
    public LOGServiceUpdateResponse update(
            @RequestPayload LOGServiceUpdateRequest request)
            throws Exception {

        service.save(request);

        LOGServiceUpdateResponse response = new LOGServiceUpdateResponse();

        response.setUpdateResultCode(0);
        response.setResponseGUID(UUID.randomUUID().toString());

        return response;

    }

}
