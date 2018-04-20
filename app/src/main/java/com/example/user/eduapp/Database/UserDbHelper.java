package com.example.user.eduapp.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class UserDbHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "UserInfo.DB";
    public static final int DATABASE_VERSION = 1;

    public static final String CREATE_QUERY =
            "CREATE TABLE " + UserContract.UserInfo.TABLE_NAME + "(" + UserContract.UserInfo.USER_NAME + " TEXT," +
                    UserContract.UserInfo.USER_PASSWORD + " TEXT," + UserContract.UserInfo.USER_EMAIL + " TEXT," +
                    UserContract.UserInfo.USER_COURSE + " TEXT," + UserContract.UserInfo.USER_INSTITUTE + " TEXT," + UserContract.UserInfo.USER_COURSERATING + " TEXT," +
                    UserContract.UserInfo.USER_EDUCATIONRATING + " TEXT," + UserContract.UserInfo.USER_SCHOOLLIFERATING + " TEXT);";

    public static final String CREATE_COURSE_QUERY =
            "CREATE TABLE " + CourseContract.CourseInfo.TABLE_NAME + "(" + CourseContract.CourseInfo.COURSE_NAME + " TEXT," +
                    CourseContract.CourseInfo.L1R4 + " TEXT," + CourseContract.CourseInfo.L1R5 + " TEXT," +
                    CourseContract.CourseInfo.Location + " TEXT," + CourseContract.CourseInfo.Rating + " TEXT," + CourseContract.CourseInfo.INSTITUTION + " TEXT," +
                    CourseContract.CourseInfo.COURSEDETAILS + " TEXT," + CourseContract.CourseInfo.COURSELEARNINGS + " TEXT," + CourseContract.CourseInfo.FURTHERSTUDIES + " TEXT,"
                    + CourseContract.CourseInfo.INTEREST + " TEXT," + CourseContract.CourseInfo.INDUSTRY + " TEXT);";

    public UserDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.e("DATABASE OPERATIONS", "DATABASE CREATED/OPENED...");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_QUERY);
        db.execSQL(CREATE_COURSE_QUERY);

        //dummy data

        addCourseInformation("Comp Eng","10","13","West", "1", "Singapore Polytechnic", "Computer engineering is a discipline that integrates several fields of electrical engineering and computer science required to develop computer hardware and software", "Computing, Sensors, Arduino, Computer Architecture", "Security, AI and Machine Learning etc", "Hands on fixing gadgets", "Manufacturing", db);
        addCourseInformation("Chemistry","8","10","West", "3", "Singapore Polytechnic", "Chemistry is the study of matter, its properties, how and why substances combine or separate to form other substances, and how substances interact with energy. ", "Analytical chemistry, Physical chemistry, Organic chemistry, Inorganic chemistry, Biochemistry", "Chemical engineering, Geochemistry, Forensic chemistry", "Science", "Medicine", db);
        addCourseInformation("Physics","6","7","West", "5", "Singapore Polytechnic", "Computer engineering is a discipline that integrates several fields of electrical engineering and computer science required to develop computer hardware and software", "Computing, Sensors, Arduino, Computer Architecture", "Security, AI and Machine Learning etc", "Science", "Manufacturing", db);

        addCourseInformation("Maths","9","13","North", "2", "Republic Polytechnic", " Mathematics is the science that deals with the logic of shape, quantity and arrangement", "Mathematical Sciences is a broad term that includes the academic disciplines of Pure Mathematics, Applied and Computational Mathematics, and Statistics","Statistics, Data Analytics and Applied Maths etc", "Numbers,graphs and figures", "Data Analytics", db);
        addCourseInformation("History","9","13","North", "2", "Republic Polytechnic", " Mathematics is the science that deals with the logic of shape, quantity and arrangement", "Mathematical Sciences is a broad term that includes the academic disciplines of Pure Mathematics, Applied and Computational Mathematics, and Statistics","Statistics, Data Analytics and Applied Maths etc", "Numbers,graphs and figures", "Data Analytics", db);
        addCourseInformation("Geography","9","13","North", "2", "Republic Polytechnic", " Mathematics is the science that deals with the logic of shape, quantity and arrangement", "Mathematical Sciences is a broad term that includes the academic disciplines of Pure Mathematics, Applied and Computational Mathematics, and Statistics","Statistics, Data Analytics and Applied Maths etc", "Numbers,graphs and figures", "Data Analytics", db);

        addCourseInformation("Comp Sci","12","17","South", "3", "Nanyang Polytechnic", "Computer Science is the study of computers and computational systems.", "Programming (Java, Python)", "Cypber Security and Artificial Intelligence etc", "Playing computer games", "Computing", db);
        addCourseInformation("Biology","8","11","South", "3", "Nanyang PolyTechnic", "Biology is the natural science that involves the study of life and living organisms, including their physical structure, chemical composition, function, development and evolution.", "Animal Biology, Biochemistry, Molecular Biology and Biophysics", "Evolutionary Biology, Genetic Sciences, Immunology and Microbiological Sciences etc ", "Health issues", "Medicine", db);
        addCourseInformation("Medicine","5","6", "South", "3", "Nanyang Polytechnic", "Medicine is the science and practice of the diagnosis, treatment, and prevention of disease.", "Blood, Respiratory & Cardiovascular Systems", "Anaesthetics, Radiology, Paediatrics etc", "Health issues", "Medicine", db);

        addCourseInformation("Economics","7","9", "East", "3", "Temasek Polytechnic", "Medicine is the science and practice of the diagnosis, treatment, and prevention of disease.", "Blood, Respiratory & Cardiovascular Systems", "Anaesthetics, Radiology, Paediatrics etc", "Health issues", "Medicine", db);
        addCourseInformation("Business","9","10", "East", "3", "Temasek Polytechnic", "Medicine is the science and practice of the diagnosis, treatment, and prevention of disease.", "Blood, Respiratory & Cardiovascular Systems", "Anaesthetics, Radiology, Paediatrics etc", "Health issues", "Medicine", db);
        addCourseInformation("English","11","14", "South", "3", "Temasek Polytechnic", "Medicine is the science and practice of the diagnosis, treatment, and prevention of disease.", "Blood, Respiratory & Cardiovascular Systems", "Anaesthetics, Radiology, Paediatrics etc", "Health issues", "Medicine", db);

        addInformation("chernyuzi","chernyuzi","sky@gmail.com","Maths", "Nanyang Polytechnic","3","4","2", db);
        addInformation("kohkianwoon","kohkianwoon","dog@gmail.com","Comp Eng","Singapore Polytechnic", "2","5","3",db);
        addInformation("aryasreekuma","aryasreekumar","cat@gmail.com","Medicine", "Republic Polytechnic", "3", "4", "2", db);

        Log.e("DATABASE OPERATIONS", "TABLE CREATED...");
    }


    //Course methods
    public void addCourseInformation(String coursename, String L1R4, String L1R5, String Location,String Rating, String Institution, String CourseDetails, String CourseLearnings, String FurtherStudies, String Interest, String Industry, SQLiteDatabase db) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(CourseContract.CourseInfo.COURSE_NAME, coursename);
        contentValues.put(CourseContract.CourseInfo.L1R4, L1R4);
        contentValues.put(CourseContract.CourseInfo.L1R5, L1R5);
        contentValues.put(CourseContract.CourseInfo.Location, Location);
        contentValues.put(CourseContract.CourseInfo.Rating, Rating);
        contentValues.put(CourseContract.CourseInfo.INSTITUTION, Institution);
        contentValues.put(CourseContract.CourseInfo.COURSEDETAILS, CourseDetails);
        contentValues.put(CourseContract.CourseInfo.COURSELEARNINGS, CourseLearnings);
        contentValues.put(CourseContract.CourseInfo.FURTHERSTUDIES, FurtherStudies);
        contentValues.put(CourseContract.CourseInfo.INTEREST, Interest);
        contentValues.put(CourseContract.CourseInfo.INDUSTRY, Industry);
        db.insert(CourseContract.CourseInfo.TABLE_NAME , null, contentValues);
        Log.e("DATABASE OPERATIONS", "NEW ENTRY CREATED...");
    }

    public Cursor getCourseInformations(SQLiteDatabase db){
        Cursor c;
        String[] projections = {CourseContract.CourseInfo.COURSE_NAME,CourseContract.CourseInfo.L1R4,
                CourseContract.CourseInfo.L1R5, CourseContract.CourseInfo.Location,CourseContract.CourseInfo.Rating,CourseContract.CourseInfo.INSTITUTION,
                CourseContract.CourseInfo.COURSEDETAILS,CourseContract.CourseInfo.COURSELEARNINGS,CourseContract.CourseInfo.FURTHERSTUDIES,CourseContract.CourseInfo.INTEREST,CourseContract.CourseInfo.INDUSTRY};
        c = db.query(CourseContract.CourseInfo.TABLE_NAME,projections,null,null,null,null,null);
        return c;
    }


    //User methods
    public void addInformation(String username, String password, String email, String course, String institute, String courserating,String educationrating, String schoolliferating, SQLiteDatabase db) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(UserContract.UserInfo.USER_NAME, username);
        contentValues.put(UserContract.UserInfo.USER_PASSWORD, password);
        contentValues.put(UserContract.UserInfo.USER_COURSE, course);
        contentValues.put(UserContract.UserInfo.USER_EMAIL, email);
        contentValues.put(UserContract.UserInfo.USER_INSTITUTE,institute);
        contentValues.put(UserContract.UserInfo.USER_COURSERATING,courserating);
        contentValues.put(UserContract.UserInfo.USER_EDUCATIONRATING,educationrating);
        contentValues.put(UserContract.UserInfo.USER_SCHOOLLIFERATING,schoolliferating);
        db.insert(UserContract.UserInfo.TABLE_NAME, null, contentValues);
        Log.e("DATABASE OPERATIONS", "NEW ENTRY CREATED...");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public Cursor getInformations(SQLiteDatabase db){
        Cursor c;
        String[] projections = {UserContract.UserInfo.USER_NAME,UserContract.UserInfo.USER_PASSWORD,UserContract.UserInfo.USER_COURSE,UserContract.UserInfo.USER_INSTITUTE,UserContract.UserInfo.USER_COURSERATING,UserContract.UserInfo.USER_EDUCATIONRATING,UserContract.UserInfo.USER_SCHOOLLIFERATING};
        c = db.query(UserContract.UserInfo.TABLE_NAME,projections,null,null,null,null,null);
        return c;
    }


}


