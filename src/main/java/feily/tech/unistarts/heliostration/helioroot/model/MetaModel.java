package feily.tech.unistarts.heliostration.helioroot.model;

public class MetaModel {

    private int size;
    private int maxf;
    private int index;
    private int view;
    private boolean isViewOk;
    
    public MetaModel() {
        
    }
    
    public MetaModel(int size, int maxf, int index, int view, boolean isViewOk) {
        this.size = size;
        this.maxf = maxf;
        this.index = index;
        this.isViewOk = isViewOk;
        this.view = view;
    }
    
    /**
     * @return the size
     */
    public int getSize() {
        return size;
    }
    /**
     * @param size the size to set
     */
    public void setSize(int size) {
        this.size = size;
    }
    
    /**
     * @return the maxf
     */
    public int getMaxf() {
        return maxf;
    }
    /**
     * @param maxf the maxf to set
     */
    public void setMaxf(int maxf) {
        this.maxf = maxf;
    }
    
    /**
     * @return the index
     */
    public int getIndex() {
        return index;
    }
    /**
     * @param index the index to set
     */
    public void setIndex(int index) {
        this.index = index;
    }
    
    /**
     * @return the view
     */
    public int getView() {
        return view;
    }
    /**
     * @param view the view to set
     */
    public void setView(int view) {
        this.view = view;
    }
    
    /**
     * @return the isViewOk
     */
    public boolean isViewOk() {
        return isViewOk;
    }
    /**
     * @param isViewOk the isViewOk to set
     */
    public void setViewOk(boolean isViewOk) {
        this.isViewOk = isViewOk;
    }
    
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("[MetaModel] size = " + size + ", maxf = " + maxf + ", index = " 
                + index + ", view = " + view + ", isViewOk = " + isViewOk);
        return str.toString();
    }
    
}
