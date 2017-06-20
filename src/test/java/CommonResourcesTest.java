import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

class CommonResourcesTest {
    TestInterface commonResProxu;
    @BeforeEach
    void setUp() {
        commonResProxu = (TestInterface) Proxy.newProxyInstance(CommonResources.class.getClassLoader(),
                new Class[]{TestInterface.class},
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        return new CommonResources();
                    }
                });

    }

    @Test
    void getStop() {
        ((CommonResources)commonResProxu.add(null)).getStop();
    }

    @Test
    void getDuplicateWord() {
        ((CommonResources)commonResProxu.add(null)).getDuplicateWord();
    }

    @Test
    void getIncorrectWord() {
        ((CommonResources)commonResProxu.add(null)).getIncorrectWord();
    }

    @Test
    void getDictionary() {
        ((CommonResources)commonResProxu.add(null)).getDictionary();
    }

    @Test
    void append() {
        ((CommonResources)commonResProxu.add(null)).append("testString");
    }

}