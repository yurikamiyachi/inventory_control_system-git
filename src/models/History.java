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
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @Column(name="trade_code",nullable=false)
    private String trade_code;

    @Column(name="trade_name",nullable=false)
    private String trade_name;

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

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
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
