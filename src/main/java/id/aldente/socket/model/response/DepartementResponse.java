package id.aldente.socket.model.response;

import id.aldente.socket.model.MessageType;
import id.aldente.socket.model.payload.DepartementPayload;

/**
 * Created by f.putra on 10/25/20.
 */
public class DepartementResponse {
    private DepartementPayload data;

    private String error;

    private Boolean success;

    private String cause;

    private MessageType messageType;

    private String senderId;

    public DepartementResponse() {
        this.data = data;
        this.error = error;
        this.success = success;
        this.cause = cause;
        this.messageType = messageType;
        this.senderId = senderId;
    }

    public DepartementPayload getData() {
        return data;
    }

    public void setData(DepartementPayload data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    @Override
    public String toString() {
        return "DepartementResponse{" +
                "data=" + data +
                ", error='" + error + '\'' +
                ", success=" + success +
                ", cause='" + cause + '\'' +
                ", messageType=" + messageType +
                ", senderId='" + senderId + '\'' +
                '}';
    }
}
