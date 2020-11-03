package id.aldente.socket.util;



public enum CommonMessage implements IMessage {
    SUCCESS("OK", 0),
    NO_RESULT("No Result", 0),
    FAILED("Failed", 400),
    VALIDATION_CUSTOMER_PHONE_NO_IS_EMPTY("Customer Phone is empty", 400),
    VALIDATION_WRONG_FORMAT_PHONE_NO("Customer Phone is wrong format", 400),
    VALIDATION_POLICY_NO_REQUIRED("Policy No is required", 400),
    VALIDATION_ALREADY_REGISTERED_AS_PH("You are already registered as a policy holder", 400),
    VALIDATION_POLICY_NOT_FOUND("Policy not found", 400),
    VALIDATION_INFORCE_POLICY_NOT_FOUND("Inforce Policy not found", 400),
    VALIDATION_PHONE_NO_UNIQUE("Customer Phone is not unique", 400),
    VALIDATION_PHONE_NO_SAME_WITH_AGENT("Customer Phone same with agent",  400),
    VALIDATION_BOOKING_CODE_OR_POLICY_NO("No bookingCode or policyNo found", 400),
    VALIDATION_INVALID_OTP("User entered invalid OTP", 400),
    VALIDATION_OTP_EXPIRED("OTP has been expired", 400),
    VALIDATION_ERROR("Validation Error", 400),
    VALIDATION_WRONG_QR_CODE("Your QR Code is wrong",  400),
    INITIAL_STATE_ERROR("Exception in Initial State event:", 500),
    VERIFICATION_STATE_ERROR("Exception in Verification State event: ", 500),
    INVALID_REQUEST("Invalid request", 500),
    INTERNAL_SERVER_ERROR("Internal Server Error", 500),
    UNAUTHORIZED("Unauthorized Access", 500),
    CUSTOMER_CREDENTIALS_ERROR("Customer credentials not correct", 500),
    DOCUMENT_NOT_SAVED("Document can not be saved", 400);

    private String message;
    private int code;

    CommonMessage(String message, int code) {
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
