package dev.neordinary.zero.base;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum BaseResponseStatus implements BaseResponseStatusImpl {
    /**
     * 100 : 진행 정보
     */

    /**
     * 200 : 요청 성공
     */
    SUCCESS(HttpStatus.OK, "SUCCESS", "요청에 성공했습니다."),
    CREATED(HttpStatus.CREATED, "CREATED", "요청에 성공했으며 리소스가 정상적으로 생성되었습니다."),
    ACCEPTED(HttpStatus.ACCEPTED, "ACCEPTED", "요청에 성공했으나 처리가 완료되지 않았습니다."),
    DELETED(HttpStatus.NO_CONTENT, "DELETED", "요청에 성공했으며 더 이상 응답할 내용이 존재하지 않습니다."),

    /**
     * 300 : 리다이렉션
     */
    SEE_OTHER(HttpStatus.SEE_OTHER, "REDIRECT", "다른 주소로 요청해주세요."),

    /**
     * 400 : 요청 실패
     */
    INPUT_INVALID_VALUE(HttpStatus.BAD_REQUEST, "REQUEST_ERROR_001", "잘못된 요청입니다."),
    INVALID_ENUM(HttpStatus.BAD_REQUEST, "REQUEST_ERROR_002", "Enum 타입으로 변경할 수 없습니다."),
    CANNOT_FIND_USER(HttpStatus.NOT_FOUND, "REQUEST_ERROR_003", "요청한 사용자를 찾을 수 없습니다."),

    // Product
    CRAWLING_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "PRODUCT_ERROR_001", "크롤링 서버와의 연결에 실패했습니다."),
    PRODUCT_INDEX_OUT_OF_RANGE(HttpStatus.BAD_REQUEST, "PRODUCT_ERROR_002", "제품 리스트의 끝입니다."),
    PRODUCT_NOT_FOUND(HttpStatus.NOT_FOUND, "PRODUCT_ERROR_003", "검색한 제품이 존재하지 않습니다."),


    /**
     * 500 : 응답 실패
     */
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "RESPONSE_ERROR_001", "서버와의 연결에 실패했습니다."),
    BAD_GATEWAY(HttpStatus.BAD_GATEWAY, "RESPONSE_ERROR_002", "다른 서버로부터 잘못된 응답이 수신되었습니다."),
    INSUFFICIENT_STORAGE(HttpStatus.INSUFFICIENT_STORAGE, "RESPONSE_ERROR_003", "서버의 용량이 부족해 요청에 실패했습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
