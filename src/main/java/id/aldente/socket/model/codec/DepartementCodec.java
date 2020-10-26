package id.aldente.socket.model.codec;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import id.aldente.socket.MainVerticle;
import id.aldente.socket.model.payload.DepartementPayload;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.MessageCodec;

import java.io.IOException;
import java.io.StringReader;

/**
 * Created by f.putra on 10/25/20.
 */
public class DepartementCodec implements MessageCodec<DepartementPayload, DepartementPayload> {

    @Override
    public void encodeToWire(Buffer buffer, DepartementPayload registerRequest) {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        try {
            String jsonToStr = ow.writeValueAsString(registerRequest);
            int length = jsonToStr.getBytes().length;
            buffer.appendInt(length);
            buffer.appendString(jsonToStr);
        } catch (JsonProcessingException e) {
            MainVerticle.getLogger(this).error("Error encoding [" + registerRequest + "] from " + this.name());
        }
    }

    @Override
    public DepartementPayload decodeFromWire(int position, Buffer buffer) {
        int length = buffer.getInt(position);
        // Get JSON string by it`s length
        // Jump 4 because getInt() == 4 bytes
        String jsonStr = buffer.getString(position += 4, position += length);
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(new StringReader(jsonStr), DepartementPayload.class);
        } catch (IOException e) {
            MainVerticle.getLogger(this).error("Error decoding [" + jsonStr + "] to " + this.name());
        }
        return null;
    }

    @Override
    public DepartementPayload transform(DepartementPayload registerRequest) {
        return registerRequest;
    }

    @Override
    public String name() {
        return this.getClass().getName();
    }

    @Override
    public byte systemCodecID() {
        return -1;
    }
}
