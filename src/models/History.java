package models;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Table(name="histories")
@NamedQueries({
    @NamedQuery(
            name="getAllHistories",
            query="SELECT r FROM History AS r ORDER BY r.id DESC"
            ),
    @NamedQuery(
            name="getHistoriesCount",
            query="SELECT COUNT(r) FROM History AS r"
            ),

})

@Entity
public class History {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name="inventory_id",nullable=false)
    private Inventory inventory;

    @Column(name="receiving",nullable=false)
    private Integer receiving;

    @Column(name="shiping",nullable=false)
    private Integer shiping;

    @Column(name = "history_date", nullable = false)
    private Date history_date;

    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this.id=id;
    }

    public Inventory getInventory(){
        return inventory;
    }

    public void setInventory(Inventory inventory){
        this.inventory=inventory;
    }

    public Integer getReceiving(){
        return receiving;
    }

    public void setReceiving(Integer receiving){
        this.receiving=receiving;
    }

    public Integer getShiping(){
        return shiping;
    }

    public void setShiping(Integer shiping){
        this.shiping=shiping;
    }

    public Date getHistory_date(){
        return history_date;
    }

    public void setHistory_date(Date history_date){
        this.history_date=history_date;
    }

}
