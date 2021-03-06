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
            query="SELECT i FROM Inventory AS i ORDER BY i.id DESC"
            ),
    @NamedQuery(
            name="getInventoriesCount",
            query="SELECT COUNT (i) FROM Inventory AS i"
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

    @Column(name = "receiving", nullable = false)
    private Integer receiving;

    @Column(name = "shiping", nullable = false)
    private Integer shiping;

    @Column(name = "stock", nullable = false)
    private Integer stock;

    @Column(name = "ordering_person", nullable = false)
    private String ordering_person;

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

    public String getOrdering_person() {
        return ordering_person;
    }

    public void setOrdering_person(String ordering_person) {
        this.ordering_person = ordering_person;
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

    public Integer getReceiving() {
        return receiving;
    }

    public void setReceiving(int receiving) {
        this.receiving = receiving;

    }

    public Integer getShiping() {
        return shiping;
    }

    public void setShiping(int shiping) {
        this.shiping = shiping;

    }
    public Integer getStock(){
        return stock;
    }

    public void setStock(Integer stock){
        this.stock=stock;
    }

}
