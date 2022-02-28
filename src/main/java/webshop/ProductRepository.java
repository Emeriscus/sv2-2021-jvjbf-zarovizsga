package webshop;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class ProductRepository {

    JdbcTemplate jdbcTemplate;

    public ProductRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public long insertProduct(String productName, int price, int stock) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(conn -> {
            PreparedStatement ps =
                    conn.prepareStatement("insert into products(product_name,price,stock) values (?,?,?)",
                            Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, productName);
            ps.setLong(2, price);
            ps.setLong(3, stock);
            return ps;
        }, keyHolder);
        return keyHolder.getKey().longValue();
    }


    public Product findProductById(long id) {
        return jdbcTemplate.queryForObject("select * from products where id = ?",
                (rs, rowNum) -> new Product
                        (rs.getLong("id"), rs.getString("product_name"),
                                rs.getLong("price"), rs.getLong("stock"))
                , id);
    }

    public void updateProductStock(long id, int amount) {
        jdbcTemplate.update("update products set stock = stock-? where id = ?", amount, id);
    }
}
