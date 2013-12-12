package br.com.delogic.csa.manager.social;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;

public class GooglePlusDateDeserializer extends JsonDeserializer<Date> {

    private SimpleDateFormat df  = new SimpleDateFormat("yyyy-MM-dd");

    private final Logger     log = Logger.getLogger(GooglePlusDateDeserializer.class);

    @Override
    public Date deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        try {
            return df.parse(jp.getText());
        } catch (ParseException e) {
            log.error("error deserealizing google plust date", e);
            return null;
        }
    }

}
