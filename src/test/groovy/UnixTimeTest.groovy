package test.groovy

import com.ripplex.unixtime.UnixTime
import spock.lang.Specification
import spock.lang.Unroll

class UnixTimeTest extends Specification {

    @Unroll
    def "コンストラクタ引数とsetUnixTime()に#inputを与えた各インスタンスはgetUnixTime()で同じ#outputを返す"() {
        setup:
        def ut1 = new UnixTime(input)
        def ut2 = new UnixTime()
        ut2.setUnixTime(input)

        expect:
        ut1.getUnixTime() == ut2.getUnixTime()

        where:
        input       | output      || result
        0           | 0           || true
        1           | 1           || true
        -1          | -1          || true
        2147483647  | 2147483647  || true
        -2147483648 | -2147483648 || true
    }

    @Unroll
    def "コンストラクタ引数に#inputを与えるとgetUnixTime()で#outputが返る"() {
        setup:
        def ut = new UnixTime(input)

        expect:
        ut.getUnixTime() == output

        where:
        input       | output      || result
        0           | 0           || true
        1           | 1           || true
        -1          | -1          || true
        2147483647  | 2147483647  || true
        -2147483648 | -2147483648 || true
    }

    @Unroll
    def "インスタンスに#inputを与えるとgetYear()が#yearを返す"() {
        setup:
        def ut = new UnixTime(input)

        expect:
        ut.getYear() == year

        where:
        input       || year | month | day
        -2147483648 || 1901 | 12    | 13    // 1901/12/13 20:45:51
        -2145916800 || 1901 | 12    | 31    // 1901/12/31 23:59:59
        -2145916800 || 1902 | 01    | 01    // 1902/01/01 00:00:00
        -31536001   || 1968 | 12    | 31    // 1968/12/31 23:59:59 (閏年)
        -31536000   || 1969 | 01    | 01    // 1969/01/01 00:00:00
        -86400      || 1969 | 12    | 31    // 1969/12/31 00:00:00
        -1          || 1969 | 12    | 31    // 1969/12/31 23:59:59
        0           || 1970 | 01    | 01    // 1970/01/01 00:00:00
        1           || 1970 | 01    | 01    // 1970/01/01 00:00:01
        86400       || 1970 | 01    | 02    // 1970/01/02 00:00:00
        31535999    || 1970 | 12    | 31    // 1970/12/31 23:59:59
        31536000    || 1971 | 01    | 01    // 1971/01/01 00:00:00
        63072000    || 1972 | 01    | 01    // 1972/01/01 00:00:00
        94608000    || 1972 | 12    | 31    // 1972/12/31 00:00:00 (閏年)
        94694400    || 1973 | 01    | 01    // 1973/01/01 00:00:00
        915148800   || 1999 | 01    | 01    // 1999/01/01 00:00:00
        946684799   || 1999 | 12    | 31    // 1999/12/31 23:59:59
        946684800   || 2000 | 01    | 01    // 2000/01/01 00:00:00 (閏年)
        2147483647  || 2038 | 01    | 19    // 2038/01/19 03:14:00
    }

    @Unroll
    def "インスタンスに#inputを与えるとgetMonth()が#monthを返す"() {
        setup:
        def ut = new UnixTime(input)

        expect:
        ut.getMonth() == month

        where:
        input       || year | month | day
        -2147483648 || 1901 | 12    | 13    // 1901/12/13 20:45:51
        -31536001   || 1968 | 12    | 31    // 1968/12/31 23:59:59 (閏年)
        -31536000   || 1969 | 01    | 01    // 1969/01/01 00:00:00
        -86400      || 1969 | 12    | 31    // 1969/12/31 00:00:00
        -1          || 1969 | 12    | 31    // 1969/12/31 23:59:59
        0           || 1970 | 01    | 01    // 1970/01/01 00:00:00
        1           || 1970 | 01    | 01    // 1970/01/01 00:00:01
        86400       || 1970 | 01    | 02    // 1970/01/02 00:00:00
        31535999    || 1970 | 12    | 31    // 1970/12/31 23:59:59
        31536000    || 1971 | 01    | 01    // 1971/01/01 00:00:00
        63072000    || 1972 | 01    | 01    // 1972/01/01 00:00:00
        94608000    || 1972 | 12    | 31    // 1972/12/31 00:00:00 (閏年)
        94694400    || 1973 | 01    | 01    // 1973/01/01 00:00:00
        915148800   || 1999 | 01    | 01    // 1999/01/01 00:00:00
        946684799   || 1999 | 12    | 31    // 1999/12/31 23:59:59
        946684800   || 2000 | 01    | 01    // 2000/01/01 00:00:00 (閏年)
        2147483647  || 2038 | 01    | 19    // 2038/01/19 03:14:00
    }

    @Unroll
    def "インスタンスに#inputを与えるとgetDay()が#dayを返す"() {
        setup:
        def ut = new UnixTime(input)

        expect:
        ut.getDay() == day

        where:
        input       || year | month | day
        -2147483648 || 1901 | 12    | 13    // 1901/12/13 20:45:51
        -31536001   || 1968 | 12    | 31    // 1968/12/31 23:59:59 (閏年)
        -31536000   || 1969 | 01    | 01    // 1969/01/01 00:00:00
        -86400      || 1969 | 12    | 31    // 1969/12/31 00:00:00
        -1          || 1969 | 12    | 31    // 1969/12/31 23:59:59
        0           || 1970 | 01    | 01    // 1970/01/01 00:00:00
        1           || 1970 | 01    | 01    // 1970/01/01 00:00:01
        86400       || 1970 | 01    | 02    // 1970/01/02 00:00:00
        31535999    || 1970 | 12    | 31    // 1970/12/31 23:59:59
        31536000    || 1971 | 01    | 01    // 1971/01/01 00:00:00
        63072000    || 1972 | 01    | 01    // 1972/01/01 00:00:00
        94608000    || 1972 | 12    | 31    // 1972/12/31 00:00:00 (閏年)
        94694400    || 1973 | 01    | 01    // 1973/01/01 00:00:00
        915148800   || 1999 | 01    | 01    // 1999/01/01 00:00:00
        946684799   || 1999 | 12    | 31    // 1999/12/31 23:59:59
        946684800   || 2000 | 01    | 01    // 2000/01/01 00:00:00 (閏年)
        2147483647  || 2038 | 01    | 19    // 2038/01/19 03:14:00
    }
}
