package com.zhongdao.gather.log;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class Log {

    private static Logger logger = LogManager.getLogger(Log.class.getName());

    public static void debug(String msg){
        logger.debug(msg);
    }
}
