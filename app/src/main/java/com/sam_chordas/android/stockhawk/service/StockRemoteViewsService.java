package com.sam_chordas.android.stockhawk.service;

import android.app.Service;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;
import android.widget.AdapterView;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.sam_chordas.android.stockhawk.R;
import com.sam_chordas.android.stockhawk.data.QuoteColumns;
import com.sam_chordas.android.stockhawk.data.QuoteProvider;

public class StockRemoteViewsService extends RemoteViewsService {

    private static final int INDEX_ID = 0;
    private static final int INDEX_SYMBOL = 1;
    private static final int INDEX_PRICE = 2;
    private static final int INDEX_CHANGE = 3;
    private static final int INDEX_PERCENT_CHANGE = 4;
    private static final int INDEX_IS_UP = 5;

    private static final String[] QUOTE_COLUMNS = {
            QuoteColumns._ID,
            QuoteColumns.SYMBOL,
            QuoteColumns.BIDPRICE,
            QuoteColumns.CHANGE,
            QuoteColumns.PERCENT_CHANGE,
            QuoteColumns.ISUP
    };

    public StockRemoteViewsService() {
    }

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new RemoteViewsFactory() {

            private Cursor cursor = null;

            @Override
            public void onCreate() {

            }

            @Override
            public void onDataSetChanged() {
                if (cursor != null)
                    cursor.close();

                long id = Binder.clearCallingIdentity();
                cursor = getContentResolver().query(QuoteProvider.Quotes.CONTENT_URI,
                        QUOTE_COLUMNS,
                        QuoteColumns.ISCURRENT + " = ?",
                        new String[]{"1"},
                        null);

                Binder.restoreCallingIdentity(id);
            }

            @Override
            public void onDestroy() {
                if (cursor != null) {
                    cursor.close();
                    cursor = null;
                }
            }

            @Override
            public int getCount() {

                if(cursor == null)
                    return 0;

                return cursor.getCount();
            }

            @Override
            public RemoteViews getViewAt(int position) {

                if (position == AdapterView.INVALID_POSITION ||
                        cursor == null || !cursor.moveToPosition(position)) {
                    return null;
                }
                String symbol = cursor.getString(INDEX_SYMBOL);

                RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.widget_listitem);
                remoteViews.setTextViewText(R.id.widget_symbol, symbol);
                remoteViews.setTextViewText(R.id.widget_price, cursor.getString(INDEX_PRICE));
                remoteViews.setTextViewText(R.id.widget_change, cursor.getString(INDEX_CHANGE));


                Uri uri = QuoteProvider.Quotes.withSymbol(symbol);
                Intent intent = new Intent()
                        .setData(uri)
                        .putExtra(Intent.EXTRA_TEXT, symbol)
                        .putExtra("tag", "historical");

                remoteViews.setOnClickFillInIntent(R.id.widget_listitem, intent);

                return remoteViews;
            }

            @Override
            public RemoteViews getLoadingView() {
                return new RemoteViews(getPackageName(), R.layout.widget_listitem);
            }

            @Override
            public int getViewTypeCount() {
                return 1;
            }

            @Override
            public long getItemId(int position) {
                if (cursor.moveToPosition(position))
                    return cursor.getInt(INDEX_ID);

                return position;
            }

            @Override
            public boolean hasStableIds() {
                return true;
            }
        };
    }
}
