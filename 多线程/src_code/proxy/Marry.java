package proxy;

/**
 * description：静态代理学习
 *              通过“结婚”这个场景带入
 * @author Leet
 * @date 2020/11/11 9:31
 */
public interface Marry {
    void marry();
}

class You implements Marry {
    @Override
    public void marry() {
        System.out.println("今天我要结婚了，我很高兴");
    }
}

class WeddingCompany implements Marry {

    public WeddingCompany(Marry marrier) {
        this.marrier = marrier;
    }

    private Marry marrier;

    @Override
    public void marry() {
        before();
        marrier.marry();
        after();
    }

    private void before() {
        System.out.println("结婚前，婚庆公司布置现场");
    }

    private void after() {
        System.out.println("结婚后，婚庆公司打扫现场");
    }
}

class TestStaticProxy {
    public static void main(String[] args) {
        WeddingCompany weddingCompany = new WeddingCompany(new You());
        weddingCompany.marry();
    }
}