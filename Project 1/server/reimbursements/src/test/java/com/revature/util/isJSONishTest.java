package com.revature.util;

import org.junit.Assert;
import org.junit.Test;

import static com.revature.util.OtherUtils.isJSONish;
import static org.junit.Assert.*;

public class isJSONishTest {

    @Test
    public void isJSONishTest() {
        assertTrue(isJSONish("{'hi'}"));
    }
}