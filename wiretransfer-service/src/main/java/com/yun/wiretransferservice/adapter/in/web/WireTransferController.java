package com.yun.wiretransferservice.adapter.in.web;

import com.yun.common.WebAdapter;
import com.yun.wiretransferservice.adapter.in.web.model.WireTransferRequest;
import com.yun.wiretransferservice.application.port.in.WireTransferUseCase;
import com.yun.wiretransferservice.domain.WireTransfer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@WebAdapter
@RestController
@RequiredArgsConstructor
@RequestMapping("/wire-transfer")
public class WireTransferController {

    private final WireTransferUseCase wireTransferUseCase;

    @PostMapping("/create")
    public ResponseEntity wireTransferAmount(@RequestBody WireTransferRequest wireTransferRequest) {
        WireTransfer wireTransfer = wireTransferUseCase.requestWireTransfer(wireTransferRequest.toCommand());
        return ResponseEntity.ok().body(wireTransfer);
    }
}
