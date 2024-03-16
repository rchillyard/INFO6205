package edu.neu.coe.info6205.util;

import org.apache.log4j.Appender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import org.apache.log4j.spi.LoggerFactory;
import org.apache.log4j.spi.LoggerRepository;
import org.apache.log4j.spi.LoggingEvent;

import java.util.Enumeration;
import java.util.ResourceBundle;
import java.util.function.Supplier;

@SuppressWarnings("rawtypes")
public class LazyLogger extends Logger {

    public LazyLogger(Class<?> clazz) {
        super("LazyLogger");
        logger = Logger.getLogger(clazz);
    }

    public void trace(Supplier<String> fMessage) {
        if (logger.isTraceEnabled())
            logger.trace(fMessage.get());
    }

    public void trace(Supplier<String> fMessage, Throwable t) {
        if (logger.isTraceEnabled())
            logger.trace(fMessage.get(), t);
    }

    public void debug(Supplier<String> fMessage) {
        if (logger.isDebugEnabled())
            logger.debug(fMessage.get());
    }

    public void debug(Supplier<String> fMessage, Throwable t) {
        if (logger.isDebugEnabled())
            logger.debug(fMessage.get(), t);
    }

    public boolean isTraceEnabled() {
        return logger.isTraceEnabled();
    }

    public boolean isDebugEnabled() {
        return logger.isDebugEnabled();
    }

    public void trace(Object message) {
        logger.trace("NOT lazy: " + message);
    }

    public void trace(Object message, Throwable t) {
        logger.trace("NOT lazy: " + message, t);
    }

    public void debug(Object message) {
        logger.debug("NOT lazy: " + message);
    }

    public void debug(Object message, Throwable t) {
        logger.debug("NOT lazy: " + message, t);
    }

    public static Logger getLogger(String name) {
        return Logger.getLogger(name);
    }

    public static Logger getLogger(Class clazz) {
        return Logger.getLogger(clazz);
    }

    public static Logger getRootLogger() {
        return Logger.getRootLogger();
    }

    public static Logger getLogger(String name, LoggerFactory factory) {
        return Logger.getLogger(name, factory);
    }

    public void addAppender(Appender newAppender) {
        logger.addAppender(newAppender);
    }

    public void assertLog(boolean assertion, String msg) {
        logger.assertLog(assertion, msg);
    }

    public void callAppenders(LoggingEvent event) {
        logger.callAppenders(event);
    }

    public void error(Object message) {
        logger.error(message);
    }

    public void error(Object message, Throwable t) {
        logger.error(message, t);
    }

    public void fatal(Object message) {
        logger.fatal(message);
    }

    public void fatal(Object message, Throwable t) {
        logger.fatal(message, t);
    }

    public boolean getAdditivity() {
        return logger.getAdditivity();
    }

    public Enumeration getAllAppenders() {
        return logger.getAllAppenders();
    }

    public Appender getAppender(String name) {
        return logger.getAppender(name);
    }

    public Level getEffectiveLevel() {
        return logger.getEffectiveLevel();
    }

    public LoggerRepository getLoggerRepository() {
        return logger.getLoggerRepository();
    }

    public ResourceBundle getResourceBundle() {
        return logger.getResourceBundle();
    }

    public void info(Object message) {
        logger.info(message);
    }

    public void info(Object message, Throwable t) {
        logger.info(message, t);
    }

    public boolean isAttached(Appender appender) {
        return logger.isAttached(appender);
    }

    public boolean isEnabledFor(Priority level) {
        return logger.isEnabledFor(level);
    }

    public boolean isInfoEnabled() {
        return logger.isInfoEnabled();
    }

    public void l7dlog(Priority priority, String key, Throwable t) {
        logger.l7dlog(priority, key, t);
    }

    public void l7dlog(Priority priority, String key, Object[] params, Throwable t) {
        logger.l7dlog(priority, key, params, t);
    }

    public void log(Priority priority, Object message, Throwable t) {
        logger.log(priority, message, t);
    }

    public void log(Priority priority, Object message) {
        logger.log(priority, message);
    }

    public void log(String callerFQCN, Priority level, Object message, Throwable t) {
        logger.log(callerFQCN, level, message, t);
    }

    public void removeAllAppenders() {
        logger.removeAllAppenders();
    }

    public void removeAppender(Appender appender) {
        logger.removeAppender(appender);
    }

    public void removeAppender(String name) {
        logger.removeAppender(name);
    }

    public void setAdditivity(boolean additive) {
        logger.setAdditivity(additive);
    }

    public void setLevel(Level level) {
        logger.setLevel(level);
    }

    public void setResourceBundle(ResourceBundle bundle) {
        logger.setResourceBundle(bundle);
    }

    public void warn(Object message) {
        logger.warn(message);
    }

    public void warn(Object message, Throwable t) {
        logger.warn(message, t);
    }

    private final Logger logger;
}