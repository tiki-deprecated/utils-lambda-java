/*
 * Copyright (c) TIKI Inc.
 * See LICENSE file in root directory.
 */

package com.mytiki.utils.lambda;

public class Env {
    public String get(String var) {
        return System.getenv(var);
    }

    public String name(String var) {
        return var
                .replace(".", "_")
                .replace("-", "_")
                .toUpperCase();
    }
}
