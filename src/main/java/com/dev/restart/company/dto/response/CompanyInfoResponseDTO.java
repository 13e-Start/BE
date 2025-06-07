package com.dev.restart.company.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "CompanyInfoResponse")
public record CompanyInfoResponseDTO(

    @Schema(description = "기업명")
    String name,

    @Schema(description = "사원수")
    Integer employeeCount,

    @Schema(description = "설립년도")
    Integer foundedYear,

    @Schema(description = "기업형태")
    String companyType,

    @Schema(description = "심볼 이미지")
    String symbolImageUrl
){}
