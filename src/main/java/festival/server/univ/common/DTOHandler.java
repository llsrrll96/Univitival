package festival.server.univ.common;

import org.springframework.stereotype.Component;

@Component
public class DTOHandler {

    // Method to build a successful response with data
    public <T> ApiResponseDTO<T> buildSuccessResponse(ApiResponseDTO<T> apiResponseDTO, T data) {
        apiResponseDTO.setSuccess(1); // 1 represents success
        apiResponseDTO.setMessage("Request processed successfully");
        apiResponseDTO.setData(data);
        return apiResponseDTO;
    }

    // Method to build a custom success response with data
    public <T> ApiResponseDTO<T> buildCustomSuccessResponse(ApiResponseDTO<T> apiResponseDTO, int customSuccessValue) {
        apiResponseDTO.setSuccess(customSuccessValue);
        apiResponseDTO.setMessage("Custom success response");
        return apiResponseDTO;
    }

    // Method to build an error response
    public <T> ApiResponseDTO<T> buildErrorResponse(ApiResponseDTO<T> apiResponseDTO) {
        apiResponseDTO.setSuccess(0); // 0 represents failure
        apiResponseDTO.setMessage("Request processing failed");
        return apiResponseDTO;
    }
}
