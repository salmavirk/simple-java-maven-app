package com.amido.unittests;

import com.amido.model.User;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Tag("unit")
public class UserTest {

    @Test
    public void dummyTest() {
        User first = new User("user", "dummy", "dummyUser@dummycom");

        assertEquals("user", first.getName());
        assertEquals("dummy", first.getSurname());
        assertEquals("dummyUser@dummycom", first.getEmail());
    }


}
