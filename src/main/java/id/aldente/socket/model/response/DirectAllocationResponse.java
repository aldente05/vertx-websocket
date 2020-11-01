package id.aldente.socket.model.response;

import id.aldente.socket.model.MessageType;
import id.aldente.socket.model.payload.DepartementPayload;
import id.aldente.socket.model.payload.DirectAllocationPayload;

import java.util.List;

/**
 * Created by f.putra on 11/1/20.
 */
public class DirectAllocationResponse {
    private List<DirectAllocationPayload> data;

    private String error;

    private Boolean success;

    private String cause;

    private MessageType messageType;

    public DirectAllocationResponse() {
        this.data = data;
        this.error = error;
        this.success = success;
        this.cause = cause;
        this.messageType = messageType;
    }

    public List<DirectAllocationPayload> getData() {
        return data;
    }

    public void setData(List<DirectAllocationPayload> data) {
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

    @Override
    public String toString() {
        return "DirectAllocationResponse{" +
                "data=" + data +
                ", error='" + error + '\'' +
                ", success=" + success +
                ", cause='" + cause + '\'' +
                ", messageType=" + messageType +
                '}';
    }
}
