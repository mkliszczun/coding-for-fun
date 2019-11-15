package entities;

import javax.persistence.*;

@Entity
@Table(name = "stal")
public class Stal implements TableListable {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column (name = "heigh")
    private float heigh;
    @Column (name = "width")
    private float width;
    @Column (name = "thick")
    private float thick;
    @Column (name = "length")
    private float lengthCM;
    @Column (name = "count")
    private int count;
    @Column (name = "tier")
    private int tier;
    @Column (name = "add_info")
    private String addInfo;



    public float getHeigh() {
        return heigh;
    }

    public void setHeigh(float heigh) {
        this.heigh = heigh;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getThick() {
        return thick;
    }

    public void setThick(float thick) {
        this.thick = thick;
    }

    public float getLengthCM() {
        return lengthCM;
    }

    public void setLengthCM(float lengthCM) {
        this.lengthCM = lengthCM;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getTier() {
        return tier;
    }

    public void setTier(int tier) {
        this.tier = tier;
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
        string.append(heigh);
        string.append(" x ");
        string.append(width);
        string.append(" x ");
        string.append(thick);
        string.append("      ");
        string.append(lengthCM);
        string.append("      ");
        string.append(count);
        string.append(" szt. ");
        return string.toString();
    }

}
