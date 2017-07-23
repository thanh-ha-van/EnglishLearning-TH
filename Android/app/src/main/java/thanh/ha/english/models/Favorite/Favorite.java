package thanh.ha.english.models.Favorite;

/**
 * Created by HaVan on 5/27/2017.
 */

public class Favorite {

    private String favorite;
    private boolean isSelected;

    public Favorite(String favorite) {
        this.favorite = favorite;
        this.isSelected = false;
    }

    public String getFavorite() {
        return favorite;
    }

    public void setFavorite(String favorite) {
        this.favorite = favorite;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
