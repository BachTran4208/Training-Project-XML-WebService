package com.example.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.LOGServiceUpdateRequest;
import com.example.demo.entity.LogEntity;
import com.example.demo.repository.LogRepository;
import com.example.demo.util.XmlUtil;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class LogService {

    private final LogRepository repository;
    private final XmlUtil xmlUtil;

    @Transactional
    public void save(LOGServiceUpdateRequest request) throws Exception {

        System.out.println("ApplicationName = "
                + request.getApplicationName());

        System.out.println("RequestGUID = "
                + request.getRequestGUID());

        System.out.println("RawPayload = "
                + request.getRawLogPayload());

        System.out.println("LogDetails = "
                + request.getLogDetails());

        LogEntity entity = new LogEntity();

        entity.setApplicationName(request.getApplicationName());
        entity.setRequestGUID(request.getRequestGUID());
        entity.setAccountName(request.getAccountName());
        entity.setRealmName(request.getRealmName());
        entity.setClientIP(request.getClientIP());
        entity.setGlobalReference(request.getGlobalReference());
        entity.setLocalReference(request.getLocalReference());
        entity.setTotalRecords(request.getTotalRecords());
        entity.setEnableGovernmentLogging(request.isEnableGovernmentLogging());
        entity.setEnableBlindLogging(request.isEnableBlindLogging());
        entity.setEnableEncryptedLogging(request.isEnableEncryptedLogging());
        entity.setEncryptionKeyGroup(request.getEncryptionKeyGroup());
        entity.setEnableForwardLog(request.isEnableForwardLog());
        entity.setEnableAutoMultiInsert(request.isEnableAutoMultiInsert());
        entity.setEnableCompressBlobs(request.isEnableCompressBlobs());

        entity.setRawPayload(request.getRawLogPayload());

        if (request.getLogDetails() != null) {
            String detailXml = xmlUtil.convert(request.getLogDetails());
            entity.setLogDetailXml(detailXml);
        }

        repository.save(entity);

    }

}
