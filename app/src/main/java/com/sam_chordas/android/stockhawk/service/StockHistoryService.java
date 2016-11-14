package com.sam_chordas.android.stockhawk.service;

import com.sam_chordas.android.stockhawk.rest.StockHistory;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by kienme on 14/11/16.
 *
 */

public class StockHistoryService {
    public interface StockHistoryAPI{
        @GET("v1/public/yql?&format=json&diagnostics=true&env=store://datatables.org/alltableswithkeys&callback=")
        Call<StockHistory> getHistory(
                @Query("q") String query
        );
    }
}
