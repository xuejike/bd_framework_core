package com.bidanet.bdcms.core.typeConversion;

import org.springframework.format.Formatter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by xuejike on 2016-06-18.
 */
public class DateFormat implements Formatter<Long> {

    protected SimpleDateFormat ymd=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//    protected SimpleDateFormat ymdHs=new SimpleDateFormat("yyyy-MM-dd HH:mm");

    @Override
    public Long parse(String s, Locale locale) throws ParseException {
        Date parse = ymd.parse(s+" 23:59:59");
        return parse.getTime();
    }

    @Override
    public String print(Long aLong, Locale locale) {
        return aLong.toString();
    }
}
