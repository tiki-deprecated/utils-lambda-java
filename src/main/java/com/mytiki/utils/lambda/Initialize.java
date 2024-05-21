/*
 * Copyright (c) TIKI Inc.
 * See LICENSE file in root directory.
 */

package com.mytiki.utils.lambda;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Initialize {
    public static Logger logger(Class<?> clazz) {
        return LogManager.getLogger(clazz);
    }

    public static Properties properties(String name) {
        try (InputStream input = Initialize.class
                .getClassLoader()
                .getResourceAsStream(name)) {
            Properties properties = new Properties();
            properties.load(input);
            return properties;
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
