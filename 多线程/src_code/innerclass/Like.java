package innerclass;

/**
 * description: 内部类实践
 * @author Leet
 * @date 2020/11/11/ 9:59
 */
public interface Like {
    void like();
}

/**
 * 1、外部类
 */
class LikeOne implements Like {
    @Override
    public void like() {
        System.out.println("like one");
    }
}

class Main {

    /**
     * 2、静态内部类
     */
    static class LikeTwo implements Like {
        @Override
        public void like() {
            System.out.println("like two");
        }
    }

    public static void main(String[] args) {
        Like like = new LikeOne();
        like.like();

        like = new LikeTwo();
        like.like();

        /**
         * 3、局部内部类
         */
        class LikeThree implements Like {
            @Override
            public void like() {
                System.out.println("like three");
            }
        }
        like = new LikeThree();
        like.like();

        /**
         * 4、匿名局部内部类
         */
        like = new Like() {
            @Override
            public void like() {
            System.out.println("like four");
            }
        };
        like.like();

        /**
         * 5、lambda
         */
        like = () -> System.out.println("like five. I am lambda!");
        like.like();
    }
}