package com.kalpana.prasun.message;

import android.provider.BaseColumns;

/**
 * Created by Himanshu Singh on 23-Oct-17.
 */

class ContentContract {
    public static final class ContentEntry implements BaseColumns {

        public static final String TABLE_NAME = "data";
        public static final String COLUMN_CONTENT_MESSAGE = "data_message";
    }
}
