package entities;

import javax.persistence.*;

@Entity
@Table (name = "pret")
public class Pret implements TableListable {
    @Id
    @Column (name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "d")
    private int d;
    @Column(name = "length")
    private float length;
    @Column (name = "zebro")
    private boolean zebro;
    @Column (name = "count")
    private int count;
    @Column (name = "add_info")
    private String addInfo;



    public Pret(){
        this.zebro = true;
    }

    public int getD() {
        return d;
    }

    public void setD(int d) {
        this.d = d;
    }

    public float getLength() {
        return length;
    }

    public void setLength(float length) {
        this.length = length;
    }

    public boolean isZebro() {
        return zebro;
    }

    public void setZebro(boolean zebro) {
        this.zebro = zebro;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getAddInfo() {
        return addInfo;
    }

    public void setAddInfo(String addInfo) {
        this.addInfo = addInfo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


//    this method is overriden to be displayed in list views in clean way
    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        string.append(d);
        if (zebro){
            string.append(" żebro           ");
        } else {
            string.append(" gładki         ");
        }
        if (length == 1200 || length == 12 || length == 600 || length == 6){
            string.append(length/100);
            string.append(" m ");
        } else {
            string.append(length);
            string.append(" cm ");
        }
        string.append("     ");
        string.append(count);
        string.append(" szt.");

        return string.toString();
    }


}
