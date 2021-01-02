package models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


@Table(name="inventories")
@NamedQueries({
    @NamedQuery(
            name="getAllInventories",
            query="SELECT e FROM Inventory AS e ORDER BY e.id DESC"
            ),
    @NamedQuery(
            name="getInventoriesCount",
            query="SELECT COUNT (e) FROM Inventory AS e"
            ),
    @NamedQuery(
            name="checkRegisteredTrade_code",
            query="SELECT COUNT(e) FROM Inventory AS e WHERE e.trade_code = :trade_code"
            )

})
@Entity
public class Inventory {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "trade_code", nullable = false, unique = true)
    private String trade_code;

    @Column(name = "trade_name", nullable = false)
    private String trade_name;

    @Column(name = "order_flag", nullable = false)
    private Integer order_flag;

    @Column(name = "receiving", nullable = false)
    private Integer receiving;

    @Column(name = "shiping", nullable = false)
    private Integer shiping;

    @Column(name = "stock", nullable = false)
    private Integer stock;

    @Column(name = "history", nullable = false)
    private Integer history;

    @Column(name = "created_at", nullable = false)
    private Timestamp created_at;

    @Column(name = "updated_at", nullable = false)
    private Timestamp updated_at;

    @Column(name = "delete_flag", nullable = false)
    private Integer delete_flag;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTrade_code() {
        return trade_code;
    }

    public void setTrade_code(String trade_code) {
        this.trade_code = trade_code;
    }

    public String getTrade_name() {
        return trade_name;
    }

    public void setTrade_name(String trade_name) {
        this.trade_name = trade_name;
    }

    public Integer getOrder_flag() {
        return order_flag;
    }

    public void setOrder_flag(Integer order_flag) {
        this.order_flag = order_flag;
    }

    public Integer getReceiving() {
        return receiving;
    }

    public void setReceiving(Integer receiving) {
        this.receiving = receiving;
    }

    public Integer getShiping() {
        return shiping;
    }

    public void setShiping(Integer shiping) {
        this.shiping = shiping;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getHistory() {
        return history;
    }

    public void setHistory(Integer history) {
        this.history = history;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public Timestamp getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Timestamp updated_at) {
        this.updated_at = updated_at;
    }

    public Integer getDelete_flag() {
        return delete_flag;
    }

    public void setDelete_flag(Integer delete_flag) {
        this.delete_flag = delete_flag;
    }
}
