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

    @Override
    public boolean isTraceEnabled() {
        return logger.isTraceEnabled();
    }

    @Override
    public boolean isDebugEnabled() {
        return logger.isDebugEnabled();
    }

    @Override
    public void trace(Object message) {
        logger.trace("NOT lazy: " + message);
    }

    @Override
    public void trace(Object message, Throwable t) {
        logger.trace("NOT lazy: " + message, t);
    }

    @Override
    public void debug(Object message) {
        logger.debug("NOT lazy: " + message);
    }

    @Override
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

    @Override
    public void addAppender(Appender newAppender) {
        logger.addAppender(newAppender);
    }

    @Override
    public void assertLog(boolean assertion, String msg) {
        logger.assertLog(assertion, msg);
    }

    @Override
    public void callAppenders(LoggingEvent event) {
        logger.callAppenders(event);
    }

    @Override
    public void error(Object message) {
        logger.error(message);
    }

    @Override
    public void error(Object message, Throwable t) {
        logger.error(message, t);
    }

    @Override
    public void fatal(Object message) {
        logger.fatal(message);
    }

    @Override
    public void fatal(Object message, Throwable t) {
        logger.fatal(message, t);
    }

    @Override
    public boolean getAdditivity() {
        return logger.getAdditivity();
    }

    @Override
    public Enumeration getAllAppenders() {
        return logger.getAllAppenders();
    }

    @Override
    public Appender getAppender(String name) {
        return logger.getAppender(name);
    }

    @Override
    public Level getEffectiveLevel() {
        return logger.getEffectiveLevel();
    }

    @Override
    public LoggerRepository getLoggerRepository() {
        return logger.getLoggerRepository();
    }

    @Override
    public ResourceBundle getResourceBundle() {
        return logger.getResourceBundle();
    }

    @Override
    public void info(Object message) {
        logger.info(message);
    }

    @Override
    public void info(Object message, Throwable t) {
        logger.info(message, t);
    }

    @Override
    public boolean isAttached(Appender appender) {
        return logger.isAttached(appender);
    }

    @Override
    public boolean isEnabledFor(Priority level) {
        return logger.isEnabledFor(level);
    }

    @Override
    public boolean isInfoEnabled() {
        return logger.isInfoEnabled();
    }

    @Override
    public void l7dlog(Priority priority, String key, Throwable t) {
        logger.l7dlog(priority, key, t);
    }

    @Override
    public void l7dlog(Priority priority, String key, Object[] params, Throwable t) {
        logger.l7dlog(priority, key, params, t);
    }

    @Override
    public void log(Priority priority, Object message, Throwable t) {
        logger.log(priority, message, t);
    }

    @Override
    public void log(Priority priority, Object message) {
        logger.log(priority, message);
    }

    @Override
    public void log(String callerFQCN, Priority level, Object message, Throwable t) {
        logger.log(callerFQCN, level, message, t);
    }

    @Override
    public void removeAllAppenders() {
        logger.removeAllAppenders();
    }

    @Override
    public void removeAppender(Appender appender) {
        logger.removeAppender(appender);
    }

    @Override
    public void removeAppender(String name) {
        logger.removeAppender(name);
    }

    @Override
    public void setAdditivity(boolean additive) {
        logger.setAdditivity(additive);
    }

    @Override
    public void setLevel(Level level) {
        logger.setLevel(level);
    }

    @Override
    public void setResourceBundle(ResourceBundle bundle) {
        logger.setResourceBundle(bundle);
    }

    @Override
    public void warn(Object message) {
        logger.warn(message);
    }

    @Override
    public void warn(Object message, Throwable t) {
        logger.warn(message, t);
    }

    private final Logger logger;
}
