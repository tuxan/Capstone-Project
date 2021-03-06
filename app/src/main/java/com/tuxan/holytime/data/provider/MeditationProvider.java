package com.tuxan.holytime.data.provider;

import android.content.ContentResolver;
import android.net.Uri;

import net.simonvt.schematic.annotation.ContentProvider;
import net.simonvt.schematic.annotation.ContentUri;
import net.simonvt.schematic.annotation.InexactContentUri;
import net.simonvt.schematic.annotation.TableEndpoint;

@ContentProvider(authority = MeditationProvider.AUTHORITY, database = HolyTimeDatabase.class)
public final class MeditationProvider {

    public static final String AUTHORITY = "com.tuxan.holytime";

    @TableEndpoint(table = HolyTimeDatabase.MEDITATIONS)
    public static class Meditations {

        @ContentUri(path = "meditations",
                type = ContentResolver.CURSOR_DIR_BASE_TYPE + "/vnd.com.tuxan.holytime.meditation",
                defaultSort = MeditationColumns.WEEK_NUMBER + " DESC",
                limit = "10"
        )
        public static final Uri meditationList = Uri.parse("content://" + AUTHORITY + "/meditations");

        @InexactContentUri(
                path = "meditations/weekfilter/#",
                type = ContentResolver.CURSOR_DIR_BASE_TYPE + "/vnd.com.tuxan.holytime.meditation",
                name = "byWeekNumber",
                whereColumn = MeditationColumns.WEEK_NUMBER,
                pathSegment = 2
        )
        public static final Uri byWeekNumber(int weekNumber) {
            return Uri.parse("content://" + AUTHORITY + "/meditations/weekfilter/" + weekNumber);
        }

        @InexactContentUri(
                path = "meditations/*",
                type = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/vnd.com.tuxan.holytime.meditation",
                name = "byId",
                whereColumn = MeditationColumns._ID,
                pathSegment = 1
        )
        public static final Uri byId(String id) {
            return Uri.parse("content://" + AUTHORITY + "/meditations/" + id);
        }
    }

}
