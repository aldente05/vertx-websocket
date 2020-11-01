package id.aldente.socket.model;

import id.aldente.socket.model.payload.DirectAllocationPayload;

import java.io.Serializable;
import java.util.List;

/**
 * Created by f.putra on 11/1/20.
 */
public class DirectAllocationParser implements Serializable {

    private static final long serialVersionUID = 8987961677015200677L;

    List<DirectAllocationPayload> data;

    public List<DirectAllocationPayload> getData() {
        return data;
    }

    public void setData(List<DirectAllocationPayload> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "DirectAllocationParser{" +
                "data=" + data +
                '}';
    }
}
