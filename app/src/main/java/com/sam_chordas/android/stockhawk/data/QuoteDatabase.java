package com.sam_chordas.android.stockhawk.data;

import net.simonvt.schematic.annotation.Database;
import net.simonvt.schematic.annotation.Table;

/**
 * Created by sam_chordas on 10/5/15.
 */
@Database(version = QuoteDatabase.VERSION)
public class QuoteDatabase {
<<<<<<< HEAD

    public static final int VERSION = 7;

    private QuoteDatabase(){

    }


    @Table(QuoteColumns.class) public static final String QUOTES = "quotes";
    @Table(QuoteColumns.class) public static final String QUOTES_HISTORY = "quotes_history";
=======
  private QuoteDatabase(){}

  public static final int VERSION = 7;

  @Table(QuoteColumns.class) public static final String QUOTES = "quotes";
>>>>>>> 1fcbb789df66de0c7e4d208d7ba462e35cf95106
}
