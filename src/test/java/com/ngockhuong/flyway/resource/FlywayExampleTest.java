package com.ngockhuong.flyway.resource;

import com.ngockhuong.flyway.AbstractTest;
import com.ngockhuong.flyway.annotation.Migration;
import org.junit.jupiter.api.Test;

public class FlywayExampleTest extends AbstractTest {

    @Test
    @Migration("/")
    public void test() {
        System.out.println("hihi");
    }
}
