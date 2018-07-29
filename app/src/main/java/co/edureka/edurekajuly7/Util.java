package co.edureka.edurekajuly7;

import android.net.Uri;

public class Util {

    public static final int DB_VERSION = 1;
    public static final String DB_NAME = "Customers.db";

    public static final String TAB_NAME = "Customer";

    public static final String COL_ID = "_ID";
    public static final String COL_NAME = "NAME";
    public static final String COL_PHONE = "PHONE";
    public static final String COL_EMAIL = "EMAIL";

    public static final String CREATE_TAB_QUERY = "create table Customer(" +
            "_ID integer primary key autoincrement," +
            "NAME varchar(256)," +
            "PHONE varchar(256)," +
            "EMAIL varchar(256)" +
            ")";

                                                    //   1              2                        3
    public static final Uri CUSTOMER_URI = Uri.parse("content://co.edureka.edurekajuly7.mycp/"+TAB_NAME);
}
