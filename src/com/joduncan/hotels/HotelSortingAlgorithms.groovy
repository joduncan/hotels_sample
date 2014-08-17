package com.joduncan.hotels
/**
 * Created by joshua on 8/17/2014.
 */
class HotelSortingAlgorithms {

    static def makeComparator(Closure comparison_criteria) {
        [ compare: comparison_criteria ] as Comparator
    }

    // min room price does not refer to the minimum prices across different hotels, it
    // refers to the minimum price for a room at this specific hotel.
    // I named it this way to give the flexibility of being able to sort all hotels low/high
    // based on the minimum or maximum price within each hotel. I'm not sure why someone would
    // want to find the lowest "high rollers" room price, but it seems fairly reasonable to allow
    // them the option to do so.
    static def min_room_price = makeComparator({ x, y ->
        x.rates.collect { it.base_price }.min() \
        <=> \
        y.rates.collect { it.base_price }.min()
    })

    static def mean_user_rating = makeComparator({ x, y ->
        x.user_ratings.collect { it.rating }.sum() / x.user_ratings.size() \
        <=> \
        y.user_ratings.collect { it.rating }.sum() / y.user_ratings.size()
    })

//////////////////////////////////////////////////////////////////////////////////////////
    // These were not part of the basic problem description, but they are mostly decent
    // examples of other possible ways we could sort hotels based on the provided data.

    // see wordy min_room_price comment above
    static def max_room_price = makeComparator({ x, y ->
        x.rates.collect { it.base_price }.max() \
        <=> \
        y.rates.collect { it.base_price }.max()
    })

    static def star_rating = makeComparator({ x, y -> x.star_rating <=> y.star_rating })

    static def num_user_ratings = makeComparator({ x, y -> x.user_ratings.size() <=> y.user_ratings.size() })

    // this is an elementary example, really. Please forgive me for not having
    // any strikingly insightful comparison algorithm, I didn't get nearly enough sleep
    // the past two nights(for unrelated reasons: http://ragnarrelay.com/race/greatriver).
    static def by_name = makeComparator({ x,y -> x.name.toLowerCase() <=> y.name.toLowerCase() })
}
