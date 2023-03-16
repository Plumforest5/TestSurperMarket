package TestSuperMarket;

public interface GoodsService {
    TableDTO referGoods(GoodsRequest request);  //封装货物查询对象

    boolean add(GoodsDO goodsDO);

    GoodsDO getById(int selectGoodsId);

    boolean update(GoodsDO goodsDO);

    boolean delete(int[] selectGoodsIds);
}
