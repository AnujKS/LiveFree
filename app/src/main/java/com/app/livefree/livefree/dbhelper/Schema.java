package com.app.livefree.livefree.dbhelper;

public class Schema {
    public static final String CREATE_TABLE_USER = "create table if not exists " + DbTableStrings.TABLE_NAME_USER_MODEL +
            "( user_id integer primary key autoincrement, "
            + DbTableStrings.NAME + " string, "
            + DbTableStrings.PHONENUMBER + " string) ";

    public static final String CREATE_TABLE_TASKS = "create table if not exists " + DbTableStrings.TABLE_NAME_TASK +
            "( task_id integer primary key autoincrement, "
            + DbTableStrings.DESCRIPTION + " string, "
            + DbTableStrings.PRIORITY + " string, "
            + DbTableStrings.DURATION + " string, "
            + DbTableStrings.REMAINING_DISTANCE + " string, "
            + DbTableStrings.REMAINING_TIME + " string, "
            + DbTableStrings.TAG + " string, "
            + DbTableStrings.TASK_USER_ID + " string, "
            + DbTableStrings.TASK_LOCATION_ID + " int, "
            + DbTableStrings.TASK_TIME+ " string) ";

        public static final String CREATE_TABLE_LOCATION = "create table if not exists " + DbTableStrings.TABLE_NAME_INTEREST_LOCATION +
                "( location_id integer primary key autoincrement, "
                + DbTableStrings.LATITUDE + " string, "
                + DbTableStrings.LONGITUDE + " string, "
                + DbTableStrings.AREA + " string, "
                + DbTableStrings.AREA_FRIENDLY_NAME + " string) ";


}


