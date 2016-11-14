package com.sam_chordas.android.stockhawk.rest;

import com.github.mikephil.charting.data.Entry;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by kienme on 14/11/16.
 *
 */

public class StockHistory {

    private Query query;

    public StockHistory(Query query) {
        this.query = query;
    }

    public int getCount() {
        return query.getCount();
    }

    public ArrayList<Entry> getClosingPrices(int count) {
        ArrayList<Entry> closingPrices = new ArrayList<Entry>();
        List<Quote> quotes = query.getResults().getQuotes();
        for (int i = 0; i < count; i++) {
            double price = quotes.get(i).getClose();
            closingPrices.add(new Entry(Float.parseFloat(String.format(Locale.US, "%.2f", price)), count - i - 1));
        }
        return closingPrices;
    }

    public ArrayList<String> getDates(int count) {
        ArrayList<String> dates = new ArrayList<>();
        List<Quote> quotes = query.getResults().getQuotes();
        for (int i = 0; i < count; i++) {
            dates.add(quotes.get(count - i - 1).getDate());
        }
        return dates;
    }

    public class Query {
        private Results results;
        private int count;

        public Query(Results results, int count) {
            this.results = results;
            this.count = count;
        }

        public int getCount() {
            return count;
        }

        public Results getResults() {
            return results;
        }
    }

    public class Results {
        private List<Quote> quote;

        public Results(List<Quote> quote) {
            this.quote = quote;
        }

        public List<Quote> getQuotes() {
            return quote;
        }
    }

    public class Quote {
        private String Symbol;
        private String Date;
        private double Close;

        public Quote(String Symbol, String Date, double Close) {
            this.Symbol = Symbol;
            this.Date = Date;
            this.Close = Close;
        }

        public double getClose() {
            return Close;
        }

        public String getSymbol() {
            return Symbol;
        }

        public String getDate() {
            return Date;
        }
    }
}
