package id.aldente.socket.model.request;

import com.google.gson.Gson;
import id.aldente.socket.model.payload.DepartementPayload;
import org.apache.flink.api.common.serialization.DeserializationSchema;
import org.apache.flink.api.common.serialization.SerializationSchema;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.api.java.typeutils.TypeExtractor;

import java.io.IOException;

public class BaseRequestSchema implements DeserializationSchema<DepartementPayload>, SerializationSchema<DepartementPayload> {


    @Override
    public DepartementPayload deserialize(byte[] bytes) throws IOException {
        String strMessage = new String(bytes);
        return new Gson().fromJson(strMessage, DepartementPayload.class);
    }

    @Override
    public boolean isEndOfStream(DepartementPayload departementPayload) {
        return false;
    }

    @Override
    public byte[] serialize(DepartementPayload element) {
        Gson g = new Gson();
        String message = g.toJson(element);
        return message.getBytes();
    }

    @Override
    public TypeInformation<DepartementPayload> getProducedType() {
        return TypeExtractor.getForClass(DepartementPayload.class);
    }
}
