package dbaccess;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created with IntelliJ IDEA.
 * User: jicui
 * Date: 14-1-7
 * Time: 下午4:17
 * To change this template use File | Settings | File Templates.
 */
@Entity(name = "dbaccess.Store")
@Table(name = "STORE")
public class Store {
    @Id
    @Column(name = "ID")
    private Long id;

    @Column(name = "STORE_NAME")
    private String storeName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }
}
