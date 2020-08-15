package com.indi.test;

import com.indi.utils.JDBCUtils;
import org.junit.Test;

public class JDBCUtilsTest {
    @Test
    public void test(){
        System.out.println(JDBCUtils.getConnection());
    }
}
