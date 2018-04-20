package com.example.user.eduapp.JobDemand;


        import android.content.ContentValues;
        import android.content.Context;
        import android.database.sqlite.SQLiteDatabase;
        import android.database.sqlite.SQLiteOpenHelper;
        import android.database.Cursor;
        import android.os.StrictMode;

        import org.json.JSONArray;
        import org.json.JSONObject;

        import java.io.BufferedReader;
        import java.io.IOException;
        import java.io.InputStream;
        import java.io.InputStreamReader;
        import java.net.URL;
        import java.util.Calendar;

        import javax.net.ssl.HttpsURLConnection;

public class JobDemandDatabase extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "JobDemand.db";
    private static final String TABLE_NAME = "JobDemand";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_YEAR = "year";
    private static final String COLUMN_INDUSTRY1 = "industry1";
    private static final String COLUMN_INDUSTRY2 = "industry2";
    private static final String COLUMN_JOB_VACANCY_RATE = "job_vacancy_rate";

    private SQLiteDatabase db;

    public JobDemandDatabase(Context context) {
        super(context, DATABASE_NAME, null, 1);
        db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase dbarg) {
        this.db = dbarg;
        boolean error = false;
        StrictMode.ThreadPolicy oldpolicy = StrictMode.getThreadPolicy();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        for (int year = 1990; year <= Calendar.getInstance().get(Calendar.YEAR) - 1; year++) {
            try {
                URL url = new URL("https://data.gov.sg/api/action/datastore_search?resource_id=2da1e0e4-b14e-424c-aa18-1ac2fceea74c&q=" + String.valueOf(year));
                HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
                InputStream is = con.getInputStream();
                JSONObject jsonObj = new JSONObject(getStringFromInputStream(is));
                JSONObject obj = jsonObj.getJSONObject("result");
                obj.getJSONArray("records");
            } catch (Exception e) {
                error = true;
                break;
            }
        }
        if (!error) {
            String sql = "DROP TABLE IF EXISTS " + TABLE_NAME;
            db.execSQL(sql);
            sql = "CREATE TABLE " + TABLE_NAME + " (" + COLUMN_ID + " INT PRIMARY KEY NOT NULL, " + " " + COLUMN_YEAR + " TEXT NOT NULL, " + " " + COLUMN_INDUSTRY1 + " TEXT NOT NULL, " + " " + COLUMN_INDUSTRY2 + " TEXT NOT NULL, " + " " + COLUMN_JOB_VACANCY_RATE + " TEXT NOT NULL)";
            db.execSQL(sql);
            for (int year = 1990; year <= Calendar.getInstance().get(Calendar.YEAR) - 1; year++) {
                try {
                    URL url = new URL("https://data.gov.sg/api/action/datastore_search?resource_id=2da1e0e4-b14e-424c-aa18-1ac2fceea74c&q=" + String.valueOf(year));
                    HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
                    InputStream is = con.getInputStream();
                    JSONObject jsonObj = new JSONObject(getStringFromInputStream(is));
                    JSONObject obj = jsonObj.getJSONObject("result");
                    JSONArray records = obj.getJSONArray("records");
                    ContentValues cv = new ContentValues();
                    for (int i = 0; i < records.length(); i++) {
                        cv.put("id", records.getJSONObject(i).getInt("_id"));
                        cv.put("year", records.getJSONObject(i).getString("year"));
                        cv.put("industry1", records.getJSONObject(i).getString("industry1"));
                        cv.put("industry2", records.getJSONObject(i).getString("industry2"));
                        cv.put("job_vacancy_rate", records.getJSONObject(i).getString("job_vacancy_rate"));
                        db.insert(TABLE_NAME, null, cv);
                    }
                } catch (Exception e) {
                }
            }
        } else {
            String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" + COLUMN_ID + " INT PRIMARY KEY NOT NULL, " + " " + COLUMN_YEAR + " TEXT NOT NULL, " + " " + COLUMN_INDUSTRY1 + " TEXT NOT NULL, " + " " + COLUMN_INDUSTRY2 + " TEXT NOT NULL, " + " " + COLUMN_JOB_VACANCY_RATE + " TEXT NOT NULL)";
            db.execSQL(sql);
        }
        StrictMode.setThreadPolicy(oldpolicy);
    }

    @Override
    public void onUpgrade(SQLiteDatabase dbarg, int oldVersion, int newVersion) {
        this.db = dbarg;
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_NAME);
        onCreate(db);
    }

    public Cursor ReadDatabase(String industry1) {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM JobDemand WHERE((";
        for (int year = Calendar.getInstance().get(Calendar.YEAR) - 8; year <= Calendar.getInstance().get(Calendar.YEAR) - 1; year++)
            sql = sql + "year = '" + String.valueOf(year) + "' OR ";
        sql = sql + "0 = 1) AND " + "industry1 = '" + industry1 + "')";
        Cursor res = db.rawQuery(sql, null);
        return res;
    }

    private static String getStringFromInputStream(InputStream is) {
        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();
        String line;
        try {

            br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
    }
}
