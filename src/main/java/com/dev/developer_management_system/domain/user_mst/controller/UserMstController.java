package com.dev.developer_management_system.domain.user_mst.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.developer_management_system.docs.swagger.SwaggerTags;
import com.dev.developer_management_system.domain.user_mst.dto.UserMstDto;
import com.dev.developer_management_system.domain.user_mst.service.UserMstService;
import com.dev.developer_management_system.global.dto.ApiResponseDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

/**
 * UserMstController
 * 개발자 인력 관리 API Controller
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user-mst")
@Tag(name = SwaggerTags.USER_NAME, description = SwaggerTags.USER_DESC)
public class UserMstController {

    private final UserMstService userMstService;

    /**
     * 개발자 목록 조회
     * GET /user-mst/list
     */
    @GetMapping("/list")
    @Operation(
        summary = "사용자(개발자 인력) 전체 조회",
        description = SwaggerTags.USER_GET_ALL_DESC
    )
    public ApiResponseDto<List<UserMstDto.UserMstResponseDto>> getUserMstList() {

        List<UserMstDto.UserMstResponseDto> users = userMstService.getUserMstList();
        return ApiResponseDto.success(HttpStatus.OK.getReasonPhrase(), users);
    }

    /**
     * 개발자 상세 조회
     */
    @GetMapping("/{userNo}")
    @Operation(
        summary = "사용자(개발자 인력) 상세 조회",
        description = SwaggerTags.USER_GET_DETAIL_DESC
    )
    public ResponseEntity<ApiResponseDto<UserMstDto.UserMstDetailResponseDto>> getUserMstDetail(@PathVariable Long userNo) {

        return userMstService.getUserMstDetail(userNo)
            .map(user -> ResponseEntity.ok(
                    ApiResponseDto.success(HttpStatus.OK.getReasonPhrase(), user)
            ))
            .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponseDto.fail(HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.getReasonPhrase())));
    }

}