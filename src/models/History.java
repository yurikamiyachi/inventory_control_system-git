package models;

import java.sql.Timestamp;

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

    @Column(name="created_at",nullable=false)
    private Timestamp created_at;

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

    public Timestamp getCreated_at(){
        return created_at;
    }

    public void setCreated_at(Timestamp created_at){
        this.created_at=created_at;
    }

}
