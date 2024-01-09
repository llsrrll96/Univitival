package festival.server.univ.common;

public class ApiResponseDTO<T> {

    // Variables to hold API response details
    private int success; // 0 for failure, 1 for success
    private String message;
    private T data; // Generic data type

    // Default constructor
    public ApiResponseDTO() {
        // Initialize default values
        this.success = 0;
        this.message = "";
        this.data = null;
    }

    // Getter and setter methods for success, message, and data
    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
