package se.emilsjolander.sprinkles;

import android.content.Context;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import se.emilsjolander.sprinkles.typeserializers.BooleanSerializer;
import se.emilsjolander.sprinkles.typeserializers.DateSerializer;
import se.emilsjolander.sprinkles.typeserializers.DoubleSerializer;
import se.emilsjolander.sprinkles.typeserializers.FloatSerializer;
import se.emilsjolander.sprinkles.typeserializers.IntSerializer;
import se.emilsjolander.sprinkles.typeserializers.LongSerializer;
import se.emilsjolander.sprinkles.typeserializers.StringSerializer;
import se.emilsjolander.sprinkles.typeserializers.TypeSerializer;

public class Sprinkles {

	static Sprinkles sInstance;
	Context mContext;
	List<Migration> mMigrations = new ArrayList<Migration>();
    Map<Class, TypeSerializer> typeSerializers = new ConcurrentHashMap<Class, TypeSerializer>();

	private Sprinkles() {
		typeSerializers.put(int.class, new IntSerializer());
        typeSerializers.put(Integer.class, new IntSerializer());

        typeSerializers.put(long.class, new LongSerializer());
        typeSerializers.put(Long.class, new LongSerializer());

        typeSerializers.put(float.class, new FloatSerializer());
        typeSerializers.put(Float.class, new FloatSerializer());

        typeSerializers.put(double.class, new DoubleSerializer());
        typeSerializers.put(Double.class, new DoubleSerializer());

        typeSerializers.put(boolean.class, new BooleanSerializer());
        typeSerializers.put(Boolean.class, new BooleanSerializer());

        typeSerializers.put(String.class, new StringSerializer());
        typeSerializers.put(Date.class, new DateSerializer());
	}

    /**
     *
     * @param context
     *      A context which is used for database operations. This context is not saved, however it's application context is.
     *
     * @return The singleton Sprinkles instance. Use this to add migrations.
     */
	public static Sprinkles getInstance(Context context) {
		if (sInstance == null) {
			sInstance = new Sprinkles();
		}
		sInstance.mContext = context.getApplicationContext();
		return sInstance;
	}

    /**
     * Add migrations to the underlying database. Every migration increments the database version.
     *
     * @param migration
     *      The migration that should be performed.
     */
	public void addMigration(Migration migration) {
		mMigrations.add(migration);
	}

    public <T> void registerType(Class<T> clazz, TypeSerializer<T> serializer) {
        typeSerializers.put(clazz, serializer);
    }

}
