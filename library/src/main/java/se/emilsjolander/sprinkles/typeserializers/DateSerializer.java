package se.emilsjolander.sprinkles.typeserializers;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.Date;

/**
 * Created by emilsjolander on 27/12/13.
 */
public class DateSerializer implements TypeSerializer<Date> {

    @Override
    public Date unpack(Cursor c, String name) {
        return new Date(c.getLong(c.getColumnIndexOrThrow(name)));
    }

    @Override
    public void pack(Date object, ContentValues cv, String name) {
        cv.put(name, object.getTime());
    }

    @Override
    public SqlType getSqlType() {
        return SqlType.INTEGER;
    }

}
