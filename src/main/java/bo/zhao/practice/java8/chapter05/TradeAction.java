package bo.zhao.practice.java8.chapter05;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Created by zhaobo on 2018/6/9.
 */
public class TradeAction {

    public static void main(String[] args) {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");
        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );


        // 找出2011年发生的所有交易，并按交易额排序（从低到高）。
        System.out.println("**************找出2011年发生的所有交易，并按交易额排序（从低到高）**************");
        transactions.stream()
                .filter(trans -> trans.getYear() == 2011)
                .sorted(Comparator.comparing(Transaction::getValue))
                .forEach(System.out::println);

        // 交易员都在哪些不同的城市工作过？
        System.out.println("**************交易员都在哪些不同的城市工作过？**************");
        transactions.stream()
                .map(trans -> trans.getTrader().getCity())
                .distinct()
                .forEach(System.out::println);

        // 查找所有来自于剑桥的交易员，并按姓名排序
        System.out.println("**************查找所有来自于剑桥的交易员，并按姓名排序**************");
        transactions.stream()
                .filter(trans -> "Cambridge".equals(trans.getTrader().getCity()))
                .map(Transaction::getTrader)
                .distinct()
                .sorted(Comparator.comparing(Trader::getName))
                .forEach(System.out::println);

        // 返回所有交易员的姓名字符串，按字母顺序排序。
        System.out.println("**************返回所有交易员的姓名字符串，按字母顺序排序**************");
        String names = transactions.stream()
                .map(trans -> trans.getTrader().getName())
                .distinct()
                .sorted()
                .reduce("", (str1, str2) -> str1 + str2);
        System.out.println(names);

        // 有没有交易员是在米兰工作的？
        System.out.println("**************有没有交易员是在米兰工作的？**************");
        boolean workInMilan = transactions.stream()
                .anyMatch(trans -> "Milan".equals(trans.getTrader().getCity()));
        System.out.println(workInMilan);

        // 打印生活在剑桥的交易员的所有交易额
        System.out.println("**************打印生活在剑桥的交易员的所有交易额**************");
        transactions.stream()
                .filter(trans -> "Cambridge".equals(trans.getTrader().getCity()))
                .map(Transaction::getValue)
                .forEach(System.out::println);

        // 所有交易中，最高的交易额是多少
        System.out.println("**************所有交易中，最高的交易额是多少**************");
        transactions.stream()
                .map(Transaction::getValue)
                .reduce(Integer::max)
                .ifPresent(System.out::println);

        // 找到交易额最小的交易。
        System.out.println("**************找到交易额最小的交易**************");
        transactions.stream()
                .map(Transaction::getValue)
                .reduce(Integer::min)
                .ifPresent(System.out::println);
    }
}
