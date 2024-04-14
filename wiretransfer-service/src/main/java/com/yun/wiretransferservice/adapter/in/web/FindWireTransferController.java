package com.yun.wiretransferservice.adapter.in.web;

import com.yun.common.anotation.WebAdapter;
import com.yun.wiretransferservice.application.port.in.ReadWIreTransferUseCase;
import com.yun.wiretransferservice.domain.WireTransfer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@WebAdapter
@RestController
@RequiredArgsConstructor
@RequestMapping("/wire-transfer")
public class FindWireTransferController {

    private final ReadWIreTransferUseCase findWireTransferUseCase;

    @GetMapping("/{from-membership-id}")
    public ResponseEntity findWireTransferHistory(@PathVariable("from-membership-id") String fromMembershipId,
                                                  @PageableDefault(size = 20, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<WireTransfer> wireTransferPage = findWireTransferUseCase.findWireTransferHistory(fromMembershipId, pageable);
        return ResponseEntity.ok().body(wireTransferPage);
    }
}
