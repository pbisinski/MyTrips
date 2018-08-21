package pl.bisinski.mytrips;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

@Entity(nameInDb = "trip")
public class Trip {

    @Id(autoincrement = true)
    private Long id;

    @Index(unique = true)
    @Property(nameInDb = "name")
    private String name;

    @Property(nameInDb = "started_at")
    private String started_at;

    @Property(nameInDb = "ended_at")
    private String ended_at;

    @Generated(hash = 729961356)
    public Trip(Long id, String name, String started_at, String ended_at) {
        this.id = id;
        this.name = name;
        this.started_at = started_at;
        this.ended_at = ended_at;
    }

    @Generated(hash = 1047475835)
    public Trip() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStarted_at() {
        return this.started_at;
    }

    public void setStarted_at(String started_at) {
        this.started_at = started_at;
    }

    public String getEnded_at() {
        return this.ended_at;
    }

    public void setEnded_at(String ended_at) {
        this.ended_at = ended_at;
    }
}
