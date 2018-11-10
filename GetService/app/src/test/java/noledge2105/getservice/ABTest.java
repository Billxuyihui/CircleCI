package noledge2105.getservice;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class ABTest {


    @Test
    public void test1() {
        ServiceType st = new ServiceType("200", "doingNothing", 200);
        assertEquals("200", st.getId());
    }

    @Test
    public void test2() {
        ServiceType st = new ServiceType("200", "doingNothing", 200);
        assertEquals("doingNothing", st.getService());
    }

    @Test
    public void test3() {
        ServiceType st = new ServiceType("200", "doingNothing", 200);
        assertEquals(200, st.getHourlyRate(), 0);
    }

    @Test
    public void test4() {
        ServiceProvider u = new ServiceProvider();
        assertEquals(0, u.getHourlyRate(), 0);

    }

    @Test
    public void test5() {
        ServiceProvider u = new ServiceProvider();
        assertEquals("", u.getServiceType());

    }

}