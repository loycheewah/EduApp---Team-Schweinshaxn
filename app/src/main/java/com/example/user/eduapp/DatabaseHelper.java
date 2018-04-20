package com.example.user.eduapp;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.database.Cursor;

public class DatabaseHelper extends SQLiteOpenHelper{
    public static final String DATABASE_NAME = "eduapp.db";

    //student table
    public static final String STU_TABLE = "student_table";
    public static final String STU_COL1 = "stu_id";
    public static final String STU_COL2 = "username";
    public static final String STU_COL3 = "password";
    public static final String STU_COL4 = "first_name";
    public static final String STU_COL5 = "last_name";
    public static final String STU_COL6 = "course_name";

    //institution table
    public static final String INS_TABLE = "inst_table";
    public static final String INS_COL1 = "inst_id";
    public static final String INS_COL2 = "loc_id";
    public static final String INS_COL3 = "inst_rating";
    public static final String INS_COL4 = "name";

    //location table
    public static final String LOC_TABLE = "location_table";
    public static final String LOC_COL1 = "loc_id";
    public static final String LOC_COL2 = "name";

    //interest table
    public static final String INTEREST_TABLE = "interest_table";
    public static final String INTEREST_COL1 = "interest_id";
    public static final String INTEREST_COL2 = "name";

    //course table
    public static final String COURSE_TABLE = "course_table";
    public static final String COURSE_COL1 = "course_id";
    public static final String COURSE_COL2 = "name";
    public static final String COURSE_COL3 = "inst_id";
    public static final String COURSE_COL4 = "course_rating";
    public static final String COURSE_COL5 = "cop"; //cut-off point
    public static final String COURSE_COL6 = "interest_id";

    //users table (for admins)
    public static final String ADM_TABLE = "admin_table";
    public static final String ADM_COL1 = "adm_id";
    public static final String ADM_COL2 = "username";
    public static final String ADM_COL3 = "password";
    public static final String ADM_COL4 = "first_name";
    public static final String ADM_COL5 = "last_name";

    /*
    //student_rating_ins
    public static final String RATING_INS_TABLE = "rating_ins_table";
    public static final String RI_COL1 = "ri_id";
    public static final String RI_COL2 = "stu_id";
    public static final String RI_COL3 = "rating";

    //student_rating_course
    public static final String RATING_COURSE_TABLE = "rating_course_table";
    public static final String RC_COL1 = "rc_id";
    public static final String RC_COL2 = "stu_id";
    public static final String RC_COL3 = "rating";
    */


