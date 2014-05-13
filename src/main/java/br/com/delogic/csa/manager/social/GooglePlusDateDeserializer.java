package br.com.delogic.csa.manager.social;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class GooglePlusDateDeserializer extends JsonDeserializer<Date> {

    private SimpleDateFormat    df  = new SimpleDateFormat("yyyy-MM-dd");

    private static final Logger log = LoggerFactory.getLogger(GooglePlusDateDeserializer.class);

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