    //constructor
    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db = this.getWritableDatabase(); //to see database created
    }
    public void onCreate(SQLiteDatabase db){

        //location table
        db.execSQL("create table "+LOC_TABLE+" ("+LOC_COL1+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                LOC_COL2+" INTEGER) ");

        //interest table
        db.execSQL("create table "+INTEREST_TABLE+" ("+INTEREST_COL1+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                INTEREST_COL2+" INTEGER) ");

        //institution table
        db.execSQL("create table "+INS_TABLE+" ("+INS_COL1+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                INS_COL2+" INTEGER, "+
                INS_COL3+" INTEGER, "+
                INS_COL4+" TEXT, "+
                "FOREIGN KEY ("+INS_COL2+") REFERENCES "+LOC_TABLE+" ) ");

        //course table
        db.execSQL("create table "+COURSE_TABLE+" ("+COURSE_COL1+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                COURSE_COL2+" TEXT, "+
                COURSE_COL3+" INTEGER, "+
                COURSE_COL4+" INTEGER, "+
                COURSE_COL5+" INTEGER,"+
                COURSE_COL6+" INTEGER,"+
                "FOREIGN KEY ("+COURSE_COL3+") REFERENCES "+INS_TABLE+", "+
                "FOREIGN KEY ("+COURSE_COL6+") REFERENCES "+INTEREST_TABLE+")");

        //users table (admin)
        db.execSQL("create table "+ADM_TABLE+" ("+ADM_COL1+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                ADM_COL2+" TEXT, "+
                ADM_COL3+" TEXT, "+
                ADM_COL4+" TEXT, "+
                ADM_COL5+" TEXT) ");

        //student table
        db.execSQL("create table "+STU_TABLE+" ("+STU_COL1+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                STU_COL2+" TEXT, "+
                STU_COL3+" TEXT, "+
                STU_COL4+" TEXT, "+
                STU_COL5+" TEXT, "+
                STU_COL6+" TEXT)");

        //dummy data
        insertStuData("john123","password", "John", "Appleseed", "Mechanical Engineering");
        insertLocData("East");
        insertLocData("West");
        insertLocData("South");
        insertLocData("North");
        insertInterestData("Math");
        insertInterestData("Physics");
        insertInterestData("Science");
        insertInterestData("Biology");
        insertInterestData("Chemistry");
        insertInsData(1, 4, "Temasek Polytechnic");
        insertInsData(2, 4, "Nanyang Polytechnic");
        insertInsData(4, 4, "Ngee Ann Polytechnic");
        insertCourseData("Mechanical Engineering", 1, 3, 1);
        insertCourseData("Chemical Engineering", 1, 3, 5);
        insertCourseData("BioEngineering", 2, 4, 4);
        insertCourseData("Aerospace Engineering", 3, 4, 2);
        insertCourseData("Computer Engineering", 3, 5, 1);
    }

    public void onUpgrade (SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("drop table if exists "+STU_TABLE);
        db.execSQL("drop table if exists "+INS_TABLE);
        db.execSQL("drop table if exists "+LOC_TABLE);
        db.execSQL("drop table if exists "+INTEREST_TABLE);
        db.execSQL("drop table if exists "+COURSE_TABLE);
        db.execSQL("drop table if exists "+ADM_TABLE);
        onCreate(db);
    }

    public boolean insertStuData(String username, String password, String first_name, String last_name, String course_name){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(STU_COL2,username);
        contentValues.put(STU_COL3,password);
        contentValues.put(STU_COL4,first_name);
        contentValues.put(STU_COL5,last_name);
        contentValues.put(STU_COL6,course_name);
        long result = db.insert(STU_TABLE, null, contentValues);
        db.close();
        if (result==-1)return false;
        return true;
    }

    public boolean insertInsData(int loc_id, int rating, String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(INS_COL2, loc_id);
        contentValues.put(INS_COL3, rating);
        contentValues.put(INS_COL4, name);
        long result = db.insert(INS_TABLE, null, contentValues);
        db.close();
        if (result == -1) return false;
        return true;
    }

    public boolean insertLocData(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(LOC_COL2, name);
        long result = db.insert(LOC_TABLE, null, contentValues);
        db.close();
        if (result == -1) return false;
        return true;
    }

    public boolean insertInterestData(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(INTEREST_COL2, name);
        long result = db.insert(INTEREST_TABLE, null, contentValues);
        db.close();
        if (result == -1) return false;
        return true;
    }

    public boolean insertCourseData(String name, int inst_id, int rating, int interest_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COURSE_COL2, name);
        contentValues.put(COURSE_COL3, inst_id);
        contentValues.put(COURSE_COL4, rating);
        contentValues.put(COURSE_COL6, interest_id);
        long result = db.insert(COURSE_TABLE, null, contentValues);
        db.close();
        if (result == -1) return false;
        return true;
    }

    public boolean insertUserData(String username, String password, String first_name, String last_name) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ADM_COL2,username);
        contentValues.put(ADM_COL3,password);
        contentValues.put(ADM_COL4,first_name);
        contentValues.put(ADM_COL5,last_name);
        long result = db.insert(ADM_TABLE, null, contentValues);
        db.close();
        if (result == -1) return false;
        return true;
    }

    //pass username and password to this method to verify login. Will return true if successful
    public boolean verifyLogin(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select username, password from "+STU_TABLE,null);
        String uname, pwd;
        if(cursor.moveToFirst()){
            do{
                uname = cursor.getString(0);
                pwd = cursor.getString(1);
                if(uname.equals(username) && pwd.equals(password)) {
                    db.close();
                    return true;
                }
            }while(cursor.moveToNext());
        }
        db.close();
        return false;
    }

    //call this method to update institution rating
    /*public boolean updateInsRating(String institutionName, int rating) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        int avgInsRating = calcAvgInsRating(rating);
        contentValues.put(INS_COL3,avgInsRating);
        db.update(INS_TABLE, contentValues, "name ="+institutionName,new String[] { id });
        db.close();
        return true;
    }*/

    //call this method to update course rating
    /*public boolean updateCourseRating(String courseName, int rating) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        int avgCourseRating = calcAvgCourseRating(rating);
        contentValues.put(COURSE_COL4, avgCourseRating);
        db.update(COURSE_TABLE, contentValues, "name = " + courseName, new String[]{id});
        db.close();
        return true;
    }*/

    //call this method to display all the query results
    public Cursor getSearchResults(String location, String institution, String cop, String interest, String course) {
        SQLiteDatabase db = this.getWritableDatabase();

        String whereStr = "";
        String joinStr = "";

        if(location!=null || institution!=null || cop!=null || interest!=null || course!=null) {
            whereStr = " where ";
            joinStr = " c inner join "+INTEREST_TABLE+" i on c.interest_id = i.interest_id "+
                    "inner join "+INS_TABLE+" s on c.inst_id = s.inst_id "+
                    "inner join "+LOC_TABLE+" l on s.loc_id = l.loc_id";
        }
        if(location!=null) whereStr = whereStr + " location = " + location;
        if(institution!=null) whereStr = whereStr + " institution = " + institution;
        if(cop!=null) whereStr = whereStr + " cop = " + cop;
        if(interest!=null) whereStr = whereStr + " interest = " + interest;
        if(course!=null) whereStr = whereStr + " course = " + course;

        Cursor res = db.rawQuery("select * from "+COURSE_TABLE+joinStr+whereStr,null);
        return res;
    }
}

